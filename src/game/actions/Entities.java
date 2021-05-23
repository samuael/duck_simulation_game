package game.actions;

import java.awt.image.BufferedImage;


abstract class elements 
{
	public int x;
	public int y;
	public int life ;

	elements(int life, int x , int y)
	{
		this.life=life;
		this.x=x;
		this.y=y;
	}
	public abstract void update();
	public abstract BufferedImage getImage();

}


class Player extends elements
{
	BufferedImage player, gun1,gun2,gun3,gun4,gun5,gun6,gun7,gun8,gun9,gun10;
	BufferedImage[] guns;
	double slope;
	Player()
	{
		super(10, 0,500);
		guns=new BufferedImage[10];
		this.slope=0.1;
		init();
	}
	
	public void init() 
	{
		player = imageLoader.loadImage("/resourcepic/gunsprite.png");
		guns[0]=player.getSubimage(0, 0, 100, 100);
		guns[1]=player.getSubimage( 100,0, 100, 100);
		guns[2]=player.getSubimage( 200,0, 100, 100);
		guns[3]=player.getSubimage( 300,0, 100, 100);
		guns[4]=player.getSubimage( 400,0, 100, 100);
		guns[5]=player.getSubimage( 500,0, 100, 100);
		guns[6]=player.getSubimage( 600,0, 100, 100);
		guns[7]=player.getSubimage(700,0, 100, 100);
		guns[8]=player.getSubimage( 800,0, 100, 100);
		guns[9]=player.getSubimage( 900,0, 100, 100);
		
	}
	
	int index=0;
	
	public void setSlope(double slope) 
	{
		this.slope=slope;
	}
	@Override
	public void update() {
		
		index=(int)((slope*(10))/4.01);
		
	}
	@Override
	public BufferedImage getImage() {
		
		return guns[index];
	}
}



public abstract class Entities extends elements  {
	public int radius ;
	
	Entities(int life,int x, int y, int radius)
	{
		super(life,x,y);
		
		this.radius=radius;
	}
	public boolean outOfBound() {
		if(this.x > 800 || this.x<0 || this.y> 600 || this.y < 0)  // this code checks whether the duck gets out of bound or not 
		{
			return true;
		}
		return false;
	
	}
	public abstract BufferedImage getImage();
	public abstract  String getDir();  // beacause i am using the an abstract class 
	public abstract int getXw();
	public abstract int getYh();
	public abstract void update();
}


class bulletPlayer extends Entities 
{
	private int xw;
	private int yh;
	public double slope=0;
	public boolean shooted=false;
	bulletPlayer()
	{
		super(1,0,600,6);
		
		fireimage =imageLoader.loadImage("/resourcepic/bullet.png");
		
		
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
	

}









class FDuck extends Entities
{
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
	BufferedImage[] buffers;
	
	public static BufferedImage im1, im2, im3;
	static BufferedImage sprite=imageLoader.loadImage("/resourcepic/theSprite.png");
	static BufferedImage sprite1=imageLoader.loadImage("/resourcepic/theTWo.png");
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

class UDuck extends Entities
{
	
	private int yh;
	private int xw;
	public static final String DIR="UP";
	BufferedImage im1;
	static BufferedImage woodduck = imageLoader.loadImage("/resourcepic/up12.png");
	BufferedImage[] imgs = new BufferedImage[2];
	UDuck()
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
	
}class BDuck extends Entities
{
	private int yh;
	private int xw;
	BDuck() {
		super(5, 800, 130, 50);
		bducks= new BufferedImage[3];
		init();
		
	}
	
	BufferedImage[] bducks ;
	private  void init() 
	{
		bducks[0]=imageLoader.loadImage("/resourcepic/BDuck1.png");
		bducks[1]=imageLoader.loadImage("/resourcepic/bduck2.png");
		bducks[2]=imageLoader.loadImage("/resourcepic/bduck3.png");
	}

	@Override
	public BufferedImage getImage() {
		BufferedImage tempo;
		tempo=bducks[0];
		for(int i=0; i<2;i++ ) 
		{
			bducks[i]=bducks[i+1];
		}bducks[2]=tempo;
		return bducks[0];
	}

	@Override
	public String getDir() {
		return "LEFT";
	}

	@Override
	public int getXw() {
		
		return xw;
	}
	
	@Override
	public int getYh() {
		this.yh=180;
		return yh;
	}

	@Override
	public void update() {
		this.x-=2;
		this.xw=x+80;	
	}
}