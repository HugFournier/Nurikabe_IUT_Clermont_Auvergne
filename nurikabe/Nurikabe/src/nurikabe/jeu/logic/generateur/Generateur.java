package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public interface Generateur {

	public abstract Matrix<Cellule> createMatrix(int width, int height);
	
	
	
}
