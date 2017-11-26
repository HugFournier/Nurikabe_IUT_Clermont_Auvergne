/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author sylat
 */
public class Case extends Parent{
    //PROPERTIES
    private ObservableValue<Integer> taille;
    
    private final int valeur;
    private final boolean caseChifree;
        
    private Paint couleur;
    private Rectangle fondCase;
    private Text texteCase;
    
    //GETTERS
    public ObservableValue<Integer> getTaille() {
        return taille;
    }
    public int getValeur() {
        return valeur;
    }
    public boolean isCaseChifree() {
        return caseChifree;
    }
    public Paint getCouleur() {
        return couleur;
    }
    public Rectangle getFondCase() {
        return fondCase;
    }
    public Text getTexteCase() {
        return texteCase;
    }
    
    //SETTERS
    public void setTaille(int taille) {
        this.taille = new SimpleIntegerProperty(taille).asObject();
    }
    public void setCouleur(Paint couleur) {
        this.couleur = couleur;
    }
    public void setFondCase(Rectangle fondCase) {
        this.fondCase = fondCase;
    }
    public void setTexteCase(Text texteCase) {
        this.texteCase = texteCase;
    }
    
    //CONSTRUCTORS
    public Case(int taille){
        this(0, taille);
    }
    
    public Case(int nombre, int taille){
        if (nombre > 0){
            valeur = nombre;
            caseChifree = true;
            couleur = Color.WHITE;
        }
        else{
            valeur = 0;
            caseChifree = false;
            couleur = Color.LIGHTGREY;
        }
        
        this.taille = new SimpleIntegerProperty(taille).asObject();
        //taille = Bindings.selectDouble(parentProperty(), "width").getValue().intValue();
        
        fondCase = new Rectangle(taille,taille,couleur);
        fondCase.setStroke(Color.BLACK);
        fondCase.setStrokeWidth(2);
        fondCase.widthProperty().bind(this.taille);
        fondCase.heightProperty().bind(this.taille);
        
        this.getChildren().add(fondCase);
        
        if(caseChifree){ 
            texteCase = new Text(valeur+""); //le texte
            texteCase.setFont(new Font(17)); //sa taille
            texteCase.setFill(Color.BLACK); //sa couleur
            texteCase.setX(15); //sa position
            texteCase.setY(25);
            this.getChildren().add(texteCase);
        }
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondCase.setStrokeWidth(1);
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondCase.setStrokeWidth(2);
            }
        });
        
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                appuyer();
            }
        });
    }
    
    //METHODS
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
