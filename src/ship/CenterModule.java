package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {
	public static int price = 0;

	public CenterModule(Ship inShip) {
		super(inShip, 0.5f);
	}
<<<<<<< HEAD

=======
	
>>>>>>> branch 'master' of https://github.com/lukedigiovanna/Ludum-Dare.git
	@Override
	protected void generateResource() {
		this.getShip().addPower((float) (3.0f*this.getModuleLevel()));
		this.getShip().addFood((float) (1.0f*this.getModuleLevel()));
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SHIP_CENTER;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
