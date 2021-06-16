package game.actions.model;

public class Score {
	public int score;
	public int maxLife ;
	public int bullletsUsed;
	
	public int getScore() {
		return this.score;
	}
	
	public int getMaxLife() {
		return this.maxLife;
	}
	
	public int getNumberofBulletsUsed() {
		return this.bullletsUsed;
	}
	
	public void setScore(int score ) {
		this.score = score;
	}
	public void setMaxLife(int life ) {
		if(life > this.maxLife) {
			this.maxLife= life;
		}
	}
}
