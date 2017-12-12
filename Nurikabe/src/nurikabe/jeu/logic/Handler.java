package nurikabe.jeu.logic;

import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.*;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.Event;
import nurikabe.affichage.controller.ControllerFenetrePrincipale;
import nurikabe.jeu.logic.palmares.Palmares;
import nurikabe.jeu.logic.palmares.PalmaresHandler;

public class Handler {

    private static final String path = System.getProperty( "user.dir").replace( "\\", "/");

    private String defaultPath = path + "/res/grilles/test.nuribin";
    private List<IGrilleHandlerObserveur> listeObserveur = new ArrayList<IGrilleHandlerObserveur>();
    private List<Enregistreur> enregistreurs;
    private List<Chargeur> chargeurs;
    private Jeu jeu;
    private String cheminDeSauvegarde = null;
    private PalmaresHandler palmaresHandler = new PalmaresHandler();
    Boolean partieEnCours = false;

    public Handler( ){
        initEnregistreursEtChargeurs();
    }

    private void initEnregistreursEtChargeurs(){
        enregistreurs = new ArrayList<Enregistreur>();
        chargeurs = new ArrayList<Chargeur>();
        enregistreurs.add( new EnregistrerDansFichierBinaire());
        chargeurs.add( new ChargerDepuisFichierBinaire());
        enregistreurs.add( new EnregisterDansFichierTexte());
        chargeurs.add( new ChargerDepuisFichierTexte());

    }
    
    public void ajouterAuPalmares(String id, int taille, long temps){
        palmaresHandler.addFile(id, taille, temps);
    }
    
    public ObservableList<Palmares> getListePalmares(){
        return palmaresHandler.getListePalmares();
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
        enregistrer(getCheminDeSauvegarde() == null ? defaultPath : getCheminDeSauvegarde());
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
        String[] arr = path.split( "\\.");
        String extention = "." + arr[arr.length-1];
        jeu = new Jeu( getChargeurTypeSpecifique( extention), path);
        setCheminDeSauvegarde(path);
        //notifier du changement de la grille
        partieEnCours = true;
        for(IGrilleHandlerObserveur obs : listeObserveur){
            obs.onNPartie();
        }
    }

    public void jouer( int x, int y) {
        jeu.jouer( x, y);
    }

    public Jeu getJeu() {
        return jeu;
    }
    
    public Boolean isPartieEnCours(){
        return partieEnCours;
    }

    public void ajouterObservateur(IGrilleHandlerObserveur controller) {
        listeObserveur.add(controller);
    }
    
    public String getCheminDeSauvegarde(){
        return cheminDeSauvegarde;
    }
    
    public void setCheminDeSauvegarde(String chemin){
        cheminDeSauvegarde=chemin;
    }
}
