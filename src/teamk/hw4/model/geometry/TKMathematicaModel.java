package teamk.hw4.model.geometry;

/**
 * An abstract class for any objects that utilizes equations output by Mathematica
 * 
 * The equations output by Mathematica contains some functions with custom names.
 * This base class provide wrapper implementations so that the equations can be 
 * directly copied and pasted into the subclasses
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKMathematicaModel {
	double power(double arg0, double arg1) {return Math.pow(arg0, arg1);}
	double Sqrt(double arg0) {return Math.sqrt(arg0);}
	
	/**
	 * Test whether two doubles equal each other
	 * 
	 * @param arg0		First double value
	 * @param arg1		Second double value
	 * @param tolerance	Comparison tolerance
	 * @return			True if the difference between the two doubles is less than tolerance, False otherwise
	 */
	boolean doubleEqual(double arg0, double arg1, double tolerance) {return Math.abs(arg0-arg1)<tolerance;}
	
	/**
	 * Overload of the the doubleEqual/3 function to provide a default tolerance of 1e-6
	 * @param arg0	First double value
	 * @param arg1	Second double value
	 * @return		True if the difference between the two doubles is less than 1e-6, False otherwise
	 */
	boolean doubleEqual(double arg0, double arg1) {return doubleEqual(arg0, arg1, 1e-6);}
}
