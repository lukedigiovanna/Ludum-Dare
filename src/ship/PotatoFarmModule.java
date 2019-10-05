package ship;

import java.awt.image.BufferedImage;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 2.0f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addFood(2.0f);
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
