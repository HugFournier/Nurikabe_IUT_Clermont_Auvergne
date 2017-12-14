package nurikabe.jeu.logic.verif.blancChiffre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;
import util.Position;

public class VerifBlancChiffreBrut implements Verif{
	
	@Override
	public boolean verification( Grille grille) {
		List<Position> listBlanc = new ArrayList<Position>();
		List<Position> listChiffre = new ArrayList<Position>();
		Map<Position, Integer> valeurs = new HashMap<Position, Integer>();
		int width = grille.getWidth(), height = grille.getHeight();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (Etat.BLANC == grille.getEtat( x, y) || Etat.VIDE == grille.getEtat( x, y)) {
					listBlanc.add( new Position( x, y));
					int v = grille.getValeur( x, y);
					if (v > 0) {
						listChiffre.add( new Position( x, y));
						valeurs.put( new Position( x, y), v);
					}
				}
		while (listChiffre.size() > 0) {
			Position p = listChiffre.remove( 0);
			int v = valeurs.get( p);
			if (v != nbBlanc( listBlanc, p))
				return false;
		}
		if (listBlanc.size() > 0)
			return false;
		return true;
	}
	
	private int nbBlanc( List<Position> list, Position position) {
		if (list.size() <= 0)
			return 0;
		if (!list.remove( position))
			return 0;
		int i = 1;
		i += nbBlanc( list, position.substraction( new Position(  1,  0)));
		i += nbBlanc( list, position.substraction( new Position( -1,  0)));
		i += nbBlanc( list, position.substraction( new Position(  0,  1)));
		i += nbBlanc( list, position.substraction( new Position(  0, -1)));
		return i;
	}

}
