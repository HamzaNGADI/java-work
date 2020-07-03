package aplimeth;

@table(name="t_livre",titre="titre", auteur="auteur", annee="annee")
public class livre {
	
	private String ti, au;
	private int an;
	public livre()
	{
		ti=au="";
		an=0;
	}
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
	public void setti(String t)
	{
		ti=t;
	}
	public void setaut(String auu)
	{
		au=auu;
	}
	public void setanne(int a)
	{
		an=a;
	}
}
