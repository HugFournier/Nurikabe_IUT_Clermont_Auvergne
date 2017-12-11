package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

public class IAalgoLesUns implements IAalgo {

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (laGrille.getGrille().getValeur(x, y) == 1){
                    laGrille.getGrille().setEtat(x-1, y, Etat.NOIR);
                    laGrille.getGrille().setEtat(x+1, y, Etat.NOIR);
                    laGrille.getGrille().setEtat(x, y-1, Etat.NOIR);
                    laGrille.getGrille().setEtat(x, y+1, Etat.NOIR);
                    laGrille.setForced(x+1, y, true);
                    laGrille.setForced(x-1, y, true);
                    laGrille.setForced(x, y+1, true);
                    laGrille.setForced(x, y-1, true);
                }
        return laGrille;
    }
}
