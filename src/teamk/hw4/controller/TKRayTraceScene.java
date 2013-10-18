package teamk.hw4.controller;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import teamk.hw4.model.*;
import teamk.hw4.model.geometry.TKAbstractGeometryObject;
import teamk.hw4.model.geometry.TKPlane;
import teamk.hw4.model.geometry.TKSphere;
import teamk.hw4.model.material.TKSimpleColorMaterial;
import teamk.hw4.model.material.TKSimpleMirrorMaterial;
import teamk.hw4.model.uvmapper.TKSimpleUVMapper;
import teamk.hw4.utils.math.*;


/**
 * A concrete TKScene class that draws the specific scene for HW4
 * 
 * @author Yi Qiao, Tyler Laracuente, Ian O'Connor
 *
 */
public class TKRayTraceScene extends TKScene {
	
	private TKVector3 lightLocation; 	/**< The location of the light source */
	
	// The location of the camera
	private TKVector3 cameraLocation;
	
	private double[][][] rayTraceBuffer;
	
	private int xScanDensity;
	private int yScanDensity;

	private double[] ambientColor = new double[] {0.25, 0.25, 0.25, 1.0};
	private double[] specularColor = new double[] {1.0, 1.0, 1.0, 1.0};
	private double ambientFactor = 0.5;
	private double specularFactor = 0.1;
	
	/** The objects in the scene */
	private List<TKAbstractGeometryObject> objects = new LinkedList<TKAbstractGeometryObject>();	
	
	public TKRayTraceScene() {
		
		// Set scan densities
		xScanDensity = 500;
		yScanDensity = 500;
		rayTraceBuffer = new double[xScanDensity][yScanDensity][4];
		
		// Set camera and light locations
		cameraLocation = new TKVector3(250.0, 250.0, 200.0);
		lightLocation = new TKVector3(500.0, 300.0, 200.0);
		
		
		// Create a green sphere at (250, 250, -10) with radius 50
		TKAbstractGeometryObject greenSphere = new TKSphere(new TKVector3(250.0, 200.0, -10.0), 50.0);
		greenSphere.setUVMapper(new TKSimpleUVMapper());
		greenSphere.setMaterial(new TKSimpleColorMaterial(0.0, 1.0, 0.0, 1.0));
		objects.add(greenSphere);
		
		// Create a mirrored sphere at (100, 100, -20) with radius 60
		TKAbstractGeometryObject mirrorSphere = new TKSphere(new TKVector3(100.0, 100.0, -20.0), 60.0);
		mirrorSphere.setUVMapper(new TKSimpleUVMapper());
		mirrorSphere.setMaterial(new TKSimpleMirrorMaterial());
		objects.add(mirrorSphere);
		
		// Create a red plane
		TKAbstractGeometryObject redPlane = new TKPlane(0.0, 0.0, 1.0, 200.0);
		redPlane.setMaterial(new TKSimpleColorMaterial(1.0, 0.0, 0.0, 1.0));
		objects.add(redPlane);
	}

	@Override
	public void render(GL2 gl) {
		gl.glBegin(GL2.GL_POINTS);
		for (TKAbstractGeometryObject object : objects) {
			
		}
	}

	@Override
	public void updateAnimation(long timeElapsed) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Calculates the light vector that starts from a given point, and points to the light source
	 * 
	 * This function is not part of TKITraceable because they are the same for all objects
	 * 
	 * @param p   The point at which the light vector starts
	 * @return    The light vector
	 */
	public TKVector3 lightVectorAtPoint(TKVector3 p) {
		return lightLocation.sub(p).getNormalized();
	}

}
