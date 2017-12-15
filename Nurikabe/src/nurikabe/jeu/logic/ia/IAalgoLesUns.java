package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

import static nurikabe.jeu.logic.ia.IAUtils.placer;

class IAalgoLesUns implements IAalgo {

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (laGrille.getGrille().getValeur(x, y) == 1){
                    placer( laGrille, x-1, y, Etat.NOIR);
                    placer( laGrille, x+1, y, Etat.NOIR);
                    placer( laGrille, x, y-1, Etat.NOIR);
                    placer( laGrille, x, y+1, Etat.NOIR);
                }
        return laGrille;
    }
}
