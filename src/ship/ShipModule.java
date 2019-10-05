package ship;

import java.awt.image.BufferedImage;

public abstract class ShipModule {
	boolean employed;
	float generationCooldown;
	float tickingTimer;
	private Ship myShip;
	
	public ShipModule(Ship inShip)	{
		myShip = inShip;
	}
	public void update(float deltaTime)	{
		tickingTimer -= deltaTime;
		if(tickingTimer<=0.0f)	{
			generateResource();
			tickingTimer = generationCooldown;
		}
	}
	protected abstract void generateResource();
	public abstract BufferedImage getImage();

}
