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
import javafx.scene.control.TabPane;
import nurikabe.affichage.Grille;
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
    private Grille grille;
    @FXML
    private TabPane onglets;
    @FXML
    private ListView listeSaves;
    ObservableList observableListeSaves = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        onglets.getSelectionModel().select(1);
        // TODO
    }
    
    @FXML
    public void afficherListeSaves(){
        List<String> lines = SaveHandler.getSaveHandler().getFiles();
        observableListeSaves.setAll(lines);
        listeSaves.setItems(observableListeSaves);
    }
    
    @FXML
    public void cheminClic(){
        String path = (String) listeSaves.getSelectionModel().getSelectedItem();
        onglets.getSelectionModel().select(0);
        //System.out.println(path);
        manager.charger(path);
        manager.getJeu().getGrille().getChrono().lancer();
        grille.initGrille(manager.getJeu().getGrille());
        //System.out.println(manager.getJeu().getGrille());
    }
    
}
