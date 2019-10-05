package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class SimpleHydrolysisModule extends ShipModule {

	public static int price = 150;
	
	public SimpleHydrolysisModule(Ship inShip) {
		super(inShip, 1.5f);
	}

	@Override
	protected void generateResource() {
		this.getShip().addWater(3.0f*this.getModuleLevel());
	}

	@Override
	public BufferedImage getImage() {
		int index = (int)(SpriteCodex.SIMPLE_HYDROLYSIS_MODULE.length*this.getGenerationPercent());
		index%= SpriteCodex.SIMPLE_HYDROLYSIS_MODULE.length;
		return SpriteCodex.SIMPLE_HYDROLYSIS_MODULE[index];
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

}
