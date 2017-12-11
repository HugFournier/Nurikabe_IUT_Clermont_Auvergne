package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public class IABlancNoir implements IAalgo{


    @Override
    public IAGrille resoudre(IAGrille grille) {
        IAGrille laGrille = grille.clone();
        List<List<Position>> blancs = new ArrayList<>();

        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (!grille.getGrille().isJouable(x, y)){
                    blancs.add( new ArrayList<>());
                    remplire( laGrille.getGrille(), blancs.get( blancs.size()-1), x, y);
                }

        for (int i = 0; i < blancs.size(); i++)
            for (Position p : blancs.get( i))
                laGrille = mettreDesNoirs( blancs, laGrille, p, i);


        return laGrille;
    }

    private IAGrille mettreDesNoirs(List<List<Position>> blancs, IAGrille grille, Position position, int index){
        int x = position.getX();
        int y = position.getY();
        if (positionExistList(blancs, index, x-2, y))
            grille = placer( grille, x-1, y, Etat.NOIR);
        if (positionExistList(blancs, index, x-1, y-1)){
            grille = placer( grille, x-1, y, Etat.NOIR);
            grille = placer( grille, x, y-1, Etat.NOIR);
        }
        if (positionExistList(blancs, index, x-1, y+1)){
            grille = placer( grille, x, y+1, Etat.NOIR);
            grille = placer( grille, x-1, y, Etat.NOIR);
        }
        if (positionExistList(blancs, index, x+2, y))
            grille = placer( grille, x+1, y, Etat.NOIR);

        if (positionExistList(blancs, index, x+1, y-1)){
            grille = placer( grille, x+1, y, Etat.NOIR);
            grille = placer( grille, x, y-1, Etat.NOIR);
        }

        if (positionExistList(blancs, index, x+1, y+1)){
            grille = placer( grille, x+1, y, Etat.NOIR);
            grille = placer( grille, x, y+1, Etat.NOIR);
        }

        if (positionExistList(blancs, index, x, y+2))
            grille = placer( grille, x, y+1, Etat.NOIR);
        if (positionExistList(blancs, index, x, y-2))
            grille = placer( grille, x, y-1, Etat.NOIR);
        return grille;
    }

    private IAGrille placer( IAGrille grille, int x, int y, Etat etat){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return grille;
        if (grille.isForced(x, y))
            return grille;
        grille.setForced( x, y, grille.getGrille().setEtat( x, y, etat));
        return grille;
    }


    private void remplire( Grille grille, List<Position> blancs, int x, int y){
        if (x < 0 || x >= grille.getWidth() || y < 0 || y >= grille.getHeight())
            return;
        if (grille.getEtat(x, y) != Etat.BLANC)
            return;

        blancs.add( new Position(x, y));
        if (!positionExistList( blancs, x-1, y))
            remplire( grille, blancs, x-1, y);
        if (!positionExistList( blancs, x+1, y))
            remplire( grille, blancs, x+1, y);
        if (!positionExistList( blancs, x, y-1))
            remplire( grille, blancs, x, y-1);
        if (!positionExistList( blancs, x, y+1))
            remplire( grille, blancs, x, y+1);
    }

    private boolean positionExistList( List<List<Position>> lists, int excludeIndex, int x, int y){
        for (int i = 0; i < lists.size(); i++)
            if (i != excludeIndex && positionExistList( lists.get(i), x, y))
                return true;
        return false;

    }

    private boolean positionExistList( List<Position> list, int x, int y){
        for (Position p : list)
            if (p.getX() == x && p.getY() == y)
                return true;
        return false;
    }


}
