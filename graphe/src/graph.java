import java.util.Collections;
import java.util.LinkedList;


public class graph {
	private LinkedList<Sommet> liste ;
	private int s=0;

	public graph() {
		liste = new LinkedList<Sommet>() ;
		}
	
	public void ajouter(Sommet s) {
		liste.add(s);
		}
	
	public Sommet get(int index) {
		return liste.get(index) ;
		}
	public int getIndex(Sommet s) {
		for(int i=0; i<liste.size(); i++) {
		if(liste.get(i).getNom() == s.getNom())
		return i ;
		}
		return -1;
		}
	public int size() {
		return liste.size() ;
		}
	public Sommet getSommetParNom(String nom) {
		for(Sommet s : liste) {
		if(s.getNom() == nom)
		return s ;
		}
		return null;
		}
	public int getCout(Sommet s1, Sommet s2) {
		return (s1.getCout(s2));
		}
	public void afficher() {
		for(Sommet s : liste) {
		s.afficher() ;
		System.out.println() ;
		}
		}
	

	public int chemin(Sommet a,Sommet b)
	{
		 boolean bb=true;
		if(a!=b && a.etat()==false)
		{
			LinkedList<Sommet> lu = a.getVoisins();
			for(int i=0;i<lu.size();i++)
			{
				if(lu.get(i) == b)
				{
					if(lu.get(i)==b)
					{
						System.out.println("node : "+a.getNom()+" - "+b.getNom());
						s+= getCout(a,b);
						bb=false;
					}
				}
			}
			
			if(bb)
			   {
			Sommet nxt=null;
			LinkedList<Integer> intu = new LinkedList<Integer>();
			LinkedList<Sommet> li = a.getVoisins();
			for(int i=0;i<li.size();i++)
			{
				intu.add(getCout(a,(Sommet)li.get(i)));
			}
			int min=Collections.min(intu);
			s+=min;
			a.marquer();
			for(int i=0;i<li.size();i++)
			{
				if(intu.get(i)==min){
					nxt = this.getSommetParNom(li.get(i).getNom());
					break;
				}
			}
	
			return chemin(nxt ,b);/////////
			   }
			else return s;
		}
		else 
		{
			//System.out.println("noeuds last : ")
			return s;
		}
	}

}
