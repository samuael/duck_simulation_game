package game.actions;
import java.util.Random;

import game.actions.model.BDuck;
import game.actions.model.Bullet;
import game.actions.model.Entities;
import game.actions.model.FDuck;
import game.actions.model.UDuck;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

 
public class Game implements Runnable{
	static Random random= new Random();
	BufferedImage image ,sprite,sprite1 ,sprite2;
	Disply display;
	private  KeyListening listenkey;
	public Player players;
	private BufferStrategy bs;
	
	private BufferedImage boom = imageLoader.getInstance().loadImage("res/boom.png");
	private int x=0;
	Game()
	{
		 
		listenkey=KeyListening.getInstance();
//		bullets= new Bullet[20];
		entit = new Entities[4];
	}
	private  void init() 
	{
		display=new Disply(); 
		players=new Player();	
		image = imageLoader.getInstance().loadImage("res/thescay.png");
		initDuckAndBullets();
		display.getFrame().addKeyListener(listenkey);	
	}
	
	private Entities duck;
	
	
	
	Entities[] entit; 
	Bullet bullet;
	int index =0;
	private  void initDuckAndBullets() // this will be called in the init() method;
	{
		entit[0]=new FDuck(0);
		entit[1]=new UDuck();
		entit[2]=new BDuck();
		entit[3]=new FDuck(1);
		index=random();
		
		
		duck=entit[index];
	}
	// this picks a number randomly  0 to 3
	public int random() 
	{
		return random.nextInt(4);
		
	}
	public int last_x=80;// this two 80 numbers are the height and the width of the player
	public int last_y=80;
	public  double slope=0.1;
	
//	private Bullet bullets[] ;
	
	
//	private void bulletUpdate() 
//	{
////		for (int j =0; j < bullets.length;j++) {
////			if(bullets[j].outOfBound()) {
////				bullets[j]=new Bullet();
////			}
////		}
////		this.players.gun.myBullets.getBullet(this.slope, 0, 0 );
////		System.out.println(this.players.gun.myBullets.remainingBullets);
//		// here i will set the count each time to the bottom bar .
//	}
	
	
	private void bulletMovement()
	{
		listenkey.update();
		if(listenkey.up) {
			if((slope +0.2)>4) 
			{
				
			}else {
			slope+=0.2;
			this.players.setSlope(slope);
			}
		}
		if(listenkey.down)
			if((slope-0.2)<=0.2) {}else {
			slope-=0.2;
			this.players.setSlope(slope);
			}
		
		
		if(listenkey.right) 
		{
			if(players.x+80>400) 
			{}
			else 
			{
				players.x+=25;
				last_x=players.x+80;
			}
		}
		if(listenkey.left) 
		{
			if(players.x -5>=0) 
			{
				players.x-=25;
				last_x=players.x+80;
			}else 
			{
				
			}
		}
		
		
		
		/// here the gun is shooting the bullet setting the slope and the shooted variabe.
		// there for in the new implementation  i am gonna use this implementation to set the slope to the newly created bullet and 
		// returning a sound of empty gun to show that the gun is empty if all the bullets are shooted .
		if(listenkey.shoot) 
		{
//			for(Bullet shut : bullets) {
//				if(!(shut.shooted)) 
//				{
//					
//					shut.slope=slope;
//					shut.shooted=true;
//					return;
//				}
//			}
			this.players.gun.myBullets.getBullet(this.slope, 0 , 0 );
			System.out.println(this.players.gun.myBullets.remainingBullets);
			listenkey.shoot=false;
		}
		
		// this player is the gun as a player entity.
		players.setSlope(this.slope);
		players.update();
	}
	private void updateDuck() 
	{
		if(duck.outOfBound()  && (duck.life>=1)) 
		{
			players.life--;
			
		}
		if (duck.outOfBound() || duck.life==0) 
			{
			if(duck.getDir()=="HORIZONTAL") 
			{
				entit[index]=new FDuck(((FDuck)(duck)).choice);
				
			}else if (duck.getDir()=="UP") 
			{
				entit[index]=new UDuck();
			}else if (duck.getDir()=="LEFT") 
			{
				entit[index]=new BDuck();
			}
			index=random();
			duck=entit[index];
			
			}
		duck.update();
	}
	
	
	private void update() 
	{
		//update key board
		listenkey.update();
		// Duck update 
		updateDuck(); // update duck
		//.................
		// update for the bullets 
//		bulletUpdate();	
	}
	Graphics g ;
	
	
//	private int score; 
	int ctr=0;
	private void show() 
	{
		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) 
		{
			
			display.getCanvas().createBufferStrategy(3);
			bs = display.getCanvas().getBufferStrategy();
		}
		g = bs.getDrawGraphics();
		// The drawn images an pictures start
		g.drawImage(image,0,0,null);//BACK GROUND 
		// start
		 
