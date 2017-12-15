package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

import static nurikabe.jeu.logic.ia.IAUtils.placer;
import static nurikabe.jeu.logic.ia.IAUtils.positionExistList;

class IABlancNoir implements IAalgo{


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
            placer( grille, x-1, y, Etat.NOIR);
        if (positionExistList(blancs, index, x-1, y-1)){
            placer( grille, x-1, y, Etat.NOIR);
            placer( grille, x, y-1, Etat.NOIR);
        }
        if (positionExistList(blancs, index, x-1, y+1)){
            placer( grille, x, y+1, Etat.NOIR);
            placer( grille, x-1, y, Etat.NOIR);
        }
        if (positionExistList(blancs, index, x+2, y))
            placer( grille, x+1, y, Etat.NOIR);

        if (positionExistList(blancs, index, x+1, y-1)){
            placer( grille, x+1, y, Etat.NOIR);
            placer( grille, x, y-1, Etat.NOIR);
        }

        if (positionExistList(blancs, index, x+1, y+1)){
            placer( grille, x+1, y, Etat.NOIR);
            placer( grille, x, y+1, Etat.NOIR);
        }

        if (positionExistList(blancs, index, x, y+2))
            placer( grille, x, y+1, Etat.NOIR);
        if (positionExistList(blancs, index, x, y-2))
            placer( grille, x, y-1, Etat.NOIR);
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




}
