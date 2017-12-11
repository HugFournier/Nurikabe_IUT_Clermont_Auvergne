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
    Grille grille;
    @FXML
    ListView listeSaves;
    ObservableList observableListeSaves = FXCollections.observableArrayList();
    Handler manager;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<String> lines = SaveHandler.getSaveHandler().getFiles();
        observableListeSaves.setAll(lines);
        listeSaves.setItems(observableListeSaves);
    }
    
    @FXML
    public void cheminClic(){
        String path = (String) listeSaves.getSelectionModel().getSelectedItem();
        if(path!=null){
            manager.charger(path);
            grille.initGrille(manager.getJeu().getGrille());
        }
    }
    
    public void setManager(Handler manager){
        this.manager=manager;
    }
    
    public void setGrille(Grille grille){
        this.grille=grille;
    }
}
