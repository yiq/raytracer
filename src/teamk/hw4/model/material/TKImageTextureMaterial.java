package teamk.hw4.model.material;

import java.awt.image.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;

public class TKImageTextureMaterial extends TKAbstractMaterial {
	
	BufferedImage textureImage;
	public double width;
	public double height;
	public double xStart;
	public double yStart;
	
	public TKImageTextureMaterial(String imageFilename) {
		try {
			textureImage = ImageIO.read(new File(imageFilename));
		}
		catch (IOException e) {
			textureImage = null;
		}
	}
	
	@Override
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double ux, double uy) {
		return TKMaterialTypes.SIMPLE;
	}

	@Override
	public double[] getColorAtUVCoordinate(double ux, double uy) {
		
		if(textureImage == null) return null;
		
		int x = (int)((ux - xStart) / width * textureImage.getWidth());
		int y = (int)((uy - yStart) / height * textureImage.getHeight());
		
		Color color = new Color(textureImage.getRGB(x, y));
		
		return new double[] {color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()};
	}

}
