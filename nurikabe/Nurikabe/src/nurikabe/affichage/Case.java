/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

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
    private final static int GLOBALSIZE = 40;
    
    private final int valeur;
    private final boolean caseChifree;
        
    Paint couleur;
    Rectangle fondCase;
    Text texteCase;
    
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
