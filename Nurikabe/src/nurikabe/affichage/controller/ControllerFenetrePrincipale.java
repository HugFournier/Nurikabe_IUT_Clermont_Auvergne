/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nurikabe.affichage.Grille;
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
    ToolBar toolbarPartie;
    @FXML
    HBox hbox;

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
    }
   
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    @FXML
    public void onExit(){
        Platform.exit();
    }
    
    public void initialize(){
        root.setMargin(hbox, new Insets(20,20,20,20));
    }
    
}
