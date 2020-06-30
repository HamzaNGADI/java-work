import java.util.ArrayList;


public class hotel {
    private ArrayList chambre_hot = new ArrayList();
    private String nome, adr,cate;
    
    private hotel(ArrayList ch, String nom, String addr,String cat)
    {
    	chambre_hot.addAll(ch);
    	nome = nom; 	adr = addr;
    	cate = cat;
    }
    public void addchambre(chambre chi)
    {
    	chambre_hot.add(chi);
    }
    public static hotel createhotel(ArrayList chs,String nom, String addr,String cat)
    {
    	if(chs.size()<2)
    	{
    		return null;
    	}
    	else
    		return new hotel(chs,nom,addr,cat);
    }
    public void setnom(String nom)
    {
    	nome = nom;
    }
    public String getnom()
    {
    	return nome;
    }
    public void setadress(String ad)
    {
    	adr = ad;
    }
    public String getadress()
    {
    	return adr;
    }
    
    public ArrayList getchambre()
    {
    	return chambre_hot;
    }
    
    
}
