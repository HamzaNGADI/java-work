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


public class exemplerecu {

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
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		SimpleDateFormat dff = new SimpleDateFormat("MM");
		Calendar cali = Calendar.getInstance();

		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  "+dy);
	

	 
	 		fen fn = new fen();	
		
		
	}

}




class RangeEvaluator implements IDateEvaluator {

    private DateUtil dateUtil = new DateUtil();

    
    public boolean isSpecial(Date date) {
        return false;
    }

    
    public Color getSpecialForegroundColor() {
        return null;
    }

    
    public Color getSpecialBackroundColor() {
        return null;
    }

    
    public String getSpecialTooltip() {
        return null;
    }
    
    public boolean isInvalid(Date date) {
        return dateUtil.checkDate(date);
        // if the given date is in the range then is invalid
    }        

    /**
     * Sets the initial date in the range to be validated.
     * @param startDate 
     */
    public void setStartDate(Date startDate) {
        dateUtil.setMinSelectableDate(startDate);
    }

    /**
     * @return the initial date in the range to be validated.
     */
    public Date getStartDate() {
        return dateUtil.getMinSelectableDate();
    }

    /**
     * Sets the final date in the range to be validated.
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        dateUtil.setMaxSelectableDate(endDate);
    }

    /**
     * @return the final date in the range to be validated.
     */
    public Date getEndDate() {
        return dateUtil.getMaxSelectableDate();
    }

	public Color getInvalidBackroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getInvalidForegroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInvalidTooltip() {
		// TODO Auto-generated method stub
		return null;
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

	private  JCalendar jc=null,jcc=null;
	private SimpleDateFormat syear = new SimpleDateFormat("yyyy");
	private SimpleDateFormat smois = new SimpleDateFormat("MM");
	private SimpleDateFormat sjr = new SimpleDateFormat("dd");
	private data dts;
	
	public fen()
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
        max.add(Calendar.YEAR, -actyear);           // nb years
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
    
        
        
        
        
        
        
       
        
	}
	
	public void itemStateChanged(ItemEvent ev)
	{
		
	}
	
	public void mouseClicked(MouseEvent ev)
	{
		if(ev.getSource()==ok)
		{
			System.out.println("\ntid");
			Calendar d = jc.getCalendar();
			d.setTime(jc.getDate());
			Calendar dd = jcc.getCalendar();
			dd.setTime(jcc.getDate());
			try
			{
			System.out.println("\n d1 : "+ syear.format(d.getTime()) + " "+smois.format(d.getTime()) + " "+sjr.format(d.getTime()) +" ");
			System.out.println("\n d2 : "+ syear.format(dd.getTime()) + " "+smois.format(dd.getTime()) + " "+sjr.format(dd.getTime()) +" ");
			 dts = new data(d);
				dts.stations();
				dts.download();
				
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

class data
{
	private SimpleDateFormat df = new SimpleDateFormat("yyyy");
	private SimpleDateFormat dff = new SimpleDateFormat("MM");
	private Calendar cali = Calendar.getInstance();

	private ArrayList stationcode = new ArrayList();
	private ArrayList stationname = new ArrayList();
	private String sact ="";
	public data(Calendar c)
	{
	  cali=c;	
	}
	
  public void download()
   {
		System.out.println("******"+df.format(cali.getTime())+dff.format(cali.getTime())+"  ");
		String donnees="";
		int mounth = 0;
		
       
		 try
		{
			
			donnees = (df.format(cali.getTime())+dff.format(cali.getTime()));	
			 Calendar co = Calendar.getInstance();
			 

		if(cali.get(Calendar.YEAR)<=co.get(Calendar.YEAR) )
		{
			if(cali.get(Calendar.YEAR)==co.get(Calendar.YEAR) && cali.get(Calendar.MONTH)>co.get(Calendar.MONTH))
			{
				System.out.println("\n  pas de telechargement de future données");
			}
			else
			{
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
		}
		else System.out.println("\n  pas de telechargement de future données");
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
		

	System.out.println("\n dowload finished !! ");

   }

  public void stations()
  {
	  try
	  {
	  BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("stations.csv"))));
		String line = "";
		int stg =0;
		while((line = fr.readLine()) != null) 
		  {
		      String sp[] =line.split(";");
	//		System.out.println(sp.length);
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
	catch(Exception e){System.out.println("\n station exep : "+e.getMessage());}

  }
  

  

  
  
  
}




