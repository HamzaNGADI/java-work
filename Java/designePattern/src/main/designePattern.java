package main;

import java.awt.Button;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.tools.JavaCompiler;

import main.controller.listen;

import MVC.*;
import adapter.*;
import bridge.*;
import builder.*;

public class designePattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("huup");
		
		CercleAdapter c = new CercleAdapter();
		c.afficher();
		c.remplir();
		
		//Ihm ih = new Ihm(new Controler());
		/*fen f = new fen();
		datafen df = new datafen();
		controller co = new controller(f,df);
		f.setVisible(true);*/
		
		WebApplication ap = new Blog(new Dark());
		User u = new User.UserBuilder("ngadi", "hamza").age(25).adr("25 rue kerg").build();
		System.out.println("user "+ u);
	}

}

class fen extends JFrame
{
	private  JButton ok = new JButton();
	private  JTextField jf = new JTextField(10);
	public fen()
	{
		setTitle("mvc");
		setSize(300,300);
		//setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout()) ;
		ok.setText("choisir");
		ok.setBounds(30, 30, 100, 60);
		jf.setBounds(30, 30, 100, 60);
		getContentPane().add(ok);
		getContentPane().add(jf);
	//	listen l = new listen();
//		ok.addActionListener(l);
		

	}
	public void change(String ch)
	{
		jf.setText(ch);
	}
	public String get()
	{
		return ok.getText();
	}
	public void addlisten(listen l)
	{
		ok.addActionListener(l);

	}
	
}
class datafen
{
	String data;
	
	public void setd(String d)
	{
		data = d;
	}
	public String getd()
	{
		return data;
	}
	public int getsi()
	{
		return data.length();
	}
}
/*interface ActionListener extends java.util.EventListener
{
	public void actionPerformed(Event ev);
}*/
class controller
{
	private fen fu ;
	private datafen df;
	public controller(fen f,datafen d)
	{
		fu = f; df = d;
		fu.addlisten(new listen());
	}
	
	class listen implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev) {
			String st = fu.get();
			df.setd(st);
			int sk = df.getsi();
			fu.change(df.getd()+" size : "+sk);
		}
		
	}
	
}

