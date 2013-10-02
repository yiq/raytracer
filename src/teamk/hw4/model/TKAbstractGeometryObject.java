package teamk.hw4.model;

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
	 * @return  The intrinsic color at the surface point, null if the point is not on the surface
	 */
	public double[] getColorAtSurfacePoint(TKVector3 p) {
		if(material == null) return null;
		
		if(surfaceNormalAtPoint(p) == null) return null;
		
		double[] uvCoordinates;
		if(uvMapper == null) 	uvCoordinates = new double[]{0.0, 0.0};
		else 					uvCoordinates = uvMapper.mapPointToUV(p);
		
		return material.getColorAtUVCoordinate(uvCoordinates[0], uvCoordinates[1]);
		
	}
	
	/**
	 * Returns the material type at the given surface point
	 * @param p The surface point
	 * @return  The material type at the given surface point, UNDEFINED if the point is not on the surface
	 */
	public TKMaterialTypes getMaterialTypeAtSurfacePoint(TKVector3 p) {
		if(material == null) return TKMaterialTypes.UNDEFINED;
		
		if(surfaceNormalAtPoint(p) == null) return TKMaterialTypes.UNDEFINED;
		
		double[] uvCoordinates;
		if(uvMapper == null) 	uvCoordinates = new double[]{0.0, 0.0};
		else 					uvCoordinates = uvMapper.mapPointToUV(p);
		
		return material.getMaterialTypeAtUVCoordinate(uvCoordinates[0], uvCoordinates[1]);
	}
}
