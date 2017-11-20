package util;

import java.io.Serializable;
import java.util.HashMap;

public class Matrix<leType> implements Serializable{

	private static final long serialVersionUID = 6230104758740040210L;
	
	protected int width, height;
	private HashMap<Position, leType> data;
	
	public Matrix( int width, int height){
		this.width = width;
		this.height = height;
		data = new HashMap<Position, leType>();
	}

	public Matrix( Matrix<leType> clone){
		width = clone.getWidth();
		height = clone.getHeight();
		data = clone.data;
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
