package nurikabe.jeu.logic.generateur;

import java.io.File;
import java.io.IOException;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public class FromTextFile implements Generateur, Enregistreur{


    @Override
    public Matrix<Cellule> createMatrix( int width, int height) {
        return null;
    }

	@Override
	public void enregistrer( Matrix<Cellule> grille, String path) {
		File f = new File( path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		
		
		
	}

	@Override
	public Matrix<Cellule> charger( String path) {
		Matrix<Cellule> grille = null;
		File f = new File( path);
		if (!f.exists()) {
			System.out.println( "ERREUR : le fichier n'existe pas");
			return null;
		}
		
		
		return grille;
	}
    
    
}
