package nurikabe.jeu;

import java.util.ArrayList;
import java.util.List;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;
import nurikabe.jeu.logic.verif.vide.VerifVideBrut;

public class Jeu {

	private Grille grille;
	private List<Verif> regles = new ArrayList<Verif>();
	
	public Jeu( ) {
		grille = new Grille( 4, 4);
		initVerif();
	}
	
	// REGLES
	private void initVerif( ) {
		regles.add( new VerifVideBrut());
	}
	
	public boolean verfication() {
		for (Verif regle : regles)
			if (!regle.verification( grille))
				return false;
		return true;
	}
	
	// GRILLE
	public int getHeight() {
		if (grille != null)
			return grille.getHeight();
		return 0;
	}

	public int getWidth() {
		if (grille != null)
			return grille.getWidth();
		return 0;
	}
	
	public Etat getEtat( int x, int y) {
		if (grille != null)
			return grille.getEtat( x, y);
		return null;
	}

	public boolean isJouable(int x, int y) {
		if (grille != null)
			return grille.isJouable(x, y);
		return false;
	}

	public int valeur(int x, int y) {
		if (grille != null)
			return grille.getValeur( x, y);
		return -1;
	}
	
	
}
