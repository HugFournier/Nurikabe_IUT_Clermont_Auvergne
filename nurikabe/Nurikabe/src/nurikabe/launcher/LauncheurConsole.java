package nurikabe.launcher;

import nurikabe.affichage.Console;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.BinaryGrille;
import nurikabe.jeu.logic.generateur.SaveHandler;

public class LauncheurConsole {



	public static void main(String[] args) {
		Console c = new Console();
		Handler handler = new Handler();
		while(true)
			c.jouer(handler);

	}
	
	
	
}
