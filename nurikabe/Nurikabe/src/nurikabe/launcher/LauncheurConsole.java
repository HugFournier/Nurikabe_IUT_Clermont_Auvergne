package nurikabe.launcher;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.HardCoded7by7;

public class LauncheurConsole {

	public static void main(String[] args) {
		Jeu jeu = new Jeu(0, 0, new HardCoded7by7());
		Affichage affichage = new Console( jeu);
		affichage.afficher();
		
		affichage.afficher();
		jeu = solve( jeu);
		affichage.afficher();
		System.out.println( jeu.verfication());
	}
	
	private static Jeu solve( Jeu jeu) {
		jeu.cycleEtat(0, 2);
		jeu.cycleEtat(0, 3);
		jeu.cycleEtat(0, 4);
		jeu.cycleEtat(0, 5);
		
		jeu.cycleEtat(1, 0);
		jeu.cycleEtat(1, 1);
		jeu.cycleEtat(1, 5);
		
		jeu.cycleEtat(2, 1);
		jeu.cycleEtat(2, 2);
		jeu.cycleEtat(2, 3);
		jeu.cycleEtat(2, 5);
		
		jeu.cycleEtat(3, 1);
		jeu.cycleEtat(3, 3);
		jeu.cycleEtat(3, 4);
		jeu.cycleEtat(3, 5);
		jeu.cycleEtat(3, 6);

		jeu.cycleEtat(4, 2);
		jeu.cycleEtat(4, 6);

		jeu.cycleEtat(5, 0);
		jeu.cycleEtat(5, 1);
		jeu.cycleEtat(5, 2);
		jeu.cycleEtat(5, 3);
		jeu.cycleEtat(5, 4);
		jeu.cycleEtat(5, 6);

		jeu.cycleEtat(6, 4);
		jeu.cycleEtat(6, 5);
		jeu.cycleEtat(6, 6);
		
		jeu.mettreVideEnBlanc();
		return jeu;
	}
	
	
}
