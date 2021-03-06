package game.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListening implements KeyListener {
	
	public boolean[] keys;
	public boolean up,down,left,right,shoot;
	private KeyListening() {
		keys= new boolean[256];
	}
	
	private static  KeyListening instance;
	public static synchronized KeyListening getInstance() {
		if( KeyListening.instance==null ) {
			KeyListening.instance= new KeyListening();
		}
		return KeyListening.instance;
	}
	

	public void update() 
	{
		shoot=keys[KeyEvent.VK_Z]; // the letter Z  tozshoot
		up=keys[KeyEvent.VK_UP];
		down=keys[KeyEvent.VK_DOWN];
		left=keys[KeyEvent.VK_LEFT];
		right=keys[KeyEvent.VK_RIGHT];	
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		keys[k.getKeyCode()]=true;	
	}

	
	public void keyReleased(KeyEvent arg) {
		keys[arg.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
