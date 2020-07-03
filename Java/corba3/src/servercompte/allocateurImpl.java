package servercompte;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class allocateurImpl extends allocateurPOA{


		protected  POA poa_;


		public  allocateurImpl(POA poa)
			{
			poa_=poa;
			}
	@Override
	public void nouveau_compte(compteHolder cpt, String titulaire, int numero_compte) {

		 comptePOA p = new compteImpl(titulaire, numero_compte);
		
         try {
        	 
        	 ORB orb = ORB.init();
	            
			org.omg.CORBA.Object oc = poa_.servant_to_reference(p);
			
		      cpt.value = compteHelper.narrow(oc);      
         }
         catch(Exception e) {}
		
	}

}
