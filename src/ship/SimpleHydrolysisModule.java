package ship;

import java.awt.image.BufferedImage;

public class SimpleHydrolysisModule extends ShipModule {

	public static int price = 150;
	
	public SimpleHydrolysisModule(Ship inShip) {
		super(inShip, 1.5f);
		}

	@Override
	protected void generateResource() {
		
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
