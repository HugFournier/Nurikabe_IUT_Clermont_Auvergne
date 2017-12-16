package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

import java.util.ArrayList;
import java.util.List;

import static nurikabe.jeu.logic.ia.IAUtils.placer;
import static nurikabe.jeu.logic.ia.PositionAvecValeur.getMaxFromList;

public class IACaseTropLoins implements IAalgo{


    @Override
    public IAGrille resoudre( IAGrille grille) {
        IAGrille laGrille = grille.clone();
        List<Position> pasAtteindre = new ArrayList<>();
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (laGrille.getGrille().getEtat( x, y) == Etat.VIDE)
                    pasAtteindre.add( new Position(x, y));
        for (int x = 0; x < laGrille.getWidth(); x++)
            for (int y = 0; y < laGrille.getHeight(); y++)
                if (!laGrille.getGrille().isJouable(x, y))
                    peutPasAtteindre( laGrille, pasAtteindre, new ArrayList<>(), x, y, laGrille.getGrille().getValeur( x, y));
        for (Position p : pasAtteindre)
            placer( laGrille, p, Etat.NOIR);

        return laGrille;
    }

    private boolean peutPasAtteindreTest( IAGrille grille, List<PositionAvecValeur> done, int x, int y){
        if (x < 0 || y < 0 || x >= grille.getWidth() || y >= grille.getHeight())
            return true;
        if (!grille.getGrille().isJouable( x, y) && done.size() != 0)
            return true;
        if (x > 0 && !grille.getGrille().isJouable( x-1, y) && !getMaxFromList(done).equals( new Position( x-1, y)))
            return true;
        if (x < grille.getWidth()-1 && !grille.getGrille().isJouable( x+1, y) && !getMaxFromList(done).equals( new Position( x+1, y)))
            return true;
        if (y > 0 && !grille.getGrille().isJouable( x, y-1) && !getMaxFromList(done).equals( new Position( x, y-1)))
            return true;
        if (y < grille.getHeight()-1 && !grille.getGrille().isJouable( x, y+1) && !getMaxFromList(done).equals( new Position( x, y+1)))
            return true;
        return false;
    }

    private void peutPasAtteindre( IAGrille grille, List<Position> pasAtteindre, List<PositionAvecValeur> done, int x, int y, int valeur){
        if (peutPasAtteindreTest( grille, done, x, y))
            return;
        addPosition( done, x, y, valeur);

        if (valeur > 0)
            pasAtteindre.remove( new Position( x, y));
        if (valeur < 0)
            return;

        if (!positionBetterList( done, x-1, y, valeur-1))
            peutPasAtteindre( grille, pasAtteindre, done, x-1, y, valeur-1);
        if (!positionBetterList( done, x+1, y, valeur-1))
            peutPasAtteindre( grille, pasAtteindre, done, x+1, y, valeur-1);
        if (!positionBetterList( done, x, y-1, valeur-1))
            peutPasAtteindre( grille, pasAtteindre, done, x, y-1, valeur-1);
        if (!positionBetterList( done, x, y+1, valeur-1))
            peutPasAtteindre( grille, pasAtteindre, done, x, y+1, valeur-1);
    }

    private void addPosition( List<PositionAvecValeur> list, int x, int y, int valeur){
        if (positionBetterList( list, x, y, valeur))
            return;
        list.add( new PositionAvecValeur( x, y, valeur));
    }

    private boolean positionBetterList( List<PositionAvecValeur> list, int x, int y, int valeur){
        for (PositionAvecValeur p : list)
            if (p.getX() == x && p.getY() == y && p.getValeur() >= valeur)
                return true;
        return false;
    }

}

class PositionAvecValeur extends Position{

    private int valeur;

    public static PositionAvecValeur getMaxFromList( List<PositionAvecValeur> list){
        if (list == null || list.size() <= 0)
            return null;
        PositionAvecValeur ret = list.get( 0);
        for (PositionAvecValeur p : list)
            if (p.getValeur() > ret.getValeur())
                ret = p;
        return ret;
    }

    public PositionAvecValeur(int x, int y, int valeur) {
        super(x, y);
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    @Override
    public boolean equals( Object o){
        if (o instanceof PositionAvecValeur)
            return equals( (PositionAvecValeur) o);
        return false;
    }

    @Override
    public boolean equals( Position p){
        return (getX() == p.getX() && getY() == p.getY());
    }

    public boolean equals( PositionAvecValeur p){
        if (getValeur() == p.getValeur())
            return equals( (Position) p);
        return false;
    }
}
