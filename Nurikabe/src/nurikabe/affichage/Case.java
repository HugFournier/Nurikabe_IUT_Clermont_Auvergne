/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
            caseChifree = true;

            couleur = Color.WHITE;
        } 
        else{
            this.valeur = 0;
            caseChifree = false;
        }
        

        if (isCaseChifree()){
            setText(getValeur()+"");
            setFont(new Font(17));
            setTextFill(Color.BLACK);
        }
        
        setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        setTextAlignment(TextAlignment.CENTER);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setBackground(new Background(new BackgroundFill(couleur, CornerRadii.EMPTY, Insets.EMPTY)));
    
        setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                appuyer();
            }
        });
    }
    
    //METHODS
    private Paint getFill(){
        return getBackground().getFills().get(0).getFill();
    }
    
    private void setFill (Paint value ){
        setBackground(new Background(new BackgroundFill(value, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    
    private void appuyer() {
        if (!caseChifree){
            if(getFill().equals(Color.LIGHTGREY)){
                setFill(Color.BLACK);
            }
            else{ if(getFill().equals(Color.BLACK)){ 
                setFill(Color.WHITE);
            }
                else{
                   setFill(Color.LIGHTGREY);
                }
            }
        }
    }
}

