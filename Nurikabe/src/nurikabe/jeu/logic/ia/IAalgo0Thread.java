package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.LesVerifs;

import java.util.ArrayList;
import java.util.List;

class IAalgo0Thread implements Runnable{

    private List<Boolean> booleanList = new ArrayList<Boolean>();
    private List<Boolean> end = new ArrayList<Boolean>();
    private IAGrille laGrille;
    private boolean solved = false;

    private LesVerifs verifs;

    protected IAalgo0Thread(List<Boolean> begin, List<Boolean> end, IAGrille grille){
        verifs = new LesVerifs();
        for (Boolean bool : begin)
            booleanList.add( bool);
        for (Boolean bool : end)
            this.end.add( bool);
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
            if (!next())
                return false;
        }
        return true;
    }

    private boolean next( ){
        int i = 0;
        booleanList.set(i, !booleanList.get(i));
        while (!booleanList.get(i)){
            i++;
            if (i >= booleanList.size())
                return false;
            booleanList.set( i, !booleanList.get(i));
        }
        for (i = 0; i < booleanList.size(); i++)
            if (!booleanList.get(i).equals( end.get(i)))
                return true;
        return false;
    }

    @Override
    public void run() {
        solved = resoudre();
    }
}
