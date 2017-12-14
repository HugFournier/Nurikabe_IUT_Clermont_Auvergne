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
import javafx.stage.Modality;
import javafx.stage.Stage;
import nurikabe.affichage.Case;
import nurikabe.affichage.Grille;
import nurikabe.affichage.events.caseClickedEvent;
import nurikabe.jeu.assets.Chronometre;
import nurikabe.jeu.assets.PositionAvecEtat;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.Handler;
import nurikabe.jeu.logic.IGrilleHandlerObserveur;
import nurikabe.jeu.logic.ia.IANew;

/**
 *
 * @author argiraud
 */
public class ControllerFenetrePrincipale implements IGrilleHandlerObserveur {

    @FXML
    BorderPane root;
    @FXML
    Grille grille;
    @FXML
    Button bouttonPause, bouttonStart, bouttonVerif, bouttonAide, bouttonSauvegarde;
    @FXML
    Label chrono;
    @FXML
    Label message;

    private boolean enPause = true;
    Chronometre c;
    private Handler manager = new Handler();
    private FenetreSauvegardeController controllerSaves;
    private FenetreCheminController controllerChemin;
    private ControllerPalmares controllerPalmares;

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
        Stage fPalmares = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetrePalmares.fxml"));
        fPalmares.setScene(new Scene((Parent) fxmlLoader.load()));
        fPalmares.setTitle("Palmarès");
        controllerPalmares = fxmlLoader.getController();
        controllerPalmares.setHandler(manager);
        fPalmares.show();
    }

    @FXML
    public void onNPartie() {
        grille.initGrille(manager.getJeu().getGrille());
        grille.setDisable(false);
        c.setTime(manager.getJeu().getGrille().getChrono());
        c.start();
        bouttonPause.setDisable(false);
        bouttonVerif.setDisable(false);
        bouttonAide.setDisable(false);
        bouttonSauvegarde.setDisable(false);
        message.setText(null);
        if (enPause) {
            onStartAndPause();
        }
    }

    @FXML
    public void onCharger() throws IOException {
        if (!enPause) {
            onStartAndPause();
        }
        if (manager.isPartieEnCours()) {
            if (manager.getCheminDeSauvegarde() == null) {
                onSauvegarde();
                return;
            }
            onSauvegarde();
        }
        Stage fSauvegarde = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetreSauvegarde.fxml"));
        fSauvegarde.setScene(new Scene((Parent) fxmlLoader.load()));
        controllerSaves = fxmlLoader.getController();
        controllerSaves.setManager(manager);
        fSauvegarde.setTitle("Charger une grille");
        fSauvegarde.show();
    }

    @FXML
    public void onStartAndPause() {
        if (!manager.isPartieEnCours()) {
            return;
        }
        if (enPause) {
            c.start();
            bouttonPause.setVisible(true);
            bouttonStart.setVisible(false);
            message.setText(null);
            grille.setVisible(true);
        } else {
            c.pause();
            bouttonPause.setVisible(false);
            bouttonStart.setVisible(true);
            grille.setVisible(false);
            message.setText("En Pause");
        }
        enPause ^= true;
    }

    //Méthode utilisée par lorsque que le bouton Quitter est utilisé
    @FXML
    public void onExit() {
        if (manager.isPartieEnCours() && !bouttonSauvegarde.isDisable()) {
            onSauvegarde();
        }
        try {
            c.pause();
        } catch (Exception e) {
        }
        Platform.exit();
    }

    @FXML
    public void initialize() {
        c = new Chronometre(0);
        c.setLabel(chrono);
        bouttonPause.setDisable(true);
        bouttonVerif.setDisable(true);
        bouttonAide.setDisable(true);
        bouttonSauvegarde.setDisable(true);
        bouttonStart.setVisible(false);
        grille.setVisible(true);
        c.setLabel(chrono);
        manager.ajouterObservateur(this);
        grille.addEventHandler(caseClickedEvent.CASE_CLICKED_AVEC_POSITION, clicSurCase);
    }

    final EventHandler<caseClickedEvent> clicSurCase = new EventHandler<caseClickedEvent>() {
        @Override
        public void handle(caseClickedEvent event) {
            if (!event.isSenderType()) {
                manager.jouer(event.getColone(), event.getLigne());
            }
        }
    };

    @FXML
    public void onVerif() {
        if (manager.getJeu().verfication()) {
            message.setText("La grille est juste. Vous avez gagné.");
            c.pause();
            bouttonPause.setDisable(true);
            bouttonVerif.setDisable(true);
            bouttonAide.setDisable(true);
            bouttonSauvegarde.setDisable(true);
            grille.setDisable(true);
            manager.PartieTerminée(c.getTime());
        } else {
            message.setText("La grille est fausse.");
        }
    }

    @FXML
    public void onAide() {
        if (manager == null || manager.getJeu() == null || manager.getJeu().getGrille() == null) {
            return;
        }
        try {
            PositionAvecEtat positionAvecEtat = manager.getJeu().demandeAide();
            if (positionAvecEtat == null) {
                return;
            }
            if (positionAvecEtat.getEtat() == Etat.BLANC) {
                grille.getCase(positionAvecEtat.getX(), positionAvecEtat.getY()).setFill(Case.COULEUR_BLANC);
            } else if (positionAvecEtat.getEtat() == Etat.NOIR) {
                grille.getCase(positionAvecEtat.getX(), positionAvecEtat.getY()).setFill(Case.COULEUR_NOIR);
            }
            manager.getJeu().getGrille().setEtat(positionAvecEtat.getX(), positionAvecEtat.getY(), positionAvecEtat.getEtat());
            message.setText("");
        } catch (Exception e) {
            message.setText("Veillez réessayer plus tard, nos patates vont le resoudre");
        }

    }

    @FXML
    public void onSauvegarde() {
        String chemin = manager.getCheminDeSauvegarde();
        manager.getJeu().getGrille().setChrono(c.getTime());
        if (chemin == null) {
            if (!enPause) {
                onStartAndPause();
            }
            //trouver une valeur a chemin en demandant
            try {
                Stage fChemin = new Stage();
                fChemin.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetreChemin.fxml"));
                fChemin.setScene(new Scene((Parent) fxmlLoader.load()));
                controllerChemin = fxmlLoader.getController();
                controllerChemin.setHandler(manager);
                fChemin.setTitle("Sauvegarder une grille");
                fChemin.showAndWait();
                return;
            } catch (Exception e) {
                return;
            }
        }
        manager.enregistrer(chemin);
    }

    @FXML
    public void onRegles() throws IOException {
        Stage fRegles = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetreReglesEtCredits.fxml"));
        fRegles.setScene(new Scene((Parent) fxmlLoader.load()));
        fRegles.setTitle("Rêgles & Crédits");
        fRegles.show();
    }
}
