package nurikabe.launcher;

import nurikabe.affichage.Console;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.BinaryGrille;

public class LauncheurConsole {



	public static void main(String[] args) {
		Console c = new Console();
		Handler handler = new Handler( new BinaryGrille(), new BinaryGrille());
		while(true)
			c.jouer(handler);

	}
	
	
	
}
