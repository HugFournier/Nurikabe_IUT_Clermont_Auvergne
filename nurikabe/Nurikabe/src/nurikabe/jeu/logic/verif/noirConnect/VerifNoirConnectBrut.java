package nurikabe.jeu.logic.verif.noirConnect;

import java.util.ArrayList;
import java.util.List;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;
import util.Position;

public class VerifNoirConnectBrut implements Verif{

	List<Position> todo;
	
	
	@Override
	public boolean verification( Grille grille) {
		todo = new ArrayList<Position>();
		int width = grille.getWidth(), height = grille.getHeight();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (Etat.NOIR == grille.getEtat( x, y))
					todo.add( new Position( x, y));
		if (todo.size() <= 0)
			return true;
		check( todo.get( 0));
		return (todo.size() <= 0);
	}
	
	private void check( Position base) {
		if (todo.size() <= 0)
			return;
		if (!todo.remove( base))
			return;
		check( base.substraction( new Position(  1,  0)));
		check( base.substraction( new Position( -1,  0)));
		check( base.substraction( new Position(  0,  1)));
		check( base.substraction( new Position(  0, -1)));
	}

}
