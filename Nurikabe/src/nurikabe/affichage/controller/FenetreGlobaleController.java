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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import nurikabe.affichage.Grille;
import nurikabe.jeu.assets.Chronometre;
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
    @FXML
    private Label chrono;
    ObservableList observableListeSaves = FXCollections.observableArrayList();
    private Chronometre c;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        onglets.getSelectionModel().select(1);
        c=new Chronometre(0); 
        c.setLabel(chrono);
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
        if(path!=null){
            onglets.getSelectionModel().select(0);
            manager.charger(path);
            c.setTime(manager.getJeu().getGrille().getChrono());
            grille.initGrille(manager.getJeu().getGrille());
            c.start();
        }
    }
    
}
