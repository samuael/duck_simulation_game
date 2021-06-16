package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public class Gun {
	
	public BulletFactory myBullets ; 
	BufferedImage player, gun1,gun2,gun3,gun4,gun5,gun6,gun7,gun8,gun9,gun10;
	BufferedImage[] guns;
	double slope;
	
	public Gun() {
//		player = imageLoader.loadImage("res/gunsprite.png");
		
		player = imageLoader.loadImage("res/PicsArt_06-15-08.21.32.png");
		this.myBullets= new BulletFactory(12);
		this.guns= new BufferedImage[10];
		guns[0]=player.getSubimage(0, 0, 100, 99);
		guns[1]=player.getSubimage( 100,0, 100, 99);
		guns[2]=player.getSubimage( 200,0, 100, 99);
		guns[3]=player.getSubimage( 300,0, 100, 99);
		guns[4]=player.getSubimage( 400,0, 100, 99);
		guns[5]=player.getSubimage( 500,0, 100, 99);
		guns[6]=player.getSubimage( 600,0, 100, 99);
		guns[7]=player.getSubimage(700,0, 100, 99);
		guns[8]=player.getSubimage( 800,0, 100, 99);
		guns[9]=player.getSubimage( 900,0, 99, 99);
	}
	int index=0;
	
	public void setSlope(double slope) 
	{
		this.slope=slope;
	}

	public BufferedImage getImage() {
		return guns[index];
	}
	
	
	public void update() {
		index=(int)((slope*(10))/4.01);
	}
}
