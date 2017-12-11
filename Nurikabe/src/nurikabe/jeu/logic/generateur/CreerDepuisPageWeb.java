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
import nurikabe.jeu.assets.Grille;

/**
 *
 * @author fourn
 */
public class CreerDepuisPageWeb {
    
    public String chercherHTML(){
        try{
            URL site = new URL("https://www.puzzle-nurikabe.com/?size=0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            site.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
        }
        catch(Exception e){
            return null;
        }
        return null;
    }
}
