package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.*;

/**
 * The base class for UV mappers
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
