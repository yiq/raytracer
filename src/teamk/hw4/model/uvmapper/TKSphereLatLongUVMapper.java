package teamk.hw4.model.uvmapper;

import teamk.hw4.utils.math.TKVector3;

/**
 * A spherical uv mapper from a point on the surface to lat/long coordinate
 * 
 * @author Yi Qiao
 *
 */
public class TKSphereLatLongUVMapper extends TKAbstractUVMapper {
	
	private TKVector3 center;
	private double radius;
	
	public TKSphereLatLongUVMapper(TKVector3 center, double radius) {
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
				
		return new double[] {Lat, Long};
	}

}
