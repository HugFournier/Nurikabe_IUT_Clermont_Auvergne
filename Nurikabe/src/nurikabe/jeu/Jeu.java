package nurikabe.jeu;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.PositionAvecEtat;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.generateur.Chargeur;
import nurikabe.jeu.logic.generateur.Enregistreur;
import nurikabe.jeu.logic.generateur.Generateur;
import nurikabe.jeu.logic.ia.IAInterface;
import nurikabe.jeu.logic.ia.IANew;
import nurikabe.jeu.logic.verif.LesVerifs;
import util.Position;

public class Jeu {

	private Grille grille;
	private LesVerifs verifs = new LesVerifs();
	private IAInterface ia = new IANew();
	
	public Jeu( int width, int height, Generateur generateur) {
	    grille = new Grille( width, height, generateur);
	    ia.resoudre( grille);
	}

	public Jeu(Chargeur chargeur, String path){
		grille = new Grille( chargeur.charger( path));
        ia.resoudre( grille);
	}
        
    public Jeu(Grille grille){
		this.grille = grille.clone();
        ia.resoudre( this.grille);
	}

	public void stopCalcule(){
        if (!ia.isSolved())
            ia.stop();
    }

    public PositionAvecEtat demandeAide() throws Exception {
	    if (!ia.isSolved())
	        throw new Exception( "Pas de solution calcule");
	    else{
	        Grille correct = ia.getGrille();
	        for (int x = 0; x < getWidth(); x++)
	            for (int y = 0; y < getHeight(); y++)
	                if (getEtat(x, y) != Etat.BLANC && correct.getEtat(x, y) == Etat.BLANC)
	                    return new PositionAvecEtat( new Position( x, y), Etat.BLANC);
	                else if (getEtat( x, y) != Etat.NOIR && correct.getEtat( x, y) == Etat.NOIR)
	                    return new PositionAvecEtat( new Position( x, y), Etat.NOIR);
        }
        return null;
    }
	
	public boolean verfication() {
		return verifs.verification( grille);
	}

	// PER
	public void enregistrer( Enregistreur enregistreur, String path){
		enregistreur.enregistrer( grille, path);
	}
	
	// GRILLE
	public int getHeight() {
		if (grille != null)
			return grille.getHeight();
		return 0;
	}

	public int getWidth() {
		if (grille != null)
			return grille.getWidth();
		return 0;
	}
	
	public Etat getEtat( int x, int y) {
		if (grille != null)
			return grille.getEtat( x, y);
		return null;
	}

	public boolean isJouable(int x, int y) {
		if (grille != null)
			return grille.isJouable(x, y);
		return false;
	}

	public int valeur(int x, int y) {
		if (grille != null)
			return grille.getValeur( x, y);
		return -1;
	}
	
	private boolean cycleEtat( int x, int y) {
		if (grille != null)
			return grille.cycleEtat( x, y);
		return false;
	}
	
	public void mettreVideEnBlanc( ) {
		int width = getWidth();
		int height = getHeight();
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (grille.getEtat( x, y) == Etat.VIDE)
					grille.setEtat( x, y, Etat.BLANC);
	}
	
	public void jouer( int x, int y) {
		cycleEtat( x, y);
	}

        @Override
        public String toString() {
            return  grille.toString();
        }

    public Grille getGrille() {
        return this.grille; 
    }
	
        
}
