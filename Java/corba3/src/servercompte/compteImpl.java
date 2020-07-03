package servercompte;

import org.omg.CORBA.IntHolder;

public class compteImpl extends comptePOA{

	private int numc;
	private String titul;
	private double solde;
	private int nbop;
	public compteImpl(String t, int num)
	{
		titul = t;
		numc = num;
		solde=0;
		nbop=0;
	}
	
	@Override
	public int numero_compte() {
		return numc;
	}

	@Override
	public String titulaire() {
		return titul;
	}

	@Override
	public double solde() {
		if(solde < 0) System.out.println("vous etes debiteur");
		if(solde == 0) System.out.println("solde à 0");

		return solde;
	}

	@Override
	public void nombre_operations(IntHolder nombre) {
		nombre.value = nbop;
//		System.out.append("nb. d'operation : "+nombre.value);
	}

	@Override
	public void debiter(double montant) {
		solde-=montant;
		nbop++;
	}

	@Override
	public void crediter(double montant) {
		solde+=montant;
		nbop++;
	}

	@Override
	public void prelevement(double montant, compte destination) {
		destination.crediter(montant);
		debiter(montant);
		nbop++;
	}

}
