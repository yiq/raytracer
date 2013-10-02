/**
 * 
 */
package teamk.hw4.model.geometry.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.*;
import teamk.hw4.model.geometry.TKSphere;
import teamk.hw4.utils.math.*;

/**
 * Unit test case for TKSphere
 * 
 * @see TKSphere
 * @author Yi Qiao
 *
 */
public class TKTestSphere {
	
	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	
	private TKSphere sphere;


	@Before
	public void setUp() throws Exception {
		sphere = new TKSphere(new TKVector3(5.0, 5.0, 0.0), 2.0);
	}

	@Test
	public void testCreation() {
		assertTrue("The center of the sphere needs to be at (5, 5, 0)", sphere.getCenter().equals(new TKVector3(5.0, 5.0, 0.0)));
		assertEquals("The radius of the sphere needs to be 2", 2.0, sphere.getRadius(), _tolerance);
	}
	
	@Test
	public void testTraceIntercept() {
		// This ray that goes through the origin and the sphere's center will intercept
		// the sphere at two points. Math is done in x-y plane and z=0 for this test
		TKRay interceptingRay = new TKRay(TKVector3.getZeroVector(), new TKVector3(5.0, 5.0, 0.0));
		
		double[] roots = sphere.findRootsOnRay(interceptingRay);
		assertTrue("There must be two roots", roots.length==2);
		
		assertTrue("The first t must be smaller than the second t", roots[0] < roots[1]);
		
		// Math done with pencil and paper suggests that these two points are
		// (5-sqrt(2), 5-sqrt(2), 0) and (5+sqrt(2), 5+sqrt(2), 0)
		TKVector3 ip1 = new TKVector3(interceptingRay.getParametricEquation().evaluate(roots[0]));
		TKVector3 ip2 = new TKVector3(interceptingRay.getParametricEquation().evaluate(roots[1]));
				
		assertTrue("ip1 must be (5-sqrt(2), 5-sqrt(2), 0)", ip1.equals(new TKVector3(5-Math.sqrt(2.0), 5-Math.sqrt(2.0), 0.0)));
		assertTrue("ip2 must be (5+sqrt(2), 5+sqrt(2), 0)", ip2.equals(new TKVector3(5+Math.sqrt(2.0), 5+Math.sqrt(2.0), 0.0)));

	}
	
	@Test
	public void testTraceTangent() {
		
		// On the x-z plane and y=5, the horizontal and vertical lines bounding the shpere will
		// tangent with the sphere. The p1 of the following rays are the tangent point
		TKRay xzTopBoundRay = new TKRay(
				new TKVector3(new double[]{0.0, 5.0, 2.0}), 
				new TKVector3(new double[]{5.0, 5.0, 2.0}));
		
		TKRay xcBottomBoundRay = new TKRay(
				new TKVector3(new double[]{0.0, 5.0, -2.0}), 
				new TKVector3(new double[]{5.0, 5.0, -2.0}));
		
		TKRay xzLeftBoundRay = new TKRay(
				new TKVector3(new double[]{3.0, 5.0, 1.0}), 
				new TKVector3(new double[]{3.0, 5.0, 0.0}));
		
		TKRay xzRightBoundRay = new TKRay(
				new TKVector3(new double[]{7.0, 5.0, 1.0}), 
				new TKVector3(new double[]{7.0, 5.0, 0.0}));
		
		double[] roots;
		
		roots = sphere.findRootsOnRay(xzTopBoundRay);
		assertNotNull("For xzTopBoundRay there must be a root", roots);
		assertTrue("For xzTopBoundRay there must be only one root", roots.length == 1);
		assertEquals("For xzTopBoundRay, t must equals 1", 1.0, roots[0], _tolerance);
		
		roots = sphere.findRootsOnRay(xcBottomBoundRay);
		assertNotNull("For xcBottomBoundRay there must be a root", roots);
		assertTrue("For xcBottomBoundRay there must be only one root", roots.length == 1);
		assertEquals("For xcBottomBoundRay, t must equals 1", 1.0, roots[0], _tolerance);
		
		roots = sphere.findRootsOnRay(xzLeftBoundRay);
		assertNotNull("For xzLeftBoundRay there must be a root", roots);
		assertTrue("For xzLeftBoundRay there must be only one root", roots.length == 1);
		assertEquals("For xzLeftBoundRay, t must equals 1", 1.0, roots[0], _tolerance);
		
		roots = sphere.findRootsOnRay(xzRightBoundRay);
		assertNotNull("For xzRightBoundRay there must be a root", roots);
		assertTrue("For xzRightBoundRay there must be only one root", roots.length == 1);
		assertEquals("For xzRightBoundRay, t must equals 1", 1.0, roots[0], _tolerance);

		// On the x-z plane and y=5, a ray that goes through (0,5,0) will tangent the sphere
		// at (21/5, 5, 2*sqrt(21)/5)
		TKRay xzTangentRay = new TKRay(new TKVector3(0.0, 5.0, 0.0), new TKVector3(21.0/5.0, 5.0, 2.0*Math.sqrt(21.0)/5.0));
		roots = sphere.findRootsOnRay(xzTangentRay);
		assertNotNull("For xzTangentRay there must be a root", roots);
		assertTrue("For xzTangentRay there must be only one root", roots.length == 1);
		assertEquals("For xzTangentRay, t must equals 1", 1.0, roots[0], _tolerance);
		
		// a ray that goes through (0,0,0) and the previously calculated point should
		// also tangent the sphere since they are in the same plane which tangent the sphere
		TKRay tangentRay = new TKRay(new TKVector3(0.0, 0.0, 0.0), new TKVector3(21.0/5.0, 5.0, 2.0*Math.sqrt(21.0)/5.0));
		roots = sphere.findRootsOnRay(tangentRay);
		assertNotNull("For tangentRay there must be a root", roots);
		assertTrue("For tangentRay there must be only one root", roots.length == 1);
		assertEquals("For tangentRay, t must equals 1", 1.0, roots[0], _tolerance);
	}
	
