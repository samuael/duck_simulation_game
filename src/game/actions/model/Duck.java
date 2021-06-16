package game.actions.model;

import java.awt.image.BufferedImage;

public class Duck extends Entities
{
	
	
	public Flys  flyingMethod;
	BufferedImage[] buffers;
	int initLife ;
	//	counts the image index;
	int ctr=0;
	
	public int xw;
	public int yh=162;
	public static final String DIR="HORIZONTAL";
	public int choice;
	
	public Duck(int life) 
	{
		super(life,0,0,35);
		choice=x;
		this.x=0;
		this.y=100;
		this.initLife= life;
		this.life=life;	
	}
	
//	@Override
//	public void update() {
//		
//	}
	
	public void setFlys(Flys flys ) {
		this.flyingMethod=flys ;
		this.flyingMethod.initializeCoordinate();
		this.buffers=this.flyingMethod.initializeImages();
	}
	
	public BufferedImage getImage() 
	{
		if(buffers.length==3) 
		{
			BufferedImage temp=buffers[0];
			
			
			for(int i =0; i <buffers.length-1;i++) 
			{
				buffers[i]=buffers[i+1];
				
			}buffers[buffers.length-1]=temp;
			return buffers[0];
		}
		else 
		{
			BufferedImage img =buffers[ctr/20];
			ctr++;
			if(ctr%40==0) 
			{
				ctr=0;
			}
			return img;
		}
	}
	public void initializeImages (){
		this.flyingMethod.initializeImages ();
	}
	
	public void initializeCoordinate () {
		this.flyingMethod.initializeCoordinate();
	}
	public void update() {
		this.flyingMethod.update();
	}
	public int getXw() 
	{
		return xw;
	}
	public int getYh() 
	{
		return yh;
	}
	public void setXw(int xw ) {
		this.xw= xw;
	}
	public void setYh(int yh ) {
		this.yh = yh ; 
	}
	@Override
	public String getDir() {
		return "UNKNOWN";
	}
	public void resetLife() {
		this.life=this.initLife;
	}
}
