package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

import java.util.LinkedList;
import java.util.List;

public class IAForceBrut implements IAInterface{

    private List<IAalgo> algos = new LinkedList<IAalgo>();

    public IAForceBrut( ){
        algos.add( new IAalgo1());
        algos.add( new IAalgo2());
        algos.add( new IAalgo3());
        algos.add( new IAalgo0());
    }

    @Override
    public Grille resoudre( Grille grille) {
        IAGrille laGrille = new IAGrille( grille);

        for (IAalgo algo : algos)
            laGrille = algo.resoudre( laGrille);

        return laGrille.getGrille();
    }
}
