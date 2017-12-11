package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IANoirs implements IAalgo{


    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (laGrille.isForced( x, y) && laGrille.getGrille().getEtat( x, y) == Etat.NOIR)
                    laGrille = mettreDesNoirs( laGrille, x, y);
        return laGrille;
    }

    private IAGrille mettreDesNoirs( IAGrille grille, int x, int y){
        List<Position> possibles = new ArrayList<>();
        possibleDePlacer( possibles, grille, x, y, new ArrayList<>());
        if (possibles.size() == 1)
            placer( grille, possibles.get(0).getX(), possibles.get(0).getY(), Etat.NOIR);
        return grille;
    }

    private boolean peutPlacer( IAGrille grille, int x, int y){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return false;
        if (grille.isForced( x, y))
            return false;
        return true;
    }

    private void possibleDePlacer( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done){
        if (existInList( done, x, y))
            return;
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        done.add( new Position( x, y));


        if (peutPlacer( grille, x-1, y))
            possibles.add( new Position( x-1, y));
        if (peutPlacer( grille, x+1, y))
            possibles.add( new Position( x+1, y));
        if (peutPlacer( grille, x, y-1))
            possibles.add( new Position( x, y-1));
        if (peutPlacer( grille, x, y+1))
            possibles.add( new Position( x, y+1));


        if (grille.isForced( x-1, y) && grille.getGrille().getEtat( x-1, y) == Etat.NOIR)
            possibleDePlacer( possibles, grille, x-1, y, done);
        if (grille.isForced( x+1, y) && grille.getGrille().getEtat( x+1, y) == Etat.NOIR)
            possibleDePlacer( possibles, grille, x+1, y, done);
        if (grille.isForced( x, y-1) && grille.getGrille().getEtat( x, y-1) == Etat.NOIR)
            possibleDePlacer( possibles, grille, x, y-1, done);
        if (grille.isForced( x, y+1) && grille.getGrille().getEtat( x, y+1) == Etat.NOIR)
            possibleDePlacer( possibles, grille, x, y+1, done);

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
