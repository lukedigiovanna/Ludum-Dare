package ship;

import java.awt.image.BufferedImage;

public class SolarPanelModule extends ShipModule {

	public static int price = 200;
	
	public SolarPanelModule(Ship inShip) {
		super(inShip, 0.1f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower(0.25f);
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
