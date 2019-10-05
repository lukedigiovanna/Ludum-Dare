package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SimpleHydrolysisModule extends ShipModule {

	public static int price = 150;
	
	public SimpleHydrolysisModule(Ship inShip) {
		super(inShip, 1.5f);
	}

	private float water = 3.0f;
	@Override
	protected void generateResource() {
		this.getShip().addWater(3.0f);
	}
	
	public void levelUp() {
		this.addLevel();
		water+=1.0f;
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
