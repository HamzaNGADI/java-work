package arbresQ;

import binarbres.Binarbre;
import binarbres.BinarbreDC;
import listes.Liste;
import listes.ListeDC;

public class arbreQsimple<E extends Comparable<E>> implements arbreQ<E>{
	
	public Binarbre<E> bin;
	Liste<arbreQ<E>> children;
	static int il=-1;
	
	public arbreQsimple(E e)
	{
		bin = new BinarbreDC<E>(e);
		children = new ListeDC<arbreQ<E>>();
	}

	@Override
	public E getVal() {
		return bin.getVal();
	
	}
	@Override
	public BinarbreDC bina()
	{
		return (BinarbreDC) bin;
	}
	@Override
	public E setVal(E e) {
		if(bin.getVal().compareTo(e)==0)
		{
		Binarbre<E> bino = new BinarbreDC<E>(e);
		bino = bino.addAll(bin.prefixe().remove(0));
		bin = bino;  
		return e;
		}
		if(bin.getLeft() == null || bin.getRight()==null) 
		{
			bin.addVal(e);   
			System.out.println("l/r ++");
			return e;
			}
			
		if(bin.getVal().compareTo(e)!=0)
		{
			children.add(new arbreQsimple<E>(e));
			System.out.println("children ++");
			return e;
		}
		return null;
	}

	@Override
	public boolean isLeaf() {
		if(bin.getLeft()==null && bin.getRight()==null && children.size()==0) return true;
		return false;
	}

	@Override
	public arbreQ<E> father() {
		return (arbreQ<E>)bin.father();
	}
	public void addfi(arbreQ<E> d)
	{
		arbreQsimple<Integer> si = new arbreQsimple<Integer>(65);
		
		si.setVal(47);
		si.setVal(78);
		si.setVal(56);
		children.add((arbreQ<E>) si);
	}
	@Override
	public Liste<arbreQ<E>> childr()
	{
		return children;
	}

	@Override
	public Liste children() {
		Liste<arbreQ<E>> ll = new ListeDC<arbreQ<E>>();
	   System.out.println(bin.getVal()+"  "+children.size()+ " "+ children);
		ll.add(new arbreQsimple<E>((E)bin.getVal()));
		if(bin.getLeft() != null) ll.add(new arbreQsimple<E>((E) bin.getLeft().getVal()));
		if(bin.getRight() != null) ll.add(new arbreQsimple<E>((E)bin.getRight().getVal()));
				
		for(int i=0;i<children.size();i++) 
		{ 
		ll.addAll(children.get(i).children()); 
		}
	
		return ll;
	}
	@Override
	public arbreQ<E> getChild(int i) {
		
		if(i==0) return new arbreQsimple<E>(bin.getLeft().getVal());
		else if(i==1) return new arbreQsimple<E>(bin.getRight().getVal());
		else
		{
			if(i>=2 && i<children.size()+2)
			{
				int il = i-2;
				return (children.get(il));
			}
		}
		return null;
	}
//-----------------------------------------------------------------
	@Override
	public arbreQ<E> cutChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public arbreQ<E> addChild(arbreQ<E> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public arbreQ<E> addChild(int i, arbreQ<E> a) {
		// TODO Auto-generated method stub
		return null;
	}

}
