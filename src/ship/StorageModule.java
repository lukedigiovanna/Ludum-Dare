package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class StorageModule extends ShipModule {
	public static int price = 75;
	
	public StorageModule() {
		super(10000.0f,0.5f);
		this.setEmployable(false);
	}
	
	public void init() {
		super.init();
		this.getShip().addScrapsStorage(150);
	}

	@Override
	protected void generateResource() {
		//generates no resources
	}
	
	public void levelUp() {
		this.addLevel();
		this.getShip().addScrapsStorage(100);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SCRAPS_STORAGE;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
}
