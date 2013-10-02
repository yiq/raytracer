package teamk.hw4.model;

import teamk.hw4.utils.math.TKVector3;

/**
 * A plane that is ray traceable
 * 
 * @author Yi Qiao
 *
 */
public class TKPlane extends TKMathematicaModel implements TKITraceable {
	
	private double A, B, C, D;	/**< Parameters */
	
	public TKPlane(double A, double B, double C, double D) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
	}
	
	public double getA() {return A;}
	public double getB() {return B;}
	public double getC() {return C;}
	public double getD() {return D;}

	@Override
	public double[] findRootsOnRay(TKRay aRay) {
		
		// Provide the constants used by the equations as local variables
		double x0 = aRay.getP0().getX(); double y0 = aRay.getP0().getY(); double z0 = aRay.getP0().getZ();
		double x1 = aRay.getP1().getX(); double y1 = aRay.getP1().getY(); double z1 = aRay.getP1().getZ();
		
		/* >>>>>> BEGIN PASTED CODE <<<<<< */
		double t = (D + A*x0 + B*y0 + C*z0)/(A*x0 - A*x1 + B*y0 - B*y1 + C*z0 - C*z1);
		/* >>>>>>  END  PASTED CODE <<<<<< */
		
		return (Double.isInfinite(t) || Double.isNaN(t)) ? null : new double[]{t};		
	}

	@Override
	public TKVector3 surfaceNormalAtPoint(TKVector3 p) {
		if(doubleEqual(A*p.getX() + B*p.getY() + C*p.getZ() + D, 0.0))
			return new TKVector3(A, B, C).getNormalized();
		else
			return null;
	}

}
