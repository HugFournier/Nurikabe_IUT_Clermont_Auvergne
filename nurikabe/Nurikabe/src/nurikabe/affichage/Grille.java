/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author sylat
 */
public class Grille extends GridPane{
        
    public void initGrille(nurikabe.jeu.assets.Grille entree){
        int col = entree.getWidth();
        int row = entree.getHeight();     
        for (int i=0 ; i<col ; i++){
            for (int j=0 ; j<row ; j++){
                if (!entree.isJouable(i, j)){
                    this.add(new Case(entree.getValeur(i, j) , Color.WHITE), i, j);
                }
                else{
                    switch(entree.getEtat(i, j)){
                        case BLANC :
                            this.add(new Case(0 , Color.WHITE), i, j);
                            break;
                        case NOIR :
                            this.add(new Case(0 , Color.BLACK), i, j);
                            break;
                        default :
                            this.add(new Case(0 , Color.LIGHTGREY), i, j);
                            break;
                    }
                }
            }
        }
        this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
}
