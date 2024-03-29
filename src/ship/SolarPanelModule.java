package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarPanelModule extends ShipModule {

	public static int price = 200;
	
	public SolarPanelModule() {
		super(0.1f,0.0f);
	}
	
	public void init() {
		super.init();
		this.getShip().addMaxPower(10.0f);
	}

	private float power = 1.0f;
	@Override
	protected void generateResource() {
		this.getShip().addPower(power);
	}
	
	public float powerProduction() {
		return power * 1/this.getGenerationCooldown();
	}

	public void levelUp() {
		this.addLevel();
		power *= 1.6f;
	}
	
	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.SOLAR_PANEL, this.getGenerationPercent());
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
