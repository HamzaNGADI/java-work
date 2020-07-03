package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import server.bibliotheque;
import server.bibliothequeHelper;
import server.liste_ouvrageHelper;
import server.liste_ouvrageHolder;
import server.ouvrage;

public class clientSearch {

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
	        ///////////////////////////////////////////////////
	        
	        try {
//	        	Any any = orb.create_any();
	//			String mc=any.extract_string();
//				biall.tous_les_ouvrages();

			biall.rechercher_ouvrage(new liste_ouvrageHolder(biall.tous_les_ouvrages()),"mc4");
			
	//		System.out.println("bt "+mc);
	        } 
	        catch (Exception e) { System.out.println(e.getMessage()); }
	        
	        System.exit(0);
		
		
	}

}
