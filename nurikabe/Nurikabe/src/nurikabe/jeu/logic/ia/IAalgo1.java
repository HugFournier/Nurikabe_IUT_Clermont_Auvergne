package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

class IAalgo1 implements IAalgo{

    /*
        ...
        2#2
        ...
    */
    protected IAalgo1(){

    }

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        int width = grille.getWidth();
        int height = grille.getHeight();
        for (int y = 1; y < height-1; y++)
            for (int x = 1; x < width-1; x++)
                if (laGrille.getGrille().isJouable(x, y) && ((!(laGrille.getGrille().isJouable(x-1, y) || laGrille.getGrille().isJouable(x+1, y))) ||
                        (!(laGrille.getGrille().isJouable(x, y-1) || laGrille.getGrille().isJouable(x, y+1))))){
                    laGrille.getGrille().setEtat(x, y, Etat.NOIR);
                    laGrille.setForced(x, y, true);
                }
        for (int y = 0; y < grille.getHeight(); y += grille.getHeight()-1)
            for (int x = 1; x < grille.getWidth()-1; x++)
                if (laGrille.getGrille().isJouable(x, y) && (!(laGrille.getGrille().isJouable(x-1, y) || laGrille.getGrille().isJouable(x+1, y)))){
                    laGrille.getGrille().setEtat(x, y, Etat.NOIR);
                    laGrille.setForced( x, y, true);
                }
        for (int x = 0; x < grille.getWidth(); x += grille.getWidth()-1)
            for (int y = 1; y < grille.getHeight()-1; y++)
                if (laGrille.getGrille().isJouable(x, y) && (!(laGrille.getGrille().isJouable(x-1, y) || laGrille.getGrille().isJouable(x+1, y)))){
                    laGrille.getGrille().setEtat(x, y, Etat.NOIR);
                    laGrille.setForced( x, y, true);
                }
        return laGrille;
    }

}
