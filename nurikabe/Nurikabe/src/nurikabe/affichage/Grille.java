/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
                grille.add(new Case(),i,j);
            }
        }
        
        this.getChildren().add(grille);
        
    }
    
    public Grille (nurikabe.jeu.assets.Grille entree){
        int col = entree.getWidth();
        int row = entree.getHeight();
        grille = new GridPane();       
        for (int i=0 ; i<col ; i++){
            for (int j=0 ; j<row ; j++){
                if (!entree.isJouable(i, j)){
                    this.grille.add(new Case(entree.getValeur(i, j) , Color.WHITE), i, j);
                }
                else{
                    switch(entree.getEtat(i, j)){
                        case BLANC :
                            this.grille.add(new Case(0 , Color.WHITE), i, j);
                            break;
                        case NOIR :
                            this.grille.add(new Case(0 , Color.BLACK), i, j);
                            break;
                        default :
                            this.grille.add(new Case(0 , Color.LIGHTGREY), i, j);
                            break;
                    }
                }
            }
        }
        this.getChildren().add(grille);
    }
    
    public GridPane getGrille(){
        return grille;
    }
}
