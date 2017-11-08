package nurikabe.jeu.logic.generateur;

import nurikabe.jeu.assets.cellule.Cellule;
import nurikabe.jeu.assets.cellule.Jouable;
import nurikabe.jeu.assets.cellule.NonJouable;
import util.Matrix;

public class HardCoded7by7 extends Generateur{

	@Override
	public Matrix<Cellule> createMatrix( int width, int height) {
		Matrix<Cellule> grid = new Matrix<Cellule>( 7, 7);
		
		grid.set( 0,  0, new NonJouable( 2));
		grid.set( 2,  0, new NonJouable( 4));
		grid.set( 6,  0, new NonJouable( 4));
		grid.set( 1,  2, new NonJouable( 4));
		grid.set( 3,  2, new NonJouable( 1));
		grid.set( 4,  3, new NonJouable( 4));
		grid.set( 0,  6, new NonJouable( 3));
		
		
		for (int x = 0; x < 7; x++)
			for (int y = 0; y < 7; y++)
				if (grid.get( x, y) == null)
					grid.set( x, y, new Jouable());
		
		return grid;
	}

}
