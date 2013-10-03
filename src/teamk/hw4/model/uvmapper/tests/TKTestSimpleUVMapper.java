package teamk.hw4.model.uvmapper.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import teamk.hw4.utils.math.*;
import teamk.hw4.model.uvmapper.TKAbstractUVMapper;
import teamk.hw4.model.uvmapper.TKSimpleUVMapper;

/**
 * Unit test case for TKSimpleUVMapper
 * 
 * @author Yi Qiao
 *
 */
public class TKTestSimpleUVMapper {
	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	private TKAbstractUVMapper mapper;

	@Before
	public void setUp() throws Exception {
		mapper = new TKSimpleUVMapper();
	}

	@Test
	public void test() {
		assertNotNull("mapper needs to be created", mapper);
		double[] coordinates = mapper.mapPointToUV(new TKVector3(Math.random(), Math.random(), Math.random()));
		assertEquals("a random point needs to be mapped to ux=0", 0.0, coordinates[0], _tolerance);
		assertEquals("a random point needs to be mapped to uy=0", 0.0, coordinates[1], _tolerance);
	}
}
