
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
public class fen extends JFrame implements MouseListener
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
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}