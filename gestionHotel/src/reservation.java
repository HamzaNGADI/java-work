import java.util.ArrayList;
import java.util.Date;


public class reservation {
	
	private ArrayList chambre = new ArrayList();
	private Date date;
	private int nbngt;

	private reservation(Date d, int nbnuit, ArrayList ch)
	{
		chambre.addAll(ch);
		date = d;
		nbngt = nbnuit;
	}
	
	public void addchambre(chambre chs)
	{
		int b=0;
		for(int i=0;i<chambre.size();i++)
		{
			if((chambre)chambre.get(i)==chs) b++;
		}
		if(b==0)chambre.add(chs);
		else System.out.println("chambre exist in reserv");
	}
	
	public static reservation createres(Date d, int nbnuit, ArrayList chr)
    {
    	if(chr.size()<1)
    	{
    		return null;
    	}
    	
    	else
    		return new reservation(d,nbnuit,chr);
    }
	public ArrayList recupcho()
	{
		return chambre;
	}
	
}
