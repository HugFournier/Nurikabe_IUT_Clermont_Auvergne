package nurikabe.jeu.assets.cellule;

import util.PublicCloneable;

import java.io.Serializable;

public abstract class Cellule implements Serializable, PublicCloneable{
	
	private static final long serialVersionUID = 5897004859064209151L;
	
	private Etat etat;
	
	protected Cellule(Etat etat) {
		this.etat = etat;
	}
	
	public Etat getEtat( ) {
		return etat;
	}

	public Cellule clone(){
		return null;
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

	@Override
	public boolean equals( Object o){
		if (o instanceof Cellule)
			return equals( (Cellule) o);
		return false;
	}

	public boolean equals( Cellule c){
		if (c.getEtat() != getEtat())
			return false;
		return true;
	}
}
