import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

public class profhelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		float fu = 14f;
		System.out.println(fu);
		fen f = new fen();

	}

}

class fen extends JFrame implements MouseListener, ItemListener, WindowListener
{
	private ButtonGroup rg = new ButtonGroup();
	private JRadioButton ajoun = new JRadioButton("ajout de notes"),calcmoy = new JRadioButton("calculer la moyenne"), calcmed = new JRadioButton("calculer le medium"), affnotes = new JRadioButton("afficher les notes");
	private JButton ok = new JButton(), aj = new JButton(), canc = new JButton();
	private JLabel labl = new JLabel(), lcode, lnote;
	private int radn = -1;
	private ArrayList arrnotes = new ArrayList(), codet = new ArrayList();
	private JTextField note, code;
	private String titre[] = {"code", "note"};
	private Object obj[][];
	private JTable tab = new JTable();
	private JScrollPane spn = new JScrollPane(tab);

	public fen()
	{
		setTitle("ProfHelper");
		setSize(300,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout()) ;
		
		rg.add(ajoun);
		rg.add(calcmoy);
		rg.add(calcmed);
		rg.add(affnotes);
		ajoun.addItemListener(this);
		calcmoy.addItemListener(this);
		calcmed.addItemListener(this);
		affnotes.addItemListener(this);
		
		getContentPane().add(ajoun);
		getContentPane().add(calcmoy);
		getContentPane().add(calcmed);
		getContentPane().add(affnotes);
		
		
		ok.setText("choisir");
		ok.setBounds(30, 30, 100, 60);
		aj.setText("ajouter");
		aj.setBounds(30, 30, 100, 60);
		//getContentPane().add(ok);
		canc.setText("annuler");
		canc.setBounds(30, 30, 100, 60);
	//	getContentPane().add(canc);
		ok.addMouseListener(this); canc.addMouseListener(this); addMouseListener(this); aj.addMouseListener(this);
		
		canc.setBounds(30, 30, 100, 100);
		
		lcode = new JLabel("code : ");
		lcode.setBounds(30,20 , 40, 20);
		getContentPane().add(lcode);
		
		code= new JTextField(15);
		code.setBounds(40,20 , 90, 20);
		getContentPane().add(code);
		
		lnote = new JLabel("note : ");
		lcode.setBounds(30,25 , 40, 20);
		getContentPane().add(lnote);
		
		note= new JTextField(15);
		note.setBounds(40,25 , 90, 20);
		getContentPane().add(note);
		
		lcode.setVisible(false); lnote.setVisible(false);
		code.setVisible(false); note.setVisible(false);
		
		getContentPane().add(spn); spn.setVisible(false);
		getContentPane().add(ok); getContentPane().add(aj); getContentPane().add(canc);  aj.setVisible(false);
		getContentPane().add(labl);  canc.setVisible(false);
		
		this.addWindowListener(this);
	}
	
	public void itemStateChanged(ItemEvent ev)
	{
		if(ev.getSource()==ajoun){
			labl.setText("      confirmer pour ajouter des notes"); radn = 0;
		}
		if(ev.getSource()==calcmoy){
			if(arrnotes.size() == 0){
				labl.setText("notes indispensables, ajouter des notes"); radn = -1;
			}
			else
				{labl.setText("confirmer pour calculer la moyenne des notes"); radn = 1;}
		}
		if(ev.getSource()==calcmed){
			if(arrnotes.size() == 0){
				labl.setText("notes indispensables, ajouter des notes"); radn = -1;
			}
			else {labl.setText("confirmer pour calculer le mediane des notes"); radn = 2;}
		}
		if(ev.getSource()==affnotes){
			if(arrnotes.size() == 0){
				labl.setText("notes indispensables, ajouter des notes"); radn = -1;
			}
			else {labl.setText("     confirmer pour afficher les notes"); radn =3;}
		} 
		
	}
	
