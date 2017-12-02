package nurikabe.launcher;

import nurikabe.affichage.Console;
import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.ia.IAForceBrut;


public class LauncheurConsole {



	public static void main(String[] args) {
		Console c = new Console();
		Handler handler = new Handler();
		c.demanderChemin(handler);
		c.afficher( handler);

		Grille grille = (new IAForceBrut()).resoudre(handler.getJeu().getGrille());
		for (int x = 0; x < grille.getWidth(); x++)
			for( int y = 0; y < grille.getHeight(); y++)
				if (grille.getEtat(x, y) == Etat.NOIR)
					handler.getJeu().getGrille().setEtat(x, y, Etat.NOIR);
		c.afficher( handler);
	}
	
	
	
}
