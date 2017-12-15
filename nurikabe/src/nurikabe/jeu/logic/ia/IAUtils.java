package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.List;

class IAUtils {

    protected static boolean peutPlacer( IAGrille grille, int x, int y){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return false;
        if (grille.isForced( x, y))
            return false;
        return true;
    }

    protected static void placer( IAGrille grille, Position p, Etat etat){
        placer( grille, p.getX(), p.getY(), etat);
    }

    protected static void placer( IAGrille grille, int x, int y, Etat etat){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        if (grille.isForced(x, y))
            return;
        grille.setForced( x, y, grille.getGrille().setEtat( x, y, etat));
        return;
    }

    protected static boolean positionExistList(List<List<Position>> lists, int excludeIndex, int x, int y){
        for (int i = 0; i < lists.size(); i++)
            if (i != excludeIndex && positionExistList( lists.get(i), x, y))
                return true;
        return false;

    }

    protected static boolean positionExistList( List<Position> list, int x, int y){
        for (Position p : list)
            if (p.getX() == x && p.getY() == y)
                return true;
        return false;
    }

}
