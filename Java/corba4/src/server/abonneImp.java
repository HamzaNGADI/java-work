package server;

public class abonneImp extends abonnePOA {

	private int numero;
	private String nompren;
	public abonneImp(int num, String n)
	{
		numero = num;
		nompren=n;
	}
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public String nom_prenom() {
		return nompren;
	}

}
