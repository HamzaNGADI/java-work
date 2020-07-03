import java.text.DecimalFormat;
import java.util.ArrayList;


public class diagclass {

	/**
	 * @param args
	 */  
	public static void main(String[] args) {
		
		piece p = new piece();
		etage et = new etage(p);
		System.out.println(et.checked()+" "+et.sizep()+" ");
		et.getlist().remove(0);
		System.out.println(et.checked()+" "+et.sizep()+" ");
		//---------------------------------------------------------------
		bourse ech5 = new echelon5();  bourse ech2 = new echelon2();
		etudiant etuboursier = new etudiant("requier","pascal", ech5);
		etuboursier.setnotes(6); etuboursier.setnotes(14); etuboursier.setnotes(15);
		etudiant etsimple[] = new etudiant[4];
		etsimple[0] = new etudiant("duval","anali");
		etsimple[0].setnotes(10); etsimple[0].setnotes(13); etsimple[0].setnotes(16);
		etsimple[1] = new etudiant("mayesir","jean");
		etsimple[1].setnotes(14); etsimple[1].setnotes(11); etsimple[1].setnotes(17);
		etsimple[2] = new etudiant("tierry","michelle");
		etsimple[2].setnotes(13); etsimple[2].setnotes(9); etsimple[2].setnotes(18);
		etsimple[3] = etuboursier;
		
		promotion liade = new promotion("liade");
		for(int i=0;i<3;i++) liade.ajouter(etsimple[i]);
		promotion siia = new promotion("siia");
		siia.ajouter(etsimple[3]);
		
		ArrayList arliad = liade.getEtudiant();
		for(int i=0;i<arliad.size();i++)
		{
			etudiant etli = (etudiant)arliad.get(i);
			System.out.println(etli.affiche());
			etli.bourse_info();
			System.out.println("moyenne : "+etli.moyenne()+"\n -----------------------------------------");
		}
		ArrayList arsia = siia.getEtudiant();
		for(int i=0;i<arsia.size();i++)
		{
			etudiant etsi = (etudiant)arsia.get(i);
			System.out.println(etsi.affiche());
			etsi.bourse_info();
			System.out.println("moyenne sia : "+etsi.moyenne()+"\n +++++++++++++++++++++++++++++++++++++++++");
		}
		System.out.println("************************************************************************************************");

		liade.supprimer(1);  liade.supprimer(8);
		etsimple[1].setnotes(15); etsimple[1].setbourse(ech2);
		siia.ajouter(etsimple[1]);
		etudiant ety = new etudiant("nelly","ruos");
		ety.setnotes((float)12.5); ety.setnotes(18);
		liade.ajouter(ety);
		
		System.out.println("************************************************************************************************");
		 arliad = liade.getEtudiant();
		for(int i=0;i<arliad.size();i++)
		{
			etudiant etli = (etudiant)arliad.get(i);
			System.out.println(etli.affiche());
			etli.bourse_info();
			System.out.println("**moyenne : "+etli.moyenne()+"\n -----------------------------------------");
		}
		arsia = siia.getEtudiant();
		for(int i=0;i<arsia.size();i++)
		{
			etudiant etsi = (etudiant)arsia.get(i);
			System.out.println(etsi.affiche());
			etsi.bourse_info();
			System.out.println("moyenne sia : "+etsi.moyenne()+"\n +++++++++++++++++++++++++++++++++++++++++");
		}
		
		promotion pu = new promotion("proexp",arliad);
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////");
		 ArrayList arliado = pu.getEtudiant();
		for(int i=0;i<arliado.size();i++)
		{
			etudiant etli = (etudiant)arliado.get(i);
			System.out.println(etli.affiche());
			etli.bourse_info();
			System.out.println("**//moyenne : "+etli.moyenne()+"\n -----------------------------------------");
		}
		
	}

}


class radiateur
{
	protected String nom;
	private String type;
	private boolean allum;
	
	public radiateur()
	{
		this.nom = "";
		this.type = "none";
		this.allum = false;
	}
	public radiateur(String n,String t, boolean a)
	{
		this.nom = n;
		this.type = t;
		this.allum = a;
	}
	public boolean allumer()
	{
		allum = true;
		return allum;
	}
	public boolean eteindre()
	{
		allum = false;
		return allum;
	}
	
