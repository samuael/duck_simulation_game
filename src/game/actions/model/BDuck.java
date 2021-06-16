package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class BDuck extends Entities
{
	private int yh;
	private int xw;
	 public BDuck() {
		super(5, 800, 130, 50);
		bducks= new BufferedImage[3];
		init();
		
	}
	
	BufferedImage[] bducks ;
	private  void init() 
	{
		bducks[0]=imageLoader.getInstance().loadImage("res/BDuck1.png");
		bducks[1]=imageLoader.getInstance().loadImage("res/bduck2.png");
		bducks[2]=imageLoader.getInstance().loadImage("res/bduck3.png");
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