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

    private static List<IAalgo> algosStart = null;
    private static List<IAalgo> algosRun = null;
    private static List<IAalgo> algosStop = null;
    private static LesVerifs lesVerifs = new LesVerifs();

    private int dept;

    private boolean done = false;


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

    }

    protected void go(){
        start();
    }

    private void initStart( ){
        if (algosStart != null)
            return;
        algosStart = new LinkedList<>();
        algosStart.add( new IAalgoLesUns());
    }

    private void initRun(){
        if (algosRun != null)
            return;
        algosRun = new LinkedList<>();
        algosRun.add( new IABlancNoir());
        algosRun.add( new IATroisNoirsUnBlanc());
        algosRun.add( new IANoirs());
        algosRun.add( new IABlancs());
        algosRun.add( new IABlancsCompletes());
        algosRun.add( new IA2choixRestant());
        algosRun.add( new IACaseTropLoins());
    }

    private void initStop(){
        if (algosStop != null)
            return;
        algosStop = new LinkedList<>();
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
        IAGrille laGrille = resoudre( grille);
        if (lesVerifs.verification( laGrille.getGrille()))
            return laGrille;
        Position p = getVide( laGrille);
        if (p == null)
            return laGrille;

        IAGrille nextGrille1 = laGrille.clone();
        placer( nextGrille1, p, Etat.BLANC);
        next1 = new IABrutThread( deptRest-1, nextGrille1);
        next1.go();
        IAGrille nextGrille2 = laGrille.clone();
        placer( nextGrille2, p, Etat.NOIR);
        next2 = new IABrutThread( deptRest-1, nextGrille2);
        next2.go();
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
            if (next1 != null)
                next1.stop();
            if (next2 != null)
                next2.stop();
            next1 = null;
            next2 = null;
            return grilleC;
        }
        if (next1 != null && next1.isCorrect()){
            grilleC = next1.getGrille().clone();
            next1 = null;
        }

        else if (next2 != null && next2.isCorrect()) {
            grilleC = next2.getGrille().clone();
            next2 = null;
        }
        return grilleC;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority( Thread.MIN_PRIORITY);

        grilleC = resoudre( grilleD, dept).getGrille();
        grilleD = null;
        done = true;
        stopAvecParent();
    }

    private synchronized void stopAvecParent(){

    }

    public synchronized void stop(){
        try{
            grilleC = null;
            grilleD = null;
            if (next1 != null){
                next1.stop();
                next1 = null;
            }
            if (next2 != null){
                next2.stop();
                next2 = null;
            }
            Thread.currentThread().setPriority( Thread.NORM_PRIORITY);
            thread.join( 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setParrent( Thread parrent){
        this.parrent = parrent;
    }

}
