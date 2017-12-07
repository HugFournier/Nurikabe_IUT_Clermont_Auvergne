package nurikabe.jeu.assets.cellule;

public class NonJouable extends Cellule {
	
	private static final long serialVersionUID = 2920309031260971068L;
	
	private int valeur;
	
	public NonJouable( int valeur) {
		super( Etat.BLANC);
		this.valeur = valeur;
	}

	@Override
	public NonJouable clone(){
		return new NonJouable( valeur);
	}

	
	public int getValeur( ) {
		return valeur;
	}
	
	@Override
	public boolean setEtat( Etat etat) {
		return false;
	}
	
	@Override
	public String toString() {
		return "" + getValeur();
	}

	@Override
	public boolean equals( Object o){
		if (o instanceof NonJouable)
			return equals( (NonJouable) o);
		return false;
	}

	public boolean equals( NonJouable c){
		if (!super.equals( c))
			return false;
		return getValeur() == c.getValeur();
	}
}
