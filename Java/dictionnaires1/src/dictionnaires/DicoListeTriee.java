package dictionnaires;

import java.io.*;

import listes.*;

/**
 * Mise en oeuvre de dictionnaire par une liste de mots maintenue triée et sans
 * doublons.
 */
public class DicoListeTriee implements Dictionnaire {

	/**
	 * Liste maintenue triée des mots du dictionnaire.
	 */
	Liste<String> liste;

	/**
	 * Constructeur : crée un dictionnaire vide.
	 */
	public DicoListeTriee() {
		liste = new ListeDC<String>();
	}

	/**
	 * Constructeur : crée un dictionnaire contenant les mots stockés dans un
	 * fichier.
	 * 
	 * @param file
	 *            fichier contenant les mots à charger
	 * @param fichierDejaTrie
	 *            indique si le fichier à charger est déjà trié ou non
	 * @throws FileNotFoundException
	 *             soulevée quand le fichier n'est pas lisible
	 * @throws IOException
	 *             soulevée quand le fichier est fermé
	 */
	public DicoListeTriee(String file, boolean fichierDejaTrie) throws FileNotFoundException, IOException {
		this();
		BufferedReader handler = new BufferedReader(new FileReader(file));
		String ligne;
		while ((ligne = handler.readLine()) != null)
			if (fichierDejaTrie) this.liste.add(ligne);
			else                 this.addWords(ligne);
		handler.close();
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < liste.size(); i++)
			res += liste.get(i) + "\n";
		return res;
	}

	/* ----------------------------------------------------------------- */
	/* Fonction de recherche de position par dichotomie                  */
	/* ----------------------------------------------------------------- */

	@SuppressWarnings("unused") // à supprimer...
	private int position(String mot) {
		int debut = 0;
		int fin = this.liste.size();
		int comparaison = 0;
		while (fin > debut) {
			int centre = (fin - debut) / 2 + debut;
			comparaison = mot.compareTo(this.liste.get(centre));
			if (comparaison == 0) return centre;
			if (comparaison <  0) fin   = centre;
			else                  debut = centre + 1;
		}
		if (comparaison < 0) return fin;
		else                 return debut;
	}
	
	/* ----------------------------------------------------------------- */

	@Override
	public Dictionnaire addWords(String... words) {
        for(int i=0;i<words.length;i++)
        {
        	int p =position(words[i]);
        	if(p == size() || !liste.get(p).equals(words[i])) liste.add(words[i]);
        }
		return this;
	}

	@Override
	public Dictionnaire cutWords(String... words) {
        
		for(int i=0;i<words.length;i++)
        {
        	int p =position(words[i]);
        	if((p >=0 && p<size())|| liste.get(p).equals(words[i])) {
        		liste.remove(p); i--;
        	}
        }
		return this; 
	}

	@Override
	public int size() {
        return liste.size();
	}

	@Override
	public boolean exists(String word) {
        int p = position(word);
        if((p >=0 && p<size())|| liste.get(p).equals(word)) return true;
        return false;
	}

	@Override
	public Liste<String> commonPrefix(String prefix) {
		Liste<String> rs = new ListeDC<>();
        if(liste == null || liste.size()==0 || prefix.trim().equals("")) return rs;
		
		for(int i=position(prefix);i<liste.size();i++)
        {
        	if(liste.get(i).startsWith(prefix)) {
        		rs.add(liste.get(i));
        	}
        }
		return rs;
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) throws Exception {
		DicoListeTriee d = new DicoListeTriee();
		
		System.out.println("-------------------------------------------------------");
		System.out.println("----              Tests fonctionnels               ----");
		System.out.println("-------------------------------------------------------");

		System.out.println(d + "(" + d.size() + " mots)");
		d = new DicoListeTriee("Dico_essais.txt", false);
		System.out.println(d + "(" + d.size() + " mots)");
		System.out.println(d.liste);
		System.out.println(d.addWords("visée", "vue", "xylophone", "trivial", "tueur") + "(" + d.size() + " mots)");
		System.out.println(d.cutWords("vue", "vue", "yyy") + "(" + d.size() + " mots)");
		System.out.println("\"vue\" présent ? " + d.exists("vue"));
		System.out.println("\"à\" présent ? " + d.exists("à"));
		System.out.println("Mots commentçant par \"t\" : " + d.commonPrefix("t"));
		System.out.println("Mots commentçant par \"tr\" : " + d.commonPrefix("tr"));
		System.out.println("Mots commentçant par \"tra\" : " + d.commonPrefix("tra"));
		System.out.println("Mots commentçant par \"travers\" : " + d.commonPrefix("travers"));
		System.out.println("Mots commentçant par \"traverse\" : " + d.commonPrefix("traverse"));
		System.out.println("Mots commentçant par \"traversent\" : " + d.commonPrefix("traversent"));

		System.out.println("-------------------------------------------------------");
		System.out.println("----               Tests techniques                ----");
		System.out.println("-------------------------------------------------------");
				
		long timeInit, top;

		timeInit = System.nanoTime(); // top départ
		d = new DicoListeTriee("Dico_enorme.txt", true);
		System.out.println(d.size() + " mots");
		top = System.nanoTime(); // top d'arrivée
		System.out.println("---- Temps d'exécution : " + ((top-timeInit) / 1000) + " µs");

		timeInit = System.nanoTime(); // top départ
		System.out.println("\"vue\" présent ? " + d.exists("vue"));
		System.out.println("\"XWXWX\" présent ? " + d.exists("XWXWX"));
		top = System.nanoTime(); // top d'arrivée
		System.out.println("---- Temps d'exécution : " + ((top-timeInit) / 1000) + " µs");

		timeInit = System.nanoTime(); // top départ
		Liste<String> ltrav = d.commonPrefix("trav");
		System.out.println(ltrav.size() + " mots commençant par \"trav\" " + ltrav);
		top = System.nanoTime(); // top d'arrivée
		System.out.println("---- Temps d'exécution : " + ((top-timeInit) / 1000) + " µs");
		
		/*

-------------------------------------------------------
----              Tests fonctionnels               ----
-------------------------------------------------------
(0 mots)
abandonner
agiter
ah
aide
combat
combien
depuis
dernier
engager
enlever
flot
marier
mouvement
oncle
prochain
travers
traverser
vue
y
yeux
à
(21 mots)
( abandonner agiter ah aide combat combien depuis dernier engager enlever flot marier mouvement oncle prochain travers traverser vue y yeux à )
abandonner
agiter
ah
aide
combat
combien
depuis
dernier
engager
enlever
flot
marier
mouvement
oncle
prochain
travers
traverser
trivial
tueur
visée
vue
xylophone
y
yeux
à
(25 mots)
abandonner
agiter
ah
aide
combat
combien
depuis
dernier
engager
enlever
flot
marier
mouvement
oncle
prochain
travers
traverser
trivial
tueur
visée
xylophone
y
yeux
à
(24 mots)
"vue" présent ? false
"à" présent ? true
Mots commentçant par "t" : ( travers traverser trivial tueur )
Mots commentçant par "tr" : ( travers traverser trivial )
Mots commentçant par "tra" : ( travers traverser )
Mots commentçant par "travers" : ( travers traverser )
Mots commentçant par "traverse" : ( traverser )
Mots commentçant par "traversent" : ( )
-------------------------------------------------------
----               Tests techniques                ----
-------------------------------------------------------
260683 mots
---- Temps d'exécution : 78503 µs
"vue" présent ? true
"XWXWX" présent ? false
---- Temps d'exécution : 7034 µs
145 mots commençant par "trav" ( travail travailla travaillai travaillaient travaillais travaillait travaillant travaillas travaillasse travaillassent travaillasses travaillassiez travaillassions travaillâmes travaillât travaillâtes travaille travaillent travailler travaillera travaillerai travailleraient travaillerais travaillerait travailleras travaillerez travailleriez travaillerions travaillerons travailleront travailles travailleur travailleurs travailleuse travailleuses travaillez travaillèrent travaillé travaillée travaillées travaillés travailliez travaillions travaillisme travailliste travaillistes travaillons travailloter travaux travelage travelages traveller's travelling travellings travelo travers traversa traversable traversables traversai traversaient traversais traversait traversant traversas traversasse traversassent traversasses traversassiez traversassions traversâmes traversât traversâtes traverse traversent traverser traversera traverserai traverseraient traverserais traverserait traverseras traverserez traverseriez traverserions traverserons traverseront traverses traversez traversèrent traversé traversée traversées traversés traversier traversiers traversiez traversière traversières traversin traversine traversines traversins traversions traversons travertin travesti travestie travesties travestir travestira travestirai travestiraient travestirais travestirait travestiras travestirent travestirez travestiriez travestirions travestirons travestiront travestis travestisme travestismes travestissaient travestissais travestissait travestissant travestisse travestissement travestissements travestissent travestisses travestissez travestissiez travestissions travestissons travestit travestîmes travestît travestîtes travée travées traviole )
---- Temps d'exécution : 4014 µs

		 */
	}

}
