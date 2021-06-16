package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class LeftGrey implements Flys {
	Duck duck;
	public LeftGrey(Duck duck){
		this.duck = duck;
	}
	
	@Override
	public void update() {
		this.duck.x-=2;
		this.duck.setXw(this.duck.x+80);
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
		buffers[0]=imageLoader.getInstance().loadImage("res/BDuck1.png");
		buffers[1]=imageLoader.getInstance().loadImage("res/bduck2.png");
		buffers[2]=imageLoader.getInstance().loadImage("res/bduck3.png");
		duck.buffers= buffers;
		return buffers;
	}

	@Override
	public void initializeCoordinate() {
		this.duck.x= 800; 
		this.duck.y= 130;
	}
}
