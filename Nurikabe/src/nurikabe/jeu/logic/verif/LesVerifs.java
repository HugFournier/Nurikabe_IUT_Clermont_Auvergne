package nurikabe.jeu.logic.verif;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.logic.verif.blancChiffre.VerifBlancChiffreBrut;
import nurikabe.jeu.logic.verif.blancConnect.VerifBlancConnectBrut;
import nurikabe.jeu.logic.verif.noirConnect.VerifNoirConnectBrut;

import java.util.ArrayList;
import java.util.List;

public class LesVerifs {

    private List<Verif> regles = new ArrayList<Verif>();

    public LesVerifs( ){
        regles.add( new VerifBlancChiffreBrut());
        regles.add( new VerifBlancConnectBrut());
        regles.add( new VerifNoirConnectBrut());
    }

    public boolean verification( Grille grille){
        for (Verif verif : regles)
            if (verif.verification( grille) == false)
                return false;
        return true;
    }
}
