/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 *
 * @author sylat
 */
public class Grille extends GridPane{
    
    public void initGrille(nurikabe.jeu.assets.Grille entree){
        System.out.println(entree.getWidth()+" dans init");
        this.getColumnConstraints().clear();
        this.getRowConstraints().clear();
        this.getChildren().clear();
        
        int col = entree.getWidth();
        int row = entree.getHeight();     
        
        for (int i=0 ; i<row ; i++){
            for (int j=0 ; j<col ; j++){
                
                if (!entree.isJouable(i, j)){
                    this.add(new Case(entree.getValeur(i, j) , Color.WHITE), i, j);
                }
                else{
                    switch(entree.getEtat(i, j)){
                        case BLANC :
                            this.add(new Case(0 , Color.WHITE), i, j);
                            break;
                        case NOIR :
                            this.add(new Case(0 , Color.BLACK), i, j);
                            break;
                        default :
                            this.add(new Case(0 , Color.LIGHTGREY), i, j);
                            break;
                    }
                }
            }
        }
        
        for (int i = 0; i < col; i++) {
            //ajouter une contrainte de colone avec les tailles min, pref et max ainsi que le comportement horizontal : croissance, position et remplissage
            getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }
        for (int i = 0; i < row; i++) {
            getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        
        setPadding(new Insets(20,20,20,20));
        setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
}
