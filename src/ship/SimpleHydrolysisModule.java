package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SimpleHydrolysisModule extends ShipModule {

	public static int price = 150;
	
	public SimpleHydrolysisModule(Ship inShip) {
		super(inShip, 0.5f, 4.0f);
		this.getShip().addMaxWater(5.0f);
	}

	private float water = 3.0f;
	@Override
	protected void generateResource() {
		this.getShip().addWater(water);
	}
	
	public float waterProduction() {
		return water*1/this.getGenerationCooldown();
	}
	
	public void levelUp() {
		this.addLevel();
		water+=(1.0f*this.getLevel());
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.SIMPLE_HYDROLYSIS_MODULE, this.getGenerationPercent());
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
