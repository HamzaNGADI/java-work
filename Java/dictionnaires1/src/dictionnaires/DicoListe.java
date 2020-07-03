package dictionnaires;

import java.io.*;

import listes.*;

/**
 * Mise en oeuvre de dictionnaire par une liste de mots non triée et sans
 * détection de doublons.
 */
public class DicoListe implements Dictionnaire {

	/**
	 * Liste non triée des mots du dictionnaire.
	 */
	Liste<String> liste;

	/**
	 * Constructeur : crée un dictionnaire vide.
	 */
	public DicoListe() {
		liste = new ListeDC<String>();
	}

	/**
	 * Constructeur : crée un dictionnaire contenant les mots stockés dans un
	 * fichier.
	 * 
	 * @param file
	 *            fichier contenant les mots à charger
	 * @throws FileNotFoundException
	 *             soulevée quand le fichier n'est pas lisible
	 * @throws IOException
	 *             soulevée quand le fichier est fermé
	 */
	public DicoListe(String file) throws FileNotFoundException, IOException {
		this();
		BufferedReader handler = new BufferedReader(new FileReader(file));
		String ligne;
		while ((ligne = handler.readLine()) != null)
			this.liste.add(ligne);
		handler.close();
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < liste.size(); i++)
			res += liste.get(i) + "\n";
		return res;
	}

	@Override
	public Dictionnaire addWords(String... words) {
        for(int i=0;i<words.length;i++)
        {
        	 liste.add(words[i]);
        }
        return this;
	}

	@Override
	public Dictionnaire cutWords(String... words) {
	   for(int i=0;i<liste.size();i++)
	   { 
		   for(int j=0;j<words.length;j++)
		   {
			   if(liste.get(i).equals(words[j]))
			   {
				   liste.remove(i); i--;
			   }
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
		for(int i=0;i<liste.size();i++)
		   {
			if(liste.get(i).equals(word)) return true;
		   }
		return false;
	}

	@Override
	public Liste<String> commonPrefix(String prefix) {
		return null;
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) throws Exception {
		DicoListe d = new DicoListe();
		
		System.out.println("-------------------------------------------------------");
		System.out.println("----              Tests fonctionnels               ----");
		System.out.println("-------------------------------------------------------");

		System.out.println(d + "(" + d.size() + " mots)");
		d = new DicoListe("Dico_essais.txt");
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
		d = new DicoListe("Dico_enorme.txt");
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
flot
marier
mouvement
oncle
prochain
travers
agiter
traverser
vue
yeux
y
à
abandonner
ah
aide
combat
combien
depuis
engager
dernier
enlever
(21 mots)
( flot marier mouvement oncle prochain travers agiter traverser vue yeux y à abandonner ah aide combat combien depuis engager dernier enlever )
flot
marier
mouvement
oncle
prochain
travers
agiter
traverser
vue
yeux
y
à
abandonner
ah
aide
combat
combien
depuis
engager
dernier
enlever
visée
vue
xylophone
trivial
tueur
(26 mots)
flot
marier
mouvement
oncle
prochain
travers
agiter
traverser
yeux
y
à
abandonner
ah
aide
combat
combien
depuis
engager
dernier
enlever
visée
xylophone
trivial
tueur
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
---- Temps d'exécution : 79238 µs
"vue" présent ? true
"XWXWX" présent ? false
---- Temps d'exécution : 12749 µs
145 mots commençant par "trav" ( travail travailla travaillai travaillaient travaillais travaillait travaillant travaillas travaillasse travaillassent travaillasses travaillassiez travaillassions travaillâmes travaillât travaillâtes travaille travaillent travailler travaillera travaillerai travailleraient travaillerais travaillerait travailleras travaillerez travailleriez travaillerions travaillerons travailleront travailles travailleur travailleurs travailleuse travailleuses travaillez travaillèrent travaillé travaillée travaillées travaillés travailliez travaillions travaillisme travailliste travaillistes travaillons travailloter travaux travelage travelages traveller's travelling travellings travelo travers traversa traversable traversables traversai traversaient traversais traversait traversant traversas traversasse traversassent traversasses traversassiez traversassions traversâmes traversât traversâtes traverse traversent traverser traversera traverserai traverseraient traverserais traverserait traverseras traverserez traverseriez traverserions traverserons traverseront traverses traversez traversèrent traversé traversée traversées traversés traversier traversiers traversiez traversière traversières traversin traversine traversines traversins traversions traversons travertin travesti travestie travesties travestir travestira travestirai travestiraient travestirais travestirait travestiras travestirent travestirez travestiriez travestirions travestirons travestiront travestis travestisme travestismes travestissaient travestissais travestissait travestissant travestisse travestissement travestissements travestissent travestisses travestissez travestissiez travestissions travestissons travestit travestîmes travestît travestîtes travée travées traviole )
---- Temps d'exécution : 16674 µs

		 */
	}

}
