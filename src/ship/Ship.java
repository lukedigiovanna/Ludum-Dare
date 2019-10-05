package ship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import misc.MathUtils;
import main.SpriteCodex;

public class Ship {
	
	private int population = 2; //start with 2 people
	
	private float maxPower = 50.0f, power = 50.0f;
	private float powerDepletionRate = 10.0f; // per day
	
	private float maxWater = 10.0f, water = 5.0f; //available water in litres
	private final float waterConsumedPerPerson = 1.5f; //per day
	
	private float maxFood = 10.0f, food = 10.0f; //kilograms of food
	private final float foodConsumedPerPerson = 1.0f; //per day
	
	private int maxScraps = 100, scraps = 0; //kilograms of food
	
	private int secondsPerDay = 10; //2 minutes per day
	private float day = 0;
	
	private List<ShipModule> modules;
	
	
	private PlanetTimer pt = new PlanetTimer(this);
	
	public Ship() {
		modules = new ArrayList<ShipModule>();
		modules.add(new CenterModule(this));
		modules.get(0).employ();
	}
	
	private float messagePersist = 5.0f; //seconds.
	private float messageTimer = 0.0f;
	private String msgTitle = "", msgSubtitle = "";
	
	public void popMessage(String title, String subtitle) {
		messageTimer+=0.01f;
		msgTitle = title;
		msgSubtitle = subtitle;
	}
	
	public void update(float elapsedTime) {
		float dayPrev = day;
		day += (elapsedTime/secondsPerDay);
		float elapsedDay = day-dayPrev;
		
		pt.update(elapsedDay);
		
		if (messageTimer > 0.0f)
			messageTimer+=elapsedTime;
		if (messageTimer >= messagePersist) 
			messageTimer = 0.0f;
		
		//power consumed by the ship for movement
		float powerUsed = elapsedDay * powerDepletionRate;
		power -= powerUsed;
		
		//each person consumes 1.5 litres of water a day
		float waterUsed = population * (elapsedDay * waterConsumedPerPerson);
		water -= waterUsed;
		
		//each person consumes 1 kg of food a day
		float foodUsed = population * (elapsedDay * this.foodConsumedPerPerson);
		food -= foodUsed;
		
		updateShipModules(elapsedDay);

	}
	
	private void updateShipModules(float elapsedDay)	{
		for(ShipModule module:modules)
			module.update(elapsedDay);
	}
	
	public void draw(Graphics g, int centerX, int centerY) {
		for (ShipModule module : modules) {
			
		}
		//draw message if there is one
		if (messageTimer > 0.0f) {
			float percent = messageTimer-(messagePersist-1.0f);
			int alpha = 255-(int)(255*percent);
			if (percent < 0)
				alpha = 255;
			Color c1 = new Color(255,0,0,alpha);
			g.setColor(c1);
			g.setFont(new Font("Arial",Font.BOLD | Font.ITALIC, 60));
			g.drawString(msgTitle, centerX-g.getFontMetrics().stringWidth(msgTitle)/2, centerY-100);
			Color c2 = new Color(125,125,125,alpha);
			g.setColor(c2);
			g.setFont(new Font("Arial",Font.PLAIN,48));
			g.drawString(msgSubtitle,centerX-g.getFontMetrics().stringWidth(msgSubtitle)/2,centerY-30);
		}
	}
	
	private void drawBar(Graphics g, int x, int y, int width, int height, float percent, Color color) {
		g.setColor(Color.GRAY);
		g.fillRect(x-2, y-2, width+4, height+4);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(color);
		g.fillRect(x, y, (int)(width * percent), height);
	}
	
	/*
	 * top left x and y
	 */
	public void drawBars(Graphics g, int x, int y) {
		int initX = x, initY = y;
		//energy then water then food then scraps
		int height = 20;
		g.drawImage(SpriteCodex.POWER_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g,x,y,55,height,this.getPowerPercent(),Color.YELLOW);
		x += 55;
		x += 5;
		g.drawImage(SpriteCodex.WATER_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g,x,y,55,height,this.getWaterPercent(),Color.BLUE);
		x += 55;
		x += 5;
		g.drawImage(SpriteCodex.FOOD_SYMBOL, x, y, height, height, null);
		x += 25;
		this.drawBar(g, x, y, 55, height, this.getFoodPercent(), Color.ORANGE);
		x+= 60;
		g.drawImage(SpriteCodex.SCRAPS_SYMBOL, x, y, height, height, null);
		g.setColor(Color.WHITE);
		x += 25;
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		g.drawString(this.scraps+"/"+this.maxScraps,x,y+height);
		
		//draw day
		x = initX; y = initY+height+3;
		g.drawString("Day: "+MathUtils.round((double)day,0.01),x, y+height);
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
	
	public String toString() {
		String s = "SHIP STATUS:\n";
		s += "Population: "+this.population+"\n";
		s += "Power: "+this.power+"/"+this.maxPower+" ("+this.getPowerPercent()*100+")\n";
		s += "Water: "+this.water+"/"+this.maxWater+" ("+this.getWaterPercent()*100+")\n";
		s += "Food: "+this.food+"/"+this.maxFood+" ("+this.getFoodPercent()*100+")\n";
		return s;
	}
}
