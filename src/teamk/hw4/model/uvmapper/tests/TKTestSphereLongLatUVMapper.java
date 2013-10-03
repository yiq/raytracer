package teamk.hw4.model.uvmapper.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import teamk.hw4.model.uvmapper.*;
import teamk.hw4.utils.math.TKVector3;

public class TKTestSphereLongLatUVMapper {
	
	double _tolerance = 1e-4; /**< Float point comparison tolerance */

	
	private TKAbstractUVMapper centeredSphereMapper;
	private TKAbstractUVMapper offsettedSphereMapper;

	@Before
	public void setUp() throws Exception {
		
		centeredSphereMapper = new TKSphereLongLatUVMapper(new TKVector3(0.0, 0.0, 0.0), 1.0);
		offsettedSphereMapper = new TKSphereLongLatUVMapper(new TKVector3(1.0, 1.0, 0.0), 2.0);
	}

	@Test
	public void testCreation() {
		assertNotNull("centeredSphereMapper should have been created", centeredSphereMapper);
		assertNotNull("offsettedSphereMapper should have been created", offsettedSphereMapper);
	}
	
	@Test
	public void testCenteredSphere() {
		TKVector3 p;
		double[] latLong;
		
		p = new TKVector3(0.0, 0.0, 1.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(0,0,1) should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
		assertEquals("(0,0,1) should be mapped to Long = 0", 0.0, latLong[0], _tolerance);

		
		p = new TKVector3(1.0, 0.0, 0.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(1,0,0) should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
		assertEquals("(1,0,0) should be mapped to Long = 90", 90.0, latLong[0], _tolerance);

		p = new TKVector3(0.0, 0.0, -1.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(0,0,-1) should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
		assertEquals("(0,0,-1) should be mapped to Long = 180", 180.0, latLong[0], _tolerance);
		
		p = new TKVector3(-1.0, 0.0, 0.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(-1,0,0) should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
		assertEquals("(-1,0,0) should be mapped to Long = 270", 270.0, latLong[0], _tolerance);
		
		
		for(int i=0; i<360; i++) {
			double x = Math.sin(Math.toRadians(i));
			double z = Math.cos(Math.toRadians(i));
			p = new TKVector3(x, 0.0, z);
			latLong = centeredSphereMapper.mapPointToUV(p);
			assertEquals("circle on y=0 should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
			assertEquals("circle on y=0 should be mapped to i", i, latLong[0], _tolerance);
		}
		
		// Latitude
		p = new TKVector3(0.0, 1.0, 0.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(0,1,0) should be mapped to Lat = 90", 90.0, latLong[1], _tolerance);
		assertEquals("(0,1,0) should be mapped to Long = 0", 0.0, latLong[0], _tolerance);
		
		p = new TKVector3(0.0, -1.0, 0.0);
		latLong = centeredSphereMapper.mapPointToUV(p);
		assertEquals("(0,-1,0) should be mapped to Lat = -90", -90.0, latLong[1], _tolerance);
		assertEquals("(0,-1,0) should be mapped to Long = 0", 0.0, latLong[0], _tolerance);
		
	}
	
	@Test
	public void testOffsettedSphere() {
		TKVector3 p;
		double[] latLong;
		
		for(int i=0; i<360; i++) {
			double x = 2.0 * Math.sin(Math.toRadians(i)) + 1.0;
			double z = 2.0 * Math.cos(Math.toRadians(i));
			p = new TKVector3(x, 1.0, z);
			latLong = offsettedSphereMapper.mapPointToUV(p);
			assertEquals("circle on y=0 should be mapped to Lat = 0", 0.0, latLong[1], _tolerance);
			assertEquals("circle on y=0 should be mapped to i", i, latLong[0], _tolerance);
		}
		
		// Latitude
		p = new TKVector3(1.0, 3.0, 0.0);
		latLong = offsettedSphereMapper.mapPointToUV(p);
		assertEquals("(1,3,0) should be mapped to Lat = 90", 90.0, latLong[1], _tolerance);
		assertEquals("(1,3,0) should be mapped to Long = 0", 0.0, latLong[0], _tolerance);
		
		p = new TKVector3(1.0, -1.0, 0.0);
		latLong = offsettedSphereMapper.mapPointToUV(p);
		assertEquals("(1,-1,0) should be mapped to Lat = -90", -90.0, latLong[1], _tolerance);
		assertEquals("(1,-1,0) should be mapped to Long = 0", 0.0, latLong[0], _tolerance);
		
	}

}
