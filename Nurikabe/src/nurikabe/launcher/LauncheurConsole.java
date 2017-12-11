package nurikabe.launcher;

import nurikabe.affichage.Console;
import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.ia.IANew;


public class LauncheurConsole {



	public static void main(String[] args) {
		Console c = new Console();
		Handler handler = new Handler();
		c.afficher( handler);
		Grille grille = (new IANew()).resoudre(handler.getJeu().getGrille());
		for (int x = 0; x < grille.getWidth(); x++)
			for( int y = 0; y < grille.getHeight(); y++)
					handler.getJeu().getGrille().setEtat(x, y, grille.getEtat( x, y));
		c.afficher( handler);
	}
	
	
	
}
