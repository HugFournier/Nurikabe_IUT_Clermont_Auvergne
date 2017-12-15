package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

import static nurikabe.jeu.logic.ia.IAUtils.positionExistList;
import static nurikabe.jeu.logic.ia.IAUtils.placer;

class IABlancsCompletes implements IAalgo{

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
        if (positionExistList( blancs, x, y))
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

}
