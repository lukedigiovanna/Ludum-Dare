package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {

	public CenterModule(Ship inShip) {
		super(inShip, 0, 0.5f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower(3.0f);
		this.getShip().addFood(1.0f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SHIP_CENTER;
	}

}
