package observtrainn;

public class evprice extends event{
	
protected stockobs st;
	
	public evprice(stockobs o)
	{
		super(o);
		st=o;
	}public evprice()
	{
		super(null);
	}
	public stockobs emmet()
	{
		if(this.st == null) return super.st;
		return this.st;
	}
	
	public int setprice(int p,int prii)
	{
		if(p != prii && p>=0)
		{
			return p;
		}
		else return prii;
	}
}
