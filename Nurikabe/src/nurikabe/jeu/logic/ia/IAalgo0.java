package nurikabe.jeu.logic.ia;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

class IAalgo0 implements IAalgo{

    private static final int NB_THREADS = 8;

    protected IAalgo0(){

    }

    @Override
    public IAGrille resoudre(IAGrille grille) {
        List<Boolean> begin = new ArrayList<Boolean>();
        List<IAalgo0Thread> algoThreads = new ArrayList<IAalgo0Thread>();
        List<Thread> threads = new ArrayList<Thread>();
        int nbCases = grille.getHeight() * grille.getWidth();
        int nbAFaire = 0;
        for (int x = 0; x < grille.getHeight(); x++)
            for (int y = 0; y < grille.getWidth(); y++)
                if (!grille.isForced(x, y))
                    nbAFaire++;
        for (int i = 0; i < nbCases; i++)
            begin.add( false);
        double perThread = Math.pow(2, nbAFaire)/NB_THREADS;
        double done = 0;
        for (int t = 0; t < NB_THREADS; t++){
            algoThreads.add( new IAalgo0Thread( begin, (int) perThread, grille));
            for (int i = (int)done; i < done+perThread; i++)
                next( begin, grille);
            done += perThread;
        }
        for (IAalgo0Thread algoThread : algoThreads){
            Thread thread = new Thread( algoThread);
            threads.add( thread);
            thread.start();
        }

        boolean running = true;
        while ( running){
            running = false;
            for (Thread thread : threads)
                if (thread.isAlive())
                    running = true;
        }

        for (IAalgo0Thread t : algoThreads)
            if (t.isSolved())
                return t.getGrille();
        return grille;
    }

    private void next( List<Boolean> booleanList, IAGrille grille){
        int i = 0;
        boolean diff = false;
        booleanList.set(i, !booleanList.get(i));
        if (!grille.isForced( i%grille.getWidth(), i/grille.getWidth()))
            diff = true;
        while (!booleanList.get(i)){
            i++;
            if (i >= booleanList.size())
                return;
            booleanList.set( i, !booleanList.get(i));
            if (!grille.isForced( i%grille.getWidth(), i/grille.getWidth()))
                diff = true;
        }
        if (!diff)
            next( booleanList, grille);
    }
}
