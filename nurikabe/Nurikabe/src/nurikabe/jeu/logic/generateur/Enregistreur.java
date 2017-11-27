package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public interface Enregistreur extends TypeDeFichierTraite{
	
	public void enregistrer( Grille grille, String path);
}
