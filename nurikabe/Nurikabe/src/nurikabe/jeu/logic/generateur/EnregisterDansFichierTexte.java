package nurikabe.jeu.logic.generateur;

import java.io.*;
import java.util.Scanner;

import nurikabe.jeu.assets.Grille;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import util.Matrix;

public class EnregisterDansFichierTexte implements Enregistreur{

	@Override
	public void enregistrer( Grille grille, String path) {
		File f = new File( path);
		if (!f.exists())
			try {
				f.getParentFile().mkdirs();
				f.createNewFile();
				f.setWritable( true);
				f.setReadable( true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		int x=grille.getWidth();
		int y=grille.getHeight();
                long t=grille.getChrono().getDureeMs();
		try {
			FileOutputStream fo = new FileOutputStream( path);
			PrintStream ps = new PrintStream( fo);

			ps.println( x);
			ps.println( y);
			ps.println( t);
			ps.print( grille.toString());

			ps.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getExtentionDeFichier() {
		return ".nuritxt";
	}


}
