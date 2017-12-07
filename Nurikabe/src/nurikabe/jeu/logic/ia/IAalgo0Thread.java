package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.LesVerifs;

import java.util.ArrayList;
import java.util.List;

class IAalgo0Thread implements Runnable{

    private List<Boolean> booleanList = new ArrayList<Boolean>();
    private int nb;
    private IAGrille laGrille;
    private boolean solved = false;

    private LesVerifs verifs;

    protected IAalgo0Thread(List<Boolean> begin, int nb, IAGrille grille){
        verifs = new LesVerifs();
        for (Boolean bool : begin)
            booleanList.add( bool);
        this.nb = nb;
        laGrille = grille.clone();
    }

    public IAGrille getGrille() {
        return laGrille;
    }

    public boolean isSolved( ){
        return solved;
    }

    public boolean resoudre() {
        int width = laGrille.getWidth();
        int height = laGrille.getHeight();
        while (!verifs.verification(laGrille.getGrille())){
            for (int i = 0; i < booleanList.size(); i++)
                if (!laGrille.isForced(i%width, i/height))
                    if (booleanList.get(0))
                        laGrille.getGrille().setEtat(i%width, i/height, Etat.BLANC);
                    else
                        laGrille.getGrille().setEtat(i%width, i/height, Etat.NOIR);
                else
                    i = booleanList.size();
            nb--;
            if (!next())
                return false;
            if (nb < 0)
                return false;

        }
        return true;
    }

    private boolean next( ){
        int i = 0;
        boolean diff = false;
        booleanList.set(i, !booleanList.get(i));
        if (!laGrille.isForced( i%laGrille.getWidth(), i/laGrille.getWidth()))
            diff = true;
        while (!booleanList.get(i)){
            i++;
            if (i >= booleanList.size())
                return false;
            booleanList.set( i, !booleanList.get(i));
            if (!laGrille.isForced( i%laGrille.getWidth(), i/laGrille.getWidth()))
                diff = true;
        }
        if (!diff)
            return next( );
        else
            return true;
    }

    @Override
    public void run() {
        solved = resoudre();
    }
}
