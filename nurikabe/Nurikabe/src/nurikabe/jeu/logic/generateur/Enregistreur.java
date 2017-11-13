package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public interface Enregistreur {
	
	public void enregistrer( Matrix<Cellule> grille, String path);
	public Matrix<Cellule> charger( String path);

}
