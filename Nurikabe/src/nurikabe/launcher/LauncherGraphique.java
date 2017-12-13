/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.launcher;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nurikabe.affichage.controller.ControllerFenetrePrincipale;
import nurikabe.jeu.logic.generateur.CreerDepuisPageWeb;

/**
 *
 * @author argiraud
 */
public class LauncherGraphique extends Application {

    ControllerFenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage fPrincipale) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nurikabe/affichage/ihm/FenetrePrincipalev2.fxml"));
        fPrincipale.setScene(new Scene(fxmlLoader.load()));
        fenetrePrincipale = fxmlLoader.getController();
        //fPrincipale.setScene(new Scene(fxmlLoader.load()));
        fPrincipale.centerOnScreen();
        fPrincipale.setTitle("Nurikabe");
        fPrincipale.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        if (fenetrePrincipale.getManager().isPartieEnCours()) {
            fenetrePrincipale.onSauvegarde();
        }
        System.exit(0);
        System.out.println("hello");
    }

}
