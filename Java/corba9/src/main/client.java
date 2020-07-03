package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.Any;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;

import server.*;

public class client {

	public static void main(String[] args) {
		
		
		 ORB orb = ORB.init(args, null);

	        if(args.length!=0)
	               {
	               System.err.println("utilisation : pas de parametre ");
	               System.exit(1);
	               }

	        ////////////////////////////////////////////////////
	        String ior = null;

	        try {
	            String ref = "bib.ref";
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

	        bibliotheque biall= bibliothequeHelper.narrow(obj);

	        if (biall == null) {
	            System.err.println("Erreur sur narrow() ");
	            throw new RuntimeException();
	        }

	        ////////////////////////////////////////////////////
	        // Invocation du serveur
	        ////////////////////////////////////////////////////

	        
	        //
	      /*  compteHolder Hcpt1 = new compteHolder();
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
	        System.out.println("cpt2 = " + cpt2.solde());*/
	        
	        try {
	        	ouvrage oi[] = new ouvrage[5];
				oi[0] = biall.ajouter_ouvrage("cpp");
				String mc[] = {"mc1","mc2","mc3","mc4"};
				oi[0].mots_clefs(mc);

				oi[1] = biall.ajouter_ouvrage("java");
				String mc2[] = {"mc1"};
				oi[1].mots_clefs(mc2);

				oi[2] = biall.ajouter_ouvrage("js");
				String mc3[] = {"mc5","mc2","mc6"};
				oi[2].mots_clefs(mc3);
				

				oi[3] = biall.ajouter_ouvrage("python");
				String mc4[] = {"mc1","mc2","mc7"};
				oi[3].mots_clefs(mc4);

				oi[4] = biall.ajouter_ouvrage("jquery");
				String mc5[] = {"mc8","mc1","mc9","mc4"};
				oi[4].mots_clefs(mc5);
				Any any = orb.create_any();
				any.insert_string("mc2");
				liste_ouvrageHelper.insert(any, oi);
			//	biall.rechercher_ouvrage(new liste_ouvrageHolder(oi), "mc2");
			
	        } 
	        catch (Exception e) { System.out.println(e.getMessage()); }
	        
	        System.exit(0);

	}

}
