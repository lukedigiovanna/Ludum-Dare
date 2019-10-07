package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class ScrapSynthesizerModule extends ShipModule {

	public static int price = 200;
	
	public ScrapSynthesizerModule(Ship inShip) {
		super(inShip, 1.0f, 10.0f);
	}

	@Override
	public void levelUp() {
		this.addLevel();
		scraps+=20;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}
	
	public float scrapsProduction() {
		return scraps * 1/getGenerationCooldown();
	}

	private int scraps = 20;
	@Override
	protected void generateResource() {
		this.getShip().addScraps(scraps);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SCRAP_SYNTHESIZER;
	}

}
