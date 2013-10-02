package teamk.hw4.model;

import teamk.hw4.utils.math.*;

/**
 * The base class for any Analytical Geometry object
 * 
 * @author Yi Qiao
 */
public abstract interface TKITraceable {
	
	/**
	 * Return a set of points that are both on the traceable object and the ray
	 * 
	 * @param aRay The ray used in ray tracing
	 * @return A set of parameter t for the traced out points. length may vary. null if not intercepting
	 */
	abstract public double[] findRootsOnRay(TKRay aRay);
	
	/**
	 * Returns the surface normal at point p
	 * @param p A point on the surface
	 * @return	The surface normal at the given point. null if the given point is not actually on the surface
	 */
	abstract public TKVector3 surfaceNormalAtPoint(TKVector3 p);

}
