package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

class IAalgo2 implements IAalgo{

    /*
        .2.
        2#.
        ...
     */
    protected IAalgo2(){

    }

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        int width = grille.getWidth();
        int height = grille.getHeight();
        for (int y = 1; y < height-1; y++)
            for (int x = 1; x < width-1; x++)
                if (!laGrille.isForced(x, y) && ((!laGrille.getGrille().isJouable(x, y-1) || !laGrille.getGrille().isJouable(x, y+1)) &&
                        (!laGrille.getGrille().isJouable( x-1, y) || !laGrille.getGrille().isJouable(x+1, y))))
                    laGrille.setForced( x, y, laGrille.getGrille().setEtat(x, y, Etat.NOIR));
        for (int y = 1; y < height-1; y++)
            if (!laGrille.isForced(0, y) && ((!laGrille.getGrille().isJouable(0, y-1) || !laGrille.getGrille().isJouable(0, y+1)) &&
                !laGrille.getGrille().isJouable(1, y)))
                    laGrille.setForced( 0, y, laGrille.getGrille().setEtat(0, y, Etat.NOIR));
        for (int y = 1; y < height-1; y++)
            if (!laGrille.isForced(width-1, y) && ((!laGrille.getGrille().isJouable(width-1, y-1)
                    || !laGrille.getGrille().isJouable(width-1, y+1)) &&
                    !laGrille.getGrille().isJouable(width-2, y)))
                laGrille.setForced( width-1, y, laGrille.getGrille().setEtat(width-1, y, Etat.NOIR));
        for (int x = 1; x < width-1; x++)
            if (!laGrille.isForced(x, 0) && ((!laGrille.getGrille().isJouable(x-1, 0) || !laGrille.getGrille().isJouable(x+1, 0)) &&
                    !laGrille.getGrille().isJouable(x, 1)))
                laGrille.setForced( x, 0, laGrille.getGrille().setEtat(x, 0, Etat.NOIR));
        for (int x = 1; x < width-1; x++)
            if (!laGrille.isForced(x, height-1) && ((!laGrille.getGrille().isJouable(x-1, height-1) || !laGrille.getGrille().isJouable(x+1, height-1)) &&
                    !laGrille.getGrille().isJouable(x, height-2)))
                laGrille.setForced( x, height-1, laGrille.getGrille().setEtat(x, height-1, Etat.NOIR));
        return laGrille;
    }
}
