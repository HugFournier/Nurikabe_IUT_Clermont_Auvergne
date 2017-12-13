/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.assets.cellule;

import nurikabe.jeu.assets.Grille;
import util.Matrix;

/**
 *
 * @author fourn
 */
public class GrilleNommee extends Grille {
    
    private String id;
    
    public GrilleNommee(Matrix<Cellule> grille, String Id) {
        super(grille);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