	public void settype(String t)
	{
		type = t;
	}
	public String gettype()
	{
		return type;
	}
	public void setnom(String n)
	{
		nom = n;
	}
	public String getnom()
	{
		return nom;
	}
	public void setallum(boolean b)
	{
		allum = b;
	}
	public boolean getallum()
	{
		return allum;
	}
	
}

class piece
{
	public int nb_sous_piece;
	public piece()
	{
		nb_sous_piece=1;
	}
}
class etage
{
	ArrayList pi = new ArrayList();
	public etage(piece p)
	{
		pi.add(p);
	}
	public boolean checked()
	{
		if(pi.size()>=1) return true;
		else return false;
	}
	public int sizep()
	{
		return pi.size();
	}
	public ArrayList getlist()
	{
		return pi;
	}
}


//---------------------------------------------------------------
class promotion
{
	private String nom_promo;
	private ArrayList etudiants = new ArrayList();
	
	public promotion(String nom_pro, ArrayList etudiants)
	{
		nom_promo = nom_pro;
		this.etudiants = etudiants;
	}
	public promotion(String nom_pro)
	{
		nom_promo = nom_pro;
	}
	public void ajouter(etudiant et)
	{
		if(et != null)
		{
			etudiants.add(et);
		}
		else System.out.println("ajout impossible");
	}
	public void supprimer(int index)
	{
		if(index>=0 && index<etudiants.size()-1)
		{
			etudiant etudiants_supp = (etudiant)etudiants.get(index);
			System.out.println("vous avez supprimer "+etudiants_supp.affiche());
			etudiants.remove(index);
		}
		else System.out.println("suppression impossible");
	}
	public ArrayList getEtudiant()
	{
		if(etudiants.size() != 0) return etudiants;
		else return null;
	}
}
class etudiant
{
	private String nom, prenom;
	private ArrayList notes = new ArrayList();
	private bourse bourse_etudiant;
	public etudiant(String nom_e, String prenom_e)
	{
		nom = nom_e;  prenom = prenom_e;
		bourse_etudiant = null;
	}
	public etudiant(String nom_e, String prenom_e, bourse bou)
	{
		nom = nom_e;  prenom = prenom_e;
		bourse_etudiant = bou;
	}
	public void setnotes(float note)
	{
		if( (note>=0 && note<=20))
			notes.add(new Float(note));
	}
	public String moyenne()
	{
		float s=0;
	if(notes.size()>0)
	  {
		for(int i=0;i<notes.size();i++)
		{
			s+= Float.parseFloat(notes.get(i).toString());
		}
		DecimalFormat df = new DecimalFormat(".##");
		return df.format(s/notes.size());
		
	  }
	 return null;
		
	}
	public void setbourse(bourse bouetu)
	{
		bourse_etudiant = bouetu;
	}
	public bourse getbourse()
	{
		return bourse_etudiant;
	}
	public void bourse_info()
	{
		if(bourse_etudiant != null)
			System.out.println("info bourse pour l'etudiant "+nom+" "+prenom+", montant est de "+bourse_etudiant.get_montant());
		else
			System.out.println("etudiant "+nom+" "+prenom+", non boursier");
	}
	public String affiche()
	{
		return (" "+nom+" "+prenom);
	}
}

abstract class bourse
{
	abstract float get_montant();
}
class echelon1 extends bourse
{
	private final float montant= (float)200.20;
	public echelon1()
	{
		System.out.println("echelon 1, montant : "+montant);
	}
	public float get_montant()
	{
		return montant;
	}
}
class echelon2 extends bourse
{
	private final float montant= (float)400.50;
	public echelon2()
	{
		System.out.println("echelon 2, montant : "+montant);
	}
	public float get_montant()
	{
		return montant;
	}
}
class echelon3 extends bourse
{
	private final float montant= (float)500.90;
	public echelon3()
	{
		System.out.println("echelon 3, montant : "+montant);
	}
	public float get_montant()
	{
		return montant;
	}
}
class echelon4 extends bourse
{
	private final float montant= (float)700;
	public echelon4()
	{
		System.out.println("echelon 4, montant : "+montant);
	}
	public float get_montant()
	{
		return montant;
	}
}
class echelon5 extends bourse
{
	private final float montant= (float)800.1;
	public echelon5()
	{
		System.out.println("echelon 5, montant : "+montant);
	}
	public float get_montant()
	{
		return montant;
	}
}




