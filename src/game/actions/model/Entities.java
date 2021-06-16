package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;


public abstract class Entities{
	public int radius ;
	public int x;
	public int y;
	public int life ;
	
	public Entities(int life,int x, int y, int radius)
	{
		this.life = life;
		this.x= x;
		this.y=y;
		this.radius= radius;
		
		this.radius=radius;
	}
	public boolean outOfBound() {
		if(this.x > 800 || this.x<0 || this.y> 600 || this.y < 0)  // this code checks whether the duck gets out of bound or not 
		{
			return true;
		}
		return false;
	
	}
	public abstract BufferedImage getImage();
	public abstract  String getDir();  // beacause i am using the an abstract class 
	public abstract int getXw();
	public abstract int getYh();
	public abstract void update();
}


