package nurikabe.jeu.logic.generateur;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import nurikabe.jeu.assets.Grille;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.NonJouable;
import util.Matrix;

public class FromTextFile implements Generateur, Enregistreur{


    @Override
    public Matrix<Cellule> createMatrix( int width, int height) {
        return null;
    }

	@Override
	public void enregistrer( Matrix<Cellule> grille, String path) {
                int x=grille.getWidth();
                int y=grille.getHeight();
                Cellule c;
                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(x);
                    writer.write(y);
                    for (int i=0; i < x; i++)
			for (int j = 0; j < y; j++)
                            writer.write(grille.get(x,y).toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
	}

	@Override
	public Matrix<Cellule> charger( String path) {
                Matrix<Cellule> grille;
		int x;
                int y;
                
                Cellule c;
                try {
                    FileReader reader = new FileReader(path);
                    x=reader.read();
                    y=reader.read();
                    grille=new Matrix<Cellule>(x,y);
                    for (int i=0; i < x; i++)
			for (int j = 0; j < y; j++){
                            switch(reader.read()){
                            
                            }
                            }
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
	}
    
    
}
