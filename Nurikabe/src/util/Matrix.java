package util;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Matrix<leType extends PublicCloneable> implements Serializable, PublicCloneable{

	private static final long serialVersionUID = 6230104758740040210L;
	
	protected int width, height;
	private HashMap<Position, leType> data;
	
	public Matrix( int width, int height){
		this.width = width;
		this.height = height;
		data = new HashMap<Position, leType>();
	}

	public Matrix( Matrix<leType> clone){
		this( clone.getWidth(), clone.getHeight());
		Set<Map.Entry<Position, leType>> entryset = clone.data.entrySet();
		for (Map.Entry<Position, leType> entry : entryset){
			if (entry.getKey() != null && entry.getValue() != null)
				data.put( entry.getKey().clone(), (leType) entry.getValue().clone());
		}
	}

	@Override
	public Matrix<leType> clone(){
		return new Matrix<leType>( this);
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

	@Override
	public boolean equals( Object o){
		if (o instanceof Matrix)
			return equals( (Matrix) o);
		return false;
	}

	public boolean equals( Matrix m){
		if (m.getHeight() != getHeight() || m.getWidth() != getWidth())
			return false;
		for (int x = 0; x < getWidth(); x++)
			for (int y = 0; y < getHeight(); y++){
				if (get(x, y) != null && !get(x, y).equals( m.get(x, y)))
					return false;
				if (get( x, y) == null && m.get(x, y) != null)
					return false;
			}
		return true;
	}
	
}
