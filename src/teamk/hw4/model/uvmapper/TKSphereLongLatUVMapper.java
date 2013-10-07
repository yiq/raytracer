package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.TKVector3;

/**
 * A spherical uv mapper from a point on the surface to long/lat coordinate
 * 
 * Among other ways, Longitude / Latitude is one way to map a 3d point on the
 * surface of a sphere to a 2d coordinate. This specific class defines the 
 * Latitude to be 90 at the north pole, and -90 at the south pole. Although
 * this is more compatible with conventions in geology, a smaller number at
 * the south pole would often require the texture image to be vertically
 * flipped since the upper corner of a image usually has a smaller x value.
 * 
 * @author Yi Qiao
 *
 */
public class TKSphereLongLatUVMapper extends TKAbstractUVMapper {
	
	private TKVector3 center;	/**< Center of the sphere the mapper is assuming */
	private double radius;		/**< The radius of the sphere the mapper is assuming */
	
	/**
	 * Designated Constructor
	 * 
	 * In order to properly calculate longitude and latitude, the mapper needs
	 * the knowledge about the theoretically sphere from which the 3d points
	 * are taken. 
	 * 
	 * @param center	The center of the sphere the mapper is assuming
	 * @param radius	The radius of the sphere the mapper is assuming
	 */
	public TKSphereLongLatUVMapper(TKVector3 center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public double[] mapPointToUV(TKVector3 p) {
		
		// calculate center to surface vector
		TKVector3 vecC2S = p.sub(center);
		
		double Lat = Math.toDegrees(Math.asin(vecC2S.getY() / radius));		
		double Long = Math.toDegrees(Math.atan(vecC2S.getX() / vecC2S.getZ()));
		
		if(vecC2S.getX() < 0 && vecC2S.getZ() >= 0) Long = 360 + Long;
		else if (vecC2S.getX() < 0 && vecC2S.getZ() < 0) Long = 180 + Long;
		else if (vecC2S.getX() >= 0 && vecC2S.getZ() < 0) Long = 180 + Long;
		
		if(Double.isNaN(Long)) Long = 0.0;
				
		return new double[] {Long, Lat};
	}

}
