package nurikabe.jeu.assets.cellule;

public class Jouable extends Cellule {

    private static final long serialVersionUID = -7845048120477137830L;

	public Jouable() {
		this(Etat.VIDE);
	}
        
	public Jouable(Etat etat) {
		super(etat);
	}

<<<<<<< HEAD
        @Override
        public String toString() {
            switch(getEtat()){
                case VIDE :
                    return ".";
                case BLANC :
                    return "+";
                case NOIR :
                    return "¤";
                default:
                    return "?";
            }
        }
=======
	@Override
    public String toString() {
		switch(getEtat()){
        	case VIDE :
        		return ".";
        	case BLANC :
        		return "+";
            case NOIR :
                return "#";
            default:
                return "?";
		}
	}
>>>>>>> 276406a5557426ad516f450c4e903a46e0bdbc8e
	
}
