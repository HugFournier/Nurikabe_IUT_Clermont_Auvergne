package nurikabe.jeu.assets;

import nurikabe.jeu.assets.cellule.Etat;
import util.Position;

public class PositionAvecEtat {

    private Position position;
    private Etat etat;

    public PositionAvecEtat( Position position, Etat etat){
        this.position = position;
        this.etat = etat;
    }

    public Position getPosition() {
        return position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public Etat getEtat(){
        return etat;
    }
}
