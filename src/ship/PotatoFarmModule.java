package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule() {
		super(1.0f, 3.0f);
	}

	public void init() {
		super.init();
		this.getShip().addMaxFood(4.0f);
	}
	
	private float food = 3.0f;
	@Override
	protected void generateResource() {
		this.getShip().addFood(food);
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
