package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarReactorModule extends ShipModule {

	public static int price = 750;
	
	public SolarReactorModule() {
		super(0.1f,0.0f);
	}
	
	public void init() {
		super.init();
		this.getShip().addMaxPower(25.0f);
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
