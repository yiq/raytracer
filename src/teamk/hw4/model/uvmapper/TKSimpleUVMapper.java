package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.TKVector3;

/**
 * A simple UV mapper that maps everything to (0, 0)
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
