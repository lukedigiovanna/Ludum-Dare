package game;

import java.awt.*;

import misc.ElapsedTime;
import ship.Ship;

public class Game {
	
	public static final int DISPLAY_WIDTH = 16 * 50, DISPLAY_HEIGHT = 9 * 50;
	
	private Screen currentScreen = Screen.GAME;
	
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
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			ship.draw(g, DISPLAY_WIDTH/2, DISPLAY_HEIGHT/2);
			ship.drawBars(g,10,10);
			break;
		case GAME_OVER:
			break;
		}
	}
	
	public int tickSpeed() {
		return 50;
	}
}
