package nurikabe.jeu.logic.ia;

import javafx.geometry.Pos;
import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IABlancsCompletes implements IAalgo{

    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (!laGrille.getGrille().isJouable( x, y)){
                    List<Position> blancs = new ArrayList<>();
                    blancListConnecter( laGrille, blancs, x, y);
                    if (blancs.size() == laGrille.getGrille().getValeur( x, y))
                        for (Position p : blancs)
                            placerLesNoirs( laGrille, p.getX(), p.getY());
                }


        return laGrille;
    }


    private void placerLesNoirs( IAGrille grille, int x, int y){
        placerNoir( grille, x-1, y);
        placerNoir( grille, x+1, y);
        placerNoir( grille, x, y-1);
        placerNoir( grille, x, y+1);
    }

    private void placerNoir( IAGrille grille, int x, int y){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        if (grille.getGrille().getEtat( x, y) == Etat.BLANC)
            return;
        placer( grille, x, y, Etat.NOIR);
    }


    private void blancListConnecter( IAGrille grille, List<Position> blancs, int x, int y){
        if (existInList( blancs, x, y))
            return;
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        if (grille.getGrille().getEtat( x, y) != Etat.BLANC)
            return;
        blancs.add( new Position( x, y));
        blancListConnecter( grille, blancs, x-1, y);
        blancListConnecter( grille, blancs, x+1, y);
        blancListConnecter( grille, blancs, x, y-1);
        blancListConnecter( grille, blancs, x, y+1);
    }

    private boolean existInList( List<Position> list, int x, int y){
        for (Position p : list)
            if (p.getX() == x && p.getY() == y)
                return true;
        return false;
    }

    private IAGrille placer( IAGrille grille, int x, int y, Etat etat){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return grille;
        if (grille.isForced(x, y))
            return grille;
        grille.setForced( x, y, grille.getGrille().setEtat( x, y, etat));
        return grille;
    }

}
