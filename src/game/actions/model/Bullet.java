package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class Bullet extends Entities 
{
	public int id ;
	private int xw;
	private int yh;
	public double slope=0;
	public boolean shooted=false;
	public static int counter=0;
	
	Bullet()
	{
		super(1,0,600,6);
		this.id = ++counter;
		fireimage =imageLoader.loadImage("res/bullet.png");	
	}
	public BufferedImage getImage() 
	{	
		return fireimage;
	}
	public void update (){
		this.x+=3;
		xw=x+10;
		y=600;
		this.y-=(this.slope* this.x);
		yh=y+10;
	}
	public BufferedImage fireimage ;
	
	public boolean overlaps(Entities enti ,int last_x) 
	{
		int last_y=80;
		if((y-last_y<=enti.getYh() )&& (y-last_y>=enti.y) && (x+ last_x>=enti.x) && (x+ last_x<=enti.getXw())) {
			return true;
		}return false;
	}
	public static  final String DIR="nop";
	@Override
	public String getDir() {
		return DIR;
		
	}
	@Override
	public int getXw() {
		return xw;
	}
	@Override
	public int getYh() {
		return yh;
	}
	public boolean outOfBound() {
		if(this.x > 800 || this.x<0 || this.y> 800 || this.y < 0)  // this code checks whether the duck gets out of bound or not 
		{
			return true;
		}
		return false;
	}
	
	
	public void resetBullet() {
		this.shooted=false;
		this.x=0;
		this.y=0;
		this.yh=0;
		this.xw=0;
	}
}
