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
		scraps+=40;
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private int scraps;
	@Override
	protected void generateResource() {
		this.getShip().addScraps(scraps);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.SCRAP_SYNTHESIZER;
	}

}
