package teamk.hw4.model;

import teamk.hw4.utils.math.*;

/**
 * A Sphere that is ray traceable
 * 
 * @author Yi Qiao
 *
 */
public class TKSphere extends TKMathematicaModel implements TKITraceable {
	
	private TKVector3 center;	/**< The center coordinate of the sphere */
	private double    radius;	/**< The radius of the sphere */
	
	public TKSphere(TKVector3 center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public TKVector3 getCenter() {return center;}
	public double getRadius() {return radius;}

	@Override
	public double[] findRootsOnRay(TKRay aRay) {
		
		// Provide the constants used by the equations as local variables
		double cx = center.getX(); double cy = center.getY(); double cz = center.getZ();
		double x0 = aRay.getP0().getX(); double y0 = aRay.getP0().getY(); double z0 = aRay.getP0().getZ();
		double x1 = aRay.getP1().getX(); double y1 = aRay.getP1().getY(); double z1 = aRay.getP1().getZ();
		double r = radius;
				
		/* >>>>>> BEGIN PASTED CODE <<<<<< */
		
		double discriminate = 4*power((cx - x0)*(x0 - x1) + (cy - y0)*(y0 - y1) + (cz - z0)*(z0 - z1),2) -
		           4*(power(cx,2) - power(r,2) - 2*cx*x0 + power(x0,2) + power(cy - y0,2) +
		              power(cz - z0,2))*(power(x0 - x1,2) + power(y0 - y1,2) + power(z0 - z1,2));
		
		/* >>>>>> END PASTED CODE <<<<<< */
				
		// Test the discriminate
		if (discriminate < 0) return null;

		/* >>>>>> BEGIN PASTED CODE <<<<<< */

		double t1 = (power(x0,2) + cx*x1 - x0*(cx + x1) - cy*y0 + power(y0,2) + cy*y1 -
		        y0*y1 - cz*z0 + power(z0,2) + Sqrt(discriminate)/2.
		          + cz*z1 - z0*z1)/(power(x0 - x1,2) + power(y0 - y1,2) + power(z0 - z1,2));

		double t2 = (power(x0,2) + cx*x1 - x0*(cx + x1) - cy*y0 + power(y0,2) + cy*y1 -
		        y0*y1 - cz*z0 + power(z0,2) - Sqrt(discriminate)/2.
		          + cz*z1 - z0*z1)/(power(x0 - x1,2) + power(y0 - y1,2) + power(z0 - z1,2));
		
		/* >>>>>> END  PASTED CODE <<<<<< */
		
		if (doubleEqual(discriminate, 0)) {
			// if discriminate is 0, t1 and t2 must be the same, or something weird must have happened
			assert(doubleEqual(t1, t2));
			
			double[] result = new double[1];
			result[0] = t1;
			return result;
		}
		
		// If the execution get to this point, discriminate must be greater than 0, and two roots we have
		double[] result = new double[2];
		result[0] = t1 < t2 ? t1 : t2; 
		result[1] = t1 < t2 ? t2 : t1;
		
		return result;
	}

	@Override
	public TKVector3 surfaceNormalAtPoint(TKVector3 p) {
		// Calculate the normal vector by vector arithmetics
		TKVector3 normalVector = p.sub(center);
		
		// Check whether p is actually on the surface by checking the normal vector length
		// If p is not on the surface, return null
		return doubleEqual(normalVector.getLength(), radius) ? normalVector.getNormalized() : null;
	}

}
