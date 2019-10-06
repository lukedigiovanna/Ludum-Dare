package ship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import misc.MathUtils;
import main.SpriteCodex;

public class Ship {
	
	private int population = 2; //start with 2 people
	private int employedPopulation = 0;
	private int maxPopulation = 4;
	
	private float maxPower = 50.0f, power = 50.0f;
	private float powerDepletionRate = 1.0f; // per day from normal ship functioning
											//any added is from modules
	
	private float maxWater = 10.0f, water = 10.0f; //available water in litres
	private final float waterConsumedPerPerson = 1.0f; //per day
	
	private float maxFood = 10.0f, food = 10.0f; //kilograms of food
	private final float foodConsumedPerPerson = 1.00f; //per day
	
	private int maxScraps = 250, scraps = 2500; //kilograms of scraps
	
	private float happiness = 1.0f; // 0.0 - 1.0 scale
	private float necessityHappiness = 0.75f;
	private float luxuryHappiness = 0.0f;
	
	private float secondsPerDay = 10.0f; //2 minutes per day
	private float day = 0;
	
	private List<ShipModule> modules;
	
	private Game game;
	
	private PlanetTimer pt = new PlanetTimer(this);
	//f
	public Ship(Game game) {
		this.game = game;
		modules = new ArrayList<ShipModule>();
		addModule(new CenterModule(this));
	}
	
	public void addModule(ShipModule mod) {
		//attempt to employ upon addition
		mod.employ();
		modules.add(mod);
	}
	
	public void updateHappiness(float dt) {
		float water = this.getWaterPercent();
		float food = this.getFoodPercent();
		float employed = this.employedPopulation/this.population;
		float avg = (water+food+employed)/3.0f*necessityHappiness;
		this.luxuryHappiness-=0.001f*dt;
		if (this.luxuryHappiness < 0)
			this.luxuryHappiness = 0;
		this.happiness = this.luxuryHappiness + avg;
		if (this.happiness < 0) this.happiness = 0.0f;
		if (this.happiness > 1) this.happiness = 1.0f;
	}
	
	public void repopulate() {
		int prevPop = population;
		int matingPopulation = (int)(population*MathUtils.randomInRange(0.2, 1.0));
		if (population >= 2 && population < 4)
			matingPopulation = 2;
		for (int i = 0; i < matingPopulation; i+=2) {
			populationIncrement();
			if (Math.random() < 0.05) //twins
				populationIncrement();
		}
		int deltaPop = population-prevPop;
		
		if (deltaPop == 0)
			return;
		
		popMessage("New people born!","+"+deltaPop+" population");
		//re try employing
		for (ShipModule mod : modules)
			mod.employ();
	}
	
	private void populationIncrement() {
		this.population = MathUtils.max(this.population+1, this.maxPopulation);
	}
	
	private float messagePersist = 5.0f; //seconds.
	private float messageTimer = 0.0f;
	private String msgTitle = "", msgSubtitle = "";
	
	public void popMessage(String title, String subtitle) {
		messageTimer = 0.01f;
		msgTitle = title;
		msgSubtitle = subtitle;
	}
	
	private float repopulationTime = 10.0f;
	private float repopulationTimer = 0.0f;
	public void update(float elapsedTime) {
		if (population <= 0)
			gameOver();
		
		float dayPrev = day;
		day += (elapsedTime/secondsPerDay);
		float elapsedDay = day-dayPrev;
		
		repopulationTimer += elapsedDay;
		if (repopulationTimer >= repopulationTime) {
			repopulationTimer = 0.0f;
			repopulate();
		}
		
		pt.update(elapsedDay);
		
		if (messageTimer > 0.0f)
			messageTimer+=elapsedTime;
		if (messageTimer >= messagePersist) 
			messageTimer = 0.0f;
		
		//power consumed by the ship for movement
		float powerUsed = elapsedDay * powerDepletionRate;
		power -= powerUsed;
		if (power < 0) power = 0;
		
		//each person consumes 1.5 litres of water a day
		float waterUsed = population * (elapsedDay * waterConsumedPerPerson);
		water -= waterUsed;
		if (water < 0) water = 0;
		
		//each person consumes 1 kg of food a day
		float foodUsed = population * (elapsedDay * this.foodConsumedPerPerson);
		food -= foodUsed;
		if (food < 0) food = 0;
		
		updateShipModules(elapsedDay);
		
		updateHappiness(elapsedDay);
		
		//update population based on food and water
		if (this.getWaterPercent() < 0.05) {
			if (Math.random() < 0.333333*elapsedDay) {
				killSomeone();
				popMessage("DEATH!","Someone died of thirst");
			}
		}
		
		if (this.getFoodPercent() < 0.05) {
			if (Math.random() < 0.1*elapsedDay) {
				killSomeone();
				popMessage("DEATH!","Someone died of hunger");
			}
		}
	}
	
