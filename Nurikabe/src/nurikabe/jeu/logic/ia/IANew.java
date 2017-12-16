package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

public class IANew implements IAInterface{

    private static final int DEPT = 10;

    private IABrutThread ia = null;

    public IANew( ){

    }

    @Override
    public void resoudre( Grille grille) {
        ia = new IABrutThread( DEPT, new IAGrille( grille));
        ia.go();
    }

    @Override
    public boolean isSolved() {
        if (ia == null)
            return false;
        return ia.isSolved();
    }

    @Override
    public Grille getGrille( ) {
        if (ia != null){
            return ia.getGrille();
        }
        return null;
    }


    public void stop(){
        if (ia != null)
            ia.stop();
    }
}
