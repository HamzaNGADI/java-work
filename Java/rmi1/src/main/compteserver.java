package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class compteserver extends UnicastRemoteObject implements compte{

	private double solde;
	public compteserver() throws RemoteException
	{
		solde = 10;
	}
	public compteserver(double s) throws RemoteException
	{
		solde = s;
	}
	
	@Override
	public void debiter(double montant) throws RemoteException {
		solde-=montant;
	}

	@Override
	public void crediter(double montant) throws RemoteException {
		
		solde+=montant;

	}

	@Override
	public double lire_solde() throws RemoteException {
		if(solde < 0) System.out.println("vous etes debiteur");
		if(solde == 0) System.out.println("solde à 0");

		return solde;
	}
	
	/////////////
	
	public static void main (String args[]) throws MalformedURLException 
	{

		try
		{
			LocateRegistry.createRegistry(1099);
			compteserver c = new compteserver(10);
			Naming.rebind("rmi://localhost:1099/hh", c);
		} 
		catch (RemoteException e) 
		{
			System.out.println("Serveur err: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
