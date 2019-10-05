package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarReactorModule extends ShipModule {

	public static int price = 750;
	
	public SolarReactorModule(Ship inShip) {
		super(inShip, 0.1f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private float power = 1.25f;
	@Override
	protected void generateResource() {
		this.getShip().addPower(power);
	}
	
	public void levelUp() {
		this.addLevel();
		this.power+=0.25f;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SOLAR_REACTOR;
	}

}
