package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class UsinePileImp extends UnicastRemoteObject implements UsinePile{

	private static Map<String, PileImp> mu=null;
	
	public UsinePileImp() throws RemoteException {

	}
	protected UsinePileImp(int t) throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pile creation_pile(String nom, int max_size) throws RemoteException {
		PileImp p = new PileImp(max_size);
		 mu.put(nom, p);
		return p;
	}
	
	public static void main (String args[]) throws MalformedURLException 
	{

		try
		{
			mu = new HashMap<String , PileImp>();
			LocateRegistry.createRegistry(1098);
		UsinePileImp u = new UsinePileImp();
			Naming.rebind("rmi://localhost:1098/hh", u);
		} 
		catch (RemoteException e) 
		{
			System.out.println("Serveur err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
