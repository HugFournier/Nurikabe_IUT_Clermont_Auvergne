package nurikabe.launcher;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.BinaryGrille;
import nurikabe.jeu.logic.generateur.FromTextFile;
import nurikabe.jeu.logic.generateur.HardCoded7by7;

public class LauncheurConsole {



	public static void main(String[] args) {

		Handler handler = new Handler( new FromTextFile(), new BinaryGrille(), null, new Console());
		handler.charger();
		
	}
	
	
	
}
