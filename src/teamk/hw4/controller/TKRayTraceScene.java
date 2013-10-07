package teamk.hw4.controller;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import teamk.hw4.model.*;
import teamk.hw4.model.geometry.TKAbstractGeometryObject;
import teamk.hw4.utils.math.*;


/**
 * A concrete TKScene class that draws the specific scene for HW4
 * 
 * @author Yi Qiao
 *
 */
public class TKRayTraceScene extends TKScene {
	
	private TKVector3 lightLocation; 	/**< The location of the light source */
	
	/** The objects in the scene */
	private List<TKAbstractGeometryObject> objects = new LinkedList<TKAbstractGeometryObject>();	
	
	public TKRayTraceScene() {
		// TODO Construct the scene
		
	}
	
	@Override
	public void render(GL2 gl) {
		// TODO Auto-generated method stub

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
