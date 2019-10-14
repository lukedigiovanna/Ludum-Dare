package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class ScrapSynthesizerModule extends ShipModule {

	public static int price = 200;
	
	public ScrapSynthesizerModule() {
		super(0.1f, 10.0f);
	}

	@Override
	public void levelUp() {
		this.addLevel();
		scraps+=2;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
	
	public float scrapsProduction() {
		return scraps * 1/getGenerationCooldown();
	}

	private int scraps = 2;
	@Override
	protected void generateResource() {
		this.getShip().addScraps(scraps);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.get(SpriteCodex.SCRAPS_SYNTHESIZER, this.getGenerationPercent());
	}

}
