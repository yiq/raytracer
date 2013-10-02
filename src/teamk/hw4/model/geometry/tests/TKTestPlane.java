package teamk.hw4.model.geometry.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.*;
import teamk.hw4.model.geometry.TKPlane;
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

	private TKPlane plane;

	@Before
	public void setUp() throws Exception {
		// This should create a plane that has a normal vector (1, 1, 1) and 
		// goes through the origin
		plane = new TKPlane(1.0, 1.0, 1.0, 0.0);
	}

	@Test
	public void testCreation() {
		assertEquals("The A component of testPlane should be 1.0", 1.0, plane.getA(), _tolerance);
		assertEquals("The B component of testPlane should be 1.0", 1.0, plane.getB(), _tolerance);
		assertEquals("The C component of testPlane should be 1.0", 1.0, plane.getC(), _tolerance);
		assertEquals("The D component of testPlane should be 0.0", 0.0, plane.getD(), _tolerance);
	}
	
	@Test
	public void testTraceIntercept() {
		// The plane normal will be intercepting the plane, since the normal perpendicular to to plane.
		TKRay interceptingRay = new TKRay(new TKVector3(0.0, 0.0, 0.0), new TKVector3(1.0, 1.0, 1.0));
		double[] roots;
		
		roots = plane.findRootsOnRay(interceptingRay);
		assertNotNull("For interceptingRay there must be a root", roots);
		assertTrue("For interceptingRay there must be only one root", roots.length == 1);
		assertEquals("For interceptingRay, t must equals 0", 0.0, roots[0], _tolerance);
	}
	
	@Test
	public void testTraceParallel() {
		// A ray in the plane x+y+z-3=0 will be parallel to the testPlane
		TKRay parallelRay = new TKRay(new TKVector3(1.0, 1.0, 1.0), new TKVector3(2.0, 2.0, -1.0));
		double[] roots;
		
		roots = plane.findRootsOnRay(parallelRay);
		assertNull("For parallelRay there must be no root", roots);
	}
	
	@Test
	public void testTraceInside() {
		// A ray in the plane
		TKRay insideRay = new TKRay(new TKVector3(0.0, 0.0, 0.0), new TKVector3(1.0, 2.0, -3.0));
		double[] roots;
		
		roots = plane.findRootsOnRay(insideRay);
		assertNull("For insideRay there must be no root", roots);
	}

	@Test
	public void testSurfaceNormal() {
		// A point inside the plane
		TKVector3 testPoint1 = new TKVector3(0.0, 0.0, 0.0);
		TKVector3 surfaceNormal = plane.surfaceNormalAtPoint(testPoint1);
		assertNotNull("Surface normal exists at point (0,0,0)", surfaceNormal);
		assertTrue("Surface normal at (0,0,0) is normalized (A,B,C)", surfaceNormal.equals((new TKVector3(1.0, 1.0, 1.0)).getNormalized()));
	
		// A point outside the plane
		TKVector3 testPoint2 = new TKVector3(1.0, 1.0, .0);
		surfaceNormal = plane.surfaceNormalAtPoint(testPoint2);
		assertNull("Surface normal does not exist at point (1,1,1)", surfaceNormal);
	}

}
