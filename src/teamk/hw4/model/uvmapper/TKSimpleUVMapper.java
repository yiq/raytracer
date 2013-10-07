package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.TKVector3;

/**
 * A simple UV mapper that maps everything to (0, 0)
 * 
 * This mapper can be used in conjuction with TKSimple*Material classes to
 * create objects with single, solid color (or mirror), as they return the
 * same color in spite of the uv coordinate.
 * 
 * @author Yi Qiao
 *
 */
public class TKSimpleUVMapper extends TKAbstractUVMapper {

	@Override
	public double[] mapPointToUV(TKVector3 p) {
		return new double[]{0.0, 0.0};
	}

}
