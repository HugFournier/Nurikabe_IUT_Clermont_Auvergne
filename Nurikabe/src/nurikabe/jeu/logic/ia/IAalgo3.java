package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

class IAalgo3 implements IAalgo{

    protected IAalgo3(){

    }

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (grille.getGrille().getValeur(x, y) == 1){
                    grille.getGrille().setEtat(x-1, y, Etat.NOIR);
                    grille.getGrille().setEtat(x+1, y, Etat.NOIR);
                    grille.getGrille().setEtat(x, y-1, Etat.NOIR);
                    grille.getGrille().setEtat(x, y+1, Etat.NOIR);
                    grille.setForced(x+1, y, true);
                    grille.setForced(x-1, y, true);
                    grille.setForced(x, y+1, true);
                    grille.setForced(x, y-1, true);
                }
        return laGrille;
    }

}
