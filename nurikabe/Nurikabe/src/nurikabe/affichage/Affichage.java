package nurikabe.affichage;

import nurikabe.jeu.Jeu;

public abstract class Affichage {

	protected Jeu jeu;
	
	public Affichage( Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void afficher( ) {
		afficher( jeu);
	}
	
	public abstract void afficher( Jeu jeu);
	
}
