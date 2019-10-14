package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Program {
	public static final String GAME_NAME = "Space Struggle";
	public static final String CREATORS = "Luke DiGiovanna and Ridha Chowdhury";
	
	public static BufferedImage getImage(String filePath) {
		
		try {
			File file = new File("images/"+filePath+".png");
		
			BufferedImage img = ImageIO.read(file);
		
			return img;
		}
		catch (Exception e) {
			return imgNotFound();	
		}
		
	}
	
	private static BufferedImage imgNotFound() {
		BufferedImage notFound = new BufferedImage(2,2,BufferedImage.TYPE_INT_ARGB);
		notFound.setRGB(0, 0, Color.MAGENTA.getRGB());
		notFound.setRGB(1, 1, Color.MAGENTA.getRGB());
		notFound.setRGB(1,0,Color.BLACK.getRGB());
		notFound.setRGB(0, 1, Color.black.getRGB());
		return notFound;
	}
	
	public static Color fromLoop(float percent, Color ... colors) {
		//0% is purely the first index
		//100% is purely the last index
		if (percent >= 1.0f)
			return colors[colors.length-1];
		if (percent <= 0.0f)
			return colors[0];
		int lowIndex = (int)(percent*colors.length);
		int highIndex = lowIndex+1;
		Color lowCol = colors[lowIndex];
		Color highCol = colors[highIndex];
		float partPercent = percent%(1.0f/colors.length)/(1.0f/colors.length);
		int red = (int)((highCol.getRed()-lowCol.getRed())*partPercent+lowCol.getRed());
		int green = (int)((highCol.getGreen()-lowCol.getGreen())*partPercent+lowCol.getGreen());
		int blue = (int)((highCol.getBlue()-lowCol.getBlue())*partPercent+lowCol.getBlue());
		return new Color(red,green,blue);
	}
}
