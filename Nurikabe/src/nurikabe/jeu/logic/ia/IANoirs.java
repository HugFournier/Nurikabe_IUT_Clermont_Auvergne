package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

import static nurikabe.jeu.logic.ia.IAUtils.placer;
import static nurikabe.jeu.logic.ia.IAUtils.peutPlacer;
import static nurikabe.jeu.logic.ia.IAUtils.positionExistList;

class IANoirs implements IAalgo{

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

    private void possibleDePlacer( List<Position> possibles, IAGrille grille, int x, int y, List<Position> done){
        if (positionExistList( done, x, y))
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

}
