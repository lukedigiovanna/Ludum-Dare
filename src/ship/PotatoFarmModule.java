package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 1.0f, 3.0f);
	}

	private float food = 3.0f;
	@Override
	protected void generateResource() {
		this.getShip().addFood(food);
		this.getShip().addMaxFood(2.0f);
	}

	public float foodProduction() {
		return food * 1/getGenerationCooldown();
	}
	
	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.POTATO_FARM, this.getGenerationPercent());
	}
	
	public void levelUp() {
		this.addLevel();
		food = food * 1.5f;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
}
