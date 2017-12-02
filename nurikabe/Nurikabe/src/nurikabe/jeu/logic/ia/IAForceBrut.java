package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

import java.util.LinkedList;
import java.util.List;

public class IAForceBrut implements IAInterface{

    private List<IAInterface> algos = new LinkedList<IAInterface>();

    public IAForceBrut( ){
        algos.add( new IAalgo1());
        algos.add( new IAalgo2());
    }

    @Override
    public Grille resoudre( Grille grille) {
        Grille laGrille = grille.clone();

        for (IAInterface algo : algos)
            laGrille = algo.resoudre( laGrille);

        return laGrille;
    }
}
