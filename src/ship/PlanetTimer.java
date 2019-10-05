package ship;

import misc.MathUtils;

public class PlanetTimer {
	private float cooldown = 0.5f; //days
	private float cooldownStatus = cooldown;
	private float chance = 0.5f;
	private Ship myShip;
	
	public PlanetTimer(Ship ship) {
		myShip = ship;
	}
	
	public void update(float elapsedDay) {
		cooldownStatus = MathUtils.min(cooldownStatus-elapsedDay, 0.0f);
		
		if (cooldownStatus == 0.0f) {
			if (Math.random() <= chance) {
				encounterPlanet();
			}
			cooldown += MathUtils.randomInRange(-0.1, 0.25);
			cooldownStatus = cooldown;
		}
	}
	
	public void encounterPlanet() {
		int planetTypes = 3;
		int planet = (int)(Math.random()*planetTypes);
		switch (planet) {
		case 0:
			double water = MathUtils.randomInRange(3, 5.0);
			water = MathUtils.round(water,0.1);
			myShip.popMessage("Water Planet!","+"+water+" litres of water");
			myShip.addWater((float)water);
			break;
		case 1:
			int add = MathUtils.randomInRange(80,150);
			myShip.popMessage("Abandoned Planet!", "+"+add+" scraps");
			myShip.addScraps(add);
			break;
		case 2:
			double add1 = MathUtils.randomInRange(3.0,7.0);
			add1 = MathUtils.round(add1, 0.1);
			myShip.popMessage("Food Planet!", "+"+add1+" kg of food!");
			myShip.addFood((float)add1);
			break;
		}
	}
}
