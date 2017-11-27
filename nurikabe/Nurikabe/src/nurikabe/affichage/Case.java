/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author sylat
 */
public class Case extends Label{
    //PROPERTIES
    private final int valeur;
    private final boolean caseChifree;
    
    //SETTERS
    public int getValeur() {
        return valeur;
    }
    public boolean isCaseChifree() {
        return caseChifree;
    }
    
    //CONSTRUCTORS
    Case(int valeur, Paint couleur) {
        if (valeur > 0){
            this.valeur = valeur;
            this.caseChifree = true;
            couleur = Color.WHITE;
        } 
        else{
            this.valeur = 0;
            this.caseChifree = false;
        }
        
        if (this.isCaseChifree()){
            this.setText(this.getValeur()+"");
            this.setFont(new Font(17));
            this.setTextFill(Color.BLACK);
        }
        
        this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setBackground(new Background(new BackgroundFill(couleur, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    //METHODS

}
    /*
    public Case(){
        this(0, Color.LIGHTGRAY);
    }
    
    public Case(int nombre, Paint couleur){
        if (nombre > 0){
            valeur = nombre;
            caseChifree = true;
            couleur = Color.WHITE;
        }
        else{
            valeur = 0;
            caseChifree = false;
            this.couleur = couleur;
        }
        
        fondCase = new Rectangle(GLOBALSIZE,GLOBALSIZE,this.couleur);
        fondCase.setStroke(Color.BLACK);
        fondCase.setStrokeWidth(2);
        this.getChildren().add(fondCase);
        
        if(caseChifree){ 
            texteCase = new Text(valeur+""); //le texte
            texteCase.setFont(new Font(17)); //sa taille
            texteCase.setFill(Color.BLACK); //sa couleur
            texteCase.setX(15); //sa position
            texteCase.setY(25);
            this.getChildren().add(texteCase);
        }
              
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                appuyer();
            }
        });
        
        
    }    
    
    private void appuyer() {
        if (!caseChifree){
            if(fondCase.getFill().equals(Color.LIGHTGREY)){
                fondCase.setFill(Color.BLACK);
            }
            else{ if(fondCase.getFill().equals(Color.BLACK)){ 
                fondCase.setFill(Color.WHITE);
            }
                else{
                   fondCase.setFill(Color.LIGHTGREY);
                }
            }
        }
    }
}
*/