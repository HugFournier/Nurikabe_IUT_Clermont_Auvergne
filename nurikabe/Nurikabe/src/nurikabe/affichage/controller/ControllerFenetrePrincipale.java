/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import javafx.application.Platform;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {
    
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    public void onExit(){
        Platform.exit();
    }
}
