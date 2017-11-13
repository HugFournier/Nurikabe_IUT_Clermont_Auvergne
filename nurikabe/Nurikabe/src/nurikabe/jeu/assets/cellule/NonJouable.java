package nurikabe.jeu.assets.cellule;

public class NonJouable extends Cellule {
	
	private static final long serialVersionUID = 2920309031260971068L;
	
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
<<<<<<< HEAD

        @Override
        public String toString() {
            return "" + getValeur();
        }
        	
=======
	
	@Override
	public String toString() {
		return "" + getValeur();
	}
	
>>>>>>> 276406a5557426ad516f450c4e903a46e0bdbc8e
}
