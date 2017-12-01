package nurikabe.jeu.logic.generateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import nurikabe.jeu.assets.Grille;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

public class EnregistrerDansFichierBinaire implements Enregistreur{


	@Override
	public void enregistrer( Grille grille, String path) {
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


	@Override
	public String getExtentionDeFichier() {
		return ".nuribin";
	}
}
