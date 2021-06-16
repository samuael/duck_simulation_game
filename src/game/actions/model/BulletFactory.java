package game.actions.model;

public class BulletFactory {

	public int quantity =0 ;
	public Bullet bullets[];
	public int remainingBullets;
	
	public BulletFactory(int count ) {
		this.quantity = count;
		
		this.bullets = new Bullet[this.quantity];
		this.remainingBullets = this.quantity;
		
		for(int i=0; i <this.quantity; i++) {
			this.bullets[i]= new Bullet();
		}
	}
	
	
	// This slope represent the slope of the Gun Position 
	// x  , y represent the initial coordinate point of the gun 
	public synchronized Bullet getBullet(double slope  , int x , int y ) {
		for (int  a=0;a < bullets.length ; a++ ) {
			if(!(this.bullets[a].shooted) ) {
				this.bullets[a].slope = slope;
				this.bullets[a].shooted= true;
				this.bullets[a].x=x;
				this.bullets[a].y= y;
				this.remainingBullets --; 
				return this.bullets[a];
			}
		}
		return null ; 
	}
	
	public void storeBullet( Bullet bullet  ) {
		if(bullet != null && bullet.shooted) {
			bullet.shooted= false;
			bullet.resetBullet();
			this.remainingBullets++;
		}
	}
	
	public synchronized int getRemainingBullets() {
		return this.remainingBullets;
	}
	
	
}
