package ship;

import java.awt.image.BufferedImage;

public class PotatoFarmModule extends ShipModule {

	public PotatoFarmModule(Ship inShip, int price, float generationCooldown) {
		super(inShip, price, generationCooldown);
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
