package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class FoodSynthesizer extends ShipModule {

	public static int price = 500;
	
	public FoodSynthesizer(Ship inShip) {
		super(inShip, 0.5f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private float food = 5.0f;
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
		return SpriteCodex.SYNTHETIC_MEATS;
	}

}
