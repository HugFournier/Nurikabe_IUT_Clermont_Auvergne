package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

import java.util.LinkedList;
import java.util.List;

public class IAForceBrut implements IAInterface{

    private List<IAalgo> algos = new LinkedList<IAalgo>();
    private IAalgo brut = new IAalgo0();

    public IAForceBrut( ){
        algos.add( new IAalgo1());
        algos.add( new IAalgo2());
        algos.add( new IAalgo3());
        algos.add( new IAalgo4());
        algos.add( new IAalgo5());
        algos.add( new IAalgo6());
    }

    @Override
    public Grille resoudre( Grille grille) {
        IAGrille laGrille = new IAGrille( grille);
        IAGrille tmp;
        boolean running = true;

        while (running){
            running = false;
            for (IAalgo algo : algos){
                tmp = laGrille.clone();
                laGrille = algo.resoudre( laGrille);
                if (!tmp.equals( laGrille))
                    running = true;
            }
        }
        return laGrille.getGrille();
    }
}
