package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public interface Chargeur extends TypeDeFichierTraite{

    public Matrix<Cellule> charger(String path);
}
