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
public class Annee extends Data{
	private ArrayList y1, y2;
	public Annee(String sta)
	{
		y1 = new ArrayList();
		y2= new ArrayList();
		stationfilter(sta);
		
	}

	
	protected void filter(String jr, String mois, String ann, String jr1, String mois1, String ann1) {}

	protected void filter(String mois, String ann, String mois1, String ann1) {	}
    
	public void filterAnnee(String ann, String ann1)
	{
		filter( ann, ann1);
	}
	protected void filter(String ann, String ann1) 
	{
		  
		  try
		  {
			if(!ann.equals(ann1))
		   {
			  ArrayList an = new ArrayList(), an1 = new ArrayList();
			  
			  File[] files = new File(".").listFiles();

			for (int i=0;i<files.length;i++) 
			{
			    if (files[i].isFile()) 
			    {
			    	String fname = files[i].getName();
			    	String ext = fname.substring(fname.lastIndexOf(".") + 1, fname.length());
			    	if(ext.equals("txt") && fname.contains(ann))
			    	  {	System.out.println(files[i].getName());     an.add(files[i].getName());        }
			    	if(ext.equals("txt") && fname.contains(ann1))
			    	  {	System.out.println("--"+files[i].getName());     an1.add(files[i].getName());     }
			    }
			}
			System.out.println(" an "+an.size()+" an2  "+an1.size());
			  
//			  ArrayList y1 = new ArrayList(), y2= new ArrayList();
			
	//		  statn = sact;
		   if( an.size()>1 && an1.size()>1)
		   {
		    for(int iy=0;iy<an.size();iy++)
		    {
			 BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File((String)an.get(iy)))));
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
				y1.add(sp[i]);
				}
				System.out.print("\n");

				//stg++;
				//System.out.print("\n******\n"+stg+" -");
				//break;
				}
				//else System.out.print(" nop ");
			  }
		    }
			System.out.print("\n\n2em annee\n\n");
			for(int iy=0;iy<an1.size();iy++)
		    {
			 BufferedReader fr1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File((String)an1.get(iy)))));
				String line1 = "";
				//int stg =0;
				
				while((line1 = fr1.readLine()) != null) 
				  {
				      String sp[] =line1.split(";");
				//	System.out.println(sp.length);
					if(statn.equals(sp[0]))
					{
					for(int i=0;i<sp.length;i++){ System.out.print(sp[i]+" %% ");       y2.add(sp[i]);         }
					System.out.print("\n");

			//		stg++;
				//	System.out.print("\n******\n"+stg+" -");
					//break;
					}
				  }
		    }
				System.out.println("y1 : "+y1.size()+" y2 : "+y2.size());		
				if(y1.size() == 0 || y2.size()==0)  System.out.print("année manquante ! besoin de telecharget ??!");
				
		   }
		   else System.out.print("pas assez de données annee ......."); 
		   }
		  else  System.out.print("année incompatible .......");
		  }
	   	catch(Exception e){System.out.println("\n years : "+e.getMessage());}

			
	}

	
	
	
	
	protected void one_day(String jr, String mois, String ann){}
	protected void one_mounth(String mois, String ann){}
	    public void year(String ann)
	    {
	    	one_year( ann);
	    }
	    protected void one_year(String ann)
	     {
			  try
			  {
				
				  ArrayList an = new ArrayList();
				  
				  File[] files = new File(".").listFiles();

				for (int i=0;i<files.length;i++) 
				{
				    if (files[i].isFile()) 
				    {
				    	String fname = files[i].getName();
				    	String ext = fname.substring(fname.lastIndexOf(".") + 1, fname.length());
				    	if(ext.equals("txt") && fname.contains(ann))
				    	  {	System.out.println(files[i].getName());     an.add(files[i].getName());        }
				    	
				    }
				}
				System.out.println(" an "+an.size());
				  
//				  ArrayList y1 = new ArrayList(), y2= new ArrayList();
				
		//		  statn = sact;
			   if( an.size()>1)
			   {
			    for(int iy=0;iy<an.size();iy++)
			    {
				 BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File((String)an.get(iy)))));
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
					y1.add(sp[i]);
					}
					System.out.print("\n");

					//stg++;
					//System.out.print("\n******\n"+stg+" -");
					//break;
					}
					//else System.out.print(" nop ");
				  }
			    }
				
			
					System.out.println("y1 : "+y1.size());		
					if(y1.size() == 0)  System.out.print("année manquante ! besoin de telecharget ??!");
					
			   }
			   else System.out.print("pas assez de données annee ......."); 
			   
			  }
		   	catch(Exception e){System.out.println("\n one years : "+e.getMessage());}

	     }
	
	
	
	
	
}
