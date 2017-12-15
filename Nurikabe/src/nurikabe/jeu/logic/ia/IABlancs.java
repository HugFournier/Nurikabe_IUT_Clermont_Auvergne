package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

import static nurikabe.jeu.logic.ia.IAUtils.peutPlacer;
import static nurikabe.jeu.logic.ia.IAUtils.placer;
import static nurikabe.jeu.logic.ia.IAUtils.positionExistList;

class IABlancs implements IAalgo{

    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        List<Position> blancs = new ArrayList<>();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (isSolo(laGrille, x, y, new ArrayList<>()))
                    blancs.add( new Position( x, y));
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (!laGrille.getGrille().isJouable( x, y)) {
                    laGrille = mettreDesBlancs(laGrille, x, y, blancs);
                }
        return laGrille;
    }

    private boolean isSolo( IAGrille grille, int x, int y, List<Position> done){
        done.add( new Position( x, y));
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return true;
        if (!grille.getGrille().isJouable( x, y))
            return false;
        if (grille.getGrille().getEtat( x, y) != Etat.BLANC)
            return true;
        boolean solo = true;
        if (!positionExistList( done, x-1, y))
            solo &= isSolo( grille, x-1, y, done);
        if (!positionExistList( done, x+1, y))
            solo &= isSolo( grille, x+1, y, done);
        if (!positionExistList( done, x, y-1))
            solo &= isSolo( grille, x, y-1, done);
        if (!positionExistList( done, x, y+1))
            solo &= isSolo( grille, x, y+1, done);
        return solo;
    }

    private IAGrille mettreDesBlancs( IAGrille grille, int x, int y, List<Position> blancs){
        List<Position> possibles = new ArrayList<>();
        possibleDePlacer( possibles, grille, x, y, new ArrayList<>(), grille.getGrille().getValeur( x, y), blancs);
        if (possibles.size() == 1){
            placer( grille, possibles.get(0).getX(), possibles.get(0).getY(), Etat.BLANC);
            removeBlancIfNeeded( blancs, possibles.get(0).getX(), possibles.get(0).getY());
        }
        return grille;
    }

    private void removeBlancIfNeeded( List<Position> blancs, int x, int y){
        if (positionExistList( blancs, x, y))
            blancs.remove( new Position( x, y));
        if (positionExistList( blancs, x-1, y))
            removeBlancIfNeeded( blancs, x-1, y);
        if (positionExistList( blancs, x+1, y))
            removeBlancIfNeeded( blancs, x+1, y);
        if (positionExistList( blancs, x, y-1))
            removeBlancIfNeeded( blancs, x, y-1);
        if (positionExistList( blancs, x, y+1))
            removeBlancIfNeeded( blancs, x, y+1);
    }

    private boolean testEtAddPossibleTest( IAGrille grille, int x, int y, List<Position> done, List<Position> blancs){
        return (!positionExistList( done, x, y) && (grille.getGrille().getEtat( x, y) == Etat.BLANC && !positionExistList( blancs, x, y)));
    }

    private void testEtAddPossible( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done, List<Position> blancs){
        if (!peutPlacer( grille, x, y))
            return;
        if (testEtAddPossibleTest( grille, x-1, y, done, blancs))
            return;
        if (testEtAddPossibleTest( grille, x+1, y, done, blancs))
            return;
        if (testEtAddPossibleTest( grille, x, y-1, done, blancs))
            return;
        if (testEtAddPossibleTest( grille, x, y+1, done, blancs))
            return;
        possibles.add( new Position( x, y));
    }

    private void possibleDePlacer( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done, int aFaire, List<Position> blancs){
        if (positionExistList( done, x, y))
            return;
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return;
        if (aFaire <= 1)
            return;
        done.add( new Position( x, y));

        testEtAddPossible( possibles, grille, x-1, y, done, blancs);
        testEtAddPossible( possibles, grille, x+1, y, done, blancs);
        testEtAddPossible( possibles, grille, x, y-1, done, blancs);
        testEtAddPossible( possibles, grille, x, y+1, done, blancs);



        if (grille.isForced( x-1, y) && grille.getGrille().getEtat( x-1, y) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x-1, y, done, aFaire-1, blancs);
        if (grille.isForced( x+1, y) && grille.getGrille().getEtat( x+1, y) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x+1, y, done, aFaire-1, blancs);
        if (grille.isForced( x, y-1) && grille.getGrille().getEtat( x, y-1) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x, y-1, done, aFaire-1, blancs);
        if (grille.isForced( x, y+1) && grille.getGrille().getEtat( x, y+1) == Etat.BLANC)
            possibleDePlacer( possibles, grille, x, y+1, done, aFaire-1, blancs);

    }

}
