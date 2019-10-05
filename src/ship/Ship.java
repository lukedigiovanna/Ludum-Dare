package ship;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	private int population = 2; //start with 2 people
	
	private float maxPower = 50.0f, power = 50.0f;
	private float powerDepletionRate = 1.0f; // per second
	
	private float maxWater = 10.0f, water = 5.0f; //available water in litres
	private final float waterConsumedPerPerson = 1.5f;
	
	private float maxFood = 10.0f, food = 10.0f; //kilograms of food
	private final float foodConsumedPerPerson = 1.0f;
	
	private int secondsPerDay = 180; //3 minutes per day
	
	
	private List<ShipModule> modules;
	
	
	public Ship() {
		modules = new ArrayList<ShipModule>();
	}
	
	public void update(float elapsedTime) {
		//power consumed by the ship for movement
		float powerUsed = elapsedTime * powerDepletionRate;
		power -= powerUsed;
		
		//each person consumes 1.5 litres of water a day
		float waterUsed = population * (elapsedTime/secondsPerDay * waterConsumedPerPerson);
		water -= waterUsed;
		
		//each person consumes 1 kg of food a day
		float foodUsed = population * (elapsedTime/secondsPerDay * this.foodConsumedPerPerson);
		food -= foodUsed;

	}
	
	public void draw(Graphics g, int centerX, int centerY) {
		for (ShipModule module : modules) {
			
		}
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
	
	public float getCurrentWater() {
		return water;
	}
	
	public float getMaxWater() {
		return maxWater;
	}
	
	public float getWaterPercent() {
		return this.water/this.maxWater;
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
	
	public String toString() {
		String s = "SHIP STATUS:\n";
		s += "Population: "+this.population+"\n";
		s += "Power: "+this.power+"/"+this.maxPower+" ("+this.getPowerPercent()*100+")\n";
		s += "Water: "+this.water+"/"+this.maxWater+" ("+this.getWaterPercent()*100+")\n";
		s += "Food: "+this.food+"/"+this.maxFood+" ("+this.getFoodPercent()*100+")\n";
		return s;
	}
}
