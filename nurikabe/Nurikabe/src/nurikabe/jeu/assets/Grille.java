package nurikabe.jeu.assets;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import nurikabe.jeu.logic.generateur.Generateur;
import util.Matrix;

public class Grille {

	private Matrix<Cellule> grille;
	
	public Grille( int width, int height) {
		grille = new Matrix<Cellule>(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				grille.set(x, y, new Jouable());
	}
	
	public Grille( Matrix<Cellule> grille) {
		this.grille = grille;
	}
	
	public Grille( int width, int height, Generateur generateur) {
		grille = generateur.createMatrix( width, height);
	}
	
	public int getWidth( ) {
		if (grille != null)
			return grille.getWidth();
		return -1;
	}
	
	public int getHeight( ) {
		if (grille != null)
			return grille.getHeight();
		return -1;
	}
	
	private Cellule getCellule( int x, int y) {
		return grille.get( x, y);
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
}
