/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author syatelais1
 */
public class FenetreCheminController {
    @FXML
    AnchorPane root;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
    }

    @FXML protected void filePicker(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    Node node = (Node) event.getSource();
    chooser.setTitle("Open File");
    File file=chooser.showOpenDialog(node.getScene().getWindow());
    
    System.out.println(file);
}
    
}
