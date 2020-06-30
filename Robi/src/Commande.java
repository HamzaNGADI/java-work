
public abstract class Commande {
	abstract public String run ();
}


class beep extends Commande
{
	private String scase[]={"low","medium","high"};
	private String si;
	public beep()
	{
		si=null;
	}
	public beep(String s)
	{
		si=null;
		for(int i=0;i<scase.length;i++)
		{
			if(scase[i].equals(s)) si=scase[i];
		}
	}
	public String getS()
	{
		return si;
	} 
	@Override
	public String run() {
		
		if(si==null || si.equals(null))  return "le robot fait beep";
		else{
			String c=null;
			if(si.equals(scase[0]))  c= "le robot fait beep de faible intensit�";
			if(si.equals(scase[1]))  c= "le robot fait beep de moyenne intensit�";
			if(si.equals(scase[2]))  c= "le robot fait beep de haute intensit�";
		return c;
		}
	}
	
}

class moveright extends Commande
{

	@Override
	public String run() {
		return "le robot est d�plac� � droite";
	}
	
}

class movetop extends Commande
{

	@Override
	public String run() {
		return "le robot est d�plac� en haut";
	}
	
}
class move extends Commande
{
	private int x,y;
	public move(int xx,int yy)
	{
		x=xx; y=yy;
	}
	public int getx(){return x;}	
	public int gety(){return y;}
	
	@Override
	public String run() {
		return "le robot se d�place en "+x+" et "+y;
	}
	
}
