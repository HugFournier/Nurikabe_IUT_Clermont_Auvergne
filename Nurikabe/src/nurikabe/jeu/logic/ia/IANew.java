package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.LesVerifs;
import util.Position;

import java.util.LinkedList;
import java.util.List;

public class IANew implements IAInterface{

    private static final int DEPT = 5;

    private List<IAalgo> algosStart = new LinkedList<>();
    private List<IAalgo> algosRun = new LinkedList<>();
    private List<IAalgo> algosStop = new LinkedList<>();
    private LesVerifs lesVerifs = new LesVerifs();

    private Grille grilleC = null;
    private Grille grilleD;
    private Thread parrent;
    private Thread thread;

    public IANew( ){
        initStart();
        initRun();
        initStop();
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

    private Position getVide( IAGrille grille){
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (grille.getGrille().getEtat( x, y) == Etat.VIDE)
                    return new Position( x, y);
        return null;
    }

    private IAGrille resoudre( IAGrille grille, int deptRest){
        if (deptRest <= 0)
            return grille.clone();
        IAGrille laGrille = resoudre( grille.clone());
        IAGrille tmp;
        if (lesVerifs.verification( laGrille.getGrille()))
            return laGrille;
        Position p = getVide( laGrille);
        if (p == null)
            return laGrille;
        placer( laGrille, p, Etat.BLANC);
        tmp = resoudre( laGrille, deptRest-1);
        if (lesVerifs.verification( tmp.getGrille()))
            return tmp;
        placer( laGrille, p, Etat.NOIR);
        tmp = resoudre( laGrille, deptRest-1);
        if (lesVerifs.verification( tmp.getGrille()))
            return tmp;
        placer( laGrille, p, Etat.VIDE);
        return laGrille;
    }

    private void placer( IAGrille grille, Position p, Etat etat){
        int x = p.getX();
        int y = p.getY();
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        grille.getGrille().setEtat( x, y, etat);
        grille.setForced( x, y, true);
    }

    @Override
    public synchronized void resoudre( Grille grille) {
        grilleD = grille;
        grilleC = null;
        parrent = Thread.currentThread();
        thread = new Thread( this);
        thread.start();
    }

    @Override
    public boolean isSolved() {
        return (grilleC != null);
    }

    @Override
    public Grille getGrille( ) {
        return grilleC;
    }

    @Override
    public void run() {
        IAGrille laGrille = new IAGrille( grilleD);
        laGrille = resoudre( laGrille, DEPT);

        grilleC = laGrille.getGrille();
        stopAvecParent();
    }

    private synchronized void stopAvecParent(){
        System.out.println( "done");
        try{
            parrent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop(){
        System.out.println( "STOP");
        try{
            thread.join();
            grilleC = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
