package game.actions;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//This has to be changed to a singleton object so that we can use the object any where 
//to load Image 
public class imageLoader {
	
	private static imageLoader instance ; 
	
	public static BufferedImage loadImage(String path) 
	{
	// this class is made to creat and to input the image from the class resourse texture class
		try {
			return ImageIO.read(imageLoader.class.getResource(path));
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
		}return null;
		
		
	}
	
	public static  synchronized imageLoader getInstance() {
		if (imageLoader.instance== null ) {
			imageLoader.instance = new imageLoader();
		}
		return imageLoader.instance ; 
	}

}