	public void killSomeone() {
		population--;
		for (int i = modules.size()-1; i >= 0; i--) {
			if (modules.get(i).isEmployed() && modules.get(i).isEmployable()) {
				modules.get(i).unemploy();
				this.employedPopulation--;
				break;
			}
		}
	}
	
	public void gameOver() {
		game.setScreen(Game.Screen.GAME_OVER);
	}
	
	private void updateShipModules(float elapsedDay)	{
		for(ShipModule module:modules)
			module.update(elapsedDay);
	}
	
	public void drawMessages(Graphics g, int centerX, int centerY) {
		//draw message if there is one
		if (messageTimer > 0.0f) {
			float percent = messageTimer-(messagePersist-1.0f);
			int alpha = 255-(int)(255*percent);
			if (percent < 0)
				alpha = 255;
			g.setFont(new Font("Arial",Font.BOLD | Font.ITALIC, 60));
			g.setColor(new Color(255,255,255,alpha));
			g.drawString(msgTitle, centerX-g.getFontMetrics().stringWidth(msgTitle)/2-2, centerY-100-2);
			g.setColor(new Color(255,0,0,alpha));
			g.drawString(msgTitle, centerX-g.getFontMetrics().stringWidth(msgTitle)/2, centerY-100);
			g.setFont(new Font("Arial",Font.PLAIN,48));
			g.setColor(new Color(255,255,255,alpha));
			g.drawString(msgSubtitle,centerX-g.getFontMetrics().stringWidth(msgSubtitle)/2-2,centerY-30-2);
			g.setColor(new Color(125,125,125,alpha));
			g.drawString(msgSubtitle,centerX-g.getFontMetrics().stringWidth(msgSubtitle)/2,centerY-30);
		}
	}
	
	public void drawShip(Graphics g, int centerX, int centerY) {
		int ind = 0;
		int size = 64;
		for (ShipModule module : modules) {
			int x = 0, y = 0;
			if (ind == 0) {
				x = centerX-size/2;
				y = centerY-size/2;
			}
			else {
				int nInd = (ind-1)%4;
				int dist = (ind-1)/4+1;
				switch (nInd) {
				case 0: //up
					x = centerX-size/2;
					y = centerY-size/2-dist*size;
					break;
				case 1: //right
					x = centerX-size/2+dist*size;
					y = centerY-size/2;
					break;
				case 2: //down
					x = centerX-size/2;
					y = centerY-size/2+dist*size;
					break;
				case 3: //left
					x = centerX-size/2-dist*size;
					y = centerY-size/2;
					break;
				}
			}
			g.drawImage(module.getImage(), x, y, size, size, null);
			BufferedImage person = SpriteCodex.PERSON;
			int personX = x+size/2-(person.getWidth()), personY = y+size/2-person.getHeight();
			if (module.isEmployed() && module.isEmployable())
				g.drawImage(person, personX, personY, person.getWidth()*2, person.getHeight()*2, null);
			ind++;
		}
	}
	
	private void drawBar(Graphics g, int x, int y, int width, int height, float percent, Color color) {
		g.setFont(new Font("Arial",Font.BOLD,12));
		g.setColor(Color.GRAY);
		g.fillRect(x-2, y-2, width+4, height+4);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(color);
		g.fillRect(x, y, (int)(width * percent), height);
		if(game.getX()>x && game.getX()<x+width&&game.getY()>y &&game.getY()<y+height)	{
			g.setColor(Color.black);
			g.drawString((int)(percent*100)+"%", x+width/2-5, y+12);
		}
	}
	
	public List<ShipModule> getModules() {
		return this.modules;
	}
	
