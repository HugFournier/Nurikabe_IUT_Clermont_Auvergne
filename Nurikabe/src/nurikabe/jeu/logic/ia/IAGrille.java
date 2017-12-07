package nurikabe.jeu.logic.ia;

import nurikabe.jeu.assets.Grille;
import util.PublicCloneable;

class IAGrille implements PublicCloneable{

    private Grille grille;
    private boolean forced[][];

    protected IAGrille( Grille grille){
        this.grille = grille.clone();
        forced = new boolean[grille.getWidth()][grille.getHeight()];
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                if (grille.isJouable(x, y))
                    forced[x][y] = false;
                else
                    forced[x][y] = true;
    }

    protected IAGrille( IAGrille grille){
        this.grille = grille.grille.clone();
        forced = new boolean[grille.getWidth()][grille.getHeight()];
        for (int x = 0; x < grille.getWidth(); x++)
            for (int y = 0; y < grille.getHeight(); y++)
                forced[x][y] = grille.isForced(x, y);
    }

    @Override
    public IAGrille clone(){
        return new IAGrille( this);
    }

    public boolean isForced( int x, int y){
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return true;
        return forced[x][y];
    }

    public void setForced( int x, int y){
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return;
        forced[x][y] = !forced[x][y];
    }

    public void setForced( int x, int y, boolean force){
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return;
        forced[x][y] = force;
    }

    protected Grille getGrille( ){
        return grille;
    }

    public int getHeight(){
        return grille.getHeight();
    }

    public int getWidth(){
        return grille.getWidth();
    }

    @Override
    public boolean equals( Object o){
        if (o instanceof IAGrille)
            return equals( (IAGrille) o);
        return false;
    }

    public boolean equals( IAGrille grille){
        if (getHeight() != grille.getHeight() || getWidth() != grille.getWidth())
            return false;
        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++)
                if (isForced( x, y) != grille.isForced( x, y))
                    return false;
        return this.grille.equals( grille.getGrille());
    }

}
