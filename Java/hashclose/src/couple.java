
public class couple<k,v> {
	
	private k cle;
	private v val;
	
	public couple(k kk, v vv)
	{
		if(kk==null) throw new NullPointerException();
		cle=kk; val=vv;
	}
	public v getval(){ return val;}
	public k getkey(){ return cle;}
	
	public void setvalue(v vv)
	{
		val = vv;
	}
	public boolean equals(Object o)
	{
		if(o==null) return false;
		if(!(o instanceof couple)) return false;
		couple<k,v> cpl = (couple<k, v>) o;
		if(val==null)
		{
			if(cpl.getval() != null) return false;
		}
		if(cpl.getval() == null)
		{
			if(val != null) return false;
		}
		if(val == null && cpl.getval() == null) return true;
		if(!val.equals(cpl.getval())) return false;
		
		return cle.equals(cpl.getkey());
	}
	
}
