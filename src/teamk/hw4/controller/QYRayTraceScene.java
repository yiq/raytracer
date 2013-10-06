package teamk.hw4.controller;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import teamk.hw4.model.TKITraceable;
import teamk.hw4.model.TKRay;
import teamk.hw4.model.geometry.TKAbstractGeometryObject;
import teamk.hw4.model.geometry.TKPlane;
import teamk.hw4.model.geometry.TKSphere;
import teamk.hw4.model.material.TKImageTextureMaterial;
import teamk.hw4.model.material.TKSimpleColorMaterial;
import teamk.hw4.model.material.TKSimpleMirrorMaterial;
import teamk.hw4.model.uvmapper.TKSphereLongLatUVMapper;
import teamk.hw4.utils.math.TKVector3;

public class QYRayTraceScene extends TKScene {

	private TKVector3 lightLocation; 	/**< The location of the light source */
	private TKVector3 eyeLocation;		/**< The location of the eye */
	
	public double windowWidth;
	public double windowHeight;
	
	private int xScanDensity;
	private int yScanDensity;
	
	private double xScanRatio = 0.5;
	private double yScanRatio = 0.5;
	
	private double[][][] rayTraceBuffer;
	
	private double[] ambientColor = new double[] {0.25, 0.25, 0.25, 1.0};
	private double[] specularColor = new double[] {1.0, 1.0, 1.0, 1.0};
	private double   ambientFactor = 0.5;
	private double   specularFactor = 0.1;
	
	TKImageTextureMaterial earthMap;
	TKImageTextureMaterial moonMap;

	
	/** The objects in the scene */
	private List<TKAbstractGeometryObject> objects = new LinkedList<TKAbstractGeometryObject>();	
	
	public QYRayTraceScene() {
		xScanDensity = 500;
		yScanDensity = 500;
		rayTraceBuffer = new double[xScanDensity][yScanDensity][4];
		
		eyeLocation = new TKVector3(250.0, 250.0, 200.0);
		lightLocation = new TKVector3(400.0, 350.0, 200.0);
		
		// Create a sphere
		TKAbstractGeometryObject sphere = new TKSphere(new TKVector3(250.0, 200.0, -10.0), 80.0);
		
		earthMap = new TKImageTextureMaterial("/Users/qiaoy/Desktop/Color Map.jpg");
		earthMap.width = 360; earthMap.height=180;
		earthMap.xStart = 0; earthMap.yStart = -90;
		earthMap.xOffset = 0; earthMap.yOffset = 0;
		sphere.setMaterial(earthMap);
		sphere.setUVMapper(new TKSphereLongLatUVMapper(new TKVector3(250.0, 200.0, -10.0), 80.0));
		objects.add(sphere);
		
		
		// Create a moon
		TKAbstractGeometryObject moon = new TKSphere(new TKVector3(100, 200, -10), 50);
		TKSphereLongLatUVMapper mapper = new TKSphereLongLatUVMapper(new TKVector3(100, 200, -10), 50);
		moonMap = new TKImageTextureMaterial("/Users/qiaoy/Desktop/DeathStar.jpg");
		moonMap.width = 360; moonMap.height = 180;
		moonMap.xStart = 0; moonMap.yStart = -90;
		moonMap.xOffset = 0; moonMap.yOffset = 0;
//		moon.setMaterial(TKSimpleColorMaterial.blueColor);
//		moon.setMaterial(new TKSimpleMirrorMaterial());
		moon.setMaterial(moonMap);
		moon.setUVMapper(mapper);
		objects.add(moon);
		
//		// Create a plane
//		TKAbstractGeometryObject plane = new TKPlane(0.0, 0.0, 1.0, 200.0);
//		plane.setMaterial(TKSimpleColorMaterial.redColor);
//		objects.add(plane);
		
		updateAnimation(0);
	}
	
