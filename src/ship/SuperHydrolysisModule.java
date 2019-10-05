package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SuperHydrolysisModule extends ShipModule {

	public static int price = 600;
	
	public SuperHydrolysisModule(Ship inShip) {
		super(inShip, 0.25f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	@Override
	protected void generateResource() {
		this.getShip().addWater(1.5f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SUPER_HYDROLYSIS;
	}

}
