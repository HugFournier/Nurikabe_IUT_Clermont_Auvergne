/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package nurikabe.jeu.logic.generateur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.assets.cellule.GrilleNommee;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import util.Matrix;

/**
 *
 * @author fourn
 */
public class CreerDepuisPageWeb {

    public static GrilleNommee chercherHTML(int taille) {

        GrilleNommee grille;

        switch (taille) {
            case 1:
                grille = new GrilleNommee(new Matrix(7, 7), "sansNom");
                break;
            case 2:
                grille = new GrilleNommee(new Matrix(10, 10), "sansNom");
                break;
            case 5:
                grille = new GrilleNommee(new Matrix(12, 12), "sansNom");
                break;
            case 3:
                grille = new GrilleNommee(new Matrix(15, 15), "sansNom");
                break;
            case 4:
                grille = new GrilleNommee(new Matrix(20, 20), "sansNom");
                break;
            default:
                grille = new GrilleNommee(new Matrix(5, 5), "sansNom");
                break;
        }

        try {
            URL site = new URL("https://www.puzzle-nurikabe.com/?size="+taille);
            BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));

            String inputLine = "", ligneGrille = "", ligneID = "";
            boolean trouve = false;

            while (!trouve && (inputLine = in.readLine()) != null) {
                if (inputLine.contains("id=\"NurikabeTable\"")) {
                    ligneGrille = inputLine;
                }
                if (inputLine.contains("Puzzle ID:")) {
                    ligneID = inputLine;
                }
            }
            in.close();

            //recherche de l'ID
            Pattern pattern = Pattern.compile("\\p{Blank}(\\d+,)*\\d+");
            Matcher matcher = pattern.matcher(ligneID);
            if (matcher.find()) {
                ligneID = matcher.group(0).split("\\p{Blank}")[1];
                grille.setId(ligneID);
                //System.out.println(ligneID);
            }
            
            //Remplissage de la grille
            int y = -1;
            int x = -1;
            int val;
            String res;
            pattern = Pattern.compile(">\\d<");
            for (String ligne : ligneGrille.split("<tr>")) {
                if (y >= 0) {
                    x = -1;
                    for (String colonne : ligne.split("<td")) {
                        if (x >= 0) {
                            matcher = pattern.matcher(colonne);
                            if (matcher.find()) {
                                res = matcher.group(0);
                                val = Integer.parseInt(res.charAt(1) + "");
                                grille.set(x, y, new NonJouable(val));
                            } else {
                                grille.set(x, y, new Jouable(Etat.VIDE));
                            }
                            //System.out.println(x+";"+y+"="+colonne);
                        }
                        x++;
                    }
                }
                y++;
            }

        } catch (Exception e) {
            System.out.println("ok");
            return null;
        }
        return grille;
    }
}
