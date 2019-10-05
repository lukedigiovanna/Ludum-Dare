package main;

import java.awt.image.BufferedImage;

public class SpriteCodex {
	//initialize the sprites.
	public static final BufferedImage
		SHIP_CENTER = Program.getImage("ship_center"),
		SOLAR_PANEL = Program.getImage("solar_panel"),
		SCRAPS_STORAGE = Program.getImage("scraps_storage"),
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
		POTATO_FARM = {Program.getImage("potato_farm/potato_farm_0"),
				Program.getImage("potato_farm/potato_farm_1"),
				Program.getImage("potato_farm/potato_farm_2"),
				Program.getImage("potato_farm/potato_farm_3"),
				Program.getImage("potato_farm/potato_farm_4"),
				Program.getImage("potato_farm/potato_farm_5"),
				Program.getImage("potato_farm/potato_farm_6"),
				Program.getImage("potato_farm/potato_farm_7")};
	
	public static final BufferedImage[]
		SIMPLE_HYDROLYSIS_MODULE = {Program.getImage("simple_hydrolysis_module/simple_hydrolysis_module_0"),
			Program.getImage("simple_hydrolysis_module/simple_hydrolysis_module_1"),
			Program.getImage("simple_hydrolysis_module/simple_hydrolysis_module_2"),
			Program.getImage("simple_hydrolysis_module/simple_hydrolysis_module_3")};
	
	public static BufferedImage get(BufferedImage[] arr, float percent) {
		return arr[(int)(percent*arr.length)%arr.length];
	}
	
}
 