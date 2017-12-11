/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nurikabe.affichage.Case;
import nurikabe.affichage.Console;
import nurikabe.affichage.Grille;
import nurikabe.affichage.events.caseClickedEvent;
import nurikabe.jeu.assets.Chronometre;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.ia.IANew;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale {

    private nurikabe.jeu.assets.Grille grilleCorrect = null;

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
        grilleCorrect = null;
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
        grilleCorrect = null;
    }

    @FXML
    public void onStart() {
        c.start();
        pause.setVisible(true);
        start.setVisible(false);
        grille.setVisible(true);
        grilleCorrect = null;
    }

    @FXML
    public void onPause() {
        c.pause();
        pause.setVisible(false);
        start.setVisible(true);
        grille.setVisible(false);
        grilleCorrect = null;
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
        grilleCorrect = null;
    }

    final EventHandler<caseClickedEvent> clicSurCase = new EventHandler<caseClickedEvent>() {
        @Override
        public void handle(caseClickedEvent event) {
            if (!event.isSenderType()) {
                manager.jouer(event.getColone(),event.getLigne());
            }
        }
    };
    
    @FXML
    public void onVerif() {
        if(manager.getJeu().verfication()) {message.setText("La grille est juste. Vous avez gagné.");}
        else {message.setText("La grille est fausse.");}
    }

    @FXML
    public void onAide() {
        if (manager == null || manager.getJeu() == null || manager.getJeu().getGrille() == null)
            return;
        if (grilleCorrect == null)
            grilleCorrect = (new IANew()).resoudre(manager.getJeu().getGrille());
        for (int x = 0; x < grilleCorrect.getWidth(); x++)
            for (int y = 0; y < grilleCorrect.getHeight(); y++)
                if (grilleCorrect.getEtat( x, y) == Etat.BLANC && manager.getJeu().getEtat( x, y) != Etat.BLANC){
                    manager.getJeu().getGrille().setEtat( x, y, Etat.BLANC);
                    Case c = grille.getCase( x, y);
                    if (c != null)
                        c.setFill( Case.couleurBlanc);
                    return;
                }
                else if (grilleCorrect.getEtat( x, y) == Etat.NOIR && manager.getJeu().getEtat( x, y) != Etat.NOIR){
                    manager.getJeu().getGrille().setEtat( x, y, Etat.NOIR);
                    Case c = grille.getCase( x, y);
                    if (c != null)
                        c.setFill( Case.couleurNoir);
                    return;
                }
    }
}
