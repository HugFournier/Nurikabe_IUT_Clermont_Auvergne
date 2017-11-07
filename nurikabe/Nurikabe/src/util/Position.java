package util;

public class Position{

	private int x, y;
	
	public Position( int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position addition( Position p) {
		return addition( this, p);
	}
	
	public Position substraction( Position p) {
		return substraction( this, p);
	}
	
	@Override
	public int hashCode() {
		return getX() * 11 + getY() * 5;
	}
	
	@Override
	public boolean equals( Object o) {
		if (o instanceof Position)
			return equals( (Position) o);
		return false;
	}
	
	public boolean equals( Position p) {
		return (p.getX() == getX() && p.getY() == getY());
	}
	
	public int getX( ) {
		return x;
	}
	
	public int getY( ) {
		return y;
	}
	
	public void setX( int x) {
		this.x = x;
	}
	
	public void setY( int y) {
		this.y = y;
	}
	
	public boolean isBetween( Position p1, Position p2) {
		return (((p1.getX() >= getX() && p2.getY() <= getX()) || (getX() >= p1.getX() && getX() <= p2.getX())) &&
				((p1.getY() >= getY() && p2.getY() <= getY()) || (getY() >= p1.getY() && getY() <= p2.getY())));
	}
	
	// STATIC
	public static Position addition( Position p1, Position p2) {
		return new Position( p1.getX() + p2.getX(), p1.getY() + p2.getY());
	}
	
	public static Position substraction( Position p1, Position p2) {
		return new Position( p1.getX() - p2.getX(), p1.getY() - p2.getY());
	}
	

	
}
