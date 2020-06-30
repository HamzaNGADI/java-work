package grandsentiers;

import java.util.*;

/**
 * Mise en oeuvre des grands entiers à l'aide d'une liste de chiffres significatifs.
 * 
 * <p>
 * 
 * Les chiffres en base 10 du grand entier sont stockés dans une liste d'entiers.
 * Afin de faciliter certaines opérations, les premiers chiffres stockés sont les
 * chiffres de <i>poids faible</i>, et les chiffres non significatifs ne sont pas stockés. <br>
 * 
 * Exemples : <br>
 * 
 * <table>
 *   <tr>
 *     <td>Nombre</td><td>Décomposition en base 10</td><td>Représentation en liste</td>
 *   </tr>
 *   <tr>
 *     <td>0</td><td>0&times;10<sup>0</sup></td><td>( 0 )</td>
 *   </tr>
 *   <tr>
 *     <td>5067</td><td>7&times;10<sup>0</sup> + 6&times;10<sup>1</sup> + 0&times;10<sup>2</sup> + 5&times;10<sup>3</sup></td><td>( 7 6 0 5 )</td>
 *   </tr>
 *   <tr>
 *     <td>123000</td><td>0&times;10<sup>0</sup> + 0&times;10<sup>1</sup> + 0&times;10<sup>2</sup> + 3&times;10<sup>3</sup> + 2&times;10<sup>4</sup> + 1&times;10<sup>5</sup></td><td>( 0 0 0 3 2 1 )</td>
 *   </tr>
 *   <tr>
 *     <td>000123</td><td>3&times;10<sup>0</sup> + 2&times;10<sup>1</sup> + 1&times;10<sup>2</sup> + 0&times;10<sup>3</sup> + 0&times;10<sup>4</sup> + 0&times;10<sup>5</sup></td><td>( 3 2 1 )</td>
 *   </tr>
 * </table>
 * 
 * <p>
 * 
 * Grâce à cette représentation inversée, l’indice d’un chiffre dans la liste correspond toujours exactement à son poids dans la décomposition.
 *
 */
public class ListeGrandEntier implements GrandEntier {

	/**
	 * Liste de chiffres significatifs en base 10 dans l'ordre croissant des poids.
	 */
	private List<Integer> chiffres;
		
	/**
	 * Crée un grand entier à partir d'une valeur de type <tt>int</tt>. <br>
	 * <b>Attention</b> : on ne tient compte que de la valeur absolue du paramètre.
	 * @param i valeur du grand entier à créer
	 */
	public ListeGrandEntier(int i) {
		if (i<0) i = -i;   // on code la valeur absolue
		this.chiffres = new ArrayList<>();
		do {
			this.chiffres.add(i%10); // chiffre de poids faible
			i = i/10; // suppression du chiffee de poids faible
		}
		while (i>0); // tant qu'il y a un chiffre significatif
	}
	
	/**
	 * Crée un grand entier de valeur 0.
	 */
	public ListeGrandEntier() {
		this(0);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "";
		for (Integer i : this.chiffres)
			res = i+res;
		return res;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof GrandEntier)) return false;
		return this.compareTo((GrandEntier)o)==0;
	}

	/**
	 * @see grandsentiers.GrandEntier#size()
	 */
	@Override
	public int size() {
		return this.chiffres.size();
	}

	/**
	 * @see grandsentiers.GrandEntier#digit(int)
	 */
	@Override
	public int digit(int i) {
		if (i >= this.chiffres.size()) return 0;
		return this.chiffres.get(i);
	}

	/**
	 * @see grandsentiers.GrandEntier#compareTo(grandsentiers.GrandEntier)
	 */
	@Override
	public int compareTo(GrandEntier g) {
		if (this.size() > g.size()) return 1;
		if (this.size() < g.size()) return -1;
		for (int i=this.size()-1; i>=0; i--) {
			if (this.digit(i) > g.digit(i)) return 1;
			if (this.digit(i) < g.digit(i)) return -1;
		}
		return 0;
	}

	/**
	 * @see grandsentiers.GrandEntier#add(grandsentiers.GrandEntier)
	 */
	@Override
	public GrandEntier add(GrandEntier g) {
        ListeGrandEntier res = new ListeGrandEntier();
        int h=0,ret=0;
        res.chiffres.clear();
        
        if (this.size() > g.size()) h=this.size();
        else h=g.size();
       
        for(int i=0;i<h;i++)
        {
        	int l= this.digit(i)+g.digit(i)+ret;
    		ret = l/10;
        		l=l%10;
        	
        	res.chiffres.add(i,l);

        }
        if(ret != 0) res.chiffres.add(ret);
		
		return res; 
	}

	/**
	 * @see grandsentiers.GrandEntier#mult(grandsentiers.GrandEntier)
	 */
	@Override
	public GrandEntier mult(GrandEntier g) {
		if(g.compareTo(new ListeGrandEntier(0))==0 || this.compareTo(new ListeGrandEntier(0))==0)
		{
			return new ListeGrandEntier(0);
		}
		else if(g.compareTo(new ListeGrandEntier(1))==0)
		{
			return this;
		}
		else if(this.compareTo(new ListeGrandEntier(1))==0)
		{
			return g;
		}
		else
	{
		ListeGrandEntier res = new ListeGrandEntier();
        res.chiffres.clear();
       
		int min,max,ret=0;
		int ri=0,ml=0;
		 if (this.size() > g.size()){ max=this.size(); min = g.size(); ml=1;}
		 if (this.size() < g.size()){ min=this.size(); max = g.size(); ml=2;}
	     else { max=this.size(); min = g.size(); ml=1;}
		 ListeGrandEntier resi[] = new ListeGrandEntier[min];
		 for(int i=0;i<min;i++){ resi[i]=  new ListeGrandEntier();  resi[i].chiffres.clear();}
		 
		 System.out.println(" "+min+""+resi.length);
		 for(int i=0;i<min;i++)
		 {
		     if(i!=0) {  for(int k=0;k<ri;k++) resi[ri].chiffres.add(0);  }

			 for(int j=0;j<max;j++)
			 {
				 int l;
				if(ml==2) l= this.digit(i)*g.digit(j)+ret;
				else  l= this.digit(j)*g.digit(i)+ret;
		    		ret = l/10;    l=l%10;
		        	resi[ri].chiffres.add(l);

			 }
		        if(ret != 0) resi[ri].chiffres.add(ret);
		        System.out.println(" ri : "+resi[ri]);


		        ri++;
		        ret=0;
		 }
		 if(resi.length>1)
		 { 
		 ListeGrandEntier resii = resi[0],reso =new ListeGrandEntier();
		 reso = (ListeGrandEntier) resii.add(resi[1]);
		 for(int i=2;i<min;i++)
		 {
			 reso = (ListeGrandEntier) resi[i].add(reso);
		 }
		 res=reso;
		 }
		 else res = resi[0];
		
		return res; }
	}
	
	/**  
	 * Crée un entier correspondant à la factorielle du paramètre fourni. 
	 * @param g grand entier dont on souhaite calculer la factorielle
	 * @return grand entier correspondant à la factorielle de <tt>g</tt>
	 */
	public static GrandEntier factorielle (GrandEntier g) {
        
		GrandEntier res = new ListeGrandEntier(1);
		GrandEntier multiplicateur = new ListeGrandEntier(0);
		GrandEntier increment = new ListeGrandEntier(1);
		while (g.compareTo(multiplicateur) > 0) {
			multiplicateur = multiplicateur.add(increment);
			res = res.mult(multiplicateur);
		}
		return res;
	}

}
