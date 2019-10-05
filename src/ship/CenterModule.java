package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {
<<<<<<< HEAD

	public CenterModule(Ship inShip) {
		super(inShip, 0, 0.5f);
=======
	
	public static int price = 0;
	
	public CenterModule(Ship inShip) {
		super(inShip, 0.5f);
>>>>>>> branch 'master' of https://github.com/lukedigiovanna/Ludum-Dare.git
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower((float) (3.0f*Math.log(moduleLevel)));
		this.getShip().addFood((float) (1.0f*Math.log(moduleLevel)));
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SHIP_CENTER;
	}

}
