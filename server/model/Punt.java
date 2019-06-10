package server.model;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class Punt {

	double x;
	double y;
	double z;
	
	/** 
	 * Constructor del punt 2D
	 * @param x
	 * @param y
	 */
	public Punt(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructor del punt 3D
	 * @param x
	 * @param y
	 * @param z
	 */
	public Punt(double x, double y,double z) {
		super();
		this.x = x;
		this.y = y;
		this.z=z;
	}
	
	/**
	 * Constructor del punt buit
	 */
	public Punt() {
		// TODO Auto-generated constructor stub
	}
	/** Retorna la coordenada X
	 * @return x
	 */
	public double getX() {
		return x;
	}
	/** Actualitza la coordenada X
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/** Retorna la coordenada Y
	 * @return y
	 */
	public double getY() {
		return y;
	}
	/** Actualitza la coordenada Y
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/** Retorna la coordenada Z
	 * @return z
	 */
	public double getZ() {
		return z;
	}
	/** Actualitza la coordenada Z
	 * @param z
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Retorna els valors del punt
	 */
	@Override
	public String toString() {
		return "Punt [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
	
	
}
