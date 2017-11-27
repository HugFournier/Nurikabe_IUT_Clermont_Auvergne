/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.generateur;

import java.io.FileInputStream;
import java.util.Scanner;
import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import util.Matrix;

/**
 *
 * @author fourn
 */
public class ChargerDepuisFichierTexte implements Chargeur {
    

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
