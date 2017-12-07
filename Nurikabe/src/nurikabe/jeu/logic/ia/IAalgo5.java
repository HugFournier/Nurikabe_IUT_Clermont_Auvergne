package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

public class IAalgo5 implements IAalgo{

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++){
                if (x > 0 && y > 0 &&
                        laGrille.getGrille().getEtat(x-1, y-1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x, y-1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x-1, y) == Etat.NOIR){
                    laGrille.getGrille().setEtat(x, y, Etat.BLANC);
                    laGrille.setForced( x, y, true);
                }
                else if (x > 0 && y <= laGrille.getHeight()-1 &&
                        laGrille.getGrille().getEtat(x-1, y+1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x, y+1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x-1, y) == Etat.NOIR){
                    laGrille.getGrille().setEtat(x, y, Etat.BLANC);
                    laGrille.setForced( x, y, true);
                }
                else if (x <= laGrille.getWidth()-1 && y > 0 &&
                        laGrille.getGrille().getEtat(x+1, y-1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x, y-1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x+1, y) == Etat.NOIR){
                    laGrille.getGrille().setEtat(x, y, Etat.BLANC);
                    laGrille.setForced( x, y, true);
                }
                else if (x <= laGrille.getWidth()-1 && y <= laGrille.getHeight()-1 &&
                        laGrille.getGrille().getEtat(x+1, y+1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x, y+1) == Etat.NOIR &&
                        laGrille.getGrille().getEtat( x+1, y) == Etat.NOIR){
                    laGrille.getGrille().setEtat(x, y, Etat.BLANC);
                    laGrille.setForced( x, y, true);
                }
            }



        return laGrille;
    }
}
