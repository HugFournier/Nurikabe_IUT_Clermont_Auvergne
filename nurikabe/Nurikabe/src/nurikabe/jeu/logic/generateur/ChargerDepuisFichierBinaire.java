package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import util.Matrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import nurikabe.jeu.assets.Grille;

public class ChargerDepuisFichierBinaire implements Chargeur{

    @SuppressWarnings("unchecked")
    @Override
    public Grille charger(String path) {
        File f = new File( path);
        if (!f.exists()) {
            System.out.println( "ERREUR : le fichier n'existe pas");
            return null;
        }
        Grille grille = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream( f);
            ois = new ObjectInputStream(fin);
            grille = (Grille) ois.readObject();
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
    public String getExtentionDeFichier() {
        return ".nuribin";
    }
}
