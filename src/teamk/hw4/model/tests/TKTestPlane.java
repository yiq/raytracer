package teamk.hw4.model.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.*;
import teamk.hw4.utils.math.*;
/**
 * Unit test case for TKPlane
 * 
 * @see TKPlane
 * @author Yi Qiao
 *
 */
public class TKTestPlane {
	
	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	private TKPlane testPlane;

	@Before
	public void setUp() throws Exception {
		// This should create a plane that has a normal vector (1, 1, 1) and 
		// goes through the origin
		testPlane = new TKPlane(1.0, 1.0, 1.0, 0.0);
	}

	@Test
	public void testCreation() {
		assertEquals("The A component of testPlane should be 1.0", 1.0, testPlane.getA(), _tolerance);
		assertEquals("The B component of testPlane should be 1.0", 1.0, testPlane.getB(), _tolerance);
		assertEquals("The C component of testPlane should be 1.0", 1.0, testPlane.getC(), _tolerance);
		assertEquals("The D component of testPlane should be 0.0", 0.0, testPlane.getD(), _tolerance);
	}
	
	@Test
	public void testTraceIntercept() {
		// The plane normal will be intercepting the plane, since the normal perpendicular to to plane.
		TKRay interceptingRay = new TKRay(new TKVector3(0.0, 0.0, 0.0), new TKVector3(1.0, 1.0, 1.0));
		double[] roots;
		
		roots = testPlane.findRootsOnRay(interceptingRay);
		assertNotNull("For interceptingRay there must be a root", roots);
		assertTrue("For interceptingRay there must be only one root", roots.length == 1);
		assertEquals("For interceptingRay, t must equals 0", 0.0, roots[0], _tolerance);
	}
	
	@Test
	public void testTraceParallel() {
		// A ray in the plane x+y+z-3=0 will be parallel to the testPlane
		TKRay parallelRay = new TKRay(new TKVector3(1.0, 1.0, 1.0), new TKVector3(2.0, 2.0, -1.0));
		double[] roots;
		
		roots = testPlane.findRootsOnRay(parallelRay);
		assertNull("For parallelRay there must be no root", roots);
	}
	
	@Test
	public void testTraceInside() {
		// A ray in the plane
		TKRay insideRay = new TKRay(new TKVector3(0.0, 0.0, 0.0), new TKVector3(1.0, 2.0, -3.0));
		double[] roots;
		
		roots = testPlane.findRootsOnRay(insideRay);
		assertNull("For insideRay there must be no root", roots);
	}


}
