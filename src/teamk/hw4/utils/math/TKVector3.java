package teamk.hw4.utils.math;


/**
 * An 3d vector class that encapsulates vector arithmetics
 * 
 * This class is intended to be immutable
 * 
 * @author Yi Qiao
 *
 */
public class TKVector3 {
	
	/**
	 * The three components of the vector
	 */
	protected double[] components = new double[3];
	
	private static TKVector3 zeroVector;
	
	/**
	 * Constructor that creates a vector object with given components
	 * 
	 * @param x The X component
	 * @param y The Y component
	 * @param z The Z component
	 */
	public TKVector3(double x, double y, double z) {
		components[0] = x;
		components[1] = y;
		components[2] = z;
	}
	
	/**
	 * Constructor that creates a vector object with given component array
	 * 
	 * The first three elements in the array will be used. If there is not enough
	 * elements, 0 will be used.
	 * 
	 * @param components An array containing the components.
	 */
	public TKVector3(double[] components) {
		int i = 0;
		
		// Use the value provided if the parameter is not null
		if(components != null) {
			for(; i<components.length; i++) {
				this.components[i] = components[i];
			}
		}
		
		// Padding with 0 if not enough components are provided
		for(; i<3; i++) {
			this.components[i] = 0;
		}
	}
	
	/**
	 * Return the object that represent the vector (0, 0, 0)
	 * 
	 * Since this class is immutable, there is no need to create a new object every time
	 * the zero vector is needed for calculation. Thus this function is static, and return
	 * the "one" object stored in the static field "zeroVector"
	 * 
	 * @return The singleton object representing the vector (0, 0, 0)
	 */
	public static TKVector3 getZeroVector() {
		if(zeroVector == null) zeroVector = new TKVector3(0, 0, 0);
		return zeroVector;
	}
	
	
	/**
	 * Getter of the X component
	 * @return The X component
	 */
	public double getX() {return components[0];}
	
	/**
	 * Getter of the Y component
	 * @return The Y component
	 */
	public double getY() {return components[1];}
	
	/**
	 * Getter of the Z component
	 * @return The Z component
	 */
	public double getZ() {return components[2];}
	
	/**
	 * Calculate the dot product of the current vector and the given vector
	 * 
	 * @param otherOperand The second operand in the dot product operation
	 * @return The dot product
	 */
	public double dotProduct(TKVector3 otherOperand) {
		return 
				components[0] * otherOperand.components[0] + 
				components[1] * otherOperand.components[1] +
				components[2] * otherOperand.components[2];
	}
	
	/**
	 * Return the length of the vector
	 * @return The length of the vector
	 */
	public double getLength() { return Math.sqrt(dotProduct(this)); }
	
	/**
	 * Calculates the cos angle between the current vector and the other vector given as parameter
	 * 
	 * @param otherOperand The other vector
	 * @return The cosine value of the angle between the current vector and the given vector
	 */
	public double cosAngleToVector(TKVector3 otherOperand) {
		return dotProduct(otherOperand) / (getLength()*otherOperand.getLength());
	}
	
	/**
	 * Return a normalized vector based on the current vector
	 * 
	 * The normalized vector has the same direction as the current vector, 
	 * but the length of the normalized vector is 1
	 * 
	 * @return The normalized vector
	 */
	public TKVector3 getNormalized() {
		double length = getLength();
		return new TKVector3(
				components[0] / length,
				components[1] / length,
				components[2] / length
				);
	}
	
	@Override
	public boolean equals(Object v) {
		
		if (v == null) return false;
		if (getClass() != v.getClass()) return false;
		
		boolean result = true;
		result = result && Math.abs(getX() - ((TKVector3)v).getX()) < 1e-6;
		result = result && Math.abs(getY() - ((TKVector3)v).getY()) < 1e-6;
		result = result && Math.abs(getZ() - ((TKVector3)v).getZ()) < 1e-6;
		return result;
	}
	
	/**
	 * Vector addition
	 * 
	 * Geometrically, the resulting vector is the third edge of a triangle with the other
	 * two edge being the current vector and the given vector, and the given vector moved
	 * the end point of the current vector
	 * 
	 * @param anotherVector The second operand in the add operation
	 * @return The resulting vector of the two vector's sum
	 */
	public TKVector3 add(TKVector3 anotherVector) {
		return new TKVector3(
				getX() + anotherVector.getX(),
				getY() + anotherVector.getY(),
				getZ() + anotherVector.getZ());
	}
	
	/**
	 * Vector subtraction
	 * 
	 * Geometrically, the resulting vector is the third edge of a triangle, with the other
	 * two edges being the current vector and the inverse of the given vector, and the inverse
	 * of the given vector moved to the end point of the current vector
	 * 
	 * A handy usage of this operation is that the length of the resulting vector is the same as
	 * the distance between the end points of the current and given vectors.
	 * 
	 * @param anotherVector The second operand in ths sub operation
	 * @return The resulting vector of current vector minus the given vector
	 */
	public TKVector3 sub(TKVector3 anotherVector) {
		return new TKVector3(
				getX() - anotherVector.getX(),
				getY() - anotherVector.getY(),
				getZ() - anotherVector.getZ());
	}
	
	/**
	 * Vector multiplies a scalar
	 * 
	 * The resulting vector will have their elements each multiplied by the scalar
	 * 
	 * @param a The scalar the vector is being multiplied by
	 * @return  The resulting vector
	 */
	public TKVector3 mul(double a) {
		return new TKVector3(
				getX() * a,
				getY() * a,
				getZ() * a);
	}
}
