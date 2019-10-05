package ship;

import java.awt.image.BufferedImage;

public abstract class ShipModule {
	private boolean employed = false;
	//private int price; price set as static variable in the subclasses
	protected int moduleLevel = 1;
	private float generationCooldown; //how many days
	private float tickingTimer;
	private Ship myShip;
	
	public ShipModule(Ship inShip, float generationCooldown)	{
		myShip = inShip;
		this.generationCooldown = generationCooldown;
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
	
	public void employ() {
		employed = true;
	}
	
	public Ship getShip() {
		return this.myShip;
	}
	
	protected abstract void generateResource();
	public abstract BufferedImage getImage();

}
