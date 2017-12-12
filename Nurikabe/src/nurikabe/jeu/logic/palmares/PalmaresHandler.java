/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.palmares;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fourn
 */
public class PalmaresHandler {

    private final Path path;
    private ObservableList<Palmares> listePalmares=FXCollections.observableArrayList();

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
    
    public ObservableList<Palmares> getListePalmares(){
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
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream(f);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(listePalmares);
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

    public void addFile(Palmares p) {
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

    public void addFile(String id, int taille, long temps) {
        addFile(new Palmares(id,taille,temps));
    }
    
    private void lirePalmaresDepuisFichier() {
        File f = new File(path.toAbsolutePath() + File.separator + "palmares.bin");
        if (!f.exists()) {
            return;
        }
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(f);
            ois = new ObjectInputStream(fin);
            listePalmares = (ObservableList<Palmares>) ois.readObject();
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
    }
}
