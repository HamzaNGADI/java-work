package server;

public class ouvrageImp extends ouvragePOA{
	
	private String titre,auteur, mcle[];
	private famille famille;
	
	public ouvrageImp(String titr)
	{
		titre = titr;
		
		
	}
	@Override
	public String titre() {
		return titre;
	}

	@Override
	public String auteurs() {
		// TODO Auto-generated method stub
		return auteur;
	}

	@Override
	public void auteurs(String newAuteurs) {
		auteur = newAuteurs;
	}

	@Override
	public String[] mots_clefs() {
		return mcle;
	}

	@Override
	public void mots_clefs(String[] newMots_clefs) {
		mcle = newMots_clefs;		
	}

	@Override
	public famille famille_ouvrage() {
		return famille;
	}

	@Override
	public void famille_ouvrage(famille newFamille_ouvrage) {
		famille = newFamille_ouvrage;	
	}

}
