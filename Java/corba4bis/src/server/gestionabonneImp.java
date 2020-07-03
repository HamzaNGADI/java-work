package server;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

public class gestionabonneImp extends gestionabonnesPOA{

	private Map<abonneImp, abonneHolder> ab;
	protected  POA poa_;

	public gestionabonneImp(POA poaa)
	{
		ab = new HashMap<abonneImp,abonneHolder>(); 
		poa_=poaa;
	}
	@Override
	public abonne[] liste_abonnes() {
		
		if(ab.size() != 0)
		{
			int i=0;
		abonne[] abn =  new abonne[ab.size()];
		for(Map.Entry<abonneImp, abonneHolder> or : ((Map<abonneImp, abonneHolder>) ab).entrySet())
		{
			abn[i]=or.getValue().value;
			i++;
		}
		
		return abn;
		
		}
		return null;
	}

	@Override
	public void creation_abonne(int numero, String nom, String prenom, type_adresse adresse, type_abonnement abonnement,
			abonneHolder ref) throws dejaExistant 
	{
		
		for(Map.Entry<abonneImp, abonneHolder> or : ((Map<abonneImp, abonneHolder>) ab).entrySet())
		{
			if(or.getKey().numero()==numero) throw new dejaExistant();
		}
		try {
		abonnePOA p = new abonneImp(numero,nom+"_"+prenom,abonnement,adresse);
		ORB orb = ORB.init();
        
		org.omg.CORBA.Object oc = poa_.servant_to_reference(p);
		
	      ref.value = abonneHelper.narrow(oc); 
		ab.put(new abonneImp(numero,nom+"_"+prenom,abonnement,adresse),ref);
		System.out.println(ab.size());
		}
		catch(Exception e) { System.out.println(e.getMessage());  }
		
	}

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
