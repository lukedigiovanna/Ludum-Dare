package game;

import java.awt.*;

public class Game {
	
	public static final int DISPLAY_WIDTH = 640, DISPLAY_HEIGHT = 480;
	
	private Screen currentScreen = Screen.MAIN;
	
	public Game() {
		
	}
	
	/*
	 * main game logic
	 */
	public void gameLoop() {
		
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
