import java.sql.Date;
import java.util.ArrayList;


public class hotelFacade implements hotelinterface
{
	private ArrayList ch,r ;
	private Date d ;
	private hotel hotl;
	private client c;
	private int ri=0;
	private chambre cchf;
	
	public hotelFacade()
	{
		ch = new ArrayList() ; r = new ArrayList();
	     d = new Date(0);
	}
	
	
	public void createchambre()
	{
		cchf = chambre.createchambre(0,1,400,"luxyExp", "suit",new douche());
	}
	/*public void createreservation()
	{
	}*/
	public void addchambre()
	{
		ch.add(chambre.createchambre(41,1,300,"ext", "normal",new salledebain()));
		this.addreservation(ch);
		
		ch.add(cchf);
		this.addreservation(ch);
		for(int i=0;i<r.size();i++) cchf.addreservation((reservation)r.get(i));
		
		ch.add(chambre.createchambre(4,1,350,"multivue", "panoram",new salledebain()));
		this.addreservation(ch);

		ch.add(chambre.createchambre(7,1,350,"multporte", "cabnum",new salledebain()));
		this.addreservation(ch);
		
		
//		for(int i=0;i<r.size();i++) cchf.addreservation((reservation)r.get(i));
		//cchf.addreservation((reservation)r.get(2));
	}
	public void addreservation(ArrayList chi)
	{
		r.add(reservation.createres(d, ri, chi)); ri++;
	}
	public void createhotel()
    {
        hotl = hotel.createhotel(ch, "place de ville", "4 rue est", "3 stars plus");
	}
	public void createclient()
	{
		c = client.createclient("appi", r);
		//c.makereserv(d, 0, r);
	}
	public void infoclient()
	{
		ArrayList hotch = hotl.getchambre();
		for(int i=0;i<hotch.size();i++)
		{
			chambre chub = (chambre)hotch.get(i);
			if(chub != null) chub.recuprse();
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		ArrayList resc = c.recuprs();
		for(int i=0;i<resc.size();i++)
		{
			reservation rt = (reservation)resc.get(i);
			ArrayList chb = rt.recupcho();
			System.out.println("\nreservation :"+(i+1));
			for(int j=0;j<chb.size();j++)
			{
				chambre chub = (chambre)chb.get(j);
				//if(chub != null)
					chub.recuprse();
				
			}
			
		}

	}
	
}
