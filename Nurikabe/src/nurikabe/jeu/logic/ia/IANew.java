package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;

public class IANew implements IAInterface{

    private static final int DEPT = 10;

    private IABrutThread ia = null;

    private Grille grilleC = null;

    public IANew( ){

    }

    @Override
    public void resoudre( Grille grille) {
        grilleC = null;
        ia = new IABrutThread( DEPT, new IAGrille( grille));
        ia.go();
    }

    @Override
    public boolean isSolved() {
        if (grilleC != null)
            return true;
        if (ia == null)
            return false;
        if (ia.isSolved()){
            grilleC = ia.getGrille();
            ia = null;
            return true;
        }
        return false;
    }

    @Override
    public Grille getGrille( ) {
        if (grilleC == null &&ia != null){
            grilleC = ia.getGrille();
            if (ia.isSolved())
                ia = null;
        }

        return grilleC;
    }


    public void stop(){
        if (ia != null)
            ia.stop();
    }
}
