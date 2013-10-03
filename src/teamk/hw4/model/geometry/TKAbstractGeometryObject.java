package teamk.hw4.model.geometry;

import teamk.hw4.model.TKITraceable;
import teamk.hw4.model.material.TKAbstractMaterial;
import teamk.hw4.model.material.TKMaterialTypes;
import teamk.hw4.model.uvmapper.TKAbstractUVMapper;
import teamk.hw4.utils.math.TKVector3;

public abstract class TKAbstractGeometryObject extends TKMathematicaModel implements TKITraceable {
	protected TKAbstractMaterial material;
	protected TKAbstractUVMapper uvMapper;
	
	public void setMaterial(TKAbstractMaterial m) {
		this.material = m;
	}
	
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
		
		double[] uvCoordinates;
		if(uvMapper == null) 	uvCoordinates = new double[]{0.0, 0.0};
		else 					uvCoordinates = uvMapper.mapPointToUV(p);
		
		if (material == null) return null;
		return material.getColorAtUVCoordinate(uvCoordinates[0], uvCoordinates[1]);
		
	}
	
	/**
	 * Returns the material type at the given surface point
	 * @param p The surface point
	 * @return  The material type at the given surface point, UNDEFINED if the point is not on the surface or no material has been set for the object
	 */
	public TKMaterialTypes getMaterialTypeAtSurfacePoint(TKVector3 p) {
		if(material == null) return TKMaterialTypes.UNDEFINED;
		
		if(surfaceNormalAtPoint(p) == null) return TKMaterialTypes.UNDEFINED;
		
		double[] uvCoordinates;
		if(uvMapper == null) 	uvCoordinates = new double[]{0.0, 0.0};
		else 					uvCoordinates = uvMapper.mapPointToUV(p);
		
		if (material == null) return TKMaterialTypes.UNDEFINED;
		return material.getMaterialTypeAtUVCoordinate(uvCoordinates[0], uvCoordinates[1]);
	}
}
