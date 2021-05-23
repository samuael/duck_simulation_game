package game.actions;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.font.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
public class Disply {
	JFrame frame;
	Canvas canvas;
	JLabel life;
	JButton newgame;
	JButton exit;
	JLabel score;
	private final int  WIDTH=800;
	private final int HEIGHT=(800/12)*9;
	private final String name ="Duck Simulation";
	public JPanel panel;
	Disply()
	{
		
		
		desplay(1);
		
	}
	Disply(int b)
	{
		desplay(0);
	}
	private boolean add=false;
	
	public JFrame getFrame() 
	{
		return frame;
		
	}
	Disply dis;
	BufferedImage ima ;
	BufferStrategy stra;
	Graphics graph;
	
	private void desplay(int bam) 
	{
		frame = new JFrame(name);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("gh.jpg")));
		frame.setSize(800,700);
		frame.setLayout(new FlowLayout());
		newgame= new JButton("NEW GAME ");
		
		newgame.addActionListener(new actionlisten());
		exit= new JButton("  EXIT  ");
		exit.addActionListener(new actionlisten());
		canvas =new Canvas();
	
		
		frame.getContentPane().setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.add(canvas);
		// this makes the compiler to not focus on the frame only
		
	
		canvas.setFocusable(false);
		canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		labe = new JLabel("__________" );
		score = new JLabel("SCORE : 0                       ");
		score.setForeground(Color.white);
		labe.setForeground(Color.WHITE);
		labe.setSize(60, 40);
		frame.add(score);
		frame.add(labe);
		life= new JLabel("",SwingConstants.LEFT);
		life.setForeground(Color.WHITE);
		
		frame.add(life);
		if(bam==0) 
		{
			frame.add(newgame);
			frame.add(exit);
			frame.remove(labe);
		}
		
		
		
		
		
	}JLabel labe;
	
	public Canvas getCanvas() 
	{
		return canvas;
	}
	private int minute;
	private int second;
	public void setTime(int minute , int second) 
	{
		this.minute=minute;
		this.second=second;
	} public void changeTime(String name) 
	{
		labe.setText(name);
	}
	public void changeLife(String life ) 
	{
		this.life.setText(life);
	}
	
	
	private class actionlisten implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getSource()==newgame) 
			{
				getInvisible();
				started=true;
				Game game = new Game();
				game.start();
				
			}
			else 
			{
				System.exit(1);
			}
		}
		
	}public void exit() 
	{
		this.getFrame().setVisible(false);
	}
	public void getInvisible()
	{
		this.getFrame().setVisible(false);
	}
	public boolean started=false;
	public void  setScore(int score) 
	{
		
		this.score.setText(String.format("SCORE : %d                     ", score));
	}

	
}
