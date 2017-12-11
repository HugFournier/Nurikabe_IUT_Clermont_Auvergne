package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

import java.util.LinkedList;
import java.util.List;

public class IANew implements IAInterface{

    private List<IAalgo> algosStart = new LinkedList<>();
    private List<IAalgo> algosRun = new LinkedList<>();
    private List<IAalgo> algosStop = new LinkedList<>();

    public IANew( ){
        initStart();
        initRun();
        initStop();
    }

    private void initStart( ){
        algosStart.add( new IAalgo3());
    }

    private void initRun(){
        algosRun.add( new IABlancNoir());
        algosRun.add( new IANoirs());
        algosRun.add( new IABlancs());
    }

    private void initStop(){
        algosStop.add( new IAalgo0());
    }

    @Override
    public Grille resoudre( Grille grille) {
        IAGrille laGrille = new IAGrille( grille);

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
        return laGrille.getGrille();
    }

}
