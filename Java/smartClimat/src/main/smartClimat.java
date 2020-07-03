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
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

public class smartClimat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int x,f=1;
		Scanner sc = new Scanner(System.in);
		
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdff = new SimpleDateFormat("MMM");
		SimpleDateFormat sdfd = new SimpleDateFormat("d");


		Calendar cal = Calendar.getInstance();
		int sic = -1,sicc = -1,siccc= -1;
		int si=0, dy=0;
		int kset =0;
		int su =cal.getActualMaximum(Calendar.DAY_OF_MONTH);  int sua=cal.get(Calendar.DAY_OF_MONTH);
		boolean bos = false;
		if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) != cal.get(Calendar.DAY_OF_MONTH)) bos=true;
		for(int i=0;i<2;i++)    //nb year
		{	
		System.out.println("year : " + sdf.format(cal.getTime()));
		  for(int j=0;j<12;j++)
		  {
			  System.out.println("--mois : " + sdff.format(cal.getTime()));
			  int ku;
			  if(bos)
			     {
				  ku = cal.getActualMaximum(Calendar.DAY_OF_MONTH)-(cal.getActualMaximum(Calendar.DAY_OF_MONTH) -  cal.get(Calendar.DAY_OF_MONTH));
			//	  kset = (cal.getActualMaximum(Calendar.DAY_OF_MONTH) -  cal.get(Calendar.DAY_OF_MONTH));
				  bos = false;
			     }
			  else ku = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			  for(int k=0;k<ku ;k++)
			  {
				  System.out.println("----"+ku+"jours : " + sdfd.format(cal.getTime()));
				  cal.add(Calendar.DAY_OF_MONTH, siccc);
               dy++;
			  }
			  
			//  sicc--;
			//  cal.add(Calendar.MONTH, sicc);
		  }
		//  sic--;
	//		cal.add(Calendar.YEAR, sic);
		  System.out.println("----**********************************************************************année fin"+i);

		}
		if(kset != 0)
	{
		for(int h=0;h<kset;h++)
		{
			 System.out.println("----"+h+"jours : " + sdfd.format(cal.getTime())+" "+sdff.format(cal.getTime()));
			  cal.add(Calendar.DAY_OF_MONTH, siccc);
          dy++;
		}
	}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		SimpleDateFormat dff = new SimpleDateFormat("MM");
		Calendar cali = Calendar.getInstance();

		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  "+dy);
		String donnees="";
		int mounth = 0;
/*	 while(mounth<6) //24           nbyear * 12
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
		//	System.out.println(s); 
			
			}
		

		BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(donnees+".txt"))));
		String line = "";
		int stg =0;
		while((line = fr.readLine()) != null) 
		  {
		      String sp[] =line.split(";");
			System.out.println(sp.length);
			for(int i=0;i<sp.length;i++) System.out.print(sp[i]+" %% ");
			stg++;
			System.out.print("\n******\n"+stg+" -");
			//break;
		  }
		
		}
		catch(Exception e){System.out.println("\n exception2 : "+e.getMessage());}
		cali.add(Calendar.MONTH, -1);
		mounth++;
	 }
*/
	 
	 		userInterface ui = new userInterface();	
		


	}

}
