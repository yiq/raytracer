package teamk.hw4.model.geometry;

import teamk.hw4.model.TKITraceable;
import teamk.hw4.model.material.TKAbstractMaterial;
import teamk.hw4.model.material.TKMaterialTypes;
import teamk.hw4.model.uvmapper.TKAbstractUVMapper;
import teamk.hw4.utils.math.TKVector3;

/**
 * The base class for any traceable and renderable objects.
 * 
 * This class enforces the implementation of TKITraceable and defines methods
 * to allow an object to take on a material and an uv mapper
 * 
 * @author Yi Qiao
 *
 */
public abstract class TKAbstractGeometryObject extends TKMathematicaModel implements TKITraceable {
	protected TKAbstractMaterial material = null;	/**< The material associated with the object */
	protected TKAbstractUVMapper uvMapper = null;	/**< The uv mapper associated with the object */
	
	/**
	 * Setting the material for the object
	 * @param m	A material
	 */
	public void setMaterial(TKAbstractMaterial m) {
		this.material = m;
	}
	
	/**
	 * Setting the uv mapper for the object
	 * @param uvMapper	A UV Mapper, which must be able to map all points on the object surface
	 */
	public void setUVMapper(TKAbstractUVMapper uvMapper) {
		this.uvMapper = uvMapper;
	}
	
	/**
	 * Returns the intrinsic color at the given surface point
	 * @param p The surface point
	 * @return  The intrinsic color at the surface point, null if the point is not on the surface or no material has been set for the object
	 */
	public double[] getColorAtSurfacePoint(TKVector3 p) {
		
		if(material == null) return null;
		
		if(surfaceNormalAtPoint(p) == null) return null;
		
		double[] uvCoordinate = uvMapper != null ? uvMapper.mapPointToUV(p) : new double[]{0.0, 0.0};
		
		return (material != null) ? material.getColorAtUVCoordinate(uvCoordinate) : null;
	}
	
	/**
	 * Returns the material type at the given surface point
	 * @param p The surface point
	 * @return  The material type at the given surface point, UNDEFINED if the point is not on the surface or no material has been set for the object
	 */
	public TKMaterialTypes getMaterialTypeAtSurfacePoint(TKVector3 p) {
		
		if(material == null) return TKMaterialTypes.UNDEFINED;
		
		if(surfaceNormalAtPoint(p) == null) return TKMaterialTypes.UNDEFINED;
		
		double[] uvCoordinate = (uvMapper != null) ? uvMapper.mapPointToUV(p) : new double[]{0.0, 0.0};
		
		return (material != null) ? material.getMaterialTypeAtUVCoordinate(uvCoordinate) : TKMaterialTypes.UNDEFINED;
	}
}
