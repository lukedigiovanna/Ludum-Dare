package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class LivingQuartersModule extends ShipModule {

	public static int price = 250;
	
	public LivingQuartersModule(Ship inShip) {
		super(inShip, 999999.0f, 0.5f);
		setEmployable(false);
		this.getShip().addMaxPopulation(4);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	@Override
	protected void generateResource() {
		//generates nothing
	}

	public void levelUp() {
		this.addLevel();
		System.out.println("upgraded living quarters");
		this.getShip().addMaxPopulation((int)(this.getLevel()*Math.pow(this.getLevel(),2)));
	}
	
	@Override
	public BufferedImage getImage() {
		return SpriteCodex.LIVING_QUARTERS;
	}
}
