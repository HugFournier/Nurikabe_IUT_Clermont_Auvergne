package nurikabe.jeu;

import java.util.ArrayList;
import java.util.List;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.generateur.Generateur;
import nurikabe.jeu.logic.verif.Verif;
import nurikabe.jeu.logic.verif.blancChiffre.VerifBlancChiffreBrut;
import nurikabe.jeu.logic.verif.blancConnect.VerifBlancConnectBrut;
import nurikabe.jeu.logic.verif.noirConnect.VerifNoirConnectBrut;
import nurikabe.jeu.logic.verif.vide.VerifVideBrut;

public class Jeu {

	private Grille grille;
	private List<Verif> regles = new ArrayList<Verif>();
	
	public Jeu( int width, int height) {
		grille = new Grille( width, height);
		initVerif();
	}
	
	public Jeu( int width, int height, Generateur generateur) {
		grille = new Grille( width, height, generateur);
		initVerif();
	}
	
	// REGLES
	private void initVerif( ) {
		regles.add( new VerifVideBrut());
		regles.add( new VerifNoirConnectBrut());
		regles.add( new VerifBlancConnectBrut());
		regles.add( new VerifBlancChiffreBrut());
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
	
	private boolean cycleEtat( int x, int y) {
		if (grille != null)
			return grille.cycleEtat( x, y);
		return false;
	}
	
	public void mettreVideEnBlanc( ) {
		int width = getWidth();
		int height = getHeight();
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (grille.getEtat( x, y) == Etat.VIDE)
					grille.setEtat( x, y, Etat.BLANC);
	}
	
	public void jouer( int x, int y) {
		cycleEtat( x, y);
	}
	
}
