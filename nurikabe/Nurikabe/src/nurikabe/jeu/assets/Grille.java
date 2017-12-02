package nurikabe.jeu.assets;

import java.io.Serializable;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import nurikabe.jeu.logic.generateur.Generateur;
import util.Chronomètre;
import util.Matrix;

public class Grille extends Matrix<Cellule> implements Serializable{
    
    private static final long serialVersionUID = 3255510956281643421L;
    
    private Chronomètre chrono;
    
    public Chronomètre getChrono() {
        return chrono;
    }
    
    
    public Grille( Matrix<Cellule> grille, long scoreTmp) {
        super( grille);
        chrono=new Chronomètre(scoreTmp);
    }
    
    public Grille( Matrix<Cellule> grille) {
        this(grille, 0);
    }
    
    public Grille( int width, int height, Generateur generateur) {
        this( generateur.createMatrix( width, height), 0);
        
    }

    @Override
    public Grille clone( ){
        return new Grille( this, chrono.getDureeMs());
    }
    
    
    private Cellule getCellule( int x, int y) {
        return get( x, y);
    }
    
    public int difficulty( ) {
        return getWidth() * getHeight();
    }
    
    public Etat getEtat( int x, int y) {
        Cellule c = getCellule( x, y);
        if (c != null)
            return c.getEtat();
        return null;
    }
    
    public boolean isJouable( int x, int y) {
        Cellule c = getCellule( x, y);
        if (c != null && c instanceof Jouable)
            return true;
        return false;
    }
    
    public int getValeur( int x, int y) {
        Cellule c = getCellule( x, y);
        if (c != null && c instanceof NonJouable)
            return ((NonJouable) c).getValeur();
        return -1;
    }
    
    public boolean cycleEtat( int x, int y) {
        Cellule c = getCellule( x, y);
        if (c != null)
            return c.cycleEtat();
        return false;
    }
    
    public boolean setEtat( int x, int y, Etat etat) {
        Cellule c = getCellule( x, y);
        if (c != null)
            return c.setEtat( etat);
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder retour = new StringBuilder();
        retour.append(getChrono().getDureeTxt()+"\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                retour.append( getCellule( x, y).toString() + " ");
            }
            retour.append( "\n");
        }
        return retour.toString();
    }
    
    
}
