package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class UpNormal implements Flys {
	Duck duck;
	public UpNormal(Duck duck){
		this.duck = duck;
	}
	
	@Override
	public void update() {
		this.duck.setXw(this.duck.x+64);
		this.duck.y-=2;
		this.duck.setYh(this.duck.y+80);
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
		BufferedImage woodduck = imageLoader.getInstance().loadImage("res/up12.png");
		buffers[0]=woodduck.getSubimage(0, 0, 100, 100);
		buffers[1]=woodduck.getSubimage(100, 0, 100, 100);
		duck.buffers= buffers;
		return buffers;
	}

	@Override
	public void initializeCoordinate() {
		this.duck.x= 650; 
		this.duck.y= 520;
	}
}