	@Override
	public void render(GL2 gl) {
		gl.glPointSize((float)Math.max(windowWidth / xScanDensity, windowHeight / yScanDensity));
		gl.glBegin(GL2.GL_POINTS);
		for(int i=0; i<xScanDensity; i++) {
			for(int j=0; j<yScanDensity; j++) {
				if(rayTraceBuffer[i][j] != null) {
					gl.glColor4d(
							rayTraceBuffer[i][j][0], 
							rayTraceBuffer[i][j][1], 
							rayTraceBuffer[i][j][2], 
							rayTraceBuffer[i][j][3]);
					gl.glVertex3d(i * 500.0 / xScanDensity, j * 500.0 / yScanDensity, 0);
				}
			}
		}
		gl.glEnd();
	}

	@Override
	public void updateAnimation(long timeElapsed) {
		// retracing is only necessary if animation states is updated
		rayTraceScene();
		earthMap.xOffset -= (timeElapsed / 1000.0) * 10.0;
		if(earthMap.xOffset < 0) earthMap.xOffset += earthMap.width;
		
		moonMap.xOffset -= (timeElapsed/1000.0) * 20.0;
		if(moonMap.xOffset < 0) moonMap.xOffset += moonMap.width;


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
	
	private TKRay reflectedRay(TKRay inRay, TKVector3 surfNorm, TKVector3 reflectPoint) {
		TKVector3 inRayDir = inRay.getDirectionalVector();
		TKVector3 reflectedRayDir = inRayDir.sub(surfNorm.getNormalized().mul(inRayDir.dotProduct(surfNorm) * 2));
		return new TKRay(reflectPoint, reflectedRayDir, true);
	}
	
	private double[] look(TKRay ray, double lowBoundT) {
		
		double minimalT = Double.POSITIVE_INFINITY;
		TKVector3 p = null;
		TKAbstractGeometryObject closestObject = null;
		
		for(TKAbstractGeometryObject o : objects) {
			double[] roots = o.findRootsOnRay(ray);
			if(roots == null)
				continue;
			
			for(double t : roots) {
				if(t > lowBoundT && t < minimalT) {
					minimalT = t;
					p = new TKVector3(ray.getParametricEquation().evaluate(t));
					closestObject = o;
				}
			}
		}
		
		if (p == null) return null;
		
		switch(closestObject.getMaterialTypeAtSurfacePoint(p)) {
		case SIMPLE:
			
			TKVector3 lightVector = lightVectorAtPoint(p);
			double illumAngleCos = lightVector.cosAngleToVector(closestObject.surfaceNormalAtPoint(p));
			if(illumAngleCos < 0) illumAngleCos = 0;

			double af = (1-illumAngleCos)*ambientFactor;
			double sf = illumAngleCos > 0 ? Math.pow(illumAngleCos, 16) : 0;
						
			double[] intrinsicColor = closestObject.getColorAtSurfacePoint(p);
			
			double[] blentColor = new double[] {
					ambientColor[0] * af + specularColor[0] * sf + intrinsicColor[0] * (1-af-sf),
					ambientColor[1] * af + specularColor[1] * sf + intrinsicColor[1] * (1-af-sf),
					ambientColor[2] * af + specularColor[2] * sf + intrinsicColor[2] * (1-af-sf),
					1//ambientColor[3] * af + specularColor[3] * sf + intrinsicColor[3] * (1-af-sf)
			};
//			return intrinsicColor;
			return blentColor;
		case MIRROR:
			TKRay refRay = reflectedRay(ray, closestObject.surfaceNormalAtPoint(p), p);
			return look(refRay, minimalT);
		case UNDEFINED:
		default:
			// Something is not right
			return null;
		}
	}
	
	private void rayTraceScene() {
		for(int i=0; i<xScanDensity; i++) {
			for(int j=0; j<yScanDensity; j++) {
				TKRay ray = new TKRay(eyeLocation, new TKVector3((double)i/xScanDensity*500.0, (double)j/yScanDensity*500.0, 0.0));
				rayTraceBuffer[i][j] = look(ray, 0.0);
			}
		}
	}
}
