package nurikabe.launcher;

import nurikabe.affichage.Console;
import nurikabe.jeu.logic.Handler;


public class LauncheurConsole {



	public static void main(String[] args) {
		Console c = new Console();
		Handler handler = new Handler();
                c.demanderChemin( handler);
		while(true)
			c.jouer(handler);

	}
	
	
	
}
