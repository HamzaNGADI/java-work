import java.awt.Color;


public class lampeIn implements observer{
	
	private boolean isalm,isoff;
	private int id;
	private static int idlamps=0;
	private interuptor interu;
	private evenement evt;
	
	public lampeIn(evenement ev, interuptor interu)
	{
		isoff = false;
		this.interu = interu;
		this.evt = ev;
		idlamps++;
		id = idlamps;
		this.interu.register(ev,this);
	}
	@Override
	public void update(boolean allum) {
		isalm = allum;
		
	}
	public void update() {
		isoff = true;
		
	}
	
	public boolean isalumer()
	{
		return isalm;
	}
	
	public Color isalumerCol()
	{
		if(isoff)
		{
//			isoff=false;
			isalm=false;
			return Color.red;
		}
		if (isalm==true)
			return Color.YELLOW;
		else
			return Color.GRAY;
	}
	public String isalumertxt()
	{
		if(isoff){
			isoff=false;
			return "off, click to on";
		}
		if (isalm==false)
			return "FERME lampe "+id;
		else
			return "OUVERT lampe "+id;
	}
	public int idlamp()
	{
		return id;
	}
	public interuptor currentInterup()
	{
		return interu;
	}
}
