package teamk.hw4.utests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import teamk.hw4.utils.math.TKVector3;


/**
 * Unit test cases for the TKVector3 class
 * 
 * @author Yi Qiao
 *
 */
public class TKTestVector3 {
	
	double _tolerance = 1e-4; /**< Float point comparison tolerance */
	
	TKVector3 vecX;	/**< A vector along the X axis */
	TKVector3 vecY; /**< A vector along the Y axis */
	TKVector3 vecZ; /**< A vector along the Z axis */
	TKVector3 vecA; /**< An arbitrary vector */

	@Before
	public void setUp() throws Exception {
		vecX = new TKVector3(2.0, 0.0, 0.0);
		vecY = new TKVector3(0.0, 4.0, 0.0);
		vecZ = new TKVector3(0.0, 0.0, 6.0);
		vecA = new TKVector3(5.0, 5.0, 0.0); // This should give an vector that is 45 degree from X and Y the axes
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		assertEquals("X component of vecX needs to be 2.0", 2.0, vecX.getX(), _tolerance);
		assertEquals("Y component of vecX needs to be 0.0", 0.0, vecX.getY(), _tolerance);
		assertEquals("Z component of vecX needs to be 0.0", 0.0, vecX.getZ(), _tolerance);
		
		assertEquals("X component of vecY needs to be 0.0", 0.0, vecY.getX(), _tolerance);
		assertEquals("Y component of vecY needs to be 4.0", 4.0, vecY.getY(), _tolerance);
		assertEquals("Z component of vecY needs to be 0.0", 0.0, vecY.getZ(), _tolerance);
		
		assertEquals("X component of vecZ needs to be 2.0", 0.0, vecZ.getX(), _tolerance);
		assertEquals("Y component of vecZ needs to be 0.0", 0.0, vecZ.getY(), _tolerance);
		assertEquals("Z component of vecZ needs to be 0.0", 6.0, vecZ.getZ(), _tolerance);
		
		assertEquals("X component of vecA needs to be 5.0", 5.0, vecA.getX(), _tolerance);
		assertEquals("Y component of vecA needs to be 5.0", 5.0, vecA.getY(), _tolerance);
		assertEquals("Z component of vecA needs to be 0.0", 0.0, vecA.getZ(), _tolerance);
	}
	
	@Test
	public void testLength() {
		assertEquals("vecX needs to have a length of 2", 2.0, vecX.getLength(), _tolerance);
		assertEquals("vecY needs to have a length of 4", 4.0, vecY.getLength(), _tolerance);
		assertEquals("vecZ needs to have a length of 6", 6.0, vecZ.getLength(), _tolerance);
		assertEquals("vecA needs to have a length of sqrt(50)", Math.sqrt(50), vecA.getLength(), _tolerance);

	}
	
	@Test
	public void testDotProduct() {
		assertEquals("vecX . vecY needs to be 0", 0.0, vecX.dotProduct(vecY), _tolerance);
		assertEquals("vecY . vecZ needs to be 0", 0.0, vecY.dotProduct(vecZ), _tolerance);
		assertEquals("vecZ . vecX needs to be 0", 0.0, vecZ.dotProduct(vecX), _tolerance);
		
		assertEquals("vecA . vecX needs to be 10", 10.0, vecA.dotProduct(vecX), _tolerance);
		assertEquals("vecA . vecY needs to be 20", 20.0, vecA.dotProduct(vecY), _tolerance);
		assertEquals("vecA . vecZ needs to be 0", 0.0, vecA.dotProduct(vecZ), _tolerance);
		
		// Test for commutativity
		assertEquals("vecX . vecA needs to be 10", 10.0, vecA.dotProduct(vecX), _tolerance);
		assertEquals("vecY . vecA needs to be 20", 20.0, vecA.dotProduct(vecY), _tolerance);
		assertEquals("vecZ . vecA needs to be 0", 0.0, vecA.dotProduct(vecZ), _tolerance);
	}
	
	@Test
	public void testCosAngle() {
		// Angle between axes
		assertEquals("The cosine of the angle between vecX and vecY needs to be 0", 0.0, vecX.cosAngleToVector(vecY), _tolerance);
		assertEquals("The cosine of the angle between vecY and vecZ needs to be 0", 0.0, vecY.cosAngleToVector(vecZ), _tolerance);
		assertEquals("The cosine of the angle between vecZ and vecX needs to be 0", 0.0, vecZ.cosAngleToVector(vecX), _tolerance);

		// Angle between the arbitrary vector and the axes
		assertEquals("The cosine of the angle between vecA and vecX needs to be sqrt(2)/2", Math.sqrt(2.0)/2.0, vecA.cosAngleToVector(vecX), _tolerance);
		assertEquals("The cosine of the angle between vecA and vecY needs to be sqrt(2)/2", Math.sqrt(2.0)/2.0, vecA.cosAngleToVector(vecY), _tolerance);
		assertEquals("The cosine of the angle between vecA and vecZ needs to be 0", 0.0, vecA.cosAngleToVector(vecZ), _tolerance);		
	}
	
	@Test
	public void testNormalization() {
		TKVector3 vecXNormalized = vecX.getNormalized();
		TKVector3 vecYNormalized = vecY.getNormalized();
		TKVector3 vecZNormalized = vecZ.getNormalized();
		TKVector3 vecANormalized = vecA.getNormalized();
		
		// Test if their length are all 1
		assertEquals("vecXNormalized needs to have a length of 1", 1.0, vecXNormalized.getLength(), _tolerance);
		assertEquals("vecYNormalized needs to have a length of 1", 1.0, vecYNormalized.getLength(), _tolerance);
		assertEquals("vecZNormalized needs to have a length of 1", 1.0, vecZNormalized.getLength(), _tolerance);
		assertEquals("vecANormalized needs to have a length of 1", 1.0, vecANormalized.getLength(), _tolerance);

		// Test if the angles between them didn't change (code adopted from testCosAngle())
		assertEquals("The cosine of the angle from vecXNormalized to vecYNormalized needs to be 0", 0.0, vecXNormalized.cosAngleToVector(vecYNormalized), _tolerance);
		assertEquals("The cosine of the angle from vecYNormalized to vecZNormalized needs to be 0", 0.0, vecYNormalized.cosAngleToVector(vecZNormalized), _tolerance);
		assertEquals("The cosine of the angle from vecZNormalized to vecXNormalized needs to be 0", 0.0, vecZNormalized.cosAngleToVector(vecXNormalized), _tolerance);

		assertEquals("The cosine of the angle from vecANormalized to vecXNormalized needs to be sqrt(2)/2", Math.sqrt(2.0)/2.0, vecANormalized.cosAngleToVector(vecXNormalized), _tolerance);
		assertEquals("The cosine of the angle from vecANormalized to vecYNormalized needs to be sqrt(2)/2", Math.sqrt(2.0)/2.0, vecANormalized.cosAngleToVector(vecYNormalized), _tolerance);
		assertEquals("The cosine of the angle from vecANormalized to vecZNormalized needs to be 0", 0.0, vecANormalized.cosAngleToVector(vecZNormalized), _tolerance);

		// Test immutability by checking the original vectors didn't change
		testCreation();
	}

}
