package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SuperHydrolysisModule extends ShipModule {

	public static int price = 600;
	
	public SuperHydrolysisModule() {
		super(0.25f, 8.5f);
	}

	public void init() {
		super.init();
		this.getShip().addMaxWater(15.0f);
	}
	
	@Override
	public int getInitialPrice() {
		return price;
	}

	public void levelUp() {
		this.addLevel();
		water+=0.5f;
	}
	
	private float water = 1.5f;
	@Override
	protected void generateResource() {
		this.getShip().addWater(water);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SUPER_HYDROLYSIS;
	}

}
