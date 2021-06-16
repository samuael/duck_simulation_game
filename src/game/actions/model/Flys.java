package game.actions.model;

import java.awt.image.BufferedImage;

import game.actions.imageLoader;

public interface Flys {
	public void update();
	public BufferedImage[] initializeImages() ;
	public void initializeCoordinate();
	
	public int getXw();
	public int getYh();
}







