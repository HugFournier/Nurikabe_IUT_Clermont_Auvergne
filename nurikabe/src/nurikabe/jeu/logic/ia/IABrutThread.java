package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.LesVerifs;
import util.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static nurikabe.jeu.logic.ia.IAUtils.placer;

class IABrutThread implements Runnable{

    private static final Random random = new Random( System.nanoTime());

    private int dept;

    private boolean done = false;

    private List<IAalgo> algosStart = new LinkedList<>();
    private List<IAalgo> algosRun = new LinkedList<>();
    private List<IAalgo> algosStop = new LinkedList<>();
    private LesVerifs lesVerifs = new LesVerifs();

    private Grille grilleC = null;
    private IAGrille grilleD;

    private IABrutThread next1;
    private IABrutThread next2;
    private Thread parrent;
    private Thread thread;

    protected IABrutThread( int dept, IAGrille grille){
        initStart();
        initRun();
        initStop();
        this.dept = dept;
        grilleD = grille.clone();
        start();
    }

    private void initStart( ){
        algosStart.add( new IAalgoLesUns());
    }

    private void initRun(){
        algosRun.add( new IABlancNoir());
        algosRun.add( new IATroisNoirsUnBlanc());
        algosRun.add( new IANoirs());
        algosRun.add( new IABlancs());
        algosRun.add( new IABlancsCompletes());
        algosRun.add( new IA2choixRestant());
    }

    private void initStop(){

    }

    private IAGrille resoudre( IAGrille grille){
        IAGrille laGrille = grille.clone();
        for (IAalgo algo : algosStart)
            laGrille = algo.resoudre( laGrille);

        IAGrille tmp;
        boolean running = true;

        while (running){
            running = false;
            for (IAalgo algo : algosRun){
                tmp = laGrille.clone();
                laGrille = algo.resoudre( laGrille);
                if (!tmp.equals( laGrille))
                    running = true;
            }
        }
        for (IAalgo algo : algosStop)
            laGrille = algo.resoudre( laGrille);
        return laGrille;
    }

    private Position getVide(IAGrille grille){
        List<Position> vides = new ArrayList<>();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (grille.getGrille().getEtat( x, y) == Etat.VIDE)
                    vides.add( new Position( x, y));
        if (vides.size() <= 0)
            return null;
        int rd = random.nextInt( vides.size());
        return vides.get( rd);
    }

    private IAGrille resoudre( IAGrille grille, int deptRest){
        if (deptRest <= 0)
            return grille.clone();
        IAGrille laGrille = resoudre( grille.clone());
        if (lesVerifs.verification( laGrille.getGrille()))
            return laGrille;
        Position p = getVide( laGrille);
        if (p == null)
            return laGrille;

        IAGrille nextGrille1 = laGrille.clone();
        placer( nextGrille1, p, Etat.BLANC);
        next1 = new IABrutThread( deptRest-1, nextGrille1);

        IAGrille nextGrille2 = laGrille.clone();
        placer( nextGrille2, p, Etat.NOIR);
        next2 = new IABrutThread( deptRest-1, nextGrille2);

        return laGrille;
    }


    private synchronized void start() {
        parrent = Thread.currentThread();
        thread = new Thread( this);
        thread.start();
    }

    public boolean isSolved() {
        if (!done)
            return false;
        if (next1 != null && !next1.isSolved())
            return false;
        if (next2 != null && !next2.isSolved())
            return false;
        return true;
    }

    public boolean isCorrect(){
        if (!isSolved())
            return false;
        return lesVerifs.verification( getGrille());
    }

    public Grille getGrille( ) {
        if (lesVerifs.verification( grilleC)){
            return grilleC;
        }
        if (next1 != null && next1.isCorrect()){
            grilleC = next1.getGrille().clone();
        }

        else if (next2 != null && next2.isCorrect()) {
            grilleC = next2.getGrille().clone();
        }
        return grilleC;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority( Thread.MIN_PRIORITY);
        IAGrille laGrille = grilleD.clone();
        laGrille = resoudre( laGrille, dept);

        grilleC = laGrille.getGrille();
        done = true;
        stopAvecParent();
    }

    private synchronized void stopAvecParent(){
        try{
            Thread.currentThread().setPriority( Thread.NORM_PRIORITY);
            parrent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop(){
        try{
            grilleC = null;
            if (next1 != null)
                next1.stop();
            if (next2 != null)
                next2.stop();
            Thread.currentThread().setPriority( Thread.NORM_PRIORITY);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
