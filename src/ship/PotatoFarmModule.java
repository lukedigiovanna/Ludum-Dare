package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 2.0f);
	}

	private float food = 2.0f;
	@Override
	protected void generateResource() {
		this.getShip().addFood(food);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.POTATO_FARM, this.getGenerationPercent());
	}
	
	public void levelUp() {
		this.addLevel();
		food += 0.5f;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
}
