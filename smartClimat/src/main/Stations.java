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

public class Stations {
	private ArrayList stationcode, stationname;
	private String currentStation; 	
	public Stations()
	{
	   stationcode = new ArrayList();
	   stationname = new ArrayList();
	   currentStation = null;
	   loadStations();
	}
	
	public void loadStations()
	{
		  try
		  {
		  BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("stations.csv"))));
			String line = "";
			int stg =0;
			while((line = fr.readLine()) != null) 
			  {
			      String sp[] =line.split(";");
				System.out.println(sp.length);
			    stationcode.add(sp[0]);
			    stationname.add(sp[1]);
		//	System.out.println("\n station : "+sp[0]+" - "+sp[1]);

				//break;
			  }

			for(int i=0;i<stationcode.size();i++)
			{
				System.out.println("\n station : "+stationcode.get(i)+" - "+stationname.get(i));
			}
			//sact = (String)stationcode.get(1);
			fr.close();
		  }
		catch(Exception e){System.out.println("\n station exep : "+e.getMessage());}

	}
	public String getStation(String nameStation)
	{
		if(stationcode.size() != 0 && stationname.size() !=0 && (stationcode.size() == stationname.size()) )
		{
			for(int i=0;i<stationname.size();i++)
			{
				String sact = (String)stationname.get(i);
				if(sact.equals(nameStation))
				{
					currentStation = (String)stationcode.get(i);
					return currentStation;
				}
			}
		}
		
		return null;
	}
	public ArrayList getNameStations()
	{
		if(stationname.size() != 0)
		{
			return stationname;
		}
		return null;
	}

	
	
	
	public void stations_old()
	  {
	/*	  try
		  {
		  BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("stations.csv"))));
			String line = "";
			int stg =0;
			while((line = fr.readLine()) != null) 
			  {
			      String sp[] =line.split(";");
				System.out.println(sp.length);
			    stationcode.add(sp[0]);
			    stationname.add(sp[1]);
		//	System.out.println("\n station : "+sp[0]+" - "+sp[1]);

				//break;
			  }

			for(int i=0;i<stationcode.size();i++)
			{
				System.out.println("\n station : "+stationcode.get(i)+" - "+stationname.get(i));
			}
			sact = (String)stationcode.get(1);
			fr.close();
		  }
		catch(Exception e){System.out.println("\n station exep : "+e.getMessage());} */

	  }

	
	
	
}
