package main;


import java.rmi.*;


public class client 
{


	// Port permettant d'acceder a rmiregistry
	//
	static String portRmiregistry;


	public client (String Machine) throws RemoteException
	{
		try
		{

			// Se connecter Ã  un objet de type compte et invoquer les methodes
			//		- debiter/crediter
			//		- lire_solde
			//***
			compte c = (compte)Naming.lookup("rmi://localhost:1099/hh");
			c.crediter(15);
			System.out.println("credit : " + c.lire_solde());
			c.debiter(8);
			System.out.println("debit : " + c.lire_solde());
		}

		catch (Exception e)
		{
			System.out.println ("Exception: " + e.getMessage ());
			e.printStackTrace ();
		}

	}



	public static void main (String args[]) throws RemoteException
	{

		new client ("");
	}


}
