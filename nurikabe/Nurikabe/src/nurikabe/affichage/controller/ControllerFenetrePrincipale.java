/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import static javafx.application.ConditionalFeature.FXML;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nurikabe.affichage.Case;
import nurikabe.affichage.Grille;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {
    @FXML
    Grille grille;

    @FXML
    ToolBar toolbarPartie;
    @FXML
    HBox hbox;
    
    
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
       hbox.getChildren().clear();
       grille = new Grille(5,7);
       hbox.getChildren().add(grille);
    }
   
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    @FXML
    public void onExit(){
        Platform.exit();
    }
    
}
