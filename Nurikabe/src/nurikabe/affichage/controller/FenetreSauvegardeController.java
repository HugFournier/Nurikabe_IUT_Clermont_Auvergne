/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.CreerDepuisPageWeb;
import nurikabe.jeu.logic.generateur.SaveHandler;

/**
 * FXML Controller class
 *
 * @author hufournier
 */
public class FenetreSauvegardeController {

    @FXML
    Button bouttonChercherChemin, bouttonLancer;
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
    String tailleCreationGrille = null;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<String> lines = SaveHandler.getSaveHandler().getFiles();
        observableListeSaves.setAll(lines);
        listeSaves.setItems(observableListeSaves);
        observableListeTaille.addAll("5x5", "7x7", "10x10", "12x12", "15x15", "20x20");
        listeTaille.setItems(observableListeTaille);
        listeTaille.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                tailleCreationGrille = (String) listeTaille.getItems().get((Integer) number2);
            }
        });
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
        try {
            path = file.getAbsolutePath();
        } catch (Exception e) {
            path = null;
        }
    }

    @FXML
    public void changeGeneration(ActionEvent event) {
        path = null;
        tailleCreationGrille = null;
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
        bouttonLancer.setDisable(true);
        bouttonLancer.setText("Chargement...");
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
            }
        } else if (tailleCreationGrille != null) {
            //creation de la grille
            int idTaille = 0;
            switch (tailleCreationGrille) {
                case "5x5":
                    idTaille = 0;
                    break;
                case "7x7":
                    idTaille = 1;
                    break;
                case "10x10":
                    idTaille = 2;
                    break;
                case "12x12":
                    idTaille = 5;
                    break;
                case "15x15":
                    idTaille = 3;
                    break;
                case "20x20":
                    idTaille = 4;
                    break;
                default:
                    messageInfo.setText("Erreur sélection taille");
                    messageInfo.setVisible(true);
                    return;
            }
            try {
                /*new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        throw new TimeoutException("Opération trop longue");
                    }
                }, 10000);*/
                manager.ouvrirNouvelleGrille(CreerDepuisPageWeb.chercherHTML(idTaille));
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                messageInfo.setText("Problème récupération web");
            }

        } else {
            messageInfo.setText("Sélection incomplète");
        }

        bouttonLancer.setDisable(true);
        bouttonLancer.setText("Lancer");
        messageInfo.setVisible(true);
    }

    public void setManager(Handler manager) {
        this.manager = manager;
    }

}
