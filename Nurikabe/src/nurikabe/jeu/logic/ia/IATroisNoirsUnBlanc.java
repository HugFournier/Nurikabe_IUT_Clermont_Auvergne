package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

import static nurikabe.jeu.logic.ia.IAUtils.placer;

class IATroisNoirsUnBlanc implements IAalgo{

    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (isObligated( laGrille, x, y))
                    placer( laGrille, x, y, Etat.BLANC);
        return laGrille;
    }

    private boolean isObligated( IAGrille grille, int x, int y){
        return isObigatedLB( grille, x, y) || isObigatedLT( grille, x, y) || isObigatedRB( grille, x, y) || isObigatedRT( grille, x, y);
    }

    private boolean isObigatedLB( IAGrille grille, int x, int y){
        if (x >= grille.getWidth()-1 && y <= 0)
            return false;
        if (grille.getGrille().getEtat( x+1, y) == Etat.NOIR && grille.getGrille().getEtat( x, y-1) == Etat.NOIR && grille.getGrille().getEtat( x+1, y-1) == Etat.NOIR)
            return true;
        return false;
    }

    private boolean isObigatedLT( IAGrille grille, int x, int y){
        if (x >= grille.getWidth()-1 && y >= grille.getHeight()-1)
            return false;
        if (grille.getGrille().getEtat( x+1, y) == Etat.NOIR && grille.getGrille().getEtat( x, y+1) == Etat.NOIR && grille.getGrille().getEtat( x+1, y+1) == Etat.NOIR)
            return true;
        return false;
    }

    private boolean isObigatedRB( IAGrille grille, int x, int y){
        if (x <= 0 && y <= 0)
            return false;
        if (grille.getGrille().getEtat( x-1, y) == Etat.NOIR && grille.getGrille().getEtat( x, y-1) == Etat.NOIR && grille.getGrille().getEtat( x-1, y-1) == Etat.NOIR)
            return true;
        return false;
    }

    private boolean isObigatedRT( IAGrille grille, int x, int y){
        if (x <= 0 && y >= grille.getHeight()-1)
            return false;
        if (grille.getGrille().getEtat( x-1, y) == Etat.NOIR && grille.getGrille().getEtat( x, y+1) == Etat.NOIR && grille.getGrille().getEtat( x-1, y+1) == Etat.NOIR)
            return true;
        return false;
    }

}
