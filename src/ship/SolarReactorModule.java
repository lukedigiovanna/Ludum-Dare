package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarReactorModule extends ShipModule {

	public static int price = 750;
	
	public SolarReactorModule(Ship inShip) {
		super(inShip, 0.01f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower(1.25f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SOLAR_REACTOR;
	}

}
