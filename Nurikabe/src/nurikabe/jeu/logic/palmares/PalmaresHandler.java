/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.palmares;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fourn
 */
public class PalmaresHandler {

    private final Path path;
    private ObservableList<Palmares> listePalmares = FXCollections.observableArrayList();

    public PalmaresHandler() {
        URI uri = null;
        try {
            uri = PalmaresHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI();

        } catch (URISyntaxException e) {
            System.err.println("Execution path not defined : " + e.getMessage());
        }
        path = Paths.get(uri);
        lirePalmaresDepuisFichier();
    }

    public ObservableList<Palmares> getListePalmares() {
        return listePalmares;
    }

    public void sauvegarderPalmares() {
        File f = new File(path.toAbsolutePath() + File.separator + "palmares.bin");
        if (f.exists()) {
            f.delete();
        }
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                f.setWritable(true);
                f.setReadable(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        FileOutputStream fout = null;
        PrintStream ps = null;
        try {
            fout = new FileOutputStream(f);
            ps = new PrintStream(fout);
            for (Palmares p : listePalmares) {
                ps.println(p);
            }
            fout.close();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPalmares(Palmares p) {
        if (listePalmares.contains(p)) {
            int index = listePalmares.indexOf(p);
            Palmares p2 = listePalmares.get(index);
            if (p.compareTo(p2) < 0) {
                return;
            }
            listePalmares.remove(index);
        }
        listePalmares.add(p);
        sauvegarderPalmares();
    }

    public void addPalmares(String id, int taille, long temps) {
        addPalmares(new Palmares(id, taille, temps));
    }

    private void lirePalmaresDepuisFichier() {
        File f = new File(path.toAbsolutePath() + File.separator + "palmares.bin");
        if (!f.exists()) {
            return;
        }
        FileInputStream fin = null;
        Scanner sc = null;
        try {
            fin = new FileInputStream(f);
            sc = new Scanner(fin);

            //System.out.println((ois.readObject()).getClass().getName());
            String id;
            int taille;
            long temps;

            try {
                id = sc.nextLine();
                taille = sc.nextInt();
                temps = sc.nextLong();
                for (;;) {
                    listePalmares.add(new Palmares(id,taille,temps));
                    
                    sc.nextLine();//retour à la ligne
                    id = sc.nextLine();
                    taille = sc.nextInt();
                    temps = sc.nextLong();
                }
            } catch (Exception e) {
            }

            fin.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
