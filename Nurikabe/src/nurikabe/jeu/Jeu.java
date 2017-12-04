package nurikabe.jeu;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.generateur.Chargeur;
import nurikabe.jeu.logic.generateur.Enregistreur;
import nurikabe.jeu.logic.generateur.Generateur;
import nurikabe.jeu.logic.verif.LesVerifs;

public class Jeu {

	private Grille grille;
	private LesVerifs verifs = new LesVerifs();
	
	public Jeu( int width, int height, Generateur generateur) {
                    grille = new Grille( width, height, generateur);
	}

	public Jeu(Chargeur chargeur, String path){
		grille = new Grille( chargeur.charger( path));
	}
	
	public boolean verfication() {
		return verifs.verification( grille);
	}

	// PER
	public void enregistrer( Enregistreur enregistreur, String path){
		enregistreur.enregistrer( grille, path);
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

        @Override
        public String toString() {
            return  grille.toString();
        }

    public Grille getGrille() {
        return this.grille; 
    }
	
        
}
