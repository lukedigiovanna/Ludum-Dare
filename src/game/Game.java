package game;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.Program;
import main.SpriteCodex;
import misc.ElapsedTime;
import ship.*;

public class Game {
	
	public static final int DISPLAY_WIDTH = 16*50, DISPLAY_HEIGHT = 9*50;
	
	private Screen currentScreen = Screen.MAIN;
	
	public Screen getScreen() {
		return currentScreen;
	}
	
	private ElapsedTime timer;
	
	private Ship ship;
	
	public Game() {
		timer = new ElapsedTime();
		ship = new Ship(this);
		populateStars();
	}
	
	public void init( ) {
		ship = new Ship(this);
	}
	
	/*
	 * main game logic
	 */
	public void gameLoop() {
		if (paused || currentScreen != Screen.GAME)
			return;
		float elapsedTime = timer.mark();
		ship.update(elapsedTime);
		handleStars();
		//System.out.println(ship);
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
	
	private boolean paused = false;
	
	public void togglePause() {
		paused = !paused;
	}
	
	private String menu = "buy"; //"buy" and "upgrade"
	private boolean mouseDown = false;
	public void draw(Graphics g) {
		switch (currentScreen) {
		case MAIN:
			g.setColor(new Color(0,0,50));
			g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			handleStars();
			renderStars(g);
			String s = Program.GAME_NAME;
			g.setFont(new Font("Arial",Font.BOLD, 68));
			g.setColor(Color.GRAY);
			g.drawString(s, DISPLAY_WIDTH/2-g.getFontMetrics().stringWidth(s)/2,DISPLAY_HEIGHT/3);
			s = "Press 'enter' to play";
			g.setFont(new Font("Arial",Font.ITALIC,40));
			g.drawString(s,DISPLAY_WIDTH/2-g.getFontMetrics().stringWidth(s)/2,DISPLAY_HEIGHT/2+DISPLAY_HEIGHT/3);
			
			break;
		case GAME:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			renderStars(g);
			float margin = 0.23f;
			ship.drawShip(g, (int)((1.0-margin)*DISPLAY_WIDTH/2), DISPLAY_HEIGHT/2);
			ship.drawBars(g,10,10);
			ship.drawMessages(g, (int)((1.0-margin)*DISPLAY_WIDTH/2), DISPLAY_HEIGHT/2);
			//draw shop menu
			g.setColor(Color.GRAY);
			int shopWidth = (int)(margin*DISPLAY_WIDTH), shopHeight = DISPLAY_HEIGHT;
			int shopLeft = DISPLAY_WIDTH-shopWidth, shopTop = 0;
			g.fillRect(shopLeft,shopTop,shopWidth,shopHeight);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(shopLeft-10, 0, 10, DISPLAY_HEIGHT);
			g.setFont(new Font("Arial",Font.BOLD | Font.ITALIC, 24));
			
			g.setColor(Color.WHITE);
			g.drawString("SHOP",shopLeft+shopWidth/2-g.getFontMetrics().stringWidth("SHOP")/2,shopTop+40);
			if (menu.contentEquals("buy")) {
				int x = shopLeft, y = shopTop+50;
				g.setFont(new Font("Arial",Font.BOLD,18));
				g.setColor(Color.LIGHT_GRAY);
				g.drawString("BUY", x+shopWidth/2-g.getFontMetrics().stringWidth("BUY")/2, y+20);
				y+=25;
				//add button to switch to upgrades
				int bw = 65, bh = 20;
				int bx = x+shopWidth/2-bw/2, by = y;
				int mx = this.getX(), my = this.getY();
				g.setColor(Color.BLACK);
				g.fillRect(bx, by, bw, bh);
				g.setColor(Color.LIGHT_GRAY);
				if (mx > bx && mx < bx+bw && my > by && my < by+bh) {
					g.setColor(Color.GRAY);
					if (this.isLeftMouseDown()) {
						if (!this.mouseDown)
							this.menu = "upgrades";
						this.mouseDown = true;
					}
				}
				g.fillRect(bx+1, by+1, bw-2, bh-2);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial",Font.PLAIN,14));
				g.drawString("upgrades", bx+bw/2-g.getFontMetrics().stringWidth("upgrades")/2, by+ bh-6);
				y+=bh+5;
				drawShopItem(g, "Living Quarters", LivingQuartersModule.price,"", x, y);
				y+=30;
				drawShopItem(g, "Potato Farm", PotatoFarmModule.price, "food", x, y);
				y+=30;
				drawShopItem(g, "Basic Hydrolysis",SimpleHydrolysisModule.price, "water", x, y);
				y+=30;
				drawShopItem(g, "Solar Panels", SolarPanelModule.price, "power", x, y);
				y+=30;
				drawShopItem(g, "Scrap Storage", StorageModule.price, "scraps", x, y);
				y+=30;
				drawShopItem(g, "Game Room", GameRoomModule.price, "happiness", x, y);
				y+=30;
				drawShopItem(g, "Food Synthesizer", FoodSynthesizerModule.price, "food", x, y);
				y+=30;
				drawShopItem(g, "Super Hydrolysis", SuperHydrolysisModule.price, "water", x, y);
				y+=30;
				drawShopItem(g, "Solar Reactor",SolarReactorModule.price, "power", x,y);
				y+=30;
				drawShopItem(g, "Scrap Synthesizer", ScrapSynthesizerModule.price, "scraps", x, y);
				y+=30;
				drawShopItem(g, "Park", ParkModule.price, "happiness", x, y);
			} else {
				int x = shopLeft, y = shopTop+50;
				g.setFont(new Font("Arial",Font.BOLD,18));
				g.setColor(Color.LIGHT_GRAY);
				g.drawString("UPGRADES", x+shopWidth/2-g.getFontMetrics().stringWidth("UPGRADES")/2, y+20);
				y+=25;
				//add button to switch to upgrades
				int bw = 45, bh = 20;
				int bx = x+shopWidth/2-bw/2, by = y;
				int mx = this.getX(), my = this.getY();
				g.setColor(Color.BLACK);
				g.fillRect(bx, by, bw, bh);
				g.setColor(Color.LIGHT_GRAY);
				if (mx > bx && mx < bx+bw && my > by && my < by+bh) {
					g.setColor(Color.GRAY);
					if (this.isLeftMouseDown()) {
						if (!this.mouseDown)
							this.menu = "buy";
						this.mouseDown = true;
					}
				}
				g.fillRect(bx+1, by+1, bw-2, bh-2);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial",Font.PLAIN,14));
				g.drawString("buys", bx+bw/2-g.getFontMetrics().stringWidth("buys")/2, by+ bh-6);
			
				y += bh + 5;
				
				//draw mods
				for (ShipModule mod : ship.getModules()) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Arial",Font.PLAIN,12));
					String title = mod.getName() + " • lvl. "+mod.getLevel();
					g.drawString(title, x, y+12);
					y+=30;
				}
			}
			if (this.mouseDown && !this.isLeftMouseDown()) {
				this.mouseDown = false;
			}
			
			if (paused) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial",Font.BOLD,68));
				g.drawString("PAUSED", DISPLAY_WIDTH/2-g.getFontMetrics().stringWidth("PAUSED")/2,DISPLAY_HEIGHT/3);
			}
			break;
		case GAME_OVER:
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			g.setFont(new Font("Arial",Font.BOLD,68));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER!", DISPLAY_WIDTH/2-g.getFontMetrics().stringWidth("GAME OVER!")/2, DISPLAY_HEIGHT/3);
			g.setColor(Color.LIGHT_GRAY);
			String s1 = "Press 'enter' to return to main screen";
			g.setFont(new Font("Arial",Font.ITALIC,40));
			g.drawString(s1, DISPLAY_WIDTH/2-g.getFontMetrics().stringWidth(s1)/2, DISPLAY_HEIGHT/2+DISPLAY_HEIGHT/3);
			break;
		}
	}
		private int starCount = 50;
		private int movingVelocity = 3;
		private Point[] stars = new Point[starCount];
		public void populateStars()	{
			//produce stars in random locations
			for(int i = 0; i<stars.length; i++)	{
				generateStar(i, false);
			}
			System.out.print(stars[0].getX() + " " + stars[0].getY());
		}
			private void generateStar(int index, boolean isRight)	{
				Point newStar;
				if(isRight)
					newStar = new Point((int)(DISPLAY_WIDTH), (int)(Math.random()*DISPLAY_HEIGHT));
				else
					newStar = new Point((int)(Math.random()*DISPLAY_WIDTH), (int)(Math.random()*DISPLAY_HEIGHT));
				stars[index] = newStar;
			}
		public void handleStars()	{
			for(int i = 0; i<stars.length; i++)	{
				stars[i].x-=movingVelocity;
			if(stars[i].x<0)
				generateStar(i, true);
			}
		}
		int starSize = 5;
		public void renderStars(Graphics g)	{
			g.setColor(Color.white);
			for(Point star:stars)
				g.fillOval(((int)star.getX()), ((int)star.getY()), starSize, starSize);
		}
	
	private boolean bought = false;
	private void drawShopItem(Graphics g, String name, int price, String resource, int x, int y) {
		//also looks for mouse input
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,12));
		g.drawString(name, x, y+12);
		BufferedImage resourceImage = null;
		switch (resource) {
		case "food":
			resourceImage = SpriteCodex.FOOD_SYMBOL;
			break;
		case "water":
			resourceImage = SpriteCodex.WATER_SYMBOL;
			break;
		case "power":
			resourceImage = SpriteCodex.POWER_SYMBOL;
			break;
		case "scraps":
			resourceImage = SpriteCodex.SCRAPS_SYMBOL;
			break;
		case "happiness":
			resourceImage = SpriteCodex.HAPPINESS_SYMBOL;
			break;
		}
		if (resourceImage != null) {
			//draw next to the name string
			g.drawImage(resourceImage, x + g.getFontMetrics().stringWidth(name)+5, y, 12, 12, null);
		}
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
					case "Solar Panels":
						ship.addModule(new SolarPanelModule(ship));
						break;
					case "Scrap Storage":
						ship.addModule(new StorageModule(ship));
						break;
					case "Game Room":
						ship.addModule(new GameRoomModule(ship));
						break;
					case "Living Quarters":
						ship.addModule(new LivingQuartersModule(ship));
						break;
					case "Food Synthesizer":
						ship.addModule(new FoodSynthesizerModule(ship));
						break;
					case "Super Hydrolysis":
						ship.addModule(new SuperHydrolysisModule(ship));
						break;
					case "Solar Reactor":
						ship.addModule(new SolarReactorModule(ship));
						break;
					case "Park":
						ship.addModule(new ParkModule(ship));
						break;
					case "Scrap Synthesizer":
						ship.addModule(new ScrapSynthesizerModule(ship));
						break;
					}
					ship.useScraps(price);
				}
			}
		}
		if (bought && !this.isLeftMouseDown())
			bought = false;
		g.fillRect(buttonX+1, buttonY+1, buttonWidth-2, buttonHeight-2);
		g.setColor(Color.BLACK);
		g.drawString("BUY", buttonX+buttonWidth/2-g.getFontMetrics().stringWidth("BUY")/2, buttonY + buttonHeight-3);
		g.setColor(Color.RED);
		if (ship.getCurrentScraps() >= price) //we can afford
			g.setColor(Color.GREEN);
		g.drawImage(SpriteCodex.SCRAPS_SYMBOL, buttonX+buttonWidth+5, buttonY+buttonHeight-3-12,12, 12, null);
		g.drawString(""+price, buttonX+buttonWidth+5+12, buttonY+buttonHeight-3);
	}
	
	public void setScreen(Screen screen) {
		this.currentScreen = screen;
	}
	
	public int tickSpeed() {
		return 50;
	}
}
