/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nurikabe.affichage.Grille;
import nurikabe.jeu.assets.Chronometre;
import nurikabe.jeu.logic.Handler;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {
    @FXML
    BorderPane root;
    @FXML
    Grille grille;
    @FXML
    Button pause;
    @FXML
    Button start;
    @FXML
    Label text;

    Chronometre c;
    private Handler manager = new Handler();

    /**
     * Get the value of manager
     *
     * @return the value of manager
     */
    public Handler getManager() {
        return manager;
    }

    /**
     * Set the value of manager
     *
     * @param manager new value of manager
     */
    public void setManager(Handler manager) {
        this.manager = manager;
    }

    @FXML
    public void onPalmares() throws IOException{
        Stage palmares = new Stage();   
        Parent digit = FXMLLoader.load(getClass().getResource("/nurikabe/affichage/ihm/FenetrePalmares.fxml"));
        palmares.setScene(new Scene(digit));
        palmares.centerOnScreen();
        palmares.show();
    }
    
    @FXML
    public void onNPartie(){
        grille.initGrille(manager.getJeu().getGrille());
        c.setTime(0);
        c.start();
        pause.setDisable(false);
    }
   
    
    @FXML
    public void onCharger(){
        grille.initGrille(manager.getJeu().getGrille());
    }
    @FXML
    public void onStart() {
         c.start();
         pause.setVisible(true);
         start.setVisible(false);
         grille.setVisible(true);
     }
    @FXML
    public void onPause() {
         c.pause();
         pause.setVisible(false);
         start.setVisible(true);
         grille.setVisible(false);
     } 
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    
    @FXML
    public void onExit(){
        c.pause();
        Platform.exit();
    }
    
    @FXML
    public void initialize(){
        c = new Chronometre(0);
        c.setLabel(text);
        pause.setDisable(true);
        start.setVisible(false);
        grille.setVisible(true);
        c.setLabel(text);
    }
    
}
