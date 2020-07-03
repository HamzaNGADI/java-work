package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;


public class gestionabonnImp extends gestionabonnesPOA{

	private Map<abonneImp, abonneHolder> ab;
	protected  POA poa_;

	public gestionabonnImp(POA poaa)
	{
		ab = new HashMap<abonneImp,abonneHolder>(); 
		poa_=poaa;
	}
	public void creation_abonne(int numero, String nom, String prenom, abonneHolder ref) throws dejaExistant {
		for(Map.Entry<abonneImp, abonneHolder> or : ((Map<abonneImp, abonneHolder>) ab).entrySet())
		{
			if(or.getKey().numero()==numero) throw new dejaExistant();
		}
		try {
		abonnePOA p = new abonneImp(numero,nom+"_"+prenom);
		ORB orb = ORB.init();
        
		org.omg.CORBA.Object oc = poa_.servant_to_reference(p);
		
	      ref.value = abonneHelper.narrow(oc); 
		ab.put(new abonneImp(numero,nom+"_"+prenom),ref);
		System.out.println(ab.size());
		}
		catch(Exception e) { System.out.println(e.getMessage());  }

		
	}
// la ref de abonneholder seRt a quoi ?? DONC La ref on la stock pas EN CLASS??
	@Override
	public void resilier_abonne(int numero) throws numeroInconnu {
		int b=0;
		abonneImp c=null;
		abonneHolder rf = null;
		for(Map.Entry<abonneImp, abonneHolder> or : ((Map<abonneImp, abonneHolder>) ab).entrySet())
		{
			if(or.getKey().numero()==numero)
				{
				b++;
				 c = or.getKey();
				 rf=or.getValue();
				 break;
				}
		}
		if(b==0) { throw new numeroInconnu();   }
		ab.remove(c);
		
		try {
			   byte [] ObjID = poa_.reference_to_id(rf.value);
			   poa_.deactivate_object(ObjID);
			   }
			catch (Exception e) {
			    System.out.println("POA Exception " + e);
			}
	
	}

	@Override
	public void rechercher_abonne(int numero, abonneHolder ref) throws numeroInconnu {
		
		int b=0;
		abonneImp c=null;
		abonneHolder rf = null;
		for(Map.Entry<abonneImp, abonneHolder> or : ((Map<abonneImp, abonneHolder>) ab).entrySet())
		{
			if(or.getKey().numero()==numero)
				{
				b++;
				 c = or.getKey(); 
				 rf=or.getValue();
				 break;
				}
		}
		if(b==0) { throw new numeroInconnu();   }
		ref.value = rf.value;
		System.out.println("abonne : "+c.nom_prenom()+ " ref : "+rf.value.toString());
	}

}
