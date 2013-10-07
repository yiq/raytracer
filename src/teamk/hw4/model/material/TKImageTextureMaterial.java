package teamk.hw4.model.material;

import java.awt.image.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;

/**
 * A material class that answers the question about colors in the uv system by looking up an image file
 * 
 * @author Yi Qiao
 *
 */
public class TKImageTextureMaterial extends TKAbstractMaterial {
	
	private BufferedImage textureImage;
	
	private double uvWidth;		/**< The upper limit of the u value */
	private double uvHeight;	/**< The upper limit of the v value */
	private double uStart;		/**< The u value at (0,0) in the image */
	private double vStart;		/**< The v value at (0,0) in the image */
	
	/**
	 * Construct a texture object with given image file
	 * 
	 * @param imageFilename	A string of the image filename
	 */
	public TKImageTextureMaterial(String imageFilename) {
		try {
			textureImage = ImageIO.read(new File(imageFilename));
		}
		catch (IOException e) {
			// If the file cannot be read successfully, set textureImage to null
			textureImage = null;
		}
	}
	
	/**
	 * Configure the UV dimension, usually in relation to a specific uv mapper
	 * @param uStart	The starting value of the u axis
	 * @param vStart	The starting value of the v axis
	 * @param uvWidth	The length of the u axis
	 * @param uvHeight	The length of the v axis
	 */
	public void setUVDimension(double uStart, double vStart, double uvWidth, double uvHeight) {
		this.uStart = uStart;
		this.vStart = vStart;
		this.uvWidth = uvWidth;
		this.uvHeight = uvHeight;
	}
	
	@Override
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double ux, double uy) {
		return TKMaterialTypes.SIMPLE;
	}

	@Override
	public double[] getColorAtUVCoordinate(double u, double v) {
		// Check if the image has been properly loaded
		if(textureImage == null) return null;
		
		// assert that the given uv point is within the defined uv bounds
		assert(u >= uStart && u < uStart + uvWidth);
		assert(v >= vStart && v < vStart + uvHeight);
		
		// transform the uv coordinates to the native x,y coordinates for the image
		int x = (int)((u - uStart) / uvWidth * textureImage.getWidth());
		int y = (int)((v - vStart) / uvHeight * textureImage.getHeight());
		
		// fetch the color
		Color color = new Color(textureImage.getRGB(x, y));
		
		// normalize the color from 0-255 to 0-1
		return new double[] {
				color.getRed()/256.0, 
				color.getGreen()/256.0, 
				color.getBlue()/256.0, 
				color.getAlpha()/256.0};
	}

}
