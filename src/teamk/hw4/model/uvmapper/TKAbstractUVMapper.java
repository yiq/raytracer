package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.*;

/**
 * The base class for UV mappers
 * 
 * UV mapping is a technique to map a point on the surface of a 3d model, which
 * would be expressed in three dimensional coordinate (x,y,z), into a two
 * dimentional coordinate (u,v) which exists in texture space. This class, in
 * conjuction with the material classes (which answers the question what color
 * it is at a specific uv coordinate), will allow 3d models to take on 'skins'
 * that ranges from solid color, color based on certain properties of the uv
 * coordinates (such as checker board), or a certain point in an image file
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKAbstractUVMapper {
	
	/**
	 * For any given point, returns the cooresponding UV coordinates at the point
	 * @param p The point in model coordinate
	 * @return  The uv coordinates in texture coordinate
	 */
	abstract public double[] mapPointToUV(TKVector3 p);

}
