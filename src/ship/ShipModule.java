package ship;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class ShipModule {
	private boolean employable = true;
	private boolean employed = false;
	//private int price; price set as static variable in the subclasses
	private int level = 1;
	private float generationCooldown; //how many days
	private float tickingTimer;
	private Ship myShip;
	private float powerUse; //per day
	private Point relativePosition;
	private int value = 0;
	
	public ShipModule(float generationCooldown, float powerUse)	{
		this.generationCooldown = generationCooldown;
		this.tickingTimer = generationCooldown;
		this.powerUse = powerUse;
		this.relativePosition = new Point(0,0);
		value += this.getInitialPrice();
	}
	
	//optionally overriden
	public void init() {
		if (myShip == null)
			throw new NullPointerException("Module tried to initialize without a ship reference");
	}
	
	public void setShip(Ship inShip) {
		myShip = inShip;
	}
	
	public void setRelativePosition(int dx, int dy) {
		this.relativePosition.x = dx;
		this.relativePosition.y = dy;
	}
	
	public float getGenerationCooldown() {
		return this.generationCooldown;
	}
	
	public void unemploy() {
		employed = false;
		this.getShip().unemploy();
	}
	
	public void setEmployable(boolean bo) {
		this.employable = bo;
	}
	
	public void update(float deltaTime)	{
		if(employed && this.employable)	{
			tickingTimer -= deltaTime;
			if(tickingTimer<=0.0f)	{
				
				//if we generate resource then use power
				if (myShip.hasPower(powerUse*generationCooldown)) {
					myShip.usePower(powerUse*generationCooldown);
					generateResource();
				}
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
		this.value += this.getUpgradePrice();
		this.level++;
	}
	
	public int getSellValue() {
		return (int)(this.value * 0.6);
	}
	
	public abstract void levelUp();
	public abstract int getInitialPrice();
	
	protected abstract void generateResource();
	public abstract BufferedImage getImage();
	
	public Point getRelativePosition() {
		return this.relativePosition;
	}
	
	public float powerProduction() {
		return 0;
	}
	
	public float foodProduction() {
		return 0;
	}
	
	public float waterProduction() {
		return 0;
	}
	
	public float powerUse() {
		return this.powerUse;
	}
	
	public float scrapsProduction() {
		return 0;
	}

	public String getName() {
		String s = this.getClass().getName();
		s = s.substring(5,s.length()-6);
		//add space between capital letters
		for (int i = 0 ; i < s.length(); i++) {
			
		}
		return s;
	}

}
