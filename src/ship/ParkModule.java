package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class ParkModule extends ShipModule {

	public static int price = 400;
	
	public ParkModule(Ship inShip) {
		super(inShip, 1.0f, 1.0f);
	}

	@Override
	public void levelUp() {
		this.addLevel();
		happy += 0.05f;
	}

	@Override
	public int getInitialPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	private float happy = 0.1f;
	@Override
	protected void generateResource() {
		this.getShip().addLuxuryHappiness(happy);
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return SpriteCodex.PARK;
	}

}
