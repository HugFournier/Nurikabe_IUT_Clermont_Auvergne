package nurikabe.jeu.logic;

import nurikabe.affichage.Affichage;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.FromBinaryFile;
import nurikabe.jeu.logic.generateur.Enregistreur;
import nurikabe.jeu.logic.generateur.FromTextFile;

import java.util.ArrayList;
import java.util.List;

public class Handler {

    private static final String binaryFileExtention = ".nuribin", textFileExtention = ".nuritxt";
    private static final String path = System.getProperty( "user.dir").replace( "\\", "/");

    private String defaultPath = path + "/res/grilles/test.bin";

    private List<Enregistreur> enregistreurs;
    private Jeu jeu;
    //private Affichage affichage;

    public Handler( ){
        initEnregistreurs();
        this.jeu = new Jeu(chargeur, defaultPath);
        //this.affichage = affichage;

    }

    private void initEnregistreurs(){
        enregistreurs = new ArrayList<Enregistreur>();
        enregistreurs.add( new FromTextFile());
        enregistreurs.add( new FromBinaryFile());
    }

    private Enregistreur getEnregistreurTypeSpecifique( String type){
        switch (type){
            case binaryFileExtention :
                return enregistreurs.get(1);
            case textFileExtention :
                return enregistreurs.get(2);
            default:
                return null;
        }


    }

    public void enregistrer( ){
        enregistrer( defaultPath);
    }

    public void enregistrer( String path){
        if (path.endsWith( binaryFileExtention))
        jeu.enregistrer( getEnregistreurTypeSpecifique( binaryFileExtention), path);
        else
            jeu.enregistrer( getEnregistreurTypeSpecifique( textFileExtention), path);

    }

    public void charger( ){
        charger( defaultPath);
    }

    public void charger( String path){
        jeu = new Jeu( chargeur, path);
    }

    public void jouer( int x, int y) {
        jeu.jouer( x, y);
    }

    public Jeu getJeu() {
        return jeu;
    }
}
