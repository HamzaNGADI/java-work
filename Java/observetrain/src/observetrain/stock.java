package observetrain;

import java.util.ArrayList;
import java.util.List;

public class stock implements stockobs{
	
	List<observer> l;
	int pri;
	
	public stock()
	{
		l = new ArrayList<observer>();
	}
	
	@Override
	public void register(observer o) {
	    if(l.indexOf(o)==-1)  l.add(o);
	}

	@Override
	public void remove(observer o) {
		if(l.indexOf(o)!=-1)  l.remove(l.indexOf(o));
	}

	@Override
	public void notifyobs() {
	   for(int i=0;i<l.size();i++)
	   {
		   l.get(i).update(pri);
	   }
		
	}
	
	public void setprice(int p)
	{
		if(p != pri && p>=0)
		{
			pri = p;
			this.notifyobs();
		}
	}

}
