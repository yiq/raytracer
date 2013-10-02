package teamk.hw4.model;

/**
 * Base class for object material
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKAbstractMaterial {

	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double ux, double uy) {
		return TKMaterialTypes.SIMPLE;
	}
	
	public double[] getColorAtUVCoordinate(double ux, double uy) {
		return null;
	}
}
