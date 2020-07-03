package main;

import java.io.*;

import java.lang.*;

import java.util.*;

import org.omg.CORBA.ORB;

import server.abonne;
import server.abonneHolder;
import server.gestionabonnes;
import server.gestionabonnesHelper;


public class liste {
    public static void main(String[] args) throws IOException {
    	
		  ////////////////////////////////////////////////////
        // On initialise l'ORB
        ////////////////////////////////////////////////////
        // initialize the ORB.
        ORB orb = ORB.init(args, null);

        if(args.length!=0)
            {
            System.err.println("utilisation : pas de parametre ");
            System.exit(1);
            }

        ////////////////////////////////////////////////////
        // Recuperation de la reference d'objet du serveur
        // Dans cet exemple, la reference est stockee sous
        // la forme d'une chaine de caracteres (IOR) dans un
        // fichier. A ce stade, il est bien sur possible 
        // d'invoquer un service de nommage.
        ////////////////////////////////////////////////////
        String ior = null;

        try {
            String ref = "gestion.ref";
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
        // Construction d'une reference d'objet, non typee d'abord,
        // puis "cast" en une reference sur l'interface 
        // "calcul"  avec narrow ("cast" dynamique)
        ////////////////////////////////////////////////////
        org.omg.CORBA.Object obj = orb.string_to_object(ior);

        if (obj == null) {
            System.err.println("Erreur sur string_to_object() ");
            throw new RuntimeException();
        }

        gestionabonnes gestion = gestionabonnesHelper.narrow(obj);

        if (gestion== null) {
            System.err.println("Erreur sur narrow() ");
            throw new RuntimeException();
        }

        ////////////////////////////////////////////////////
        // Invocation du serveur
        ////////////////////////////////////////////////////
	
	abonne[] b =  gestion.liste_abonnes();
	for(abonne bv : b)
			System.out.println("abonne " + bv.nom_prenom());


  }
}

