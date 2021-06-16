package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class RightWhite implements Flys {
	Duck duck;
	public RightWhite(Duck duck){
		this.duck = duck;
	}
	
	@Override
	public void update() {
		this.duck.x+=2;
		this.duck.setXw(this.duck.x+62);
	}
	@Override
	public int getXw() 
	{
		return this.duck.getXw();
	}
	@Override
	public int getYh() 
	{
		return this.duck.getYh();
	}

	@Override
	public BufferedImage[] initializeImages() {
		BufferedImage[] buffers = new BufferedImage[3];
		BufferedImage sprite1=imageLoader.getInstance().loadImage("res/theTWo.png");
		buffers[0]=sprite1.getSubimage(0, 0, 100, 100);		
		buffers[1]=sprite1.getSubimage(100, 0, 100, 100);
		duck.buffers= buffers;
		return buffers;
	}

	@Override
	public void initializeCoordinate() {
		this.duck.x= 0; 
		this.duck.y= 100;
	}
}
