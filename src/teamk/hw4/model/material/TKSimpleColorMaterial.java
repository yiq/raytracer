package teamk.hw4.model.material;

/**
 * A simple material class that has only one intrinsic color, at any point in uv system
 * 
 * @author Yi Qiao
 *
 */
public class TKSimpleColorMaterial extends TKAbstractMaterial {
	
	// Color components
	private double r, g, b, a;
	
	/**
	 * Constructor that creates a color with the given components
	 * 
	 * @param r	The red value
	 * @param g	The green value
	 * @param b	The blue value
	 * @param a	The alpha value
	 */
	public TKSimpleColorMaterial (double r, double g, double b, double a) {
		if(r<0 || r>1 || g<0 || g>1 || b<0 || b>1 || a<0 || a>1) 
			throw new IllegalArgumentException();
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public double[] getColorAtUVCoordinate(double u, double v) {
		return new double[]{r, g, b, a};
	}
	
	/** A static object representing pure red color */
	private static TKAbstractMaterial _redColorObject;
	
	/** return the shared copy of the red color object */
	public static TKAbstractMaterial getRedColor() {
		if(_redColorObject == null) _redColorObject = new TKSimpleColorMaterial(1.0, 0.0, 0.0, 1.0);
		return _redColorObject;
	}
	
	/** A static object representing pure green color */
	private static TKAbstractMaterial _greenColorObject;
	
	/** return the shared copy of the green color object */
	public static TKAbstractMaterial getGreenColor() {
		if(_greenColorObject == null) _greenColorObject = new TKSimpleColorMaterial(0.0, 1.0, 0.0, 1.0);
		return _greenColorObject;
	}
	
	/** A static object representing pure blue color*/
	private static TKAbstractMaterial _blueColorObject;
	
	/** return the shared copy of the blue color object */
	public static TKAbstractMaterial getBlueColor() {
		if(_blueColorObject == null) _blueColorObject = new TKSimpleColorMaterial(0.0, 0.0, 1.0, 1.0);
		return _blueColorObject;
	}


}
