import java.util.ArrayList;
import java.util.Date;


public class client
{
	ArrayList reservations_client = new ArrayList();
	private String nom;
	private client(String nom, ArrayList rs)
	{
		this.nom = nom;
		reservations_client.addAll(rs);
	}
	
	public static client createclient(String n,ArrayList chs)
    {
    	if(chs.size()<1)
    	{
    		return null;
    	}
    	else
    		return new client(n,chs);
    }
	public void makereserv(Date date, int nbnuit,ArrayList ch)
	{
		int b=0;
		for(int i=0;i<ch.size();i++)
		{
			for(int j=0;j<reservations_client.size();j++)
			{
				if((reservation)ch.get(i)== (reservation)reservations_client.get(j)) b++;
			}
		}
		if(b==0) reservations_client.add(reservation.createres(date, nbnuit, ch));
		else System.out.println("deja dans une reservation");
	}
	public ArrayList recuprs()
	{
		return reservations_client;
	}
}
