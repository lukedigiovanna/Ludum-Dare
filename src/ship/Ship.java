package ship;

public class Ship {
	
	private int numOfPeople = 2; //start with 2 people
	
	private float maxPower = 50.0f, power = 50.0f;
	private float powerDepletionRate = 1.0f; // per second
	
	private float maxWater = 10.0f, water = 5.0f; //available water in litres
	private final float waterConsumedPerPerson = 1.5f;
	
	private float maxFood = 10.0f, food = 10.0f; //kilograms of food
	private float foodConsumedPerPerson = 1.0f;
	
	private int secondsPerDay = 180; //3 minutes per day
	
	public Ship() {
		
	}
	
	public void update(float elapsedTime) {
		//power consumed by the ship for movement
		float powerUsed = elapsedTime * powerDepletionRate;
		power -= powerUsed;
		
		//each person consumes 1.5 litres of water a day
		float waterUsed = numOfPeople * (elapsedTime/secondsPerDay * waterConsumedPerPerson);
		water -= waterUsed;
		
		//each person consumes 1 kg of food a day
		float foodUsed = numOfPeople * (elapsedTime/secondsPerDay * this.foodConsumedPerPerson);
		food -= foodUsed;
	}
	
	public float getCurrentPower() {
		return power;
	}
	
	public float getPowerPercent() {
		return this.power/this.maxPower;
	}
	
	public String toString() {
		String s = "SHIP STATUS:\n";
		s += "Power: "+this.power+"/"+this.maxPower+" ("+this.power/this.maxPower+")\n";
		return s;
	}
}
