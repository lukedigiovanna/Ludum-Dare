package ship;

import java.awt.image.BufferedImage;

import main.SpriteCodex;

public class GameRoomModule extends ShipModule {
	public static int price = 250;
	
	public GameRoomModule(Ship inShip) {
		super(inShip,1.0f);
	}

	@Override
	public int getInitialPrice() {
		return price;
	}

	private float happy = 0.1f;
	@Override
	protected void generateResource() {
		this.getShip().addHappiness(0.1f);
	}
	
	public void levelUp() {
		this.addLevel();
		happy+=0.05f;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteCodex.GAME_ROOM;
	}
}
