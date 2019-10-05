package ship;

import java.awt.image.BufferedImage;

public abstract class ShipModule {
	private boolean employable = true;
	private boolean employed = false;
	//private int price; price set as static variable in the subclasses
	private int level = 1;
	private float generationCooldown; //how many days
	private float tickingTimer;
	private Ship myShip;
	private float powerUse;
	
	public ShipModule(Ship inShip, float generationCooldown, float powerUse)	{
		myShip = inShip;
		this.generationCooldown = generationCooldown;
		this.powerUse = powerUse;
	}
	
	public void setEmployable(boolean bo) {
		this.employable = bo;
	}
	
	public void update(float deltaTime)	{
		if(employed && this.employable)	{
			tickingTimer -= deltaTime;
			if(tickingTimer<=0.0f)	{
				generateResource();
				//if we generate resource then use power
				myShip.usePower(powerUse*generationCooldown);
				tickingTimer = generationCooldown;
			}
		}
	}
	
	public boolean isEmployed() {
		return this.employed;
	}
	
	public float getGenerationPercent() {
		return 1-this.tickingTimer/this.generationCooldown;
	}
	
	public boolean isEmployable() {
		return this.employable;
	}
	
	public void employ() {
		if (!employable) {
			employed = true;
			return;
		}
		if (employed) //cant re-employ if someone is already working there
			return;
		if (myShip.employ())
			employed = true;
	}
	
	public Ship getShip() {
		return this.myShip;
	}
	
	public int getUpgradePrice() {
		return (this.level+1)*getInitialPrice();
	}
	public int getLevel()	{
		return level;
	}
	
	public void addLevel() {
		this.level++;
	}
	
	public abstract void levelUp();
	public abstract int getInitialPrice();
	
	protected abstract void generateResource();
	public abstract BufferedImage getImage();

	public String getName() {
		String s = this.getClass().getName();
		s = s.substring(5,s.length()-6);
		//add space between capital letters
		for (int i = 0 ; i < s.length(); i++) {
			
		}
		return s;
	}

}
