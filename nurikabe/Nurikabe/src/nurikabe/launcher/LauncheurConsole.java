package nurikabe.launcher;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.Jeu;

public class LauncheurConsole {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Affichage affichage = new Console( jeu);
		affichage.afficher();
		System.out.println( jeu.verfication());
	}
	
	
}
