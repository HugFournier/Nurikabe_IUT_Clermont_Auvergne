package nurikabe.affichage;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.assets.cellule.Etat;

public class Console extends Affichage{

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

	
}
