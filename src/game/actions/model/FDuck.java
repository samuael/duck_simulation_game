package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class FDuck extends Entities
{
	
	
	public Flys  flyingMethod;
	
	
	public int xw;
	public int yh=162;
	public static final String DIR="HORIZONTAL";
	public int choice;
	public FDuck(int x) 
	{
		super(1,0,100,35);
		if(x==0) 
		{
			buffers=new BufferedImage[3];
			init();
			
		}
		else{
			buffers=new BufferedImage[2];
			init(x);
			
		}
		choice=x;
		this.x=0;
		this.y=100;
		this.life=1;	
	}
	
	
	// optional 
	public void update() 
	{
		this.x+=2;
		xw=x+62;
		
		
	}
	
//	Flys fly = new SideFlys();
//	
//	public void update() {
//		fly.update();
//	}
	
	BufferedImage[] buffers;
	
	static BufferedImage sprite=imageLoader.getInstance().loadImage("res/theSprite.png");
	static BufferedImage sprite1=imageLoader.getInstance().loadImage("res/theTWo.png");
	int ctr=0;
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
	public int getXw() 
	{
		return xw;
	}
	public int getYh() 
	{
		return yh;
	}
	public void init(int n) 
	{
		buffers[0]=sprite1.getSubimage(0, 0, 100, 100);
		
		buffers[1]=sprite1.getSubimage(100, 0, 100, 100);
		
		
		
	}
	
	public void init() 
	{
		
		buffers[0]=sprite.getSubimage(2, 0, 62, 62);
		buffers[1]=sprite.getSubimage(66, 0, 62, 62);
		buffers[2]=sprite.getSubimage(129, 0, 62, 62);
		
	}

	@Override
	public String getDir() {
		return DIR;
	}
	
	
	
}
