package game;

import java.awt.*;

import misc.ElapsedTime;
import ship.Ship;

public class Game {
	
	public static final int DISPLAY_WIDTH = 640, DISPLAY_HEIGHT = 480;
	
	private Screen currentScreen = Screen.MAIN;
	
	private ElapsedTime timer;
	
	private Ship ship;
	
	public Game() {
		timer = new ElapsedTime();
		ship = new Ship();
	}
	
	/*
	 * main game logic
	 */
	public void gameLoop() {
		float elapsedTime = timer.mark();
		ship.update(elapsedTime);
		System.out.println(ship);
	}
	
	public enum Screen {
		MAIN,
		GAME,
		GAME_OVER
	}
	
	public void draw(Graphics g) {
		switch (currentScreen) {
		case MAIN:
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			break;
		case GAME:
			break;
		case GAME_OVER:
			break;
		}
	}
	
	public int tickSpeed() {
		return 50;
	}
}
