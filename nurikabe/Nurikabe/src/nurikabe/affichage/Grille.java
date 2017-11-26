/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import static java.lang.Integer.min;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sylat
 */
public class Grille extends Parent{
    //PROPERTIES
    private GridPane grille;
    private ObservableValue<Integer> width;
    private ObservableValue<Integer> height;
    
    //GETTERS
    public GridPane getGrille(){
        return grille;
    }
    public ObservableValue<Integer> getWidth() {
        return width;
    }
    public ObservableValue<Integer> getHeight() {
        return height;
    }
    
    public int getWidthValue(){
        return this.width.getValue().intValue();
    }
    public int getHeightValue(){
        return this.height.getValue().intValue();
    }
    //SETTERS
    public void setWidth(int width) {
        this.width = new SimpleIntegerProperty(width).asObject();
    }
    public void setHeight(int height) {
        this.height = new SimpleIntegerProperty(height).asObject();
    }
    
    //CONSTRUCTORS
    public Grille(){
        this(5,5);
    }
    
    public Grille(int x, int y){
        if (x<5) x=5;
        if (y<5) y=5;
        
        this.width = new SimpleIntegerProperty(x*40).asObject();
        this.height = new SimpleIntegerProperty(y*40).asObject();
        
        grille = new GridPane();
        
        
        
        for (int row=0; row<x; row++){
            for (int col=0; col<y; col++){
                    grille.add(new Case(min(this.getWidthValue()/x, this.getHeightValue()/y)),row,col);
            }
        }
        this.getChildren().add(grille);
    }  
}
