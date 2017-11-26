package nurikabe.affichage;

import java.util.Scanner;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.Handler;

public class Console extends Affichage{
	
	Scanner sc = new Scanner( System.in);

	public Console () {
	}

	@Override
	public void afficher( Handler handler) {
		if (handler == null)
			return;
		if (handler.getJeu() == null)
			return;
		System.out.println(handler.getJeu().toString());
	}
	
	@Override
	public void jouer( Handler handler) {
		afficher( handler);
		int x = -1, y = -1;
		System.out.println( "Tapez -1 pour quiter ou -2 pour sauvegarder");
		System.out.print( "Donnez la position x du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		x = sc.nextInt();
		if (x == -2){
			handler.enregistrer( );
                        return;
                }
		if (x == -1)
			System.exit( 0);
		System.out.print( "Donnez la position y du case (commence par 0) : ");
		while (!sc.hasNextInt()) {}
		y = sc.nextInt();
		if (y == -1)
			System.exit( 0);
		handler.jouer( x, y);
	}

	
}
