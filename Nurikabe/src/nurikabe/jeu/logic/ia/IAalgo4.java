package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;

public class IAalgo4 implements IAalgo{


    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        boolean changed = true;
        while (changed){
            changed = false;
            for (int x = 0; x < laGrille.getWidth(); x++)
                for (int y = 0; y < laGrille.getHeight(); y++)
                    if (laGrille.getGrille().getEtat( x, y) == Etat.NOIR && laGrille.isForced( x, y)){
                        boolean posUp       = canNotConnect( laGrille, x, y-1, -4, -4),
                                posDown     = canNotConnect( laGrille, x, y+1, -4, -4),
                                posLeft     = canNotConnect( laGrille, x-1, y, -4, -4),
                                posRight    = canNotConnect( laGrille, x+1, y, -4, -4);
                        if (posUp && posDown && posLeft && !posRight && !laGrille.isForced( x+1, y)){
                            laGrille.getGrille().setEtat( x+1, y, Etat.NOIR);
                            laGrille.setForced(x+1, y, true);
                            changed = true;
                        }
                        else if (posUp && posDown && !posLeft && posRight && !laGrille.isForced( x-1, y)){
                            laGrille.getGrille().setEtat( x-1, y, Etat.NOIR);
                            laGrille.setForced(x-1, y, true);
                            changed = true;
                        }
                        else if (posUp && !posDown && posLeft && posRight && !laGrille.isForced( x, y+1)){
                            laGrille.getGrille().setEtat( x, y+1, Etat.NOIR);
                            laGrille.setForced(x, y+1, true);
                            changed = true;
                        }
                        else if (!posUp && posDown && posLeft && posRight && !laGrille.isForced( x, y-1)){
                            laGrille.getGrille().setEtat( x, y-1, Etat.NOIR);
                            laGrille.setForced(x, y-1, true);
                            changed = true;
                        }
                    }
        }

        return laGrille;
    }

    private boolean canNotConnect( IAGrille grille, int x, int y, int oldX, int oldY){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return true;
        if (grille.isForced( x, y))
            if (grille.getGrille().getEtat( x, y) != Etat.NOIR)
                return true;
            else{
                boolean b = true;
                b &= (x-1 != oldX || y   != oldY) || canNotConnect( grille, x-1, y, x, y);
                b &= (x+1 != oldX || y   != oldY) || canNotConnect( grille, x+1, y, x, y);
                b &= (x   != oldX || y-1 != oldY) || canNotConnect( grille, x, y-1, x, y);
                b &= (x   != oldX || y+1 != oldY) || canNotConnect( grille, x, y+1, x, y);
                return b;
            }
        else
            return false;
    }
}
