package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {
	public static int price = 0;

	public CenterModule(Ship inShip) {
		super(inShip, 0.5f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower((float) (3.0f*this.getLevel()));
		this.getShip().addFood((float) (1.0f*this.getLevel()));
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SHIP_CENTER;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	@Override
	public void levelUp() {
		this.addLevel();
	}

}
