package main;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class client {
	
	//
	static String portRmiregistry;


	public client (String Machine)
	{
		try
		{
			UsinePile usine = (UsinePile) Naming.lookup ("rmi://localhost:1098/hh");


			// Invocations de l'usine
			//
			Pile p = usine.creation_pile("mapile", 10);
			p.empiler(100);
			p.empiler(101);
			System.out.println ("Taille courante = " + p.lire_taille_courante() + "; Taille max = " + p.lire_taille_max() );
			System.out.println ("Element en haut de la pile : " + p.haut());
				
		}

		catch (RemoteException e)
		{
			System.out.println ("RemoteException: " + e.getMessage ());
			e.printStackTrace ();
		}
		catch (Exception e)
		{
			System.out.println ("Exception: " + e.getMessage ());
			e.printStackTrace ();
		}

	}



	public static void main (String args[])
	{
		
		new client ("");
	}


}
