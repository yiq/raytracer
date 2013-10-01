package teamk.hw4.utils.math;

/**
 * Any Parametric Equation
 * 
 * The main purpose for this interface is for inner classes to implement, and
 * then later an object of that class be returned, to emulate a higher order
 * function.
 * 
 * @author Yi Qiao
 *
 */
public interface TKIParametricEquation {
	
	/**
	 * Evaluate the parametric equation at a given parameter value, and return
	 * all the components
	 * 
	 * @param t	At which parameter value should all the equations be evaluated
	 * @return	All components when evaluated at t
	 */
	public double[] evaluate(double t);

}
