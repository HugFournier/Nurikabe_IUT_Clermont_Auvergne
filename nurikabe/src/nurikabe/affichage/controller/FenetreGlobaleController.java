/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.generateur.SaveHandler;

/**
 * FXML Controller class
 *
 * @author fourn
 */
public class FenetreGlobaleController {

    private Handler manager = new Handler();
    @FXML
    private ListView listeSaves;
    ObservableList observableListeSaves = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }
    
    @FXML
    public void afficherListeSaves(){
        List<String> lines = SaveHandler.getSaveHandler().getFiles();
        observableListeSaves.setAll(lines);
        listeSaves.setItems(observableListeSaves);
        //grille.initGrille(manager.getJeu().getGrille());
    }
    
}
