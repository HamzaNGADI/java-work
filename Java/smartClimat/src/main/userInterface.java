package main;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.lang.*;
import java.net.URL;
import java.util.zip.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYLine3DRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.*;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JCalendar;

import datas.*;


public class userInterface extends JFrame implements MouseListener, ItemListener, WindowListener
{
	private ButtonGroup rg = new ButtonGroup();
	private JButton ok = new JButton(), aj = new JButton(), canc = new JButton();
	private JLabel labl = new JLabel(), lcode, lnote;
	private int radn = -1;
	private ArrayList arrnotes = new ArrayList(), codet = new ArrayList();
	private JTextField note, code;
	private String titre[] = {"code", "note"};
	private Object obj[][];
	private JTable tab = new JTable();
	private JScrollPane spn = new JScrollPane(tab);

	private  JCalendar jc=null,jcc=null;
	private SimpleDateFormat syear = new SimpleDateFormat("yyyy");
	private SimpleDateFormat smois = new SimpleDateFormat("MM");
	private SimpleDateFormat sjr = new SimpleDateFormat("dd");
	private dataa dts;
	
	public userInterface()
	{
		setTitle("smartclimat");
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout()) ;
		
		
		
		ok.setText("choisir");
		ok.setBounds(30, 30, 100, 60);
		aj.setText("ajouter");
		aj.setBounds(30, 30, 100, 60);
		//getContentPane().add(ok);
		canc.setText("annuler");
		canc.setBounds(30, 30, 100, 60);
	//	getContentPane().add(canc);
		ok.addMouseListener(this); canc.addMouseListener(this); addMouseListener(this); aj.addMouseListener(this);
		getContentPane().add(ok);
		DefaultPieDataset datp = new DefaultPieDataset();
		datp.setValue("homme", 15);
		datp.setValue("femme", 8);
		
		JFreeChart pc = ChartFactory.createPieChart("humains", datp, true,false,false); 
		ChartPanel cpa = new ChartPanel(pc);
	    cpa.setPreferredSize(new Dimension(350,300));
		getContentPane().add(cpa);
		
		
		YIntervalSeries xy= new YIntervalSeries("Series 1");
		YIntervalSeries xyy= new YIntervalSeries("Series 2");
		xy.add(1, 2, 0, 1);
		xy.add(2, 6, 2, 1);
		xy.add(3, 1, 0, 0);
		xyy.add(4, 1, 0, 1);
		xyy.add(3, 4, 1, 1);
		xyy.add(6, 3, 2, 0);
		YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(xy);
        dataset.addSeries(xyy);

        JFreeChart chart = ChartFactory.createXYLineChart("Deviat"," temp", "record",dataset, PlotOrientation.VERTICAL,true,true,false);
       
        XYSplineRenderer sp = new  XYSplineRenderer();
        sp.setSeriesShapesVisible(0, false);
        sp.setSeriesShapesVisible(1, false);
        sp.setSeriesStroke(0, new BasicStroke(3.0f));
        chart.getXYPlot().setShadowGenerator(new DefaultShadowGenerator(16, java.awt.Color.BLACK, 1, 10, 0));
        chart.getXYPlot().setRenderer(sp);
     
        ChartPanel cpas = new ChartPanel(chart);
       cpas.setPreferredSize(new Dimension(350,300));
        getContentPane().add(cpas);     		revalidate(); repaint();
		
        jc = new JCalendar();
        jcc = new JCalendar();
        Calendar cbol = Calendar.getInstance();
        SimpleDateFormat csbol = new SimpleDateFormat("yyyy");
        int actyear = Integer.parseInt(csbol.format(cbol.getTime()));
        
        Calendar min = Calendar.getInstance();
        min.add(Calendar.YEAR, -actyear);
        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, -150);           // nb years
         RangeEvaluator re = new RangeEvaluator();
         re.setStartDate(min.getTime());
         re.setEndDate(max.getTime());          
         
           Calendar minn = Calendar.getInstance();
         minn.add(Calendar.DAY_OF_MONTH, 1);
         Calendar maxx = Calendar.getInstance();
         maxx.add(Calendar.YEAR, actyear);  
         RangeEvaluator rre = new RangeEvaluator();
         rre.setStartDate(minn.getTime());
         rre.setEndDate(maxx.getTime());
         
        jc.getDayChooser().addDateEvaluator(re); 
        jc.setCalendar(jc.getCalendar());
        jc.getDayChooser().addDateEvaluator(rre);
        jc.setCalendar(jc.getCalendar());
        
        jcc.getDayChooser().addDateEvaluator(re); 
        jcc.setCalendar(jcc.getCalendar());
        jcc.getDayChooser().addDateEvaluator(rre);
        jcc.setCalendar(jcc.getCalendar());
        
        SimpleDateFormat sdj = new SimpleDateFormat("yyyy MMM d");
		Calendar cjc = jc.getCalendar();
		System.out.println("JC is : "+sdj.format(cjc.getTime()));
		
        getContentPane().add(jc);   getContentPane().add(jcc); 		revalidate(); repaint();
    
        
        
        
        
        
        
        //dts = new dataa();
		//dts.stations();
