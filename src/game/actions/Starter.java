package game.actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Starter extends JFrame
{
	private JButton start;
	private JButton help;
	private JButton exit;
	JLabel label;
	JLabel label2;
	private ImageIcon Help;
	private ImageIcon Exit;
	private ImageIcon play;
	
	
	JLabel[] st;
	
	Starter ()
	{	 
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/gh.jpg")));
		setLayout(new FlowLayout());
		ImageIcon imc= new ImageIcon(getClass().getResource("res/backupps.png"));
		Help= new ImageIcon(getClass().getResource("res/help.png"));
		play= new ImageIcon(getClass().getResource("res/play.png"));
		Exit= new ImageIcon(getClass().getResource("res/exit.png"));
		this.getContentPane().setBackground(Color.black);
		
		setSize(800,700);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start= new JButton("",play);
		{
			{
				start.setSize(100,100);
				start.setMaximumSize(new Dimension(100,100));
				start.addActionListener(new action());
				start.setBounds(10, 10, 100, 100);
				start.setBackground(Color.red);
			}
		};
		
		init();
		
		exit= new JButton("",Exit);
		{
			{
				exit.setSize(new Dimension(300,50));
				exit.setMaximumSize(new Dimension(100,100));
				exit.addActionListener(new action());
				exit.setBackground(Color.red);
			}
		};
		//label.setLocation(50, 300);
		help= new JButton("",Help);{
			{
				help.setSize(new Dimension(300,50));
				help.setMaximumSize(new Dimension(300,50));
				help.addActionListener(new action());
				help.setBackground(Color.red);
			}
		};
		
		label= new JLabel(imc);
		this.getContentPane().add(label);
		
		this.getContentPane().add(start);
		
		this.getContentPane().add(help);
		this.getContentPane().add(exit);
		this.getContentPane().add(a0);
		this.getContentPane().add(a);
		this.getContentPane().add(b);
		this.getContentPane().add(c);
		this.getContentPane().add(d);
		this.getContentPane().add(e);
		this.getContentPane().add(f);

		
	}
	JLabel a,b,c,d,e,f,a0; // i cannot write a labels that canbe w
	private void init() 
	{
		a=new JLabel(".  ");
		b=new JLabel(".  ");
		c=new JLabel(".   ");
		d=new JLabel(".   ");
		e=new JLabel(".z.  ");
		f=new JLabel("    ");
		a0=new JLabel(".");
	}
	
	private class action implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent arg) {
			if(arg.getSource()==exit) 
			{
				System.exit(1);
			}else if(arg.getSource()==start) 
			{
				exit();
				new Game().start();
			}else if(arg.getSource()==help) 
			{
				a0.setText("                                                                                                                ");
				a.setText("                                             DuckSImulation Game                                                ");
				a.setForeground(Color.white);
				b.setText("FOR MOVEMENTS USE THE   UP <up> ,  LEFT <left> ,  RIGHT <right>                                                 ");
				b.setForeground(Color.white);
				c.setText("AND DOWN <down> keys . AND , THE 'Z' CHARACTER TO SHOOT                                                           ");
				c.setForeground(Color.white);
				d.setText("   IN ORDER TO SERVIVE TRY NOT TO MISS DUCKS OR NOT TO HAVE A LIFE LESS THAN 1                                     ");
				d.setForeground(Color.white);
				e.setText("BUT KILLING A DUCK THAT MOVES TO THE LEFT WILL GIVE A BONUS LIFE OF 2                                           ");
				e.setForeground(Color.white);
				f.setText("TRY TO SCORE MORE POINT IN A MINIMUM AMOUNT OF TIME                                                             ");
				f.setForeground(Color.white);
			}
			
		}
		
	}
	private void exit() 
	{
		this.setVisible(false);
	}



}