	@Test
	public void testTraceNoRoot() {
		// Any ray in a x-z plane with y < 3 or y>7 will not intercept the sphere
		TKRay nonInterceptingRay1 = new TKRay(new TKVector3(0.0, 2.9, 0.0), new TKVector3(10.0, 2.9, 10.0));
		TKRay nonInterceptingRay2 = new TKRay(new TKVector3(0.0, 7.1, 0.0), new TKVector3(10.0, 7.1, 10.0));

		double[] roots;
		
		roots = sphere.findRootsOnRay(nonInterceptingRay1);
		assertNull("For nonInterceptingRay1 there must be no root", roots);
		
		roots = sphere.findRootsOnRay(nonInterceptingRay2);
		assertNull("For nonInterceptingRay2 there must be no root", roots);
		
		// On the x-z plane where y=5
		TKRay nonInterceptingRay3 = new TKRay(new TKVector3(0.0, 5.0, 0.0), new TKVector3(5.0, 5.0, 3.0));
		TKRay nonInterceptingRay4 = new TKRay(new TKVector3(0.0, 5.0, 0.0), new TKVector3(5.0, 5.0, -3.0));
		
		roots = sphere.findRootsOnRay(nonInterceptingRay3);
		assertNull("For nonInterceptingRay3 there must be no root", roots);
		
		roots = sphere.findRootsOnRay(nonInterceptingRay4);
		assertNull("For nonInterceptingRay4 there must be no root", roots);
	}
	
	@Test
	public void testSurfaceNormal() {
		TKVector3 surfaceNormal;
		
		// x-y plane
		TKVector3 testPoint1 = new TKVector3(5.0, 7.0, 0.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint1);
		assertNotNull("Surface normal exists at point (5,7,0)", surfaceNormal);
		assertTrue("Surface normal at (5,7,0) needs to be (0,1,0)", surfaceNormal.equals(new TKVector3(0.0, 1.0, 0.0)));
	
		TKVector3 testPoint2 = new TKVector3(7.0, 5.0, 0.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint2);
		assertNotNull("Surface normal exists at point (7,5,0)", surfaceNormal);
		assertTrue("Surface normal at (7,5,0) needs to be (1,0,0)", surfaceNormal.equals(new TKVector3(1.0, 0.0, 0.0)));
		
		TKVector3 testPoint3 = new TKVector3(5.0, 3.0, 0.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint3);
		assertNotNull("Surface normal exists at point (5,3,0)", surfaceNormal);
		assertTrue("Surface normal at (5,3,0) needs to be (0,-1,0)", surfaceNormal.equals(new TKVector3(0.0, -1.0, 0.0)));
		
		TKVector3 testPoint4 = new TKVector3(3.0, 5.0, 0.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint4);
		assertNotNull("Surface normal exists at point (3,5,0)", surfaceNormal);
		assertTrue("Surface normal at (3,5,0) needs to be (0,1,0)", surfaceNormal.equals(new TKVector3(-1.0, 0.0, 0.0)));
		
		TKVector3 testPoint5 = new TKVector3(5.0 + Math.sqrt(2), 5.0 + Math.sqrt(2), 0.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint5);
		assertNotNull("Surface normal exists at point (5+sqrt(2),5+sqrt(2),0)", surfaceNormal);
		assertTrue("Surface normal at (5+sqrt(2),5+sqrt(2),0) needs to be (sqrt(2)/2,sqrt(2)/2,0)", surfaceNormal.equals(new TKVector3(Math.sqrt(2.0)/2.0, Math.sqrt(2.0)/2.0, 0.0)));
	
		// a point outside the sphere
		TKVector3 testPoint6 = new TKVector3(5.0, 5.0, 5.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint6);
		assertNull("Surface normal does not exist at point (5,5,5)", surfaceNormal);
		
		// a point inside the sphere
		TKVector3 testPoint7 = new TKVector3(5.0, 5.0, 1.0);
		surfaceNormal = sphere.surfaceNormalAtPoint(testPoint7);
		assertNull("Surface normal does not exist at point (5,5,1)", surfaceNormal);
	}

}
