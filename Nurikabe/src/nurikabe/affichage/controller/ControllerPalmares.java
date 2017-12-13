/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.palmares.Palmares;

/**
 *
 * @author argiraud
 */
public class ControllerPalmares {
    
    @FXML
    private TableColumn idCol, tailleCol, tempsCol;
    @FXML
    private TableView tablePalmares;
    
        
    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<Palmares,String>("id"));
        tailleCol.setCellValueFactory(new PropertyValueFactory<Palmares,Integer>("taille"));
        tempsCol.setCellValueFactory(new PropertyValueFactory<Palmares,Long>("temps"));
    }
    
    @FXML
    public void onExit(Event event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setHandler(Handler h){
        tablePalmares.setItems(h.getListePalmares());
    }
}
