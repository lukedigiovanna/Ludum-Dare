package ship;

import misc.MathUtils;

public class PlanetTimer {
	private float cooldown = 1.0f; //days
	private float cooldownStatus = cooldown;
	private float chance = 0.01f;
	private Ship myShip;
	
	public PlanetTimer(Ship ship) {
		myShip = ship;
	}
	
	public void update(float elapsedDay) {
		cooldownStatus = MathUtils.min(cooldownStatus-elapsedDay, 0.0f);
		
		if (cooldownStatus == 0.0f) {
			if (Math.random() <= chance) {
				encounterPlanet();
				cooldownStatus = cooldown;
			}
		}
	}
	
	public void encounterPlanet() {
		int planetTypes = 3;
		int planet = (int)(Math.random()*planetTypes);
		switch (planet) {
		case 0:
			myShip.popMessage("Water Planet!","Filled water tanks");
			myShip.addWater(9999);
			break;
		case 1:
			myShip.popMessage("Abandoned Planet!", "+100 scraps");
			myShip.addScraps(100);
			break;
		case 2:
			myShip.popMessage("Food Planet!", "+4 kg of food!");
			myShip.addFood(4.0f);
			break;
		}
	}
}
