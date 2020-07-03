package observtrainn;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stock implements stockobs{
	
	Map<String,observer> l;
	int pri;

	
	public stock()
	{
		l = new HashMap<String,observer>();
	}
	
	@Override
	public void register(event ev,observer o) {
	    
		if(l.containsKey(ev)==false)  l.put(ev.getClass().getName(),o);
	}

	@Override
	public void remove(event ev,observer o) {
		for(Map.Entry<String,observer> ol : l.entrySet())
		{
			if(ol.getKey().equals(ev.getClass().getName()) && ol.getValue().equals(o))
			{
				System.out.println("removed");
				l.remove(ol.getKey());
			} 
		}
	}

	@Override
	public void notifyobs() {
		for(Map.Entry<String,observer> ol : l.entrySet())
	   {
		   if(ol.getKey().equals("observtrainn.evprice")) {
			 //  System.out.println("updated obs");
			   ol.getValue().update(pri);
		   }
	   }
		
	}
	
	public void setprice(int p)
	{

		for(Map.Entry<String,observer> ol : l.entrySet())
		   {
			if(ol.getKey().equals("observtrainn.evprice"))
			{
				try {
				 Class<?> myClass = Class.forName(ol.getKey());
			        Constructor<?> ctr = myClass.getConstructor();
			       
				Object h = ctr.newInstance();
				     if(h instanceof evprice)
				     {
				    	 pri = ((evprice)h).setprice(p,pri);
				    	 notifyobs();          return;
				     }
				}
				catch(Exception e) {			   System.out.println("excep : "+e.getMessage());}
				
			}
		   }
		
	}
	
}
