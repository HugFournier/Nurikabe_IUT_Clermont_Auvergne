package nurikabe.jeu.logic.verif.vide;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;

public class VerifVideBrut implements Verif{

	@Override
	public boolean verification(Grille grille) {
		int width = grille.getWidth();
		int height = grille.getHeight();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (grille.getEtat(x, y) == Etat.VIDE)
					return false;
		return true;
	}

}
