package nurikabe.jeu.assets.cellule;

public abstract class Cellule {
	
	private Etat etat;
	
	protected Cellule( ) {
		this( Etat.VIDE);
	}
	
	protected Cellule( Etat etat) {
		this.etat = etat;
	}
	
	public Etat getEtat( ) {
		return etat;
	}
	
	public boolean setEtat( Etat etat) {
		this.etat = etat;
		return true;
	}
	
	public boolean cycleEtat( ) {
		if (etat == Etat.VIDE)
			return setEtat(Etat.NOIR);
		if (etat == Etat.NOIR)
			return setEtat(Etat.BLANC);
		return setEtat(Etat.VIDE);
	}
}
