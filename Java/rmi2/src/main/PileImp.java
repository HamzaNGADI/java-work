package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class PileImp extends UnicastRemoteObject implements Pile{

    private Integer st[]=null;
    private int taill; 
	public PileImp() throws RemoteException {
		
		st =new Integer[10];
		taill=-1;
	}
	public PileImp(int t) throws RemoteException {
	
		st =new Integer[t];
		taill=-1;
	}

	@Override
	public void empiler(int data) throws RemoteException {
		if(st[st.length-1] != null)
		{
			System.out.println("taille pleine"); return;
		}
		taill++;
		st[taill] = data;
		
	}

	@Override
	public int haut() throws RemoteException {
		// TODO Auto-generated method stub
		if(taill != -1) return st[taill];
		return -1;
	}

	@Override
	public void depiler() throws RemoteException {
		if(taill==-1)
		{
			System.out.println("taille vide"); return;
		}
		
		st[taill] = null;
		taill--;
	}

	@Override
	public void vider() throws RemoteException {
		
			for(int i=0;i<st.length;i++)
			{
				st[i]=null;
			}
		
	}

	@Override
	public int lire_taille_courante() throws RemoteException {
		return taill+1;
	}

	@Override
	public int lire_taille_max() throws RemoteException {
		return st.length;
	}
	
	public static void main (String args[]) throws MalformedURLException 
	{

		try
		{
			LocateRegistry.createRegistry(1098);
	//		compteserver c = new compteserver(10);
		//	Naming.rebind("rmi://localhost:1098/hh", c);
		} 
		catch (RemoteException e) 
		{
			System.out.println("Serveur err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
