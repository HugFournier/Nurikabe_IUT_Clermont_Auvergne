package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IAalgo6 implements IAalgo{

    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        List<Position> chiffres = new ArrayList<Position>();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (!laGrille.getGrille().isJouable(x, y))
                    chiffres.add( new Position(x, y));
        for (Position position : chiffres)
            laGrille = ajoutBlanc( laGrille.getGrille().getValeur( position.getX(), position.getY())-1, position, laGrille);


        return laGrille;
    }

    private boolean canPlace( int x, int y, IAGrille grille){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return false;
        if (!grille.isForced( x, y))
            return true;
        if (grille.getGrille().getEtat(x, y) == Etat.NOIR)
            return false;
        return true;
    }

    private IAGrille ajoutBlanc( int encore, Position p, IAGrille grille){
        return ajoutBlanc( encore, p.getX(), p.getY(), grille, -1, -1);
    }

    private IAGrille ajoutBlanc( int encore, int x, int y, IAGrille grille, int oldX, int oldY){
        if (encore == 0){
            if (!(grille.getGrille().getEtat( x-1, y) == Etat.BLANC)){
                grille.getGrille().setEtat( x-1, y, Etat.NOIR);
                grille.setForced( x-1, y, true);
            }
            if (!(grille.getGrille().getEtat( x+1, y) == Etat.BLANC)){
                grille.getGrille().setEtat( x+1, y, Etat.NOIR);
                grille.setForced( x+1, y, true);
            }
            if (!(grille.getGrille().getEtat(x, y-1) == Etat.BLANC)){
                grille.getGrille().setEtat( x, y-1, Etat.NOIR);
                grille.setForced( x, y-1, true);
            }
            if (!(grille.getGrille().getEtat( x, y+1) == Etat.BLANC)){
                grille.getGrille().setEtat( x, y+1, Etat.NOIR);
                grille.setForced( x, y+1, true);
            }

            return grille;
        }
        if (encore < 0)
            return grille;
        boolean up      = (x   != oldX || y-1 != oldY) && canPlace( x, y-1, grille),
                down    = (x   != oldX || y+1 != oldY) && canPlace( x, y+1, grille),
                left    = (x-1 != oldX || y   != oldY) && canPlace( x-1, y, grille),
                right   = (x+1 != oldX || y   != oldY) && canPlace( x+1, y, grille);
        if ( !(up || down || left) && right){
            grille.getGrille().setEtat( x+1, y, Etat.BLANC);
            grille.setForced( x+1, y, true);
            grille = ajoutBlanc( encore-1, x+1, y, grille, x, y);
        }
        else if ( !(up || down || right) && left){
            grille.getGrille().setEtat( x-1, y, Etat.BLANC);
            grille.setForced( x-1, y, true);
            grille = ajoutBlanc( encore-1, x-1, y, grille, x, y);
        }
        else if ( !(up || right || left) && down){
            grille.getGrille().setEtat( x, y+1, Etat.BLANC);
            grille.setForced( x, y+1, true);
            grille = ajoutBlanc( encore-1, x, y+1, grille, x, y);
        }
        else if ( !(right || down || left) && up){
            grille.getGrille().setEtat( x, y-1, Etat.BLANC);
            grille.setForced( x, y-1, true);
            grille = ajoutBlanc( encore-1, x, y-1, grille, x, y);
        }

        return grille;
    }
}
