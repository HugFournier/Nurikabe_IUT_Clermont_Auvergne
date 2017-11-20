/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;

/**
 *
 * @author argiraud
 */
public class ControllerPalmares {
    @FXML
    public void onExit(Event event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
