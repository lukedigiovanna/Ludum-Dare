package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 2.0f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addFood(2.0f);
	}

	@Override
	public BufferedImage getImage() {
		int index = (int)(SpriteCodex.POTATO_FARM.length*this.getGenerationPercent());
		index%=SpriteCodex.POTATO_FARM.length;
		return SpriteCodex.POTATO_FARM[index];
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
}
