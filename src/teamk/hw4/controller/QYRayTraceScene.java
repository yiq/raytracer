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
import teamk.hw4.model.uvmapper.TKSphereLatLongUVMapper;
import teamk.hw4.utils.math.TKVector3;

public class QYRayTraceScene extends TKScene {

	private TKVector3 lightLocation; 	/**< The location of the light source */
	private TKVector3 eyeLocation;		/**< The location of the eye */
	
	private int xScanDensity;
	private int yScanDensity;
	private double[][][] rayTraceBuffer;
	
	private double[] ambientColor = new double[] {1.0, 1.0, 1.0, 1.0};
		
	/** The objects in the scene */
	private List<TKAbstractGeometryObject> objects = new LinkedList<TKAbstractGeometryObject>();	
	
	public QYRayTraceScene() {
		xScanDensity = 1000;
		yScanDensity = 1000;
		rayTraceBuffer = new double[xScanDensity][yScanDensity][4];
		
		eyeLocation = new TKVector3(250.0, 100.0, 100.0);
		lightLocation = new TKVector3(250.0, 400.0, -20.0);
		
		// Create a sphere
		TKAbstractGeometryObject sphere = new TKSphere(new TKVector3(250.0, 250.0, -100.0), 50.0);
		
		TKImageTextureMaterial earthMap = new TKImageTextureMaterial("/Users/qiaoy/Desktop/4128.jpg");
		earthMap.width = 180; earthMap.height=360;
		earthMap.xStart = -90; earthMap.yStart = 0;
		sphere.setMaterial(earthMap);
		sphere.setUVMapper(new TKSphereLatLongUVMapper(new TKVector3(250.0, 250.0, -100.0), 50.0));
		objects.add(sphere);
		
		// Create a plane
		TKAbstractGeometryObject plane = new TKPlane(0.0, 0.0, 1.0, 200.0);
		plane.setMaterial(TKSimpleColorMaterial.redColor);
		objects.add(plane);
		
		updateAnimation(0);
	}
	
	@Override
	public void render(GL2 gl) {
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
	
	private double[] look(TKRay ray) {
		
		double minimalT = Double.POSITIVE_INFINITY;
		TKVector3 p = null;
		TKAbstractGeometryObject closestObject = null;
		
		for(TKAbstractGeometryObject o : objects) {
			double[] roots = o.findRootsOnRay(ray);
			if(roots == null)
				continue;
			
			for(double t : roots) {
				if(t > 0 && t < minimalT) {
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
			double illumAngle = lightVector.cosAngleToVector(closestObject.surfaceNormalAtPoint(p));
						
			if(illumAngle < 0) illumAngle = 0;
			double[] intrinsicColor = closestObject.getColorAtSurfacePoint(p);
			
			double[] blentColor = new double[] {
					ambientColor[0] * 0.2 + intrinsicColor[0] * 0.8 * illumAngle,
					ambientColor[1] * 0.2 + intrinsicColor[1] * 0.8 * illumAngle,
					ambientColor[2] * 0.2 + intrinsicColor[2] * 0.8 * illumAngle,
					ambientColor[3] * 0.2 + intrinsicColor[3] * 0.8 * illumAngle
			};
			return intrinsicColor;
//			return blentColor;
		case MIRROR:
			TKRay refRay = reflectedRay(ray, closestObject.surfaceNormalAtPoint(p), p);
			return look(refRay);
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
				rayTraceBuffer[i][j] = look(ray);
			}
		}
	}
}
