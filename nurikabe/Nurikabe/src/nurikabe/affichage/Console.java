package nurikabe.affichage;

import java.util.Scanner;

import nurikabe.jeu.Jeu;

public class Console extends Affichage{
	
	Scanner sc = new Scanner( System.in);

	public Console(Jeu jeu) {
		super(jeu);
	}

	@Override
	public void afficher( Jeu jeu) {
		if (jeu == null)
			return;
                System.out.println(jeu.toString());
        System.out.println(jeu.toString());
	}
	
	@Override
	public void jouer( Jeu jeu) {
		afficher( jeu);
		int x = -1, y = -1;
		System.out.println( "Tapez -1 pour quiter");
		System.out.print( "Donnez la position x du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		x = sc.nextInt();
		if (x == -1)
			System.exit( 0);
		System.out.print( "Donnez la position y du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		y = sc.nextInt();
		if (y == -1)
			System.exit( 0);
		jeu.jouer( x, y);
	}

	
}
