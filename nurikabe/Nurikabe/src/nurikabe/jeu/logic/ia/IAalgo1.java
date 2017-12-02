package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;

class IAalgo1 implements IAInterface{

    protected IAalgo1(){

    }

    @Override
    public Grille resoudre(Grille grille) {
        Grille laGrille = grille.clone();
        int width = grille.getWidth();
        int height = grille.getHeight();
        for (int y = 1; y < height-1; y++)
            for (int x = 1; x < width-1; x++)
                if (laGrille.isJouable(x, y) && ((!(laGrille.isJouable(x-1, y) || laGrille.isJouable(x+1, y))) ||
                        (!(laGrille.isJouable(x, y-1) || laGrille.isJouable(x, y+1)))))
                    laGrille.setEtat(x, y, Etat.NOIR);
        return laGrille;
    }

}
