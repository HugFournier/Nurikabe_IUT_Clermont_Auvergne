package util;

import java.util.HashMap;
import java.util.Map;

public class Matrix<leType> {

	private int width, height;
	private Map<Position, leType> data;
	
	public Matrix( int width, int height){
		this.width = width;
		this.height = height;
		data = new HashMap<Position, leType>();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public leType get( int x, int y) {
		return get( new Position( x, y));
	}
	
	public void set( int x, int y, leType o) {
		set( new Position( x, y), o);
	}
	
	public leType get( Position p) {
		return data.get( p);
	}
	
	public void set( Position p, leType o) {
		if (p.isBetween( new Position( 0, 0), new Position( width-1, height-1)))
			data.put( p, o);
	}
	
}
