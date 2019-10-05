package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {

	public CenterModule(Ship inShip) {
<<<<<<< HEAD
		super(inShip, 0, 10.0f);
=======
		super(inShip, 0, 0.5f);
>>>>>>> branch 'master' of https://github.com/lukedigiovanna/Ludum-Dare.git
		// TODO Auto-generated constructor stub
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
