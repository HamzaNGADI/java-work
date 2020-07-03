package adapter;

public class CercleAdapter extends Forme {
	
	private CercleXX cercle;
	
	public CercleAdapter(){
		cercle=new CercleXX();
	}

	@Override
	public void afficher() {
		cercle.afficherCercle();		
	}

	@Override
	public void remplir() {
		cercle.remplirCercle();		
	}

	@Override
	public void definirCouleur() {
		
		
	}
	
	
	private class CercleXX{
		
		public void afficherCercle(){
			System.out.println("affiche xx");
		}
		
		public void remplirCercle(){
			System.out.println("remplire xx");
		}
		
		
	}
	

}
