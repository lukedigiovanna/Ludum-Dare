package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SolarPanelModule extends ShipModule {

	public static int price = 200;
	
	public SolarPanelModule(Ship inShip) {
		super(inShip, 0.01f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower(0.25f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SOLAR_PANEL;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
