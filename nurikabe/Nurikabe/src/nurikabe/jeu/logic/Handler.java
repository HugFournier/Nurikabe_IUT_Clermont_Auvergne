package nurikabe.jeu.logic;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.*;

import java.util.ArrayList;
import java.util.List;

public class Handler {

    private static final String path = System.getProperty( "user.dir").replace( "\\", "/");

    private String defaultPath = path + "/res/grilles/test.nuribin";

    private List<Enregistreur> enregistreurs;
    private List<Chargeur> chargeurs;
    private Jeu jeu;

    public Handler( ){
        initEnregistreursEtChargeurs();
        jeu = new Jeu( 7, 7, new HardCoded7by7());
    }

    private void initEnregistreursEtChargeurs(){
        enregistreurs = new ArrayList<Enregistreur>();
        chargeurs = new ArrayList<Chargeur>();
        enregistreurs.add( new EnregistrerDansFichierBinaire());
        chargeurs.add( new ChargerDepuisFichierBinaire());
        enregistreurs.add( new EnregisterDansFichierTexte());
        chargeurs.add( new ChargerDepuisFichierTexte());

    }

    private Enregistreur getEnregistreurTypeSpecifique( String type){
        for (Enregistreur e : enregistreurs)
            if (e.getExtentionDeFichier().equals( type))
                return e;
        return null;
    }

    private Chargeur getChargeurTypeSpecifique( String type){
        for (Chargeur c : chargeurs)
            if (c.getExtentionDeFichier().equals( type))
                return c;
        return null;
    }

    public void enregistrer( ){
        enregistrer( defaultPath);
    }

    public void enregistrer( String path){
        String[] arr = path.split("\\.");
        String extention = "." + arr[arr.length-1];
        jeu.enregistrer( getEnregistreurTypeSpecifique( extention), path);
        SaveHandler.getSaveHandler().addFile( path);
        SaveHandler.getSaveHandler().saveFiles();

    }

    public void charger( ){
        charger( defaultPath);
    }

    public void charger( String path){
        String[] arr = path.split( ".");
        String extention = "." + arr[arr.length-1];
        jeu = new Jeu( getChargeurTypeSpecifique( extention), path);
    }

    public void jouer( int x, int y) {
        jeu.jouer( x, y);
    }

    public Jeu getJeu() {
        return jeu;
    }
}
