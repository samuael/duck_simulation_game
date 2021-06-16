package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class UDuck extends Entities
{
	
	private int yh;
	private int xw;
	public static final String DIR="UP";
	BufferedImage im1;
	static BufferedImage woodduck = imageLoader.loadImage("res/up12.png");
	BufferedImage[] imgs = new BufferedImage[2];
	public UDuck()
	{
		super(1,650,520,40);
		this.life=1;
		imgs[0]=woodduck.getSubimage(0, 0, 100, 100);
		imgs[1]=woodduck.getSubimage(100, 0, 100, 100);
		
	}
	int ctr =0;
	@Override
	public BufferedImage getImage() {
		
		BufferedImage img =imgs[ctr/20];
		ctr++;
		if(ctr%40==0) 
		{
			ctr=0;
		}
		return img;
		
	}
	
	public void update() 
	{
		this.xw=x+64;
		this.y-=2;
		this.yh=y+80;
	}
	public int getXw() 
	{
		return xw;
	}
	public int getYh() 
	{
		return yh;
	}
	@Override
	public String getDir() {
		
		return DIR;
	}
	
}
