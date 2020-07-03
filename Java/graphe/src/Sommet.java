import java.util.LinkedList;


public class Sommet {
	private String nom ;
	private boolean marque = false ;
	private LinkedList<Sommet> voisins ;
	private LinkedList<Integer> couts ;
	
	public Sommet(String nom) {
		voisins = new LinkedList<Sommet>() ;
		couts = new LinkedList<Integer>() ;
		this.nom = nom ;
		}
	public void ajouterVoisin(Sommet s, int c) {
		voisins.add(s) ;
		couts.add(c) ;
		}
	public String getNom() {
		return nom ;
		}
	public void marquer() {
		marque = true ;
		}
	public boolean etat() {
		return marque ;
		}
	
	public int getCout(Sommet s) {
		for(int i=0; i <voisins.size(); i++) {
		if(s == voisins.get(i)) {
		return couts.get(i);
		}
		}
		return -1 ;
		}
	public LinkedList<Sommet> getVoisins() {
		return voisins ;
		}
	public void afficher() {
		System.out.print(getNom()+" : ");
		for(Sommet s : voisins) {
		System.out.print(s.getNom()+" ") ;
		}
		}
	

}
