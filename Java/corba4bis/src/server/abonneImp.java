package server;

public class abonneImp extends abonnePOA {

	private int numero;
	private String nompren;
	private type_abonnement typeab;
	private type_adresse typeadr;
	public abonneImp(int num, String n, type_abonnement abn, type_adresse add)
	{
		numero = num;
		nompren=n;
		typeab = abn;
		typeadr=add;
	}
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public String nom_prenom() {
		return nompren;
	}

	@Override
	public type_abonnement abonnement() {
		return typeab;
	}

	@Override
	public void abonnement(type_abonnement newAbonnement) {
	     typeab = newAbonnement;
	}

	@Override
	public type_adresse adresse() {
		return typeadr;
	}

	@Override
	public void adresse(type_adresse newAdresse) {
		typeadr= newAdresse;
	}

}
