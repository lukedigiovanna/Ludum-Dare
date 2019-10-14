package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class CenterModule extends ShipModule {
	public static int price = 100;

	public CenterModule() {
		super(0.5f, 0.0f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addPower((float) (0.75f*this.getLevel()));
		this.getShip().addFood((float) (0.5f*this.getLevel()));
		this.getShip().addScraps(5*this.getLevel());
	}

	public float powerProduction() {
		return 0.75f*getLevel()*1/this.getGenerationCooldown();
	}
	
	public float foodProduction() {
		return 0.5f*getLevel()*1/this.getGenerationCooldown();
	}
	
	public float scrapsProduction() {
		return 5.0f*this.getLevel()*1/this.getGenerationCooldown();
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
