import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class VueLampe extends JPanel {
	private static final long serialVersionUID = 1L;
	lampeIn lampe;
	interuptor interrupteur;
	
	Rectangle intRect = new Rectangle(0, 0, 100, 25);

  

	public void paint(Graphics g) {
		super.paint(g);
		Color c = g.getColor();
		g.setColor(((lampeIn)interrupteur.obsfrominterup(interrupteur.idfrominterup(lampe))).isalumerCol());
		g.fillOval(50, 25, 100, 100);
		g.setColor(Color.BLACK);
		g.fillRect((int) intRect.getX(), (int) intRect.getY(),
				(int) intRect.getWidth(), (int) intRect.getHeight());
		g.setColor(Color.WHITE);
		g.drawString(((lampeIn)interrupteur.obsfrominterup(interrupteur.idfrominterup(lampe))).isalumertxt(), (int) intRect.getX(),
				(int) intRect.getY() + 15);
		g.setColor(c);
	}

	public void ouvrirFenetre() {
		JFrame window = new JFrame();
		window.getContentPane().add(this);
		window.pack();
		window.setBounds(new Rectangle(0, 0, 200, 200));
		window.setVisible(true);
		interrupteur = new interuptor();
		lampe = new lampeIn(new changeColor(interrupteur), interrupteur);
		interrupteur.register(new off(interrupteur), lampe);
//		interrupteur.delete(new off(interrupteur), lampe);
	//	interrupteur.delete(lampe);
		event egs = new event(this,intRect);
		this.addMouseListener(egs);
		window.addWindowListener(new GestFenetre());
	}
  
	public lampeIn getlamp()
	{
		return lampe;
	}
	public interuptor getinterup()
	{
		return interrupteur; 
	}
	
	public static void main(String[] args) throws InterruptedException {
		VueLampe vl = new VueLampe();
		vl.ouvrirFenetre();
		
	
	}

}



class GestFenetre extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
