package main;

@table(name="t_livre",titre="titre", auteur="auteur", annee="annee")
public class livre {
	
	private String ti, au;
	private int an;
	public livre(String titre, String auteur, int anne)
	{
		ti = titre;
		au = auteur;
		an=anne;
	}
	public String gettitre()
	{
		return ti;
	}
	public String getauteur()
	{
		return au;
	}
	public int getannee()
	{
		return an;
	}
}
