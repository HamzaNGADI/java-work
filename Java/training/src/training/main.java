package training;

import arbresQ.arbreQ;
import arbresQ.arbreQsimple;
import listes.Liste;
import listes.ListeDC;

public class main {

	
	
	public static int argu(int...n)
	{
		int s=0;
		for(int i=0;i<n.length ;i++)
		{
			s+=n[i];
		}
		return s;
	}
	
	public static int argum(int n, int...t)
	{
		
		if(n==t.length) return 0;
		else return t[n]+argum(n+1,t);
	}
	
	public static void main(String[] args) {
		System.out.println("yuu");
		
		System.out.println("argu is : "+argu(1, 2,4,1,3)+"    argum : "+argum(0, 2,4,1,1,3));
		arbreQsimple<Integer> s = new arbreQsimple<Integer>(1);
		System.out.println("s is : "+ s.getVal());
		s.setVal(4);
		s.setVal(0);
		s.setVal(7);
		System.out.println("l : "+ s.getChild(0).getVal()+ "    r : "+s.getChild(1).getVal());
		System.out.println("s2 is : "+ s.getChild(2).getVal());
		s.addfi(s);
		System.out.println(s.children().toString());
	}

}
