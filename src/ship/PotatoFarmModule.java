package ship;

import java.awt.image.BufferedImage;

public class PotatoFarmModule extends ShipModule {

	public static int price = 100;
	
	public PotatoFarmModule(Ship inShip) {
		super(inShip, 1.0f);
	}

	@Override
	protected void generateResource() {
		
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
