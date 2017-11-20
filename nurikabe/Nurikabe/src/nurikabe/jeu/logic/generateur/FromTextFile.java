package nurikabe.jeu.logic.generateur;

import java.io.*;
import java.util.Scanner;

import nurikabe.jeu.assets.Grille;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
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
				f.getParentFile().mkdirs();
				f.createNewFile();
				f.setWritable( true);
				f.setReadable( true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		int x=grille.getWidth();
		int y=grille.getHeight();
		try {
			FileOutputStream fo = new FileOutputStream( path);
			PrintStream ps = new PrintStream( fo);

			ps.println( x);
			ps.println( y);
			ps.print( grille.toString());

			ps.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Matrix<Cellule> charger( String path) {
		Matrix<Cellule> grille = null;
		int x;
		int y;

		try {
			FileInputStream fi = new FileInputStream( path);
			Scanner sc = new Scanner(fi);
			String pattern = "[#.+]";
			x=sc.nextInt();
			y=sc.nextInt();
			grille=new Matrix<Cellule>(x,y);
			for (int i=0; i < y; i++)
				for (int j = 0; j < x; j++){
					if (sc.hasNextInt())
						grille.set( j, i, new NonJouable( sc.nextInt()));
					else{
						String b = sc.next(pattern);
						if (b.equals("#"))
							grille.set( j, i, new Jouable( Etat.NOIR));
						else if (b.equals("."))
							grille.set( j, i, new Jouable( Etat.VIDE));
						else if (b.equals("+"))
							grille.set( j, i, new Jouable( Etat.BLANC));
					}
				}
			sc.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grille;
	}


}
