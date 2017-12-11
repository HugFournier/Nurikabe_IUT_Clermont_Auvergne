package nurikabe.jeu.logic.ia;

import nurikabe.affichage.Affichage;
import nurikabe.affichage.Console;
import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.logic.Handler;

import java.util.LinkedList;
import java.util.List;

public class IAForceBrut implements IAInterface{

    private List<IAalgo> algos = new LinkedList<IAalgo>();
    private IAalgo brut = new IAalgo0();

    public IAForceBrut( ){

        //algos.add( new IAalgo1());
        //algos.add( new IAalgo2());
        //algos.add( new IAalgo3());
        //algos.add( new IAalgo4());
        //algos.add( new IAalgo5());
        //algos.add( new IAalgo6());
        algos.add( new IABlancs());
    }

    @Override
    public Grille resoudre( Grille grille) {
        IAGrille laGrille = new IAGrille( grille);
        IAGrille tmp;
        Handler handler = new Handler();
        Console c = new Console();
        boolean running = true;

        while (running){
            running = false;
            for (IAalgo algo : algos){
                tmp = laGrille.clone();
                laGrille = algo.resoudre( laGrille);
                if (!tmp.equals( laGrille))
                    running = true;
                for (int x = 0; x < grille.getWidth(); x++)
                    for( int y = 0; y < grille.getHeight(); y++)
                        handler.getJeu().getGrille().setEtat(x, y, laGrille.getGrille().getEtat( x, y));
                c.afficher( handler);
            }
        }
        return laGrille.getGrille();
    }
}
