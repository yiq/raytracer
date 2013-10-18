package teamk.hw4.model.material;

/**
 * A simple material class that has two colors in a checkered pattern, at any point in uv system
 * 
 * @author Yi Qiao, Tyler Laracuente, Ian O'Connor
 *
 */
public class TKSimpleCheckerMaterial extends TKAbstractMaterial {
	
	// Color components
	private double r1, r2, g1, g2, b1, b2, a;
	
	private int squareSize;

	/**
	 * Constructor that creates a checkered pattern with the given colors
	 * 
	 * @param r1 	The first red value
	 * @param g	1	The first green value
	 * @param b	1	The first blue value
	 * @param r2 	The second red value
	 * @param g	2	The second green value
	 * @param b	2	The second blue value

	 * @param a	The alpha value
	 */
	public TKSimpleCheckerMaterial (double r1, double g1, double b1, double r2, double g2, double b2, double a, int squareSize) {
		if(r1<0 || r1>1 || g1<0 || g1>1 || b1<0 || b1>1 || 
				r2<0 || r2>1 || g2<0 || g2>1 || b2<0 || b2>1 || a<0 || a>1) 
			throw new IllegalArgumentException();
		
		this.r1 = r1;
		this.g1 = g1;
		this.b1 = b1;
		this.r2 = r2;
		this.g2 = g2;
		this.b2 = b2;
		this.a = a;
		this.squareSize = squareSize;
	}

	@Override
	public double[] getColorAtUVCoordinate(double u, double v) {
		if ((Math.round(v) % (squareSize)) < squareSize/2) {
			if ((Math.round(u) % (squareSize)) <= squareSize/2) {
				return new double[]{r1, g1, b1, a};
			}
			else {
				return new double[]{r2, g2, b2, a};
			}
		} else {
			if ((Math.round(u) % (squareSize)) <= squareSize/2) {
				return new double[]{r2, g2, b2, a};
			}
			else {
				return new double[]{r1, g1, b1, a};
			}
		}
	}

}
