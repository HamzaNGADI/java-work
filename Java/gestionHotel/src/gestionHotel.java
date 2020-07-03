import java.sql.Date;
import java.util.ArrayList;


public class gestionHotel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("go");
		ArrayList ch = new ArrayList(),r = new ArrayList();
		ch.add(chambre.createchambre(41,1,300,"ext", "normal",new salledebain()));


		Date d = new Date(0);
		r.add(reservation.createres(d, 0, ch));
		ch.add(chambre.createchambre(4,1,350,"multivue", "panoram",new salledebain()));
		r.add(reservation.createres(d, 6, ch));
		
		ch.add(chambre.createchambre(4,1,350,"multporte", "cabnum",new salledebain()));
		r.add(reservation.createres(d, 3, ch));
		
		chambre cchf = chambre.createchambre(0,1,400,"luxyExp", "suit",new douche());
		
		for(int i=0;i<r.size()-1;i++) cchf.addreservation((reservation)r.get(i));
		cchf.addreservation((reservation)r.get(2)); // -1 du for ans res1 console
		//cchf.addreservation((reservation)r.get(1));
		ch.add(cchf);
		
		r.add(reservation.createres(d, 0, ch));
	
		//r.add(reservation.createres(d, 0, ch));

		hotel hotl = hotel.createhotel(ch, "place de ville", "4 rue est", "3 stars plus");
		
		client c = client.createclient("appi", r);
		c.makereserv(d, 0, r);
		System.out.println("*****************************************************");
		ArrayList resc = c.recuprs();
		for(int i=0;i<resc.size();i++)
		{
			reservation rt = (reservation)resc.get(i);
			ArrayList chb = rt.recupcho();
			System.out.println("\nreservation :"+(i+1));
			for(int j=0;j<chb.size();j++)
			{
				chambre chub = (chambre)chb.get(j);
				if(chub != null) chub.recuprse();
				
			}
		}
		
		System.out.println("\n            ---------------------------\n");
		
		hotelFacade hf = new hotelFacade();
		hf.createchambre();
		hf.addchambre(); // with reserv
		hf.createhotel();
		hf.createclient();
		hf.infoclient();
		
		
	}

}
