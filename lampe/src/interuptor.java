import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class interuptor implements interupteurIn{
	
	private ArrayList<evenement> evb;
	private Map<String, observer> ob;
	private boolean isalum=false, isoff=false;
	
	public interuptor()
	{
		ob = new  HashMap<String, observer>();
		evb = new ArrayList<evenement>();
	}
	
	@Override
	public void register(evenement ev, observer o) {
		if(!ob.containsKey(ev.getClass().getName()))
		{
			ob.put(ev.getClass().getName(), o);
	       evb.add(ev);
		}
	}
	@Override
	public void delete(evenement ev, observer o) {
		System.out.println("removing ....");
		for(Map.Entry<String, observer> or : ((Map<String, observer>) ob).entrySet())
		{
		if(or.getKey().equals(ev.getClass().getName()) && or.getValue().equals(((lampeIn)o)))
		{
			System.out.println("removed "+or.getKey());
			ob.remove(or.getKey()); evb.remove(or);
		}
		}
	}
	@Override
	public void notifyobs() {
		for(Map.Entry<String, observer> o : ((Map<String, observer>) ob).entrySet())
		{
			if(o.getKey().equals("changeColor"))  o.getValue().update(isalum);
			if(o.getKey().equals("off") && isoff){  
				  o.getValue().update(); 
			      isoff = false;
			}
		}
	}
	public void setchange(boolean b)
	{
		for(Map.Entry<String, observer> o : ((Map<String, observer>) ob).entrySet())
		{
			System.out.println(o.getKey());
			if(o.getKey().equals("changeColor")){
				for(evenement ec : evb)
				{
					if(ec instanceof changeColor)
					{
					   isalum = ((changeColor)ec).change(b);
			    	   notifyobs();
			          return;
					}
				}
		   
			}
		}
	}
	public void setoff()
	{
		for(Map.Entry<String, observer> o : ((Map<String, observer>) ob).entrySet())
		{
			System.out.println(o.getKey());
			if(o.getKey().equals("off")){
				for(evenement ec : evb)
				{
					if(ec instanceof off)
					{
					   isoff = ((off)ec).setoff();
			    	   notifyobs();
			          return;
					}
				}
		   
			}
		}
	}
	public boolean isalumerint()
	{
		return isalum;
	}
	public int idfrominterup(observer o) {
		
		return ((lampeIn)o).idlamp();
	}
	public observer obsfrominterup(int id) {
		for(Map.Entry<String, observer> o : ob.entrySet())
		{
			if(((lampeIn)o.getValue()).idlamp()==id)    return (lampeIn)o.getValue();
		}
		return null;
	}
}
