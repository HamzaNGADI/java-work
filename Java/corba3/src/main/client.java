package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;

import servercompte.allocateur;
import servercompte.allocateurHelper;
import servercompte.compte;
import servercompte.compteHolder;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ORB orb = ORB.init(args, null);

	        if(args.length!=0)
	               {
	               System.err.println("utilisation : pas de parametre ");
	               System.exit(1);
	               }

	        ////////////////////////////////////////////////////
	        String ior = null;

	        try {
	            String ref = "cpt.ref";
	            FileInputStream file = new FileInputStream(ref);
	            BufferedReader in = new BufferedReader(new InputStreamReader(file));
	            ior = in.readLine();
	            file.close();
	        } catch (IOException ex) {
	            System.err.println("Impossible de lire fichier : `" +
	                ex.getMessage() + "'");
	            System.exit(1);
	        }

	        ////////////////////////////////////////////////////
	        org.omg.CORBA.Object obj = orb.string_to_object(ior);

	        if (obj == null) {
	            System.err.println("Erreur sur string_to_object() ");
	            throw new RuntimeException();
	        }

	        allocateur alloc= allocateurHelper.narrow(obj);

	        if (alloc == null) {
	            System.err.println("Erreur sur narrow() ");
	            throw new RuntimeException();
	        }

	        ////////////////////////////////////////////////////
	        // Invocation du serveur
	        ////////////////////////////////////////////////////

	        // Creation de deux comptes
	        //
	        compteHolder Hcpt1 = new compteHolder();
	        compte cpt1;
	        compteHolder Hcpt2 = new compteHolder();
	        compte cpt2;

	        alloc.nouveau_compte(Hcpt1,"toto",1);
	        cpt1=Hcpt1.value;
	        alloc.nouveau_compte(Hcpt2,"tim",2);
	        cpt2=Hcpt2.value;

	        System.out.println("titulaire cpt1 = " + cpt1.titulaire());
	        
	        cpt1.crediter(200);
	        System.out.println("cpt1 = " + cpt1.solde());

	        cpt1.debiter(14);
	        System.out.println("cpt1 = " + cpt1.solde());

	        cpt1.prelevement(10, cpt2);
	        IntHolder ll = new IntHolder();
	         cpt1.nombre_operations(ll);
	        System.out.println("cpt1 = " + cpt1.solde()+ " nb:"+ll.value);
	        System.out.println("cpt2 = " + cpt2.solde());
	        
	        
	        System.exit(0);
		
		
	}

}