	/*
	 * top left x and y
	 */
	String timeSuffix = "Am";
	public void drawBars(Graphics g, int x, int y) {
		int initX = x, initY = y;
		//energy then water then food then scraps
		int height = 20;
		int barLength = 55;
		g.drawImage(SpriteCodex.POWER_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g,x,y,barLength,height,this.getPowerPercent(),Color.YELLOW);
		x += 60;
		g.drawImage(SpriteCodex.WATER_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g,x,y,barLength,height,this.getWaterPercent(),Color.BLUE);
		x += 60;
		g.drawImage(SpriteCodex.FOOD_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g, x, y, barLength, height, this.getFoodPercent(), Color.ORANGE);
		x+= 60;
		g.drawImage(SpriteCodex.HAPPINESS_SYMBOL, x, y, height, height, null);
		x+=25;
		this.drawBar(g, x, y, barLength, height, this.happiness, Color.GREEN);
		x+=60;
		g.drawImage(SpriteCodex.SCRAPS_SYMBOL, x, y, height, height, null);
		g.setColor(Color.WHITE);
		x += 25;
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		g.drawString(this.scraps+"/"+this.maxScraps,x,y+height);
		
		//draw day
		x = initX; y = initY+height+3;
		int hour = (int)(day%1.0*24);
		int minute = (int)(day%1.0*24%1.0*60);
		if(hour >= 12)
			timeSuffix = " pm";
		else
			timeSuffix = " am";
		if (hour > 12)
			hour -= 12;
		if (hour == 0)
			hour = 12;
		g.drawString("Day: "+ ((int)day) +" Hour: "+ hour + ":" +  minute + timeSuffix ,x, y+height);
		
		//draw the population
		y += 21;
		g.drawString("Population: "+this.population+"/"+this.maxPopulation, x, y + 18);
		y += 21;
		g.setColor(Color.GREEN);
		String empStr = "Employed: "+this.employedPopulation;
		g.drawString(empStr, x, y + 18);
		g.setColor(Color.RED);
		y += 21;
		g.drawString("Unemployed: "+(population-this.employedPopulation), x, y+18);
	}
	
	public boolean hasPower(float amt) {
		return power >= amt;
	}
	
	public void usePower(float use) {
		this.power-=use;
		if (this.power < 0) this.power = 0;
	}
	
	public void increasePowerDepletion(float add) {
		this.powerDepletionRate += add;
	}
	
	public void addMaxWater(float water) {
		this.maxWater+=water;
	}
	
	public void addMaxPower(float power) {
		this.maxPower+=power;
	}
	
	public void addMaxFood(float food) {
		this.maxFood+=food;
	}
	
	public void addHappiness(float val) {
		this.happiness = MathUtils.max(this.happiness+val, 1.0f);
	}
	
	public float getCurrentPower() {
		return power;
	}
	
	public float getMaxPower() {
		return maxPower;
	}
	
	public float getPowerPercent() {
		return this.power/this.maxPower;
	}
	
	public void addPower(float add) {
		this.power = MathUtils.max(this.power+add,this.maxPower);
	}
	
	public float getCurrentWater() {
		return water;
	}
	
	public float getMaxWater() {
		return maxWater;
	}
	
	public float getWaterPercent() {
		return this.water/this.maxWater;
	}
	
	public void addWater(float add) {
		this.water = MathUtils.max(this.water+add, this.maxWater);
	}
	
	public float getCurrentFood() {
		return food;
	}
	
	public float getMaxFood() {
		return maxFood;
	}
	
	public float getFoodPercent() {
		return this.food/this.maxFood;
	}
	
	public void addFood(float add) {
		this.food = MathUtils.max(this.food+add, this.maxFood);
	}
	
	public void addScraps(int add) {
		this.scraps = MathUtils.max(this.scraps+add, this.maxScraps);
	}
	
	public void useScraps(int use) {
		this.scraps -= use;
	}
	
	public void addScrapsStorage(int add) {
		this.maxScraps+=add;
	}
	
	public int getCurrentScraps() {
		return this.scraps;
	}
	
	public float getHappiness() {
		return this.happiness;
	}
	
	public void addLuxuryHappiness(float val) {
		this.luxuryHappiness = MathUtils.max(this.luxuryHappiness+val, 0.25f);
	}
	
	public void addMaxPopulation(int inc) {
		this.maxPopulation+=inc;
	}
	
	public boolean employ() {
		if (this.employedPopulation < this.population) {
			this.employedPopulation++;
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String s = "SHIP STATUS:\n";
		s += "Population: "+this.population+"\n";
		s += "Power: "+this.power+"/"+this.maxPower+" ("+this.getPowerPercent()*100+")\n";
		s += "Water: "+this.water+"/"+this.maxWater+" ("+this.getWaterPercent()*100+")\n";
		s += "Food: "+this.food+"/"+this.maxFood+" ("+this.getFoodPercent()*100+")\n";
		return s;
	}
}