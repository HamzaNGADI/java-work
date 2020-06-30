
public class DynTab<E> implements DynList<E> {
	
	private int size;
	private Object tab[]=null;
	public DynTab()
	{	
		size=0;
		tab = new Object[1];
	}
	public void aff()
	{
		for(int i=0;i<size();i++) System.out.print(tab[i]+" ");  System.out.print("\n");
	}
	@Override
	public void add(int index, E element) {
		int il=0;
		if(size==capacity())
		{
			Object tb[] = tab;
		
			tab = new Object[tb.length+1];
			for(int i=0;i<index;i++)  tab[i]=tb[i];	
			tab[index]=element;
    		for(int i=index;i<tb.length;i++)
	    	{
    				tab[i+1]=tb[i];
	    	}
    		size++;
		}
		else
		{
			
			Object tb[]= new Object[tab.length];
			for(int i=0;i<tab.length;i++)	tb[i]= tab[i];
			int in;
			for(int i=0;i<index;i++)  tab[i]=tb[i];
			in=index+1;
			for(int i=index;i<tb.length-1;i++)
	    	{
    				tab[i+1]=tb[i];
	    	}
			tab[index]=element;

    		size++;
		}
		

	}
	private int capacity()
	{
		return tab.length;
	}
	@Override
	public boolean add(E e) {
		if(size==capacity())
		{
			Object tb[] = tab;
		
			tab = new Object[tb.length+1];
    		for(int i=0;i<tb.length;i++)
	    	{
    			tab[i]=tb[i];
		    }
    		tab[size]=e;
    		size++;
		}
		else
		{
			tab[size]=e;
			size++;
		}
		
		return true;
	}

	@Override
	public void clear() {
		size=0;
		tab = new Object[1];
	}

	@Override
	public boolean contains(Object o) {
		try{
		o = (E) o;
		for(int i=0;i<size();i++)
		{
			if(get(i).equals(o)) return true;
		}
		return false;}
		catch(ClassCastException e){ return false;}
	}

	@Override
	public E get(int index) {
		try{
			return (E) tab[index];
		}
		catch(IndexOutOfBoundsException e){System.out.println(e.getMessage()); return null;}
	}

	@Override
	public int indexOf(Object o) {
		try{
			o = (E) o;
			for(int i=0;i<size();i++)
			{
				if(get(i).equals(o)) return i;
			}
			return -1;}
			catch(ClassCastException e){ return -1;}
	}

	@Override
	public boolean isEmpty() {
		if(size ==0) return true;
		else return false;
	}

	@Override
	public int lastIndexOf(Object o) {
		try{
			E oo = (E) o;
			for(int i=size()-1;i>=0;i--)
			{
				if(get(i).equals(oo)) return i;
			}
			return -1;}
			catch(ClassCastException e){ return -1;}
	}

	@Override
	public E remove(int index) {
		E e=null;
		for(int i=0;i<size();i++)
		{
			if(i==index) e=get(index);
			if(i>index)
			{
				
				tab[i-1] = tab[i];
			}
		}
		tab[size-1]=null;
		size--;
		return e;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		for(int i=0;i<size();i++)
		{
			if(get(i).equals(o))
			{
				for(int j=i;j<size-1;j++)
				{
					tab[j]=tab[j+1];
				}
				tab[size-1]=null; size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return tab.length;
	}

	@Override
	public Object[] toArray() {
		return tab;
	}

}
