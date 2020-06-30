import java.awt.List;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


public class training {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("training");
		
		try{
	    listdc<Integer> dc = new listdc<Integer>(); System.out.println(dc.isempty());
		dc.add(2);
		System.out.println(dc);  System.out.println(dc.isempty());
		dc.add(5);
		System.out.println(dc);
		dc.add(7);
		System.out.println(dc);
		dc.add(6);
		System.out.println(dc);
		dc.add(0, 3);  		System.out.println(dc);   
		System.out.println(dc.get(0));
		System.out.println("\n-- "+dc.set(3, 9));   System.out.println(dc);
		
		System.out.println("all");
		listdc<Integer> da = new listdc<Integer>(); 
		da.add(8);
		System.out.println(da);
	 		da.add(9);
		System.out.println(da);
		da.add(7);
		System.out.println(da);
		
		System.out.println(dc);
		dc.addall(da);  System.out.println("all done\n"+dc+"_____");

		dc.addall(4,da);  System.out.println("all done\n"+dc);
		System.out.println(dc.remove(4));  System.out.println(dc);
		
		System.out.println(dc.sublist(0, 9));
		listdc<Integer> dau = new listdc<Integer>(); 
		dau.add(8);
		System.out.println(dau);
		dau.add(9);
		System.out.println(dau);
		dau.add(7);
		System.out.println(dau);  System.out.println(da);
		System.out.println(da.equals(dau));  
		System.out.println(da.contains(9)); 
		System.out.println(dc.containsall(dau)); 
		System.out.println(da.get(0));  	System.out.println(da.set(0, 1));
		System.out.println(da);
		}
		catch(horsbornes e){ System.out.println("exception by horsbornes");}

	}

}
interface liste<E>
{
	boolean isempty();
	int size();
	liste<E> clear();
	E get(int i) throws horsbornes;  //
	E set(int i ,E e) throws horsbornes;//
	int contains(Object o);
	boolean containsall(liste<?> l);
	boolean equals(Object o);
	liste<E> add(E e);
	liste<E> add(int i, E e) throws horsbornes;//
	liste<E> addall(liste<? extends E> e);
	liste<E> addall(int i,liste<? extends E> e) throws horsbornes;//
	E remove(int i) throws horsbornes; //
	liste<E> sublist(int deb, int fin) throws horsbornes; //
	
}
class listdc<E> implements liste<E>
{
	
	private class chaino
	{
		public E val;
		public chaino suiv, prec;
		public chaino(E v, chaino suivo, chaino pre)
		{
			val = v; suiv = suivo; prec = pre;
		}
		public chaino(E v)
		{
			val = v; suiv = null; prec = null;
		}
		
	}
	private chaino tete,qu,c;
	private int t,ic;
	
	public listdc()
	{
		tete=qu=c=new chaino(null);   t=0; ic =-1;
	}
	public listdc(E tab[])
	{
		this();
		for(int i=0;i<tab.length; i++)
		{
			this.add(tab[i]);
		}
	}
	
	@Override
	public boolean isempty() {
		if(t==0) return true;
		else return false;
	}

	@Override
	public int size() {
		return t;
	}

	@Override
	public liste<E> clear() {
		return new listdc<E>();
	}

	@Override
	public E get(int i) throws horsbornes
	{
		if(i<0 || i>=t) throw new horsbornes();
		int iv;
		if(i<t/2)
		{
			iv=0;
		chaino c = tete;
		while(iv<i){ c = c.suiv; iv++;}
		return c.val;
		}
		else{
			chaino c = qu;
			if(t%2 == 0){
				iv=t-2;
			while(iv>=i) {c = c.prec; iv--;}
			return c.val;  }
			else {
				iv=t-1;
				while(iv>i) {c = c.prec; iv--;}
				return c.val;
			}
		}
	}

	@Override
	public E set(int i, E e) throws horsbornes
	{
		if(i<0 || i>=t) throw new horsbornes();
		E ivv;
		int iv;
		if(i<t/2)
		{
			iv=0;
		chaino c = tete;
		while(iv<i){ c = c.suiv; iv++;}
		ivv = c.val;
		c.val = e;
		return ivv;
		}
		else{
			
			chaino c = qu;
			if(t%2 == 0){
				iv=t-2;
			while(iv>=i) {c = c.prec; iv--;}
			ivv = c.val;
			c.val = e;
			return ivv;}
			else{
				iv=t-1;
				while(iv>i) {c = c.prec; iv--;}
				ivv = c.val;
				c.val = e;
				return ivv;
			}
		}
		
	}

	@Override
	public int contains(Object o) {
		E ol = (E)o;
		int i=0;
		for( chaino c = tete;c != null; c = c.suiv)
		{
			if(c.val == ol)return i;
			i++;
		}
	    return -1;
	}
	@Override
	public boolean containsall(liste<?> l) {
		try{
	 if(l.size()<=this.size())
	{
		 int cb=0;
		for(int i=0; i<l.size(); i++)
		{
			int b=0;
			for( chaino c = tete;c != null; c = c.suiv) 		
					{
				      if(l.get(i)  == c.val) b++;
					}
			if(b>0)cb++;
		}
		if(cb==l.size()) return true;
		else return false;
	}
	 else
		{
			 int cb=0;
		for( chaino c = tete;c != null; c = c.suiv) 		
			{
				int b=0;
				for(int i=0; i<l.size(); i++)		
						{
					      if(l.get(i)  == c.val) b++;
						}
				if(b>0)cb++;
			}
			if(cb==this.size()) return true;
			else return false;
		}
	  }
	  catch(horsbornes e){new horsbornes(); return false;}
	}
	@Override
	public boolean equals(Object o)
	{
		try
		{
		listdc<E> ol = (listdc<E>)o;
		int geti=0,gt=0;
		if(ol.size() != this.size())
		{
			System.out.println("size not compatible"); return false;
		}
		else{
			for( chaino c = tete;c != null; c = c.suiv)
			{
				if(c.val == ol.get(geti))gt++;
				//System.out.println(c.val+" "+ol.get(geti));				
				geti++;

			}
//			return true;
		}
		
		if(gt==this.size())	return true;
		else return false;
		}
		  catch(horsbornes e){new horsbornes(); return false;}

	}

	@Override
	public liste<E> add(E e) {
		chaino c = new chaino(e);
		if(c != null)
		{
			if(t==0)
			{
				tete=c;
				qu=c;
				tete.prec =null;
				tete.suiv=qu;
				qu.prec=tete;
				qu.suiv=null;
				t+=1; ic+=1;
			}
			else
			{

				chaino cc  = tete;
				while(cc.suiv != null) cc = cc.suiv;
				
			
				c.prec = cc;
				cc.suiv=c;
				qu=c;
				qu.suiv=null;
				
				t+=1; ic+=1;
			}
		}
	//	else {System.out.println("c null");}
			
		return this;
	}
	@Override
	public liste<E> add(int i, E e) throws horsbornes
	{
		if(i<0 || i>=t) throw new horsbornes();
	if(i<=t)
	{
		chaino c = new chaino(e);

		if(i==0)
		{
			c.suiv=tete;
			tete.prec = c;
			tete=c;
			t+=1; ic+=i;
		}
		else if(i==(t-1))
		{
			this.add(e);
		}
		else 
		{

		chaino cc  = tete;
		for(int ii=0;ii<i-1;ii++) cc = cc.suiv;
		
	
		c.suiv = cc.suiv;
		cc.suiv.prec = c;
		c.prec = cc;
		cc.suiv = c;
		
		
		t+=1; ic+=i;
		}
	}
		return this;
		
	}

	@Override
	public liste<E> addall(liste<? extends E> e) {
	//	E ee;
		try{
		for(int i=0;i<e.size(); i++)
		{
			System.out.print("**** "+e.get(i)+" "+i);
			add(e.get(i));
		}
		return this;
		}
	  catch(horsbornes he){new horsbornes(); return null;}

	}

	@Override
	public liste<E> addall(int i, liste<? extends E> e) throws horsbornes
	{
		if(i<0 || i>=t) throw new horsbornes();
		int ij=i;
		for(int ii=0;ii<e.size(); ii++)
		{
			System.out.print("**** "+e.get(ii)+" "+ii);
			add(ij,e.get(ii)); ij++;
		}
		return this;
	}

	@Override
	public E remove(int i) throws horsbornes  {

		if(i<0 || i>=t) throw new horsbornes();

		chaino e = new chaino(null);
		if(i==0)
		{
			e=tete;
			tete=tete.suiv;
			t-=1; ic=0;
		}
		else if(i==(t-1))
		{
			e=qu;
			qu = qu.prec; qu.suiv = null;
			t-=1; ic=i-1;
		}
		else 
		{

		chaino cc  = tete;
		for(int ii=0;ii<i;ii++) cc = cc.suiv;
		
		e=cc;
	    cc.prec.suiv = cc.suiv;
	    cc.suiv.prec = cc.prec;
	    
		
		
		t-=1; ic=i-1;
		}
		return e.val;
	}

	@Override
	public liste<E> sublist(int deb, int fin) throws horsbornes{
		if(deb<0 || deb>=t  || fin<0 || fin>=t) throw new horsbornes();

	    listdc<E> dsub = new listdc<E>(); 
	    int it=0;
	   // System.out.println("t:"+t);
		if(deb<fin)
	   {
			for(chaino c = tete;c != null; c = c.suiv){
				if(it>=deb && it<=fin) dsub.add(c.val);
			it++;}
	   }
		else if(deb==fin)
		{
			dsub.add(get(deb));
		}
		else System.out.println("error");
	   
		return dsub;
	}
	public String toString()
	{
		String res="( ";
		for(chaino c = tete;c != null; c = c.suiv)
			res += c.val;
		return res+" )";
	}
	

}


class horsbornes extends Exception
{
	public horsbornes()
	{
		System.out.println("l'indice donné est non conforme avec la taille ou negatif ...");
	}
}