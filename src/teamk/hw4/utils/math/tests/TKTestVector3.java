package teamk.hw4.utils.math.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import teamk.hw4.utils.math.TKVector3;


/**
 * Unit test case for the TKVector3 class
 * 
 * @see TKVector3
 * @author Yi Qiao
 *
 */
public class TKTestVector3 {
	
	double _tolerance = 1e-4; /**< Float point comparison tolerance */
	
	TKVector3 vecX;	/**< A vector along the X axis */
	TKVector3 vecY; /**< A vector along the Y axis */
	TKVector3 vecZ; /**< A vector along the Z axis */
	TKVector3 vecA; /**< An arbitrary vector */
	
	TKVector3 vecO; /**< Test vector creation by null array */
	
	@Before
	public void setUp() throws Exception {
		vecX = new TKVector3(2.0, 0.0, 0.0);
		vecY = new TKVector3(0.0, 4.0, 0.0);
		vecZ = new TKVector3(0.0, 0.0, 6.0);
		
		double[] vecAPoints = new double[]{5.0, 5.0};
		vecA = new TKVector3(vecAPoints); // This should give an vector that is 45 degree from X and Y the axes
		
		vecO = new TKVector3(null);
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
		
		assertEquals("X component of vecO needs to be 0.0", 0.0, vecO.getX(), _tolerance);
		assertEquals("Y component of vecO needs to be 0.0", 0.0, vecO.getY(), _tolerance);
		assertEquals("Z component of vecO needs to be 0.0", 0.0, vecO.getZ(), _tolerance);
	}
	
	@Test
	public void testZeroVectorConstant() {
		TKVector3 zeroVector = TKVector3.getZeroVector();
		
		assertTrue("zeroVector is not null", zeroVector != null);
		assertEquals("X component of zeroVector needs to be 0.0", 0.0, zeroVector.getX(), _tolerance);
		assertEquals("Y component of zeroVector needs to be 0.0", 0.0, zeroVector.getY(), _tolerance);
		assertEquals("Z component of zeroVector needs to be 0.0", 0.0, zeroVector.getZ(), _tolerance);
	}
	
	@Test
	public void testEquality() {
		assertFalse("vecX should not be equal to null", vecX.equals(null));
		assertFalse("vecX should not be equal to an object that is not TKVector3", vecX.equals("hello world"));
		assertFalse("vecX should not be equal to vecY", vecX.equals(vecY));

		assertTrue("vecX should be equal to itself", vecX.equals(vecX));
		assertTrue("vecX should be equal to an TKVector3 vector that has the same coordinates", vecX.equals(new TKVector3(2.0, 0.0, 0.0)));
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
	
	@Test
	public void testAdd() {
		TKVector3 xPlusA = vecX.add(vecA);
		
		// Test for Algebra meanings
		assertEquals("X component of xPlusA needs to be 7.0", 7.0, xPlusA.getX(), _tolerance);
		assertEquals("Y component of xPlusA needs to be 5.0", 5.0, xPlusA.getY(), _tolerance);
		assertEquals("Z component of xPlusA needs to be 0.0", 0.0, xPlusA.getZ(), _tolerance);
		
		// Test for Geometry meanings
		assertEquals("xPlusA needs to have a length of sqrt(74)", Math.sqrt(74), xPlusA.getLength(), _tolerance);
		assertEquals("The cosine of the angle between xPlusA and xAxis needs to be 7/sqrt(74)", 7.0 / Math.sqrt(74), xPlusA.cosAngleToVector(vecX), _tolerance);
		
		// Test for immutability
		testCreation();
	}
	
	@Test
	public void testSub() {
		TKVector3 xMinusA = vecX.sub(vecA);
		
		// Test for Algebra meanings
		assertEquals("X component of xMinusA needs to be -3.0", -3.0, xMinusA.getX(), _tolerance);
		assertEquals("Y component of xMinusA needs to be -5.0", -5.0, xMinusA.getY(), _tolerance);
		assertEquals("Z component of xMinusA needs to be 0.0",   0.0, xMinusA.getZ(), _tolerance);
		
		// Test for Geometry meanings
		assertEquals("xMinusA needs to have a length of sqrt(34)", Math.sqrt(34), xMinusA.getLength(), _tolerance);
		assertEquals("The cosine of the angle between xMinusA and xAxis needs to be -3/sqrt(34)", -3.0 / Math.sqrt(34), xMinusA.cosAngleToVector(vecX), _tolerance);
		
		// Test for immutability
		testCreation();
	}
	
	@Test
	public void testMul() {
		TKVector3 aMul3 = vecA.mul(3);
		// Test for Algebra meanings
		assertEquals("X component of aMul3 needs to be 15.0", 15.0, aMul3.getX(), _tolerance);
		assertEquals("Y component of aMul3 needs to be 15.0", 15.0, aMul3.getY(), _tolerance);
		assertEquals("Z component of aMul3 needs to be 0.0",   0.0, aMul3.getZ(), _tolerance);
		
		// Test for Geometry meanings
		assertEquals("aMul3 needs to have a length of 3 times the length of vecA", vecA.getLength()*3.0, aMul3.getLength(), _tolerance);

		
		// Test for immutability
		testCreation();
		
	}

}
