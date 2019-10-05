package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpriteCodex {
	//initialize the sprites.
	public static final BufferedImage
		SHIP_CENTER = Program.getImage("ship_center"),
		SCRAPS_STORAGE = Program.getImage("scrap_storage"),
		GAME_ROOM = Program.getImage("game_room"),
		SYNTHETIC_MEATS = Program.getImage("synthetic_meats"),
		SUPER_HYDROLYSIS = Program.getImage("super_hydrolysis"),
		SOLAR_REACTOR = Program.getImage("solar_reactor"),
		SCRAP_SYNTHESIZER = Program.getImage("scrap_synthesizer"),
		PARK = Program.getImage("park"),
		POWER_SYMBOL = Program.getImage("power_symbol"),
		WATER_SYMBOL = Program.getImage("water_symbol"),
		FOOD_SYMBOL = Program.getImage("food_symbol"),
		SCRAPS_SYMBOL = Program.getImage("scraps_symbol"),
		HAPPINESS_SYMBOL = Program.getImage("happiness_symbol"),
		PERSON = Program.getImage("person");
	public static final BufferedImage[]
		POTATO_FARM = getArray("potato_farm");
	
	public static final BufferedImage[]
		SIMPLE_HYDROLYSIS_MODULE = getArray("simple_hydrolysis_module");

	public static final BufferedImage[]
		SOLAR_PANEL = getArray("solar_panel");
	
	private static BufferedImage[] getArray(String folderName) {
		List<BufferedImage> list = new ArrayList<BufferedImage>();
		int index = 0;
		while (true) {
			BufferedImage img = Program.getImage(folderName+"/"+folderName+"_"+index);
			try {
				File file = new File("images/"+folderName+"/"+folderName+"_"+index+".png");
				if (!file.exists())
					break;
			} catch (Exception e) {}
			list.add(img);
			index++;
		}
		BufferedImage[] arr = new BufferedImage[list.size()];
		for (int i = 0; i < list.size(); i++) 
			arr[i] = list.get(i);
		return arr;
	}
	
	public static BufferedImage get(BufferedImage[] arr, float percent) {
		return arr[(int)(percent*arr.length)%arr.length];
	}
	
}
 