package nurikabe.jeu.logic.generateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public class BinaryGrille implements Generateur, Enregistreur{
	
	@Override
	public void enregistrer( Matrix<Cellule> grille, String path) {
		File f = new File( path);
		if (!f.exists())
			try {
				f.getParentFile().mkdirs();
				f.createNewFile();
				f.setWritable( true);
				f.setReadable( true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream( f);
			oos = new ObjectOutputStream( fout);
			oos.writeObject( grille);
			SaveHandler.getSaveHandler().addFile( path);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Matrix<Cellule> charger( String path) {
		File f = new File( path);
		if (!f.exists()) {
			System.out.println( "ERREUR : le fichier n'existe pas");
			return null;
		}
		Matrix<Cellule> grille = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = new FileInputStream( f);
			ois = new ObjectInputStream(fin);
			grille = (Matrix<Cellule>) ois.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return grille;
	}
	
	
	
	@Override
	public Matrix<Cellule> createMatrix( int width, int height) {
		return null;
	}

}
