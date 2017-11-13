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
	
	public void jouer( ) {
		jouer( jeu);
	}
	
	public abstract void afficher( Jeu jeu);
	
	public abstract void jouer( Jeu jeu);
	
}