	public void mouseClicked(MouseEvent ev)
	{
		if(ev.getSource()==ok)
		{
			if(radn ==0)
			{
				setSize(260,200);

				labl.setText("enter le code et la note");
				lcode.setVisible(true); lnote.setVisible(true);
				code.setVisible(true); note.setVisible(true);
				ajoun.setVisible(false); calcmoy.setVisible(false);
				calcmed.setVisible(false); affnotes.setVisible(false);
				ok.setVisible(false); aj.setVisible(true);
				 canc.setVisible(true);
				
			}
			if(radn ==1)
			{
				getnotes(1);
			}
			if(radn ==2)
			{
				getnotes(2);
			}
			if(radn ==3)
			{
				getnotes(0);
			}

		}
		if(ev.getSource()==aj)
		{
			try
			{
				int bc=0;
				float no = Float.parseFloat(note.getText());
				String cc= code.getText();
				cc = cc.trim();
				if(codet.size()>0)
					{
					for(int i=0;i<codet.size();i++)
				{
					if(codet.get(i).equals(cc)) bc++;
				}
					}
				
				if(cc.length() != 0 && (no>=0 && no<=20) && bc==0)
				{
					arrnotes.add(new Float(no));
					codet.add(new String(cc));
					labl.setText("note ajoutée, ajoutez une note ou annuler");
				}
				else
				{
					labl.setText("donnée exisante ou format inadapté");
				}
			}
			catch(Exception e)
			{
				labl.setText("erreur de données");
			}
		}
		if(ev.getSource()==canc)
		{
			this.cancle();
		}
	}
	public void cancle()
	{
		labl.setText("");
		setSize(300,200);

		lcode.setVisible(false); lnote.setVisible(false);
		code.setVisible(false); note.setVisible(false);
		ajoun.setVisible(true); calcmoy.setVisible(true);
		calcmed.setVisible(true); affnotes.setVisible(true);
		ok.setVisible(true);  canc.setVisible(true); aj.setVisible(false);
		ok.setText("choisir");
		code.setText(""); note.setText("");
		ajoun.setSelected(false); calcmoy.setSelected(false);
		calcmed.setSelected(false); affnotes.setSelected(false);     canc.setVisible(false);
//		radn = -1;
		spn.setVisible(false);
	}
	public void getnotes(int x)
	{
		setSize(500,600);

		lcode.setVisible(false); lnote.setVisible(false);
		code.setVisible(false); note.setVisible(false);
		ajoun.setVisible(false); calcmoy.setVisible(false);
		calcmed.setVisible(false); affnotes.setVisible(false);
		ok.setVisible(false); aj.setVisible(false);
		canc.setVisible(false); canc.setVisible(true);
		data d = new data();
		labl.setText(d.gestion(x, obj, arrnotes, codet));
		obj = new Object[arrnotes.size()][2];
		obj = (Object[][]) d.affiche(x, obj, arrnotes, codet);
		
		tab = new JTable(obj,titre);  tab.setSize(200, 350);
		spn.setViewportView(tab); 
		spn.setVisible(true);
	}
	public void mouseEntered(MouseEvent ev) {}
	public void mouseExited(MouseEvent ev) {}
	public void mousePressed(MouseEvent ev) {	}
	public void mouseReleased(MouseEvent ev) {}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
		
	}

	public void windowClosing(WindowEvent arg0) {
		int x;
		
		x = JOptionPane.showConfirmDialog(null, "voullez-vous enregistrer avant de quitter ?");
		if(arrnotes.size() != 0)
		{
		while(x == 2 || x == -1)
		  {
			x = JOptionPane.showConfirmDialog(this, "voullez-vous enregistrer avant de quitter ?");
		  }
		}
		if(x==0)
		{
			data d = new data();
			d.file(arrnotes, codet);
	       if(arrnotes.size()==0)
			{
				JOptionPane.showMessageDialog(this, "pas de notes","Message d'avertissement",JOptionPane.ERROR_MESSAGE);
						 
			}
		}
		else
		{
			setVisible(false);
			dispose();
		}
	}

	public void windowDeactivated(WindowEvent arg0) {

	}

	public void windowDeiconified(WindowEvent arg0) {
	
	}

	public void windowIconified(WindowEvent arg0) {
		
	}

	public void windowOpened(WindowEvent arg0) {

	}

	
}

class data
{
	private String stri="";
	public data()
	{
		
	}
	public String gestion(int x,Object obj[][],  ArrayList arrnotes,  ArrayList codet)
	{
		obj = new Object[arrnotes.size()][2];
		for(int i=0;i<arrnotes.size();i++)
		{
			obj[i][0] = codet.get(i);
			obj[i][1] = arrnotes.get(i);
		}
		

		if(x==0)stri="\n";
		if(x==1)
		{
			float s=0;
			for(int i=0;i<arrnotes.size();i++)
			{
				s+= Float.parseFloat(arrnotes.get(i).toString());
			}
			DecimalFormat df = new DecimalFormat(".##");
			stri = "                                                     la moyenne : "+df.format((s/arrnotes.size()))+"                                                       ";
		}
		if(x==2)
		{
			float ts[] = new float[arrnotes.size()], var;
			for(int i=0;i<arrnotes.size();i++)
			{
				ts[i] = Float.parseFloat(arrnotes.get(i).toString());
			}
			
			for(int i=0;i<ts.length;i++)
			{
				for(int j=0;j<ts.length;j++)
				{
					if(ts[i]<ts[j])
					{
						var = ts[i];
						ts[i]=ts[j];
						ts[j]=var;
					}
				}
			}
			for(int i=0;i<ts.length;i++) System.out.println(" "+ts[i]);
			//System.out.println("mi "+ts[ts.length/2]+" "+ts[(ts.length/2)-1]);
			if(arrnotes.size()%2==0 && arrnotes.size()>1)
			{
				stri=("                                                     le mediane : "+((ts[ts.length/2]+ts[(ts.length/2)-1])/2)+"                                                       ");
			}
			else if(arrnotes.size()==1)
			{
				stri=("                                                     le mediane : "+(ts[ts.length-1])+"                                                       ");		
			}
			else stri=("                                                     le mediane : "+(ts[ts.length/2])+"                                                       ");

		}
		return stri;
	}
	public Object affiche(int x,Object obj[][],  ArrayList arrnotes,  ArrayList codet)
	{
		obj = new Object[arrnotes.size()][2];
		for(int i=0;i<arrnotes.size();i++)
		{
			obj[i][0] = codet.get(i);
			obj[i][1] = arrnotes.get(i);
		}
		

		return obj;

	}
	public void file(ArrayList arrnotes,  ArrayList codet)
	{
	       try{
			 int n = 0 ;
			 String srt="";
			 PrintWriter sortie = new PrintWriter(new FileWriter ("notes.txt")) ;
			 float ts[] = new float[arrnotes.size()];
			 String tss[] = new String[arrnotes.size()];
				for(int i=0;i<arrnotes.size();i++)
				{
					ts[i] = Float.parseFloat(arrnotes.get(i).toString());
					tss[i] = codet.get(i).toString();
				    sortie.println(tss[i]+" "+ts[i]+"\n");
				}
				

			 sortie.close () ;
	       }
	       catch(Exception e){}
	}
}