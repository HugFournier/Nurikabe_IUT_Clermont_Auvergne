package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IA2choixRestant implements IAalgo{

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
        int nbDone = possibleDePlacer( possibles, grille, x, y, new ArrayList<>(), grille.getGrille().getValeur( x, y));
        if (possibles.size() == 2 && nbDone == grille.getGrille().getValeur( x, y)-1){
            int x1 = possibles.get(0).getX();
            int x2 = possibles.get(1).getX();
            int y1 = possibles.get(0).getY();
            int y2 = possibles.get(1).getY();
            if ((x1 == x2-1 && y1 == y2-1) || (x1 == x2+1 && y1 == y2+1)){
                if (grille.getGrille().getEtat(x1, y2) == Etat.BLANC)
                    placer( grille, x2, y1, Etat.NOIR);
                else
                    placer( grille, x1, y2, Etat.NOIR);
            }
            else if ((x1 == x2-1 && y1 == y2+1) || (x1 == x2+1 && y1 == y2-1)){
                if (grille.getGrille().getEtat(x1, y2) == Etat.BLANC)
                    placer( grille, x2, y1, Etat.NOIR);
                else
                    placer( grille, x1, y2, Etat.NOIR);
            }


        }
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

    private int possibleDePlacer( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done, int aFaire){
        if (existInList( done, x, y))
            return 0;
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return 0;
        if (aFaire <= 1)
            return 0;
        done.add( new Position( x, y));

        testEtAddPossible( possibles, grille, x-1, y, done);
        testEtAddPossible( possibles, grille, x+1, y, done);
        testEtAddPossible( possibles, grille, x, y-1, done);
        testEtAddPossible( possibles, grille, x, y+1, done);
        int nbDone = 1;


        if (grille.isForced( x-1, y) && grille.getGrille().getEtat( x-1, y) == Etat.BLANC)
            nbDone += possibleDePlacer( possibles, grille, x-1, y, done, aFaire-1);
        if (grille.isForced( x+1, y) && grille.getGrille().getEtat( x+1, y) == Etat.BLANC)
            nbDone += possibleDePlacer( possibles, grille, x+1, y, done, aFaire-1);
        if (grille.isForced( x, y-1) && grille.getGrille().getEtat( x, y-1) == Etat.BLANC)
            nbDone += possibleDePlacer( possibles, grille, x, y-1, done, aFaire-1);
        if (grille.isForced( x, y+1) && grille.getGrille().getEtat( x, y+1) == Etat.BLANC)
            nbDone += possibleDePlacer( possibles, grille, x, y+1, done, aFaire-1);
        return nbDone;
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
