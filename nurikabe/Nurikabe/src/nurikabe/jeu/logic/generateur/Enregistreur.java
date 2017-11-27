package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public interface Enregistreur extends TypeDeFichierTraite{
	
	public void enregistrer( Matrix<Cellule> grille, String path);
}
