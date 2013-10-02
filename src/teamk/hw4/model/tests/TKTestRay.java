package teamk.hw4.model.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.*;
import teamk.hw4.utils.math.*;

/**
 * Unit test case for TKRay
 * 
 * @see TKRay
 * @author Yi Qiao
 *
 */
public class TKTestRay {
	
	TKRay testRay;
	
	@Before
	public void setUp() throws Exception {

		// Create a ray that goes through (0,0,0) and (3,4,5)
		testRay = new TKRay(
				new TKVector3(0.0, 0.0, 0.0),
				new TKVector3(3.0, 4.0, 5.0)
				);
	}

	@Test
	public void testCreation() {
		assertTrue("The p0 of the ray should be (0,0,0)", testRay.getP0().equals(new TKVector3(0.0, 0.0, 0.0)));
		assertTrue("The p1 of the ray should be (3,4,5)", testRay.getP1().equals(new TKVector3(3.0, 4.0, 5.0)));
	}
	
	@Test
	public void testParametric() {
		TKIParametricEquation equation = testRay.getParametricEquation();
		
		assertTrue("The equation object should not be null", equation != null);
		
		double[] results;

		results = equation.evaluate(0.0);
		assertTrue("Evaluating at t=0 should return three components", results.length == 3);
		assertTrue("Point evaluated at t=0 should be the same as p0", testRay.getP0().equals(new TKVector3(results)));
		
		results = equation.evaluate(1.0);
		assertTrue("Evaluating at t=1 should return three components", results.length == 3);
		assertTrue("Point evaluated at t=1 should be the same as p1", testRay.getP1().equals(new TKVector3(results)));
		
		results = equation.evaluate(-1.0);
		assertTrue("Evaluating at t=-1 should return three components", results.length == 3);
		assertTrue("Point evaluated at t=-1 should be the same as -p1", TKVector3.getZeroVector().sub(testRay.getP1()).equals(new TKVector3(results)));
		
		results = equation.evaluate(0.5);
		assertTrue("Evaluating at t=0.5 should return three components", results.length == 3);
		assertTrue("Point evaluated at t=0.5 should be the mid point between p0 and p1", (new TKVector3(1.5, 2.0, 2.5)).equals(new TKVector3(results)));
		
	}

}