/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {
    
    public void onPalmares() throws IOException{
        Stage palmares = new Stage();   
        Parent digit = FXMLLoader.load(getClass().getResource("/nurikabe/affichage/ihm/FenetrePalmares.fxml"));
        palmares.setScene(new Scene(digit));
        palmares.centerOnScreen();
        palmares.show();
    }
   
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    public void onExit(){
        Platform.exit();
    }
}
