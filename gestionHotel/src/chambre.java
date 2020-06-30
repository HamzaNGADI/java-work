import java.util.ArrayList;


public class chambre
{
	private ArrayList reservations_chambre = new ArrayList();
	private salleEau salleE;
	
	private int nombrelit,numero;
	private int prix;
	private String typelit; 
	private String categorie;
	private chambre(int num, int nombli,int prixi, String typlit,String cat , salleEau s/*,ArrayList res*/)
	{
		numero = num;
		nombrelit = nombli;
		prix = prixi;
		typelit = typlit;
		categorie = cat;
		salleE = s;
//		reservations_chambre.add(res); 
	}
	 public static chambre createchambre(int num, int nombli,int prixi, String typlit,String cat , salleEau s/*,ArrayList chs*/)
	    {
	   /* 	if(chs.size()<1)
	    	{
	    		System.out.println(" null");
	    		return null;
	    	}*/
	    	//else
		    
	    		return new chambre(num,  nombli, prixi,typlit, cat, s/*,chs*/);
	    }
	
	public void addreservation(reservation res)
	{
		if((this.chambredisp(res))==false)
		reservations_chambre.add(res);
		else System.out.println("reservation chambre already exist");
	}
	public boolean chambredisp(reservation chr)
	{
	
			for(int j=0;j<reservations_chambre.size();j++)
			{
				if(chr == (reservation)reservations_chambre.get(j)) return true;
			}
		
		
		return false;
	}
	public void recuprse()
	{
		System.out.println(" "+numero+" "+nombrelit+" "+prix+" "+typelit+" "+categorie); 
		System.out.print("salle est : ");
		
		salleE.specifity();
		for(int j=0;j<reservations_chambre.size();j++)
		{
			System.out.print("reservation"+(j+1));
		}
		System.out.print("\n");
	}
}

