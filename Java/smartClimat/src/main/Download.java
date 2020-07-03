package main;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.lang.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
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
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

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
public class Download {
	
	private SimpleDateFormat df, dff;
	private Calendar cali;

	
	public Download()
	{
	    df = new SimpleDateFormat("yyyy");
	    dff = new SimpleDateFormat("MM");
//		cali = d;

	}
	
	public void getData(Calendar d)
	{
		cali = d;
		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  ");
		String donnees="";
		int mounth = 0;
		boolean down = true;
		int ku = 12,mcl=0;
		Calendar cd =  Calendar.getInstance();
		Calendar back = cali;
		
		if(cd.get(Calendar.YEAR) == cali.get(Calendar.YEAR) && ((cd.get(Calendar.MONTH) <= cali.get(Calendar.MONTH))||(cd.get(Calendar.MONTH) >= cali.get(Calendar.MONTH))))
		{
			cali.set(Calendar.MONTH, cd.get(Calendar.MONTH));
			 ku = (cd.getActualMaximum(Calendar.MONTH)+1) -((cd.getActualMaximum(Calendar.MONTH)+1) -  (cd.get(Calendar.MONTH)+1));
	//	  System.out.println("mois = "+ku+ " --- act "+(cali.get(Calendar.MONTH)+1)+" max "+(cali.getActualMaximum(Calendar.MONTH)+1));
		}
		else if(cd.get(Calendar.YEAR) > cali.get(Calendar.YEAR)){ 
	//		  System.out.println("oldd 2-mois = "+ku+ "acttt "+dff.format(cali.getTime())); 
			cali.set(Calendar.MONTH, 11);
		//  System.out.println("2-mois = "+ku+ "acttt "+dff.format(cali.getTime())); 
		}
		else ku=0;

		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  ");

	 while(mounth<ku && ku!=0) //24           nbyear * 12
	 {
		 down = true;
			donnees = (df.format(cali.getTime())+dff.format(cali.getTime()));	

		 if(/*mounth>2 &&*/ ((new File(donnees+".txt").exists())) ) {down = false; /*ku--;*/}
			 
		if(down == true)
	   {
		 try
		{
			
			donnees = (df.format(cali.getTime())+dff.format(cali.getTime()));	
	
		URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop."+donnees+".csv.gz");
		//URL urll = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/synop.2017013021.csv");
		InputStream in = (InputStream)url.openStream();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(donnees+".csv.gz"));

	//	 PrintWriter sortie = new PrintWriter(new FileWriter (donnees+".txt")) ;
	
		 byte[] buf = new byte[1024];
		int n;
		while ((n=in.read(buf,0,buf.length))>0) out.write(buf,0,n);
		out.close();
		in.close();
		}
		catch(Exception e){System.out.println("\n exception : "+e.getMessage());}
		
		try
		{
		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(donnees+".csv.gz"));
		BufferedReader br = new BufferedReader(new InputStreamReader(gzip));
		 PrintWriter sortie = new PrintWriter(new FileWriter (donnees+".txt")) ;

		String s="",sup="",spi="";
		while((s = br.readLine()) != null)
	     	{	
			sortie.println(s); 
			sortie.flush();
			System.out.println(s); 
			
			}
		
		}
		catch(Exception e){System.out.println("\n exception2 : "+e.getMessage());}
	  }
		cali.add(Calendar.MONTH, -1);
		mounth++; mcl++;
	 }
        for(int i=0;i<mcl;i++) cali.add(Calendar.MONTH, +1);
	System.out.println("\n dowload finished !! ");
	}
	
	public boolean cleanfile()
	{
	
//			  String a1 = df.format(d.getTime());
	//		  String a2 = df.format(dd.getTime());
			  ArrayList remove = new ArrayList(), removename = new ArrayList();
			  File[] files = new File(".").listFiles();
       try{
			for (int i=0;i<files.length;i++) 
			{
			    if (files[i].isFile() && (!files[i].getName().equals(".classpath") && !files[i].getName().equals(".project") && !files[i].getName().equals("stations.csv"))) 
			    {
			    	Path path = Paths.get(files[i].getName());
			    	BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			    	System.out.println(files[i].getName()+"  "+attr.creationTime());
			    	
			    	String str = (attr.creationTime().toString())+";"+(files[i].getName().toString());
			    	remove.add(str);
			    }
			}
			for(int i=0;i<remove.size();i++) System.out.println(remove.get(i));
		//	SortedList re = new SortedList((ObservableList) remove);
			Collections.sort(remove);
			for(int i=0;i<remove.size();i++) System.out.println(remove.get(i));
			
	//		System.out.println("----------"+remove.get(0));
          if(remove.size()>(24*2))
          {
        	  int lk=0;
			while(lk<(12*2))
			{
			//	System.out.println("--hgf--------"+remove.get(0));
				 String sp[] =((String)remove.get(lk)).split(";");
				File file = new File(sp[1]);
//				System.out.println("----------"+sp[0]);
	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    		lk++;
			}
          }
			
       }
       catch(Exception e){				System.out.println("exp : "+e.getMessage());}
		return true;
	}
	
	public void download_old()
	{
		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  ");
		String donnees="";
		int mounth = 0;
		
	 while(mounth<24+1) //24           nbyear * 12
	 {
		 try
		{
			
			donnees = (df.format(cali.getTime())+dff.format(cali.getTime()));	
		
		URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop."+donnees+".csv.gz");
		//URL urll = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/synop.2017013021.csv");
		InputStream in = (InputStream)url.openStream();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(donnees+".csv.gz"));

	//	 PrintWriter sortie = new PrintWriter(new FileWriter (donnees+".txt")) ;
	
		 byte[] buf = new byte[1024];
		int n;
		while ((n=in.read(buf,0,buf.length))>0) out.write(buf,0,n);
		out.close();
		in.close();
		}
		catch(Exception e){System.out.println("\n exception : "+e.getMessage());}
		
		try
		{
		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(donnees+".csv.gz"));
		BufferedReader br = new BufferedReader(new InputStreamReader(gzip));
		 PrintWriter sortie = new PrintWriter(new FileWriter (donnees+".txt")) ;

		String s="",sup="",spi="";
		while((s = br.readLine()) != null)
	     	{	
			sortie.println(s); 
			sortie.flush();
			System.out.println(s); 
			
			}
		
		}
		catch(Exception e){System.out.println("\n exception2 : "+e.getMessage());}
		cali.add(Calendar.MONTH, -1);
		mounth++;
	 }

	System.out.println("\n dowload finished !! ");


	}
}
