package server.exo2;

import org.omg.CORBA.IntHolder;

public class calculImpl extends calculPOA
{


	private	double contenu;


	public calculImpl()
	{
		contenu=0;
	}


	public double memoire()
	{
		return contenu;
	}


	@Override
	public void diviseMemoire(double valeur) 
	throws divisionParZero
	{
		if(valeur==0)
			throw new divisionParZero();

		contenu=contenu/valeur;
	}

	@Override
	public void incrementer(IntHolder data)
	{
		System.out.println("Invocation d'incrementer()");

		data.value=data.value+1;
	}

	@Override
	public void decrementer(IntHolder data)
	{
		System.out.println("Invocation de decrementer()");

		data.value=data.value-1;
	}


	@Override
	public void ajouteMemoire(double donnee) {
		contenu +=donnee;
		System.out.println("add : "+contenu);
	}
	@Override
	public void soustraitMemoire(double donnee) {
		contenu-=donnee;	
		System.out.println("sous : "+contenu);
	}


	@Override
	public void multiplieMemoire(double donnee) {
		contenu*=donnee;
		System.out.println("mult : "+contenu);
	}


	@Override
	public void miseAZero() {
		contenu=0;
		System.out.println("init 0 : "+contenu);
	}


}
