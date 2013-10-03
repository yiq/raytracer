package teamk.hw4.model.material.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.material.*;

/**
 * Unit test cases for TKSimpleMirrorMaterial
 * @author Yi Qiao
 *
 */
public class TKTestSimpleMirrorMaterial {

	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	TKAbstractMaterial m;
	
	@Before
	public void setUp() throws Exception {
		m = new TKSimpleMirrorMaterial();
	}

	@Test
	public void test() {
		assertNotNull("m needs to be created", m);
		assertTrue("m needs to have a material type of MIRROR", m.getMaterialTypeAtUVCoordinate(0, 0) == TKMaterialTypes.MIRROR);
		assertNull("m needs to not return a valid color", m.getColorAtUVCoordinate(0, 0));
	}

}
