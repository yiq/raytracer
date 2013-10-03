package teamk.hw4.model;

import teamk.hw4.utils.math.*;

/**
 * This class represent a ray
 * 
 * This class is intended to by immutable
 * 
 * @author Yi Qiao
 */
public class TKRay {
	
	private TKVector3 p0;	/**< The first point the ray goes through */
	private TKVector3 p1;	/**< The second point the ray goes through */
	
	/**
	 * The private inner class to represent the ray in a parametric equation fashion.
	 * 
	 * The intend for this class is that an object of such can be created by methods
	 * inside the TKRay class definition (but not outside, hence private), returned
	 * to the outside, and used to evaluate the parametric equation that is the line.
	 * In another word, this is a higher order function version of the line's parametric
	 * equation, in an emulated fashion. 
	 * 
	 * It is not entirely necessary in the current design as the TKRay can simply 
	 * implement the TKIParametricEquation interface. But utilizing higher order function
	 * seems to ease the maintenance of the code (at the very least, if more representations
	 * are developed, this pattern will prevent a rather long "implements" line)
	 * 
	 * @author Yi Qiao
	 *
	 */
	private class TKLineParametric implements TKIParametricEquation {
		private TKVector3 p0;
		private TKVector3 p1;
		
		public TKLineParametric(TKVector3 p0, TKVector3 p1) {
			this.p0 = p0;
			this.p1 = p1;
		}

		@Override
		public double[] evaluate(double t) {
			double[] results = new double[3];
			results[0] = p0.getX() + t * (p1.getX() - p0.getX());
			results[1] = p0.getY() + t * (p1.getY() - p0.getY());
			results[2] = p0.getZ() + t * (p1.getZ() - p0.getZ());

			return results;
		}
	}
	
	/**
	 * Construct a ray with two points, or one point and a directional vector
	 * @param arg1 				The first point the ray passes through
	 * @param arg2 				The second point the ray passes through, or a directional vector
	 * @param arg2Directional 	If the second parameter is a direction vector
	 * 
	 * @throws IllegalArgumentException If p0 and p1 winds up to be the same, since a line cannot be defined by one point only
	 */
	public TKRay(TKVector3 arg1, TKVector3 arg2, boolean arg2Directional) throws IllegalArgumentException {
		// since TKVector3 is immutable, a copy-by-reference should be fine
		p0 = arg1;
		
		if(arg2Directional)
			p1 = arg1.add(arg2);
		else
			p1 = arg2;
		
		// If p0 and p1 end up to be the same, an IllegalArgumentException will be thrown
		if(p0.equals(p1)) throw new IllegalArgumentException();
	}
	
	/**
	 * Construct a ray with two points
	 * @param p0 					The first point the ray passes through
	 * @param p1 					The second point the ray passes through
	 */
	public TKRay(TKVector3 p0, TKVector3 p1) {
		this(p0, p1, false);
	}
	
	public TKVector3 getP0() {return p0;}
	public TKVector3 getP1() {return p1;}
	
	public TKVector3 getDirectionalVector() {
		return p1.sub(p0).getNormalized();
	}
	
	public TKIParametricEquation getParametricEquation() {
		return new TKLineParametric(p0, p1);
	}
}
