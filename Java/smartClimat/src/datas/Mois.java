package datas;
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
public class Mois extends Data{
	 private ArrayList m1 , m2;

	public Mois(String sta)
	{
		m1 = new ArrayList();
		m2= new ArrayList();
		stationfilter(sta);
	}

	protected void filter(String jr, String mois, String ann, String jr1, String mois1,String ann1) {}
    public void filterMois(String mois, String ann, String mois1, String ann1) 
    {
    	filter( mois,  ann,  mois1, ann1) ;
    }
	protected void filter(String mois, String ann, String mois1, String ann1) 
	{
	
		  try
		  {
	//		  ArrayList m1 = new ArrayList(), m2= new ArrayList();
			  String donnees = ann+mois;
			  String donnees1 = ann1+mois1;
//			  statn = sact;
			  System.out.println(donnees+" "+donnees1+" "+statn);
		   if( (mois.equals(mois1)) && (!ann.equals(ann1)))
		   {
		    if((new File(donnees+".txt").exists()) && !(new File(donnees+".txt")).isDirectory() && (new File(donnees1+".txt").exists()) && !(new File(donnees1+".txt")).isDirectory())
		    {
			 BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(donnees+".txt"))));
			String line = "";
			int stg =0;
			
			while((line = fr.readLine()) != null) 
			  {
			      String sp[] =line.split(";");
			//	System.out.println(sp.length);
				if(statn.equals(sp[0]))
				{
				for(int i=0;i<sp.length;i++)
				{System.out.print(sp[i]+" %% ");
				m1.add(sp[i]);
				}
				System.out.print("\n");

				//stg++;
				//System.out.print("\n******\n"+stg+" -");
				//break;
				}
				//else System.out.print(" nop ");
			  }
			System.out.print("\n\n2em mois\n\n");
		   
			 BufferedReader fr1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(donnees1+".txt"))));
				String line1 = "";
				//int stg =0;
				
				while((line1 = fr1.readLine()) != null) 
				  {
				      String sp[] =line1.split(";");
				//	System.out.println(sp.length);
					if(statn.equals(sp[0]))
					{
					for(int i=0;i<sp.length;i++){ System.out.print(sp[i]+" %% ");       m2.add(sp[i]);         }
					System.out.print("\n");

			//		stg++;
				//	System.out.print("\n******\n"+stg+" -");
					//break;
					}
				  }
				System.out.println("m1 : "+m1.size()+" m2 : "+m2.size());
				if(m1.size() == 0 || m2.size()==0)  System.out.print("mois manquant ! besoin de telecharget ??!");

		    }
		    else System.out.print("data not found ! besoin de telecharget ??!");
		   }
		   else System.out.print("mois incompatible.......");
		  }
	   	catch(Exception e){System.out.println("\n mois : "+e.getMessage());}

		  
	}

	protected void filter(String ann, String ann1) {}
	
	
	
	
	protected void one_day(String jr, String mois, String ann){}
	  
	  public void mounth(String mois, String ann)
	  {
		  one_mounth( mois, ann);
	  }
	 protected void one_mounth(String mois, String ann)
	  {
		  
		  try
		  {
	//		  ArrayList m1 = new ArrayList(), m2= new ArrayList();
			  String donnees = ann+mois;
//			  statn = sact;
			  System.out.println(donnees+" "+" "+statn);
		   
		    if((new File(donnees+".txt").exists()) && !(new File(donnees+".txt")).isDirectory() )
		    {
			 BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(donnees+".txt"))));
			String line = "";
			int stg =0;
			
			while((line = fr.readLine()) != null) 
			  {
			      String sp[] =line.split(";");
			//	System.out.println(sp.length);
				if(statn.equals(sp[0]))
				{
				for(int i=0;i<sp.length;i++)
				{System.out.print(sp[i]+" %% ");
				m1.add(sp[i]);
				}
				System.out.print("\n");

				//stg++;
				//System.out.print("\n******\n"+stg+" -");
				//break;
				}
				//else System.out.print(" nop ");
			  }

				System.out.println("m1 : "+m1.size());
				if(m1.size() == 0 )  System.out.print("mois manquant ! besoin de telecharget ??!");

		    }
		    else System.out.print("data not found ! besoin de telecharget ??!");
		   
		  }
	   	catch(Exception e){System.out.println("\n one mois : "+e.getMessage());}

		  
	  }
   protected void one_year(String ann){}
	
	
	
	

}
