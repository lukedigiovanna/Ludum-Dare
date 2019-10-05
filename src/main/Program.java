package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Program {
	public static final String GAME_NAME = "Space Jam";
	public static final String CREATORS = "Luke DiGiovanna and Ridha Chowdhury";
	
	public static BufferedImage getImage(String filePath) {
		
		//parse string such 
		
		try {
			File file = new File(filePath);
		
			BufferedImage img = ImageIO.read(file);
		
			return img;
		}
		catch (Exception e) {};
		
		return imgNotFound();
	}
	
	private static BufferedImage imgNotFound() {
		BufferedImage notFound = new BufferedImage(2,2,BufferedImage.TYPE_INT_ARGB);
		notFound.setRGB(0, 0, Color.MAGENTA.getRGB());
		notFound.setRGB(1, 1, Color.MAGENTA.getRGB());
		notFound.setRGB(1,0,Color.BLACK.getRGB());
		notFound.setRGB(0, 1, Color.black.getRGB());
		return notFound;
	}
}
