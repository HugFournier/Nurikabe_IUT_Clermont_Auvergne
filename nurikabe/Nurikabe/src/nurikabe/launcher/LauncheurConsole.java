package nurikabe.launcher;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.FromTextFile;
import nurikabe.jeu.logic.generateur.HardCoded7by7;

import java.net.URI;
import java.net.URL;

public class LauncheurConsole {

	public static void main(String[] args) {
		//Jeu jeu = new Jeu(0, 0, new HardCoded7by7());
		//Affichage affichage = new Console( jeu);

		URL path = LauncheurConsole.class.getResource( "/test.txt");


		//jeu.enregistrer( new FromTextFile(), path.getPath());

		//affichage.afficher();
		Jeu jeu = new Jeu( new FromTextFile(), path.getPath());
		Affichage affichage = new Console(jeu);
		affichage.afficher();

		
	}
	
	
	
}
