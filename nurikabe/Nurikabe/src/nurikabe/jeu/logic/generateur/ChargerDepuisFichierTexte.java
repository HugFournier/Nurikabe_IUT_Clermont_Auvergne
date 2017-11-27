/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.generateur;

import java.io.FileInputStream;
import java.util.Scanner;
import nurikabe.jeu.assets.Grille;
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
	public Grille charger( String path) {
		Grille grille = null;
		int x;
		int y;
                long t;
                
		try {
			FileInputStream fi = new FileInputStream( path);
			Scanner sc = new Scanner(fi);
			String pattern = "[#.+]";
			x=sc.nextInt();
			y=sc.nextInt();
                        t=sc.nextLong();
			grille=new Grille(new Matrix<Cellule>(x,y),t);
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

	@Override
	public String getExtentionDeFichier() {
		return ".nuritxt";
	}
}
