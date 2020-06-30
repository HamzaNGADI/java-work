import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("closed map");
		
		closedhash<Integer,String> ch = new closedhash<Integer,String>(5);
		ch.put(1, "a");
		System.out.println("rem "+ch.remove(1));
		System.out.println(ch.get(1)+" "+ch.isEmpty());
		ch.put(1, "a");
		System.out.println(ch.get(1)+" "+ch.isEmpty());
		ch.clear();
		System.out.println(ch.get(1)+" "+ch.isEmpty());
		
		ch.put(1, "a");
		ch.put(22, "b");
		ch.put(12, "c");
		System.out.println("-------------------\n"+ch.get(1)+" "+ch.get(12)+" size "+ch.size());
		System.out.println("rem "+ch.remove(12));
		System.out.println(ch.get(1)+" "+ch.get(12)+" size "+ch.size());
		System.out.println(ch.get(1)+" "+ch.get(22));
		ch.put(12, "cne");
		System.out.println(ch.get(1)+" "+ch.get(12)+" size "+ch.size());
		System.out.println(ch.get(1)+" "+ch.get(22));
		
		
		System.out.println(ch.containsKey(10)+" "+ch.containsKey(1)+" "+ch.containsKey(12)+" "+ch.containsKey(22));
		ch.put(10, "cone");
		System.out.println(ch.containsKey(10)+" "+ch.containsKey(1)+" "+ch.containsKey(12)+" "+ch.containsKey(22));
		
		System.out.println(ch.containsValue("a")+" "+ch.containsValue("c")+" "+ch.containsValue("cne")+" "+ch.containsValue("cone"));
		ch.put(11, "c");
		System.out.println(ch.containsValue("a")+" "+ch.containsValue("c")+" "+ch.containsValue("cne")+" "+ch.containsValue("cone"));
		System.out.println(ch.keySet()+"      --      "+ch.values());
		System.out.println(ch.entrySet());
		ch.remove(11);
		ch.put(16, "n");
		System.out.println(ch.keySet()+"      --      "+ch.values());
		System.out.println(ch.entrySet());
		
		  JavaPlot p = new JavaPlot("C:\\Users\\PackardBell\\Downloads\\gp507-win64-mingw\\gnuplot\\bin\\gnuplot.exe");
	      double tb[][] = new double[ch.size()][2];
	      TreeSet<Integer> s =  (TreeSet<Integer>) ch.keySet();

	      int i=0;
	      for(Integer cf: s)
	      {
	    	  tb[i][0] = cf; tb[i][1] = i+1; 
	    	  System.out.println(cf);
	    	  i++;
	      }
	      DataSetPlot ds = new DataSetPlot(tb);
	      PlotStyle myPlotStyle = new PlotStyle();
	        myPlotStyle.setStyle(Style.LINES);   myPlotStyle.setLineWidth(2);
	        ds.setPlotStyle(myPlotStyle);
		  p.addPlot(ds);
	        p.plot();
	}

}
