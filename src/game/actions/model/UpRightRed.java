package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class UpRightRed implements Flys {
	Duck duck;
	static String dir ="UP";
	public UpRightRed(Duck duck){
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
		BufferedImage sprite=imageLoader.getInstance().loadImage("res/theSprite.png");
		buffers[0]=sprite.getSubimage(2, 0, 62, 62);
		buffers[1]=sprite.getSubimage(66, 0, 62, 62);
		buffers[2]=sprite.getSubimage(129, 0, 62, 62);
		duck.buffers= buffers;
		return buffers;
	}

	@Override
	public void initializeCoordinate() {
		this.duck.x= 0; 
		this.duck.y= 100;
	}
	
//	public void getDir() {
//		return this.dir ;
//	}
}