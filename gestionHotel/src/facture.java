import java.util.ArrayList;


public class facture
{
	ArrayList facture_reservations = new ArrayList();
	
	private facture(ArrayList rs)
	{
		facture_reservations.addAll(rs);
	}
	
	public static facture createfacture(ArrayList chs)
    {
    	if(chs.size()<1)
    	{
    		return null;
    	}
    	else
    		return new facture(chs);
    }
}
