package nurikabe.launcher;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.HardCoded7by7;

public class LauncheurConsole {

	public static void main(String[] args) {
		Jeu jeu = new Jeu(0, 0, new HardCoded7by7());
		Affichage affichage = new Console( jeu);
		//affichage.afficher();
		
		while( !jeu.verfication()) {
			affichage.jouer();
		}
		
		affichage.afficher();
		
	}
	
	
	
}
