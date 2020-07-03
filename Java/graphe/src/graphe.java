
public class graphe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("ok");
		
		graph gr = new graph() ;
		Sommet a = new Sommet("A1");
		Sommet b = new Sommet("B2");
		Sommet c = new Sommet("C3");
		Sommet d = new Sommet("D4");
		Sommet e = new Sommet("E5");
		Sommet f = new Sommet("F6");
		Sommet g = new Sommet("G7");
		
		a.ajouterVoisin(b,8);
		b.ajouterVoisin(c,2);
		c.ajouterVoisin(d,5); 
		c.ajouterVoisin(g,14);
		d.ajouterVoisin(e,3);
		e.ajouterVoisin(a,11); 
		e.ajouterVoisin(f,2);
		f.ajouterVoisin(d,7); 
		f.ajouterVoisin(g,9);
		g.ajouterVoisin(b,6);
		
		gr.ajouter(a);
		gr.ajouter(b);
		gr.ajouter(c);
		gr.ajouter(d);
		gr.ajouter(e);
		gr.ajouter(f);
		gr.ajouter(g);
		
		gr.afficher();

		System.out.println("s : "+gr.chemin(a, g));
		
	}

}
