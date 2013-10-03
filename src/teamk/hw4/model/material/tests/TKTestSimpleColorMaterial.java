package teamk.hw4.model.material.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.material.*;

/**
 * Unit test case for TKSimpleColorMaterial
 * 
 * @author Yi Qiao
 *
 */
public class TKTestSimpleColorMaterial {

	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	TKAbstractMaterial m1, m2;

	@Before
	public void setUp() throws Exception {
		// A normal color
		try {
			m1 = new TKSimpleColorMaterial(0.5, 0.5, 0.25, 1.0);
		}
		catch (Exception e) {
			m1 = null;
		}
		
		// A color with the red factor above 1, and blue below 0
		try {
			m2 = new TKSimpleColorMaterial(1.2, 0.5, -0.2, 1.0);
		}
		catch (Exception e) {
			m2 = null;
		}
		
	}

	@Test
	public void testCreation() {
		assertNotNull("m1 needs to be created", m1);
		double[] colors1 = m1.getColorAtUVCoordinate(0, 0);
		assertEquals("m1 needs to have a red factor of 0.5",   0.5,  colors1[0], _tolerance);
		assertEquals("m1 needs to have a green factor of 0.5", 0.5,  colors1[1], _tolerance);
		assertEquals("m1 needs to have a blue factor of 0.25", 0.25, colors1[2], _tolerance);
		assertEquals("m1 needs to have a alpha factor of 1.0", 1.0,  colors1[3], _tolerance);
		assertTrue("m1 needs to have a material type SIMPLE", m1.getMaterialTypeAtUVCoordinate(0, 0) == TKMaterialTypes.SIMPLE);

		assertNull("m2 needs to be not created", m2);
	}
}
