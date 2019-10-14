package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SimpleHydrolysisModule extends ShipModule {

	public static int price = 150;
	
	public SimpleHydrolysisModule() {
		super(0.5f, 4.0f);
	}
	
	public void init() {
		super.init();
		this.getShip().addMaxWater(4.0f);
	}

	private float water = 1.5f;
	@Override
	protected void generateResource() {
		this.getShip().addWater(water);
	}
	
	public float waterProduction() {
		return water*1/this.getGenerationCooldown();
	}
	
	public void levelUp() {
		this.addLevel();
		water+=(0.75f*this.getLevel());
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
