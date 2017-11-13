package nurikabe.affichage;

import java.util.Scanner;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.assets.cellule.Etat;

public class Console extends Affichage{
	
	Scanner sc = new Scanner( System.in);

	public Console(Jeu jeu) {
		super(jeu);
	}

	@Override
	public void afficher( Jeu jeu) {
		if (jeu == null)
			return;
		int height = jeu.getHeight();
		int width = jeu.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Etat e = jeu.getEtat(x, y);
				if (e == Etat.BLANC)
					if (jeu.isJouable(x, y))
						System.out.print( "+");
					else
						System.out.print( jeu.valeur( x, y));
				else if (e == Etat.NOIR)
					System.out.print( "X");
				else if (e == Etat.VIDE)
					System.out.print( ".");
			}
			System.out.println("");
		}
	}
	
	@Override
	public void jouer( Jeu jeu) {
		afficher( jeu);
		int x = -1, y = -1;
		System.out.println( "Tapez -1 pour quiter");
		System.out.print( "Donnez la position x du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		x = sc.nextInt();
		System.out.print( "Donnez la position y du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		y = sc.nextInt();
		if (y == -1)
			System.exit( 0);
		jeu.jouer( x, y);
	}

	
}
