package game.actions;

import java.awt.image.BufferedImage;

import game.actions.model.Entities;
import game.actions.model.Gun;

public class Player extends Entities
{
	double slope;
	public Gun gun ;
//	public Score score ;
	Player()
	{
		super(10, 0,500 , 200);
		this.slope=0.1;
		this.gun = new Gun();
		this.gun.setSlope(this.slope);
	}

	
	int index=0;
	
	public void setSlope(double slope) 
	{
		this.slope=slope;
		this.gun.setSlope(slope);
	}
	
//	public Score getScore() {
//		return this.score;
//	}
	@Override
	public BufferedImage getImage() {
		return this.gun.getImage();
	}
	
	
	public void setSlop(int slope) {
		this.slope = slope;
		this.gun.setSlope(slope);
	}

	@Override
	public String getDir() {
		return null;
	}

	
	@Override
	public int getXw() {
		return 0;
	}

	@Override
	public int getYh() {
		return 0;
	}
	@Override
	public void update() {
		this.gun.update();
	}
}
