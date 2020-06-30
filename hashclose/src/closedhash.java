import java.awt.List;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


public class closedhash<k,v> implements Map<k,v>{

	couple<k,v> t[];
	int cap;
	int size;
	
	public closedhash(int n)
	{
		t = new couple[n]; cap=n;
		size=0;
	}
	
    public int computepos(k kk)
    {
    	return  Math.abs(kk.hashCode()) % cap;
    }
    public int computeposv(v vv)
    {
    	return  Math.abs(vv.hashCode()) % cap;
    }
	@Override
	public void clear() {
		t = new couple[cap]; size=0;
	}



	@Override
	public boolean containsKey(Object kk) {
		k ko = (k)kk;
		int pos = computepos(ko);
		int idx = pos;
		do
		{
			if(t[idx] != null)
			{
				if(t[idx].getkey().equals(ko)) return true;
			}
			
			idx=(idx+1)%cap;
		}while(idx != pos);
		return false;
	}

	@Override
	public boolean containsValue(Object vv) {
		v vo = (v)vv;
		int pos = computeposv(vo);
		int idx = pos;
		do
		{
			if(t[idx] != null)
			{
				if(t[idx].getval().equals(vo)) return true;
			}
			
			idx=(idx+1)%cap;
		}while(idx != pos);
		return false;
	}

	@Override
	public v get(Object kk) {
		k ko = (k)kk;
		int pos = computepos(ko);
		int idx = pos;
		do
		{
			if(t[idx] != null)
			{
				if(t[idx].getkey().equals(ko)) return t[idx].getval();
			}
			
			idx=(idx+1)%cap;
		}while(idx != pos);
		
		return null;
	}

	

	@Override
	public boolean isEmpty() {
		if(size()==0) return true;
		else return false;
	}
	@Override
	public int size() {
		return this.size;
	}

	
	

	@Override
	public v put(k kk, v vv) {
		for(int i=0;i<t.length;i++)
	      {
	    	  if(t[i] != null)
	    	  {
	    		  if(t[i].getkey().equals(kk)){ System.out.println("key existed");
	    		  return null;}
	    	  }
	      }
		if(size != t.length)
		{
			couple<k,v> c = new couple<k,v>(kk,vv);
		      for(int i=0;i<t.length;i++)
		      {
		    	  if(t[i] == null)
		    	  {
		    		  t[i]=c;  size++;
		    		  return vv;
		    	  }
		      }
		    	  
		}
		else System.out.println("tab full");
		return vv;
	}



	@Override
	public v remove(Object kk) {
		k ko = (k)kk;
		for(int i=0;i<t.length;i++)
	      {
	    	  if(t[i] != null)
	    	  {
	    		  if(t[i].getkey().equals(kk)){
	    			  v val = t[i].getval();
	    				t[i]=null;  size--;
	    				return val;
	    		  }
	    	  }
	      }
	
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<k, v>> entrySet() {
		Set<Entry<k,v>> kl = new HashSet<Entry<k,v>>();
		for(int i=0;i<t.length;i++)
	      {
	    	  if(t[i] != null)
	    	  {
	    		  Entry<k, v> ke = new SimpleEntry<k,v>(t[i].getkey(), t[i].getval());
	    		 kl.add(ke);
	    	  }
	      }
		if(kl.size()!=0) return kl;
		return null;
	}	
	@Override
	public Set<k> keySet() {
		Set<k> ks = new TreeSet<k>();
		for(int i=0;i<t.length;i++)
	      {
	    	  if(t[i] != null)
	    	  {
	    		 ks.add(t[i].getkey());
	    	  }
	      }
		if(ks.size()!=0) return ks;
		return null;
	}
	@Override
	public Collection<v> values() {
		Collection<v> kv = new TreeSet<v>();
		for(int i=0;i<t.length;i++)
	      {
	    	  if(t[i] != null)
	    	  {
	    		 kv.add(t[i].getval());
	    	  }
	      }
		if(kv.size()!=0) return kv;
		return null;
	}


	

	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------
	@Override
	public boolean remove(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public v getOrDefault(Object arg0, v arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void forEach(BiConsumer<? super k, ? super v> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public v merge(k arg0, v arg1,
			BiFunction<? super v, ? super v, ? extends v> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void putAll(Map<? extends k, ? extends v> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public v putIfAbsent(k arg0, v arg1) {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public v replace(k arg0, v arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean replace(k arg0, v arg1, v arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void replaceAll(BiFunction<? super k, ? super v, ? extends v> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public v compute(k arg0, BiFunction<? super k, ? super v, ? extends v> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public v computeIfAbsent(k arg0, Function<? super k, ? extends v> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public v computeIfPresent(k arg0,
			BiFunction<? super k, ? super v, ? extends v> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
