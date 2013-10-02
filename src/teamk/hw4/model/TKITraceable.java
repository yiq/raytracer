package teamk.hw4.model;

import teamk.hw4.utils.math.*;

/**
 * The base class for any Analytical Geometry object
 * 
 * @author Yi Qiao
 */
public abstract interface TKITraceable {
	
	abstract public double[] findRootsOnRay(TKRay aRay);
	
	abstract public TKVector3 surfaceNormalAtPoint(TKVector3 p);

}
