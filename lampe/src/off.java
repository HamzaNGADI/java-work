

public class off extends evenement {

	private interupteurIn obss;
	public off(interupteurIn o)
	{
		super(o);
	    obss = o;
	}
	
	@Override
	public interupteurIn emmeteur() {
		if(super.obs != null) return super.obs;
		else return obss;
	}
    public boolean setoff()
	{
    	return true;
	}
	

}
