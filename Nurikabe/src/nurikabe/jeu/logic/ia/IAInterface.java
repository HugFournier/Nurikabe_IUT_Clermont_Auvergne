package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

public interface IAInterface extends Runnable{

    void resoudre(Grille grille);
    boolean isSolved();
    Grille getGrille();
    void stop();

}
