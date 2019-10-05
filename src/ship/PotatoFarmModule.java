package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 1.0f);
	}

	@Override
	protected void generateResource() {
		
	}

	@Override
	public BufferedImage getImage() {
		int index = (int)(SpriteCodex.POTATO_FARM.length*this.getGenerationPercent());
		return SpriteCodex.POTATO_FARM[index];
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
}
