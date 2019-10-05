package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteCodex {
	//initialize the sprites.
	public static final BufferedImage
		SHIP_CENTER = Program.getImage("ship_center.png"),
		SIMPLE_HYDROLYSIS_MODULE = Program.getImage("hydrolysis_module.png"),
		POWER_SYMBOL = Program.getImage("power_symbol.png"),
		WATER_SYMBOL = Program.getImage("water_symbol.png"),
		FOOD_SYMBOL = Program.getImage("food_symbol.png"),
		SCRAPS_SYMBOL = Program.getImage("scraps_symbol.png");
	
	public static final BufferedImage[]
		POTATO_FARM = {Program.getImage("potato_farm_0"),
				Program.getImage("potato_farm_1"),
				Program.getImage("potato_farm_2"),
				Program.getImage("potato_farm_3"),
				Program.getImage("potato_farm_4"),
				Program.getImage("potato_farm_5"),
				Program.getImage("potato_farm_6"),
				Program.getImage("potato_farm_7")};
}
