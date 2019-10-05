package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarPanelModule extends ShipModule {

	public static int price = 200;
	
	public SolarPanelModule(Ship inShip) {
		super(inShip, 0.1f);
	}

	private float power = 0.5f;
	@Override
	protected void generateResource() {
		this.getShip().addPower(power);
	}

	public void levelUp() {
		this.addLevel();
		power+=0.1f;
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
