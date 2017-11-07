package nurikabe.jeu.assets.cellule;

public class NonJouable extends Cellule {
	
	private int valeur;
	
	public NonJouable( int valeur) {
		super( Etat.BLANC);
		this.valeur = valeur;
	}

	
	public int getValeur( ) {
		return valeur;
	}
	
	@Override
	public boolean setEtat( Etat etat) {
		return false;
	}
	
}
