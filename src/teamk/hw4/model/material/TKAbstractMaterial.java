package teamk.hw4.model.material;

/**
 * Base class for object material
 * 
 * A material object answers the question what is the color at a uv coordinate.
 * This base class provides a default behavior that returns SIMPLE as the
 * material type and null as the color value, at any uv point.
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKAbstractMaterial {

	/**
	 * Returns the color type at given uv coordinate
	 * 
	 * @param u		u component in uv coordinate system
	 * @param v		v component in uv coordinate system
	 * @return		A value from the TKMaterialTypes enum specifying the material type
	 */
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double u, double v) {
		return TKMaterialTypes.SIMPLE;
	}
	
	/**
	 * Compatibility version of getMaterialTypeAtUVCoordinate/2 to better work with uv mappers
	 * 
	 * @see TKAbstractUVMapper
	 * @param uv	An array of components in uv coordinate system.
	 * @return		A value from the TKMaterialTypes enum specifying the material type
	 */
	public final TKMaterialTypes getMaterialTypeAtUVCoordinate(double[] uv) {
		if(uv == null) {
			return getMaterialTypeAtUVCoordinate(0, 0);
		}
		else {
			if(uv.length == 0) 		return getMaterialTypeAtUVCoordinate(0, 0);
			else if(uv.length == 1) return getMaterialTypeAtUVCoordinate(uv[0], 0);
			else					return getMaterialTypeAtUVCoordinate(uv[0], uv[1]);
		}
	}
	
	/**
	 * Returns the Red, Green, Blue and Alpha value at given uv coordinate
	 * 
	 * @param u		u component in uv coordinate system
	 * @param v		v component in uv coordinate system
	 * @return		A array with length of 4, containing the Red, Green, Blue and Alpha of the color value
	 */
	public double[] getColorAtUVCoordinate(double u, double v) {
		return null;
	}
	
	/**
	 * Compatibility version of getColorAtUVCoordinate/2 to better work with uv mappers
	 * @see TKAbstractUVMapper
	 * @param uv	An array of components in uv coordinate system.
	 * @return		A array with length of 4, containing the Red, Green, Blue and Alpha of the color value
	 */
	public final double[] getColorAtUVCoordinate(double[] uv) {
		if(uv == null) {
			return getColorAtUVCoordinate(0, 0);
		}
		else {
			if(uv.length == 0) 		return getColorAtUVCoordinate(0, 0);
			else if(uv.length == 1) return getColorAtUVCoordinate(uv[0], 0);
			else					return getColorAtUVCoordinate(uv[0], uv[1]);
		}
	}
}
