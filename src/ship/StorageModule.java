package ship;

import java.awt.image.BufferedImage;

public class StorageModule extends ShipModule {
	public static int price = 75;
	
	public StorageModule(Ship inShip) {
		super(inShip, 10000.0f);
		this.getShip().addScrapsStorage(100);
	}

	@Override
	protected void generateResource() {
		//generates no resources
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
