package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {

	public CenterModule(Ship inShip) {
		super(inShip, 0, 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower(3.0f);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SHIP_CENTER;
	}

}
