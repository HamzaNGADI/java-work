
public class changeColor extends evenement {

	private interupteurIn obss;
	public changeColor(interupteurIn o)
	{
		super(o);
	    obss = o;
	}
	
	@Override
	public interupteurIn emmeteur() {
		if(super.obs != null) return super.obs;
		else return obss;
	}
    public boolean change(boolean b)
	{
    	
		if(b==false)
		{
			b = true;
			return b;
		}
		else
		{
			b = false;
			return b;
		}
	}
	

}
