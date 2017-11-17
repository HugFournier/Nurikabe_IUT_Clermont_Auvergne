/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sylat
 */
public class Grille extends Parent{
    GridPane grille;
    
    public Grille(){
        this(5,5);
    }
    
    public Grille(int x, int y){
        if (x<5) x=5;
        if (y<5) y=5;
        
        grille = new GridPane();
        
        for (int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                grille.add(new Case(),i,j);
            }
        }
        
        this.getChildren().add(grille);
        
    }
}
