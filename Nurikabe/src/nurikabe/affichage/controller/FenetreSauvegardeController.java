/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nurikabe.affichage.Grille;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.SaveHandler;

/**
 * FXML Controller class
 *
 * @author hufournier
 */
public class FenetreSauvegardeController {

    @FXML
    Button bouttonChercherChemin;
    @FXML
    RadioButton newGrille, unkGrille, knoGrille;
    @FXML
    AnchorPane root;
    @FXML
    ListView listeSaves;
    @FXML
    Label messageInfo;
    @FXML
    ChoiceBox listeTaille;
    ObservableList observableListeTaille = FXCollections.observableArrayList();
    ObservableList observableListeSaves = FXCollections.observableArrayList();
    Handler manager;
    String path = null;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<String> lines = SaveHandler.getSaveHandler().getFiles();
        observableListeSaves.setAll(lines);
        listeSaves.setItems(observableListeSaves);
        observableListeTaille.addAll("5x5", "7x7", "10x10", "12x12", "15x15", "20x20");
        listeTaille.setItems(observableListeTaille);
    }

    @FXML
    public void cheminClic() {
        path = (String) listeSaves.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void chercherChemin(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        Node node = (Node) event.getSource();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(node.getScene().getWindow());
        try{
        path = file.getAbsolutePath();
        }catch(Exception e){
            path = null;
        }
    }

    @FXML
    public void changeGeneration(ActionEvent event) {
        path = null;
        listeTaille.setDisable(true);
        bouttonChercherChemin.setDisable(true);
        listeSaves.setDisable(true);
        switch (((RadioButton) event.getSource()).getId()) {
            case "newGrille":
                listeTaille.setDisable(false);
                break;
            case "unkGrille":
                bouttonChercherChemin.setDisable(false);
                break;
            case "knoGrille":
                listeSaves.setDisable(false);
                break;
            default:
                break;
        }
    }

    @FXML
    public void chargerGrille() {
        if (path != null) {
            try {
                manager.charger(path);
                SaveHandler.getSaveHandler().addFile(path);
                SaveHandler.getSaveHandler().saveFiles();
                //fermer fenetre
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                messageInfo.setText("Fichier invalide");
                messageInfo.setVisible(true);
            }
        } else {
            messageInfo.setText("Aucun fichier sélectionné");
            messageInfo.setVisible(true);
        }
    }

    public void setManager(Handler manager) {
        this.manager = manager;
    }
}
