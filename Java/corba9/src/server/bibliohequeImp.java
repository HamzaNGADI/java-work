package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

public class bibliohequeImp extends bibliothequePOA{
	
	private Map<ouvrageImp, ouvrageHolder> ouvr;
	protected  POA poa_;

	public bibliohequeImp(POA poaa)
	{
		ouvr = new HashMap<ouvrageImp, ouvrageHolder>();
		poa_=poaa;
	}
	
	@Override
	public ouvrage[] tous_les_ouvrages() {
		if(ouvr.size() != 0)
		{
			int i=0;
		ouvrage[] avr =  new ouvrage[ouvr.size()];
		for(Map.Entry<ouvrageImp, ouvrageHolder> or : ((Map<ouvrageImp, ouvrageHolder>) ouvr).entrySet())
		{
			avr[i]=or.getValue().value;
			i++;
		}
		
		return avr;
		
		}
		return null;
	}

	@Override
	public ouvrage ajouter_ouvrage(String titre) throws deja_trouve {
		
		for(Map.Entry<ouvrageImp, ouvrageHolder> or : ((Map<ouvrageImp, ouvrageHolder>) ouvr).entrySet())
		{
			if(or.getKey().titre().equals(titre)) throw new deja_trouve();
		}
		try {
		ouvragePOA p = new ouvrageImp(titre);
		ORB orb = ORB.init();
        
		org.omg.CORBA.Object oc = poa_.servant_to_reference(p);
		ouvrageHolder ref= new ouvrageHolder();
	      ref.value = ouvrageHelper.narrow(oc); 
		ouvr.put( new ouvrageImp(titre),ref);
		System.out.println(ouvr.size());
		return ref.value;
		}
		catch(Exception e) { System.out.println(e.getMessage());  }
		
		
		return null;
	}
	
	
	@Override
	public void rechercher_ouvrage(liste_ouvrageHolder l, String mot) throws non_trouve {
		ouvrage[] ll = l.value;
		System.out.println(ll.length+" "+ouvr.size());
		int sin=0;
		for(int i=0;i<ll.length; i++)
		{
			System.out.println(ll[i].titre());	
		}
		
			for(int i=0;i<ll.length;i++)
			{
		//	  if(or.getKey().titre().equals(ll[i].titre()));
			//   {
				String s[] = ll[i].mots_clefs();
				for(int j=0;j<s.length;j++)
				{
				  if(s[j].equals(mot))
				  {
					  System.out.println("trouvé ! titre : "+ll[i].titre());sin++;  break;
				  }
				}
			  // }
			}
		
		
		if(sin==0) throw new non_trouve();

		
	}

}
