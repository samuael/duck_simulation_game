package game.actions;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gameover 
{
	Gameover(int score)
	{
		init(score);
		gameOver();
		
	}
	public static BufferStrategy bs;
	public static Graphics g;
	public static BufferedImage img2=imageLoader.loadImage("/resourcepic/GAMEOVER.png");
	public  void gameOver()
	{
		int pps=4;
		long lastt=System.currentTimeMillis();
		double ppertick=1000/pps;
		long now;
		double ctr=0;
		
		while(!(desp.started)) 
		{
			now=System.currentTimeMillis();
			ctr+=(now-lastt)/ppertick;
			lastt=now;
			
			if(ctr >=1) {
				bs=desp.getCanvas().getBufferStrategy();
				if(bs==null) 
				{
					desp.getCanvas().createBufferStrategy(3);
					bs=desp.getCanvas().getBufferStrategy();
				}
				g=bs.getDrawGraphics();
				
				g.drawImage(getImage(), 0, 0, null);
				g.dispose();
				bs.show();
				ctr--;
			}
		}
		
	}
	Disply desp;
	BufferedImage[] bim;
	private void init(int score ) 
	{
		desp = new Disply(0);
		bim= new BufferedImage[8];
		bim[0]=img2.getSubimage(0, 0, 795, 595);
		bim[1]=img2.getSubimage(800, 0, 795, 595);
		bim[2]=img2.getSubimage(1600, 0, 795, 595);
		bim[3]=img2.getSubimage(2400, 0, 795, 595);
		bim[4]=img2.getSubimage(0, 600, 795, 595);
		bim[5]=img2.getSubimage(800, 600, 795, 595);
		bim[6]=img2.getSubimage(1600, 600, 795, 595);
		bim[7]=img2.getSubimage(2400, 600, 795, 595);
		desp.setScore(score);
	}
	private BufferedImage getImage() 
	{
		BufferedImage tempo= bim[0];
		for (int i =0; i<7; i++) 
		{
			bim[i]=bim[i+1];
			
		}bim[7]=tempo;
		return bim[1];
	}
	
	
}





















































/**
class DisplayImage extends JFrame 

{
	JButton newgame;
	JButton exit;
	boolean visibility =true;
	DisplayImage()
	{
		setLayout(new FlowLayout());
		newgame= new JButton("NEW GAME");
		exit= new JButton("EXIT");
		newgame.addActionListener(new listener());
		exit.addActionListener(new listener());
		add(new ThePanel());
		setSize(800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(visibility);
		add(newgame);
		add(exit);
	}
	private class listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==newgame) 
			{
				
				new Game().start();
			}else if (e.getSource()==exit) 
			{
				System.exit(1);
			}
			
		}
		
	}



}class ThePanel extends JPanel
{
	private ImageIcon imageicon = new ImageIcon("/resourcepic/backupp.jpg");
	private Image image =imageicon.getImage();
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if(image != null) 
		{
			g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
		}
	}
}*/