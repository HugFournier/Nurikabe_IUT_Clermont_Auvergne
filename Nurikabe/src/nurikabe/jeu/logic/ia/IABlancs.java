package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IABlancs implements IAalgo{


    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (!laGrille.getGrille().isJouable( x, y))
                    laGrille = mettreDesBlancs( laGrille, x, y);
        return laGrille;
    }

    private IAGrille mettreDesBlancs( IAGrille grille, int x, int y){
        List<Position> possibles = new ArrayList<>();
        possibleDePlacer( possibles, grille, x, y, new ArrayList<>(), grille.getGrille().getValeur( x, y));
        if (possibles.size() == 1)
            placer( grille, possibles.get(0).getX(), possibles.get(0).getY(), Etat.BLANC);
        return grille;
    }

    private boolean peutPlacer( IAGrille grille, int x, int y){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return false;
        if (grille.isForced( x, y))
            return false;
        return true;
    }

    private void testEtAddPossible( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done){
        if (!peutPlacer( grille, x, y))
            return;
        if (!existInList( done, x-1, y) && grille.getGrille().getEtat( x-1, y) == Etat.BLANC)
            return;
        if (!existInList( done, x+1, y) && grille.getGrille().getEtat( x+1, y) == Etat.BLANC)
            return;
        if (!existInList( done, x, y-1) && grille.getGrille().getEtat( x, y-1) == Etat.BLANC)
            return;
        if (!existInList( done, x, y+1) && grille.getGrille().getEtat( x, y+1) == Etat.BLANC)
            return;
        possibles.add( new Position( x, y));
    }

    private void possibleDePlacer( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done, int aFaire){
        if (existInList( done, x, y))
            return;
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        if (aFaire <= 1)
            return;
        done.add( new Position( x, y));

        testEtAddPossible( possibles, grille, x-1, y, done);
        testEtAddPossible( possibles, grille, x+1, y, done);
        testEtAddPossible( possibles, grille, x, y-1, done);
        testEtAddPossible( possibles, grille, x, y+1, done);



        if (grille.isForced( x-1, y) && grille.getGrille().getEtat( x-1, y) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x-1, y, done, aFaire-1);
        if (grille.isForced( x+1, y) && grille.getGrille().getEtat( x+1, y) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x+1, y, done, aFaire-1);
        if (grille.isForced( x, y-1) && grille.getGrille().getEtat( x, y-1) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x, y-1, done, aFaire-1);
        if (grille.isForced( x, y+1) && grille.getGrille().getEtat( x, y+1) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x, y+1, done, aFaire-1);

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
