import java.lang.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.Random;



public class pingpong {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("its works !!");
		//pongframe f = new pongframe(0,0);
	    ponginit pi = new ponginit();
	}

}
class pongframe extends JFrame implements Runnable,KeyListener 
{
	private int xp,yp, xpp,ypp;
	private int xbal, ybal;
	private Thread t,tball;
	private boolean byy=true, bbal=false,bv,bvo,bvv,bvvo, drawscr=false, pcpu;
	private int scrp=0,scrpp=0;
	private Random rand,randb;
	public pongframe(int x, int xx,boolean pc)
	{
		this.setTitle("pong game");
		this.setSize(500, 400);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		pcpu=pc;
		rand = new Random();  randb = new Random();
 
		if(pcpu==true)
		{
		 t = new Thread(){
			public void run()
			{
				try
				{
					while(true)
					{
						int rcpu = rand.nextInt(30-1)+1;
						if(ypp<40) {byy=true; }
						if(ypp>330) {byy=false; }
						if(rcpu==22) {byy=true; }
						if(rcpu==10) {byy=false; }
						
						if(byy==true) {ypp+=10; repaint(); }
						if(byy==false) {ypp-=10; repaint(); }
						
						t.sleep(100);
						
						if(xbal<-10)     t.stop();
						if(xbal>510)	t.stop(); 
					}
				}
				catch(Exception e){}
			}
		 };
		 t.start(); 
		}
		tball = new Thread(this);
		tball.start();
		xp=30; yp=50;      xpp= this.getWidth()-40; ypp=50;
		xbal= this.getWidth()/2;    ybal=this.getHeight()/2;   
		scrp=x; scrpp=xx;
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 400);
		g.setColor(Color.red);
		g.fillRect(xp, yp, 5, 50);
		g.setColor(Color.red);
		g.fillRect(xpp, ypp, 5, 50);
		
		g.setColor(Color.blue);
		g.fillOval(xbal, ybal, 10, 10);
		g.setColor(Color.black);
		
		if(pcpu==true)g.drawString("player1 : "+scrp+" | CPU : "+scrpp, (this.getWidth()/2)-70, this.getHeight()-40); 
		if(pcpu==false)g.drawString("player1 : "+scrp+" | player2 : "+scrpp, (this.getWidth()/2)-70, this.getHeight()-40); 
		if(drawscr==true){
			g.drawString("tappez espace pour rejouer", (this.getWidth()/2)-90, this.getHeight()-20);  //drawscr=false;
		}
	}
	public void run() 
	{ 
		try
		{
			while(true)
			{
				int cpuball=5;

				if(xbal==xpp-5 && (ybal>=ypp && ybal<=ypp+50) )
				{bbal=false;
				cpuball = randb.nextInt(10-3)+3;
          		   if(ybal>ypp+25) { /*System.out.println("plus de 25------2");*/  bvv=true;  bvvo=true;    }
			       if(ybal<ypp+25)  {  /*System.out.println("moin de 25------2");*/ bvv=false;  bvvo=false;    }
				}
				if(xbal==xp && (ybal>=yp && ybal<=yp+50) )
				{ bbal=true;
				cpuball = randb.nextInt(10-3)+3;
				  if(ybal>yp+25) { /*System.out.println("plus de 25");*/  bv=true;  bvo=true;    }
				  if(ybal<yp+25)  {  /*System.out.println("moin de 25");*/ bv=false;  bvo=false;    }
				}
				if(bbal==true)   // 1 player
				{
					xbal+=5;
				    if(bv==false)
				    {
				    	if(ybal<25) bvo=true;
				    	if(ybal>380) bvo=false;
				    	if(bvo==true) ybal+=cpuball;
				    	if(bvo==false) ybal-=cpuball;

				    }
				    if(bv==true)
				    {
				    	if(ybal<25) bvo=true;
				    	if(ybal>380) bvo=false;
				    	if(bvo==true) ybal+=cpuball;
				    	if(bvo==false) ybal-=cpuball; 
				    }
				    
				repaint(); 
				}//----------------------------------------------------
				if(bbal==false) // player 2
				{
					xbal-=5; 
				    if(bvv==false)
				    {
				    	if(ybal<25) bvvo=true;
				    	if(ybal>380) bvvo=false;
				    	if(bvvo==true) ybal+=cpuball;
				    	if(bvvo==false) ybal-=cpuball;

				    }
				    if(bvv==true)
				    {
				    	if(ybal<25) bvvo=true;
				    	if(ybal>380) bvvo=false;
				    	if(bvvo==true) ybal+=cpuball;
				    	if(bvvo==false) ybal-=cpuball; 
				    }	
					
					repaint();
				}    // 2 player
				
				if(xbal<-10)
				{
					scrpp++; drawscr=true; repaint();
					tball.stop();
				}
				if(xbal>this.getWidth()+10)
				{
					scrp++; drawscr=true; repaint();
					tball.stop(); 
				}
				
				tball.sleep(60);
			}
		}
		catch(Exception e){}
	}

	public void keyPressed(KeyEvent kev)
	{
		if(kev.getKeyCode() == KeyEvent.VK_A)
		{
			if(yp>30) {yp-=10; repaint(); }
		}
		if(kev.getKeyCode() == KeyEvent.VK_Q)
		{
			if(yp<340) {yp+=10; repaint(); }
		}
		//------------------------------ player2
	if(pcpu==false)
	{
		if(kev.getKeyCode() == KeyEvent.VK_Z)
		{
			if(ypp>30) {ypp-=10; repaint(); }
		}
		if(kev.getKeyCode() == KeyEvent.VK_S)
		{
			if(ypp<340) {ypp+=10; repaint(); }
		}
	}	
		if(kev.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(drawscr==true)
			{
			pongframe pn= new pongframe(scrp,scrpp, pcpu);
			this.setVisible(false);
			}
		}	
	}
	public void keyReleased(KeyEvent kev) 
	{}
	public void keyTyped(KeyEvent kev)
	{}

	
}


class ponginit extends JFrame implements MouseListener
{
	private JButton pl = null, pcpul=null;
	private boolean bk=true;
	public ponginit()
	{
		this.setTitle("pong game");
		this.setSize(200, 300);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		getContentPane().setLayout(null) ;
		
		
		pl = new JButton("player1 vs player2");
		pl.setBounds(10, 70, 150, 50);
		pcpul = new JButton("player1 vs CPU");
		pcpul.setBounds(10, 120, 150, 50);
		pl.addMouseListener(this);
		pcpul.addMouseListener(this);
		this.getContentPane().add(pl);
		this.getContentPane().add(pcpul);
		pl.setVisible(false);
		pcpul.setVisible(false);

	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		g.drawString("ping pong game", 50, 70);
		pl.setVisible(true);
		pcpul.setVisible(true);
		g.drawString("player1 :", 30, 220);
		g.drawString("player2 :", 120, 220);
		
		g.drawString("up : A                      up : Z", 20, 240);
		g.drawString("down : Q                down : S", 20, 260);


	}
	public void mouseClicked(MouseEvent ev) {
		if(ev.getSource()==pl)
		{
			this.setVisible(false);
			pongframe f = new pongframe(0,0,false);
		}
		if(ev.getSource()==pcpul)
		{
			this.setVisible(false);
			pongframe f = new pongframe(0,0,true);
		}
	}

	public void mouseEntered(MouseEvent ev) {
	}

	public void mouseExited(MouseEvent ev) {
	}

	public void mousePressed(MouseEvent ev) {
	}

	public void mouseReleased(MouseEvent ev) {
		
	}
	
}
