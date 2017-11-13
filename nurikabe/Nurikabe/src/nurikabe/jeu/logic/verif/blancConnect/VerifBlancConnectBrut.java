package nurikabe.jeu.logic.verif.blancConnect;

import java.util.ArrayList;
import java.util.List;

import nurikabe.jeu.assets.Grille;
import nurikabe.jeu.assets.cellule.Etat;
import nurikabe.jeu.logic.verif.Verif;
import util.Position;

public class VerifBlancConnectBrut implements Verif{

	@Override
	public boolean verification( Grille grille) {
		List<Position> listBlanc = new ArrayList<Position>();
		List<Position> listChiffre = new ArrayList<Position>();
		int width = grille.getWidth(), height = grille.getHeight();
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (Etat.BLANC == grille.getEtat( x, y) || Etat.VIDE == grille.getEtat( x, y)) {
					listBlanc.add( new Position( x, y));
					if (grille.getValeur( x, y) > 0)
						listChiffre.add( new Position( x, y));
				}
		while (listChiffre.size() > 0){
			Position p = listChiffre.remove( 0);
			if (!check( listBlanc, listChiffre, p))
				return false;
		}
		return true;
	}

	
	private boolean check( List<Position> listBlanc, List<Position> listChiffre, Position p) {
		
		if (listChiffre.size() <= 0)
			return true;
		if (!listBlanc.remove( p))
			return true;
		if (listChiffre.contains( p))
			return false;
		boolean ret = true;
		ret &= check( listBlanc, listChiffre, p.substraction( new Position(  1,  0)));
		ret &= check( listBlanc, listChiffre, p.substraction( new Position( -1,  0)));
		ret &= check( listBlanc, listChiffre, p.substraction( new Position(  0,  1)));
		ret &= check( listBlanc, listChiffre, p.substraction( new Position(  0, -1)));
		return ret;
	}


}
