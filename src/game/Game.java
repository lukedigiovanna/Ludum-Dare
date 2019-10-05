package game;

import java.awt.*;

import misc.ElapsedTime;
import ship.*;

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
			g.setFont(new Font("Arial",Font.BOLD | Font.ITALIC, 24));
			g.setColor(Color.WHITE);
			g.drawString("SHOP",shopLeft+shopWidth/2-g.getFontMetrics().stringWidth("SHOP")/2,shopTop+40);
			int x = shopLeft, y = shopTop+40;
			drawShopItem(g, "Potato Farm", PotatoFarmModule.price, x, y);
			y+=30;
			drawShopItem(g, "Basic Hydrolysis",SimpleHydrolysisModule.price, x, y);
			break;
		case GAME_OVER:
			break;
		}
	}
	
	private boolean bought = false;
	private void drawShopItem(Graphics g, String name, int price, int x, int y) {
		//also looks for mouse input
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,12));
		g.drawString(name, x, y+12);
		int buttonX = x + 20, buttonY = y+16;
		int buttonWidth = 30, buttonHeight = 15;
		g.setColor(Color.BLACK);
		g.fillRect(buttonX, buttonY, buttonWidth, buttonHeight);
		g.setColor(Color.LIGHT_GRAY);
		int mx = this.getX(), my = this.getY();
		if (mx > buttonX && mx < buttonX+buttonWidth && my > buttonY && my < buttonY+buttonHeight) {
			g.setColor(Color.GRAY);
			if (this.isLeftMouseDown()) {
				//buy
				if (!bought && ship.getCurrentScraps() >= price) {
					bought = true;
					switch (name) { 
					case "Potato Farm":
						ship.addModule(new PotatoFarmModule(ship));
						break;
					case "Basic Hydrolysis":
						ship.addModule(new SimpleHydrolysisModule(ship));
						break;
					}
					ship.useScraps(price);
				}
			}
		}
		g.fillRect(buttonX+1, buttonY+1, buttonWidth-2, buttonHeight-2);
		g.setColor(Color.BLACK);
		g.drawString("BUY", buttonX+buttonWidth/2-g.getFontMetrics().stringWidth("BUY")/2, buttonY + buttonHeight-3);
		g.setColor(Color.RED);
		if (ship.getCurrentScraps() >= price) //we can afford
			g.setColor(Color.GREEN);
		g.drawString("$"+price, buttonX+buttonWidth+5, buttonY+buttonHeight-3);
		bought = bought && !this.isLeftMouseDown();
	}
	
	public int tickSpeed() {
		return 50;
	}
}
