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
	public double xOffset;
	public double yOffset;
	
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
		
		assert(ux >= xStart && ux < xStart + width);
		assert(uy >= yStart && uy < yStart + height);
		
		int x = (int)((ux - xStart + xOffset) / width * textureImage.getWidth());
		int y = (int)((uy - yStart + yOffset) / height * textureImage.getHeight());
		
		if(x >= textureImage.getWidth()) x = x % textureImage.getWidth();
		if(y >= textureImage.getHeight()) y = y % textureImage.getHeight();
		
		//System.out.println("u("+ux+","+uy+") mapping to ("+x+","+y+")");
		
		Color color = new Color(textureImage.getRGB(x, y));
		
		return new double[] {
				color.getRed()/256.0, 
				color.getGreen()/256.0, 
				color.getBlue()/256.0, 
				1.0 /*color.getAlpha()*/};
	}

}
