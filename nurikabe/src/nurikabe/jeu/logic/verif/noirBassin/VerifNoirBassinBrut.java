package nurikabe.jeu.logic.verif.noirBassin;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;

public class VerifNoirBassinBrut implements Verif{

    @Override
    public boolean verification( Grille grille) {
        for (int x = 1; x < grille.getWidth(); x++)
            for (int y = 1; y < grille.getHeight(); y++)
                if (grille.getEtat( x-1, y-1) == Etat.NOIR &&
                        grille.getEtat( x-1, y) == Etat.NOIR &&
                        grille.getEtat( x, y-1) == Etat.NOIR &&
                        grille.getEtat( x, y) == Etat.NOIR)
                    return false;
        return true;
    }

}
