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
	
}
