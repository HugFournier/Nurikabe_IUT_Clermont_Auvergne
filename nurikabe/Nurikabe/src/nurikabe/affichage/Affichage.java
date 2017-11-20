package nurikabe.affichage;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.Enregistreur;

public abstract class Affichage {

	
	public Affichage( ) {

	}
	
	public abstract void afficher( Handler handler);
	
	public abstract void jouer( Handler handler);
	
}
