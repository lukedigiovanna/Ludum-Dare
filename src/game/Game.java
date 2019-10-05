package game;

import java.awt.*;

import misc.ElapsedTime;
import ship.Ship;
import main.Panel;

public class Game {
	
	public static final int DISPLAY_WIDTH = 16*50, DISPLAY_HEIGHT = 9*50;
	
	private Screen currentScreen = Screen.GAME;
	
	private ElapsedTime timer;
	
	private Ship ship;
	
	public Game() {
		timer = new ElapsedTime();
		ship = new Ship(this);
	}
	
	/*
	 * main game logic
	 */
	public void gameLoop() {
		float elapsedTime = timer.mark();
		ship.update(elapsedTime);
		System.out.println(ship);
		System.out.println(getX()+", "+getY());
	}
	
	public enum Screen {
		MAIN,
		GAME,
		GAME_OVER
	}
	
	public int getX() {
		float perc = main.Main.panel.getMouseXPercent();
		return (int)(perc*DISPLAY_WIDTH);
	}
	
	public int getY() {
		float perc = main.Main.panel.getMouseYPercent();
		return (int)(perc*DISPLAY_HEIGHT);
	}
	
	public boolean isLeftMouseDown() {
		return main.Main.panel.isMouseDown();
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
			float margin = 0.20f;
			ship.draw(g, (int)((1.0-margin)*DISPLAY_WIDTH/2), DISPLAY_HEIGHT/2);
			ship.drawBars(g,10,10);
			//draw shop menu
			g.setColor(Color.GRAY);
			int shopWidth = (int)(margin*DISPLAY_WIDTH), shopHeight = DISPLAY_HEIGHT;
			int shopLeft = DISPLAY_WIDTH-shopWidth, shopTop = 0;
			g.fillRect(shopLeft,shopTop,shopWidth,shopHeight);
			break;
		case GAME_OVER:
			break;
		}
	}
	
	public int tickSpeed() {
		return 50;
	}
}
