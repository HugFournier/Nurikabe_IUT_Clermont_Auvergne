/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import nurikabe.affichage.events.caseClickedEvent;

/**
 *
 * @author sylat
 */

public class Case extends Label{

    //Les couleurs des cases
    public static final Paint COULEUR_BLANC = Color.WHITE;
    public static final Paint COULEUR_NOIR = Color.BLACK;
    public static final Paint COULEUR_INCONNU = Color.LIGHTGREY;
     
    //Les differents Backgrounds
    private static final Background BG_BLANC = new Background(new BackgroundFill(COULEUR_BLANC, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background BG_NOIR = new Background(new BackgroundFill(COULEUR_NOIR, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background BG_INCONNU = new Background(new BackgroundFill(COULEUR_INCONNU, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Border BORDURE = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    
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
        
        this.setAlignment( Pos.CENTER);
        setBorder(BORDURE);
        setFill(couleur);
        setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
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
    
    public void setFill (Paint value ){
        if (value.equals(COULEUR_BLANC)){
            setBackground(BG_BLANC);                
        }
        else if (value.equals(COULEUR_NOIR)){
            setBackground(BG_NOIR);                
        }
        else {
            setBackground(BG_INCONNU);                
        }
    }
    
    private void appuyer() {
        if (!caseChifree){
            if(getBackground().equals(BG_INCONNU)){
                setBackground(BG_NOIR);
            }
            else if(getBackground().equals(BG_NOIR)){
                setBackground(BG_BLANC);
            }
            else{
                setBackground(BG_INCONNU);
            }
        }
        fireEvent(new caseClickedEvent((Node)this));
    }
}

