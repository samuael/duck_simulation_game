package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public interface Flys {
	public void update();
	public int getXw();
	public int getYh();
}

class UpFlys implements Flys {
	
	public static UpFlys instance ;
	public int xw;
	public int yh=162;
	public int x;
	public int y;
	
	public static synchronized UpFlys getInstance() {
		if(instance == null ) {
			instance = new UpFlys();
		}
		return instance;
	}
	
	
	@Override
	public void update() {
		this.x+=2;
		xw=x+62;
	}

	@Override
	public int getXw() 
	{
		return xw;
	}
	@Override
	public int getYh() 
	{
		return yh;
	}
}



class SideFlys implements Flys {

	public static SideFlys instance;
	public int xw;
	public int yh=162;
	public int x;
	public int y;
	private SideFlys() {
	}
	
	public static synchronized SideFlys getInstance() {
		if(instance == null ) {
			instance = new SideFlys();
		}
		return instance;
	}
	
	public BufferedImage[] images ;
	public BufferedImage[] getImages() {
		try {
			BufferedImage sprite = imageLoader.loadImage("res/theSprite.png");
			images[0]=sprite.getSubimage(2, 0, 62, 62);
			images[1]=sprite.getSubimage(66, 0, 62, 62);
			images[2]=sprite.getSubimage(129, 0, 62, 62);
			return images;
		}catch(Exception e ) {
			return null;
		}
	}
	@Override
	public void update() 
	{
		this.xw=x+64;
		this.y-=2;
		this.yh=y+80;
	}
	@Override
	public int getXw() 
	{
		return xw;
	}
	@Override
	public int getYh() 
	{
		return yh;
	}
}



class ProjectileFlys implements Flys {
	private ProjectileFlys(    ) {
		
	}
	public static ProjectileFlys instance ;
	
	public static synchronized ProjectileFlys getInstance() {
		if(instance == null ) {
			instance = new ProjectileFlys();
		}
		return instance;
	}
	@Override
	public void update() {
		
	}

	@Override
	public int getXw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYh() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
