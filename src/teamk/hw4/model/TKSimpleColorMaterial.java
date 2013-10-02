package teamk.hw4.model;

/**
 * A simple material class that has only one intrinsic color
 * 
 * @author Yi Qiao
 *
 */
public class TKSimpleColorMaterial extends TKAbstractMaterial {
	
	// Color components
	private double r, g, b, a;
	
	public TKSimpleColorMaterial (double r, double g, double b, double a) {
		if(r<0 || r>1 || g<0 || g>1 || b<0 || b>1 || a<0 || a>1) 
			throw new IllegalArgumentException();
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public double[] getColorAtUVCoordinate(double ux, double uy) {
		return new double[]{r, g, b, a};
	}


}