//		dts.download();
		
        
	}
	
	public void itemStateChanged(ItemEvent ev)
	{
		
	}
	
	public void mouseClicked(MouseEvent ev)
	{
		if(ev.getSource()==ok)
		{
			System.out.println("\ntyy");
			Calendar d = jc.getCalendar();
			d.setTime(jc.getDate());
			Calendar dd = jcc.getCalendar();
			dd.setTime(jcc.getDate());
			try
			{
			System.out.println("\n d1 : "+ syear.format(d.getTime()) + " "+smois.format(d.getTime()) + " "+sjr.format(d.getTime()) +" ");
			System.out.println("\n d2 : "+ syear.format(dd.getTime()) + " "+smois.format(dd.getTime()) + " "+sjr.format(dd.getTime()) +" ");
			
		
		//Data j = new Jours("MARIPASOULA");
		//	((Jours)j).filterjours(sjr.format(d.getTime()), smois.format(d.getTime()), syear.format(d.getTime()), sjr.format(dd.getTime()), smois.format(dd.getTime()), syear.format(dd.getTime()));
			
		//	Data m = new Mois("MARIPASOULA");
			//((Mois)m).filterMois(smois.format(d.getTime()), syear.format(d.getTime()), smois.format(dd.getTime()), syear.format(dd.getTime()));
			
	//	Data y = new Annee("MARIPASOULA");
		//	((Annee)y).filterAnnee(syear.format(d.getTime()), syear.format(dd.getTime()));
			
		             	//new stuuff
			
		//	Data jj = new Jours("MARIPASOULA");
	//		((Jours)jj).day(sjr.format(d.getTime()), smois.format(d.getTime()), syear.format(d.getTime()));
				
	//			Data mm = new Mois("MARIPASOULA");
		//	((Mois)mm).mounth(smois.format(d.getTime()), syear.format(d.getTime()));
				
			Data yy = new Annee("MARIPASOULA");
				((Annee)yy).year(syear.format(d.getTime()));
			
			
			
			
			Download dn = new Download();
		//	dn.getData(d);
	/*		System.out.println(dn.cleanfile());
			
			System.out.println("\n d1 : "+ syear.format(d.getTime()) + " "+smois.format(d.getTime()) + " "+sjr.format(d.getTime()) +" ");
			System.out.println("\n d2 : "+ syear.format(dd.getTime()) + " "+smois.format(dd.getTime()) + " "+sjr.format(dd.getTime()) +" ");
			
			dn=null; d=null; dd=null;*/
			
		//	System.out.println("\n d1 : "+ syear.format(d.getTime()) + " "+smois.format(d.getTime()) + " "+sjr.format(d.getTime()) +" ");
			//System.out.println("\n d2 : "+ syear.format(dd.getTime()) + " "+smois.format(dd.getTime()) + " "+sjr.format(dd.getTime()) +" ");
			
	 
		//	dts.getjours(sjr.format(d.getTime()), smois.format(d.getTime()), syear.format(d.getTime()), sjr.format(dd.getTime()), smois.format(dd.getTime()), syear.format(dd.getTime()),"");
		//	dts.getmois(smois.format(d.getTime()), syear.format(d.getTime()), smois.format(dd.getTime()), syear.format(dd.getTime()),"");	
		//	dts.getyears(syear.format(d.getTime()), syear.format(dd.getTime()), "");
			}
			catch(Exception e){System.out.println("\n  "+e.getMessage());}

		}
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

class dataa
{
	private SimpleDateFormat df = new SimpleDateFormat("yyyy");
	private SimpleDateFormat dff = new SimpleDateFormat("MM");
	private Calendar cali = Calendar.getInstance();

	private ArrayList stationcode = new ArrayList();
	private ArrayList stationname = new ArrayList();
	private String sact ="";
	public dataa()
	{
		
	}
	
  public void download(){}

  public void stations()
  {  }
  
  public void getjours(String jr, String mois, String ann, String jr1, String mois1, String ann1, String statn)
  {	  }
  
  public void getmois(String mois, String ann, String mois1, String ann1, String statn){}
  
  public void getyears(String ann, String ann1, String statn){}

}


/*
 2017 1 3 

2016 2 8

17 2 16 2 -- 10679  12567

213*59   52*59     235*59   =  29500     

213   235   53        29559


273  225  238  227   224    181   240     146   241   142


(237*59)+(225*59)+(238*59)+(227*59)+(224*59)+(181*59)+(240*59)+(146*59)+(241*59)+(142*59)


225  146  238  237 181  224 241  227   142  240 == 123959
 */

