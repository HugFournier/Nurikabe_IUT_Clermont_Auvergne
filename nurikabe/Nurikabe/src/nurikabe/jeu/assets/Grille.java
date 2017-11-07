package nurikabe.jeu.assets;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;

public class Grille {

	private Cellule[][] grille;
	
	public Grille( int width, int height) {
		grille = new Cellule[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				grille[x][y] = new Jouable();
	}
	
	public int getWidth( ) {
		if (grille != null)
			return grille.length;
		return -1;
	}
	
	public int getHeight( ) {
		if (grille != null)
			return grille[0].length;
		return -1;
	}
	
	private Cellule getCellule( int x, int y) {
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight())
			return grille[x][y];
		return null;
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
}
