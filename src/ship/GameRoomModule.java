package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class GameRoomModule extends ShipModule {
	public static int price = 250;
	
	public GameRoomModule() {
		super(1.0f,0.5f);
		this.setEmployable(false);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private float happy = 0.05f;
	@Override
	protected void generateResource() {
		this.getShip().addLuxuryHappiness(happy);
	}
	
	public void levelUp() {
		this.addLevel();
		happy+=0.025f;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.GAME_ROOM;
	}
}
