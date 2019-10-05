package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SyntheticMeatsModule extends ShipModule {

	public static int price = 500;
	
	public SyntheticMeatsModule(Ship inShip) {
		super(inShip, 0.5f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	@Override
	protected void generateResource() {
		this.getShip().addFood(5.0f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SYNTHETIC_MEATS;
	}

}
