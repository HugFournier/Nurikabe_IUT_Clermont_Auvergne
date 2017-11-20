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
    private GridPane grille;
    
    public Grille(){
        this(5,5);
    }
    
    public Grille(int x, int y){
        if (x<5) x=5;
        if (y<5) y=5;
        
        grille = new GridPane();
        
        for (int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                if (i== 4 && j==5 ) grille.add(new Case(9),i,j);
                else if (i==2 && j==2 ) grille.add(new Case(2),i,j);
                else if (i==1 && j==4 ) grille.add(new Case(2),i,j);
                else
                    grille.add(new Case(),i,j);
                
            }
        }
        
        this.getChildren().add(grille);
        
    }
    public GridPane getGrille(){
        return grille;
    }
}
