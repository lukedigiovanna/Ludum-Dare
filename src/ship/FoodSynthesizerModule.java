package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class FoodSynthesizerModule extends ShipModule {

	public static int price = 500;
	
	public FoodSynthesizerModule(Ship inShip) {
		super(inShip, 0.25f, 6.0f);
		this.getShip().addMaxFood(20.0f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private float food = 2.5f;
	@Override
	protected void generateResource() {
		this.getShip().addFood(food);
	}
	
	public void levelUp() {
		this.addLevel();
		food+=1.5f;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.FOOD_SYNTHESIZER, this.getGenerationPercent());
	}

}
