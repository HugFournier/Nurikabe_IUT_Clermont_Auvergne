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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nurikabe.jeu.logic.Handler;

/**
 * FXML Controller class
 *
 * @author syatelais1
 */
public class FenetreCheminController {

    @FXML
    AnchorPane root;
    String chemin, title = "sansNom", extention = ".nuritxt";
    Handler handler;
    @FXML
    RadioButton choixBinaire, choixTexte;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }

    @FXML
    protected void filePicker(ActionEvent event) {
        try {
            DirectoryChooser chooser = new DirectoryChooser();
            Node node = (Node) event.getSource();
            chooser.setTitle("Open File");
            File file = chooser.showDialog(node.getScene().getWindow());
            chemin = file.getAbsolutePath();
        } catch (Exception e) {
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
        this.title = handler.getIDgrille();
    }

    public void onChoixExtention(ActionEvent event) {
        switch (((RadioButton) event.getSource()).getId()) {
            case "choixBinaire":
                extention = ".nuribin";
                break;
            default:
                extention = ".nuritxt";
                break;
        }
    }

    public void renvoyerChemin() {
        if (chemin == null || handler == null) {
            return;
        }
        String path = chemin + File.separator + title + extention;
        handler.setCheminDeSauvegarde(path);
        handler.enregistrer(path);

        onQuitter();
    }
    
    public void onQuitter(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
