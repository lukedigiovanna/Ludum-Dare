package ship;

import java.awt.image.BufferedImage;

public abstract class ShipModule {
	boolean employed = false;
	public static int price;
	float generationCooldown;
	float tickingTimer;
	private Ship myShip;
	
	public ShipModule(Ship inShip, int price, float generationCooldown)	{
		myShip = inShip;
	}
	
	public void update(float deltaTime)	{
		if(employed)	{
			tickingTimer -= deltaTime;
			if(tickingTimer<=0.0f)	{
				generateResource();
				tickingTimer = generationCooldown;
			}
		}
	}
	
	protected abstract void generateResource();
	public abstract BufferedImage getImage();

}