		g.drawImage(duck.getImage() ,duck.x, duck.y,null);
		//end draw
		
		
		for(int i =0;i<this.players.gun.myBullets.bullets.length;i++) {
			if(this.players.gun.myBullets.bullets[i].shooted==true && this.players.gun.myBullets.bullets[i].overlaps(duck,last_x)    )// && 	if(this.x > 800 || this.x<0 || this.y> 600 || this.y < 0) this.players.gun.myBullets.bullets[i].x >0  ) 
			{
				duck.life--;
				
				
				// TODO:  i have to change this to reset the bullet using the bullet ID .
//				bullet = new Bullet();
//				bullets[i]=bullet;
				this.players.gun.myBullets.bullets[i].resetBullet();
				
				// TODO: ----
				for(int k=0;k<=100;k++) {
					g.drawImage(boom,duck.x,duck.y,null);
				}
				
				if(duck.life<=0 ) 
				{
					this.players.score.score++;
					if( duck.getDir()=="LEFT") 
					{
						
						this.players.life+=2;
					}
					display.setScore(this.players.score);
				}
			
			}
			
			if(players.life<=0) 
			{
				
				display.exit();
				Gameover.getInstance(this.players.score);
				stop();
				
			}
			
			display.changeLife(String.format("               LIFE : %s", players.life));
			
			
			// here the bullet is shooted and the game logic is drawing the bullets 
			if(this.players.gun.myBullets.bullets[i].shooted) 
			{
				if(this.players.gun.myBullets.bullets[i].outOfBound()) {
					this.players.gun.myBullets.storeBullet( this.players.gun.myBullets.bullets[i] );
					this.players.gun.myBullets.bullets[i].resetBullet();
					continue;
				}
//				g.drawImage(this.players.gun.myBullets.bullets[i].getImage(),( this.players.gun.myBullets.bullets[i].x), (this.players.gun.myBullets.bullets[i].y), null);
				g.drawImage(this.players.gun.myBullets.bullets[i].getImage(),( this.players.gun.myBullets.bullets[i].x+ last_x), (this.players.gun.myBullets.bullets[i].y-last_y), null);
				this.players.gun.myBullets.bullets[i].update();
			}
		}
		g.drawImage(players.getImage(), players.x, 520, null);
		
		
		//done 
		g.dispose();
		bs.show();
	
	}
	
	int time;
	public void run() 
	{
		init();// this code once initialise the game and stops
		// draw the needed image and render;
		int fps=200;
		int sps=10;
		double shootpertick=1000000000/sps;
		double timepertick=1000000000/fps;
		double beta=0;
		double delta=0;
		long now;
		long lastTime=System.nanoTime();
		long timer1=System.nanoTime();
		int ticks=0;
	
		
		while(running ) 
		{
			now=System.nanoTime();
			delta+=(now-lastTime)/timepertick;
			beta+=(now-lastTime)/shootpertick;
			lastTime=now;
			if(beta>=1) 
			{
				bulletMovement();
				beta--;
				
			}
			if(delta >= 1.2) 
			{
				update();
				//duck.x++;
				
				show(); 
				ticks++;
				delta--;
			}
			long timer2=System.nanoTime();
			long timer =timer2-timer1;
			if(timer >= 1000000000) {
				time++;
				int minute =time/60;
				int second= time%60;
				display.changeTime(String.format("Time : %s : %s", minute , second));
				
				timer1=timer2;
			ticks=0;
			}
		}stop();	
	}
	
	
	
	private boolean running=false;
	Thread thread ;
	
	
	public synchronized void start() 
	{
		if(running )
			return;
		
		running=true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() 
	{
		if (!running )
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
		}
		System.exit(1);
	}
	
	
	

}
