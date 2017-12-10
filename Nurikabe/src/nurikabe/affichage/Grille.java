/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import nurikabe.affichage.events.caseClickedEvent;

/**
 *
 * @author sylat
 */
public class Grille extends GridPane {

    private final NumberBinding taille = Bindings.min(widthProperty(), heightProperty());

    public void initGrille(nurikabe.jeu.assets.Grille entree) {
        //System.out.println(entree.getWidth()+" dans init");
        this.getColumnConstraints().clear();
        this.getRowConstraints().clear();
        this.getChildren().clear();

        int col = entree.getWidth();
        int row = entree.getHeight();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (!entree.isJouable(i, j)) {
                    ajouterCase(i,j,Color.WHITE,entree.getValeur(i, j),col,row);
                } else {
                    switch (entree.getEtat(i, j)) {
                        case BLANC:
                            ajouterCase(i,j,Color.WHITE,0,col,row);
                            break;
                        case NOIR:
                            ajouterCase(i,j,Color.BLACK,0,col,row);
                            break;
                        default:
                            ajouterCase(i,j,Color.LIGHTGREY,0,col,row);
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < col; i++) {
            //ajouter une contrainte de colone avec les tailles min, pref et max ainsi que le comportement horizontal : croissance, position et remplissage
            getColumnConstraints().add(new ColumnConstraints(5, taille.doubleValue(), Double.POSITIVE_INFINITY, Priority.SOMETIMES, HPos.CENTER, false));
        }
        for (int i = 0; i < row; i++) {
            getRowConstraints().add(new RowConstraints(5, taille.doubleValue(), Double.POSITIVE_INFINITY, Priority.SOMETIMES, VPos.CENTER, false));
        }

        setPadding(new Insets(20, 20, 20, 20));
        prefWidthProperty().bind(taille);
        prefHeightProperty().bind(taille);
        setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        //setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        
        //definition du clic
        addEventHandler(caseClickedEvent.CASE_CLICKED_AVEC_SENDER, new EventHandler<caseClickedEvent>() {
            @Override
            public void handle(caseClickedEvent event) {
                System.out.println("Event reçu dans grille");
                if (event.isSenderType()){
                    caseClickedEvent test = new caseClickedEvent(getColumnIndex(event.getSender()), getRowIndex(event.getSender()));
                    fireEvent(test);
                    event.consume(); //destruction de event
                }
            }
        }          
        );
    }

    private void ajouterCase(int colone, int ligne, Paint couleur, int valeur, int totalCol, int totalLig) {
        Case nouvelle = new Case(valeur, couleur);
        nouvelle.prefWidthProperty().bind(widthProperty().divide(totalCol));
        nouvelle.prefHeightProperty().bind(heightProperty().divide(totalLig));
        this.add(nouvelle, ligne, colone);
    }

}
