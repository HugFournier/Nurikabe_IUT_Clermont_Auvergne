package nurikabe.jeu.logic;

import nurikabe.affichage.Affichage;
import nurikabe.jeu.Jeu;
import nurikabe.jeu.logic.generateur.Enregistreur;

public class Handler {

    private static final String path = System.getProperty( "user.dir").replace( "\\", "/");

    private String defaultPath = path + "/res/grilles/test.bin";

    private Enregistreur chargeur, enregistreur;
    private Jeu jeu;
    private Affichage affichage;

    public Handler( Enregistreur chargeur, Enregistreur enregistreur, Jeu jeu, Affichage affichage){
        this.chargeur = chargeur;
        this.enregistreur = enregistreur;
        this.jeu = jeu;
        this.affichage = affichage;
    }

    public void enregistrer( ){
        enregistrer( defaultPath);
    }

    public void enregistrer( String path){
        jeu.enregistrer( enregistreur, path);
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

    public Enregistreur getChargeur() {
        return chargeur;
    }

    public Jeu getJeu() {
        return jeu;
    }
}
