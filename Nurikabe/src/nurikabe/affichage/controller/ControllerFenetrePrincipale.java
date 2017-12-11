/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nurikabe.affichage.Console;
import nurikabe.affichage.Grille;
import nurikabe.affichage.events.caseClickedEvent;
import nurikabe.jeu.assets.Chronometre;
import nurikabe.jeu.logic.Handler;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {

    @FXML
    BorderPane root;
    @FXML
    Grille grille;
    @FXML
    Button pause;
    @FXML
    Button start;
    @FXML
    Label chrono;
    @FXML
    Label message;

    Chronometre c;
    private Handler manager = new Handler();
    private FenetreSauvegardeController controllerSaves;

    /**
     * Get the value of manager
     *
     * @return the value of manager
     */
    public Handler getManager() {
        return manager;
    }

    /**
     * Set the value of manager
     *
     * @param manager new value of manager
     */
    public void setManager(Handler manager) {
        this.manager = manager;
    }

    @FXML
    public void onPalmares() throws IOException {
        Stage palmares = new Stage();
        Parent digit = FXMLLoader.load(getClass().getResource("/nurikabe/affichage/ihm/FenetrePalmares.fxml"));
        palmares.setScene(new Scene(digit));
        palmares.centerOnScreen();
        palmares.show();
    }

    @FXML
    public void onNPartie() {
        grille.initGrille(manager.getJeu().getGrille());
        c.setTime(manager.getJeu().getGrille().getChrono());
        c.start();
        pause.setDisable(false);
        message.setText(null);
    }

    @FXML
    public void onCharger() throws IOException {
        Stage fSauvegarde = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetreSauvegarde.fxml"));
        fSauvegarde.setScene(new Scene((Parent) fxmlLoader.load()));
        controllerSaves = fxmlLoader.getController();
        controllerSaves.setManager(manager);
        controllerSaves.setGrille(grille);
        fSauvegarde.centerOnScreen();
        fSauvegarde.show();
    }

    @FXML
    public void onStart() {
        c.start();
        pause.setVisible(true);
        start.setVisible(false);
        grille.setVisible(true);
    }

    @FXML
    public void onPause() {
        c.pause();
        pause.setVisible(false);
        start.setVisible(true);
        grille.setVisible(false);
    }
    //Méthode utilisée par lorsque que le bouton Quitter est utilisé

    @FXML
    public void onExit() {
        c.pause();
        Platform.exit();
    }

    @FXML
    public void initialize() {
        c = new Chronometre(0);
        c.setLabel(chrono);
        pause.setDisable(true);
        start.setVisible(false);
        grille.setVisible(true);
        c.setLabel(chrono);
        
        grille.addEventHandler(caseClickedEvent.CASE_CLICKED_AVEC_POSITION, clicSurCase);
    }

    final EventHandler<caseClickedEvent> clicSurCase = new EventHandler<caseClickedEvent>() {
        @Override
        public void handle(caseClickedEvent event) {
            if (!event.isSenderType()) {
                manager.jouer(event.getColone(),event.getLigne());
                new Console().afficher(manager);
            }
        }
    };
    
    @FXML
    public void onVerif() {
        new Console().afficher(manager);
        if(manager.getJeu().verfication()) {message.setText("La grille est juste. Vous avez gagné.");}
        else {message.setText("La grille est fausse.");}
    }

}
