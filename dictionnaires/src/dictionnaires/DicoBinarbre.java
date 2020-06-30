package dictionnaires;

import arbres.Arbre;
import binarbres.*;
import listes.*;

import java.io.*;

/**
 * Mise en oeuvre de dictionnaire par un arbre binaire de recherche AVL
 * contenant des mots.
 */
public class DicoBinarbre implements Dictionnaire {
	
	/**
	 * Arbre binaire AVL contenant les mots du dictionnaire.
	 */
	Binarbre<String> binarbre;

	/**
	 * Constructeur : crée un dictionnaire vide.
	 */
	public DicoBinarbre() {
		binarbre = null;
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
	public DicoBinarbre(String file) throws FileNotFoundException, IOException {
		this();
		BufferedReader handler = new BufferedReader(new FileReader(file));
		String ligne;
		while ((ligne = handler.readLine()) != null)
			this.addWords(ligne);
		handler.close();
	}

	@Override
	public String toString() {
		String res = "";
		if (binarbre != null) {
			Liste<String> liste = binarbre.infixe();
			for (int i = 0; i < liste.size(); i++)
				res += liste.get(i) + "\n";
		}
		return res;
	}

	@Override
	public Dictionnaire addWords(String... words) {
        int i=0;
		if(binarbre == null && words.length!=0)
        {
        	binarbre = new BinarbreAVL<>(words[i]);
        	i++;
        }
		while(i<words.length)
		{
			binarbre=binarbre.addVal(words[i]); i++;
		}
		
		return this; 
	}

	@Override
	public Dictionnaire cutWords(String... words) {
		
		if(binarbre != null && words.length!=0)
        {
			for(int i=0;i<words.length;i++)
			    binarbre = binarbre.cutVal(words[i]);
        }
		
		return this; 
	}

	@Override
	public int size() {
        if(binarbre ==null) return 0;
        else return binarbre.familySize()+1;
	}

	@Override
	public boolean exists(String word) {
		if(binarbre ==null) return false;
        else return binarbre.containsVal(word);
	}

	/* ----------------------------------------------------------------- */
	/* Fonction récursive auxiliaire de commonPrefix                     */
	/* ----------------------------------------------------------------- */

	/**
	 * Renvoie la liste des mots d'un arbre binaire de recherche commençant par un
	 * certain préfixe
	 * 
	 * @param prefix
	 *            préfixe des mots recherchés
	 * @param sousarbre
	 *            arbre dans lequel les mots qui commencent par prefix sont
	 *            recherchés
	 * @return mots de sousarbre qui commencent par prefix
	 */
	private static Liste<String> commonPrefixRec(String prefix, Binarbre<String> sousarbre) {
        Liste<String> rs= new ListeDC<String>();
		if(sousarbre == null) return rs;
		if(sousarbre.getVal().startsWith(prefix))
		{
			rs.add(sousarbre.getVal());
		}
		else
		{
			if(sousarbre.getVal().compareTo(prefix)<0)
				return commonPrefixRec(prefix, sousarbre.getRight());
			else commonPrefixRec(prefix, sousarbre.getLeft());
		}
		return rs; 
	}

	/* ----------------------------------------------------------------- */

	@Override
	public Liste<String> commonPrefix(String prefix) {
		return commonPrefixRec(prefix, binarbre);
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) throws Exception {
		DicoBinarbre d = new DicoBinarbre();
				
		System.out.println("-------------------------------------------------------");
		System.out.println("----              Tests fonctionnels               ----");
		System.out.println("-------------------------------------------------------");

		System.out.println(d + "(" + d.size() + " mots)");
		d = new DicoBinarbre("Dico_essais.txt");
		System.out.println(d + "(" + d.size() + " mots)");
		System.out.println(d.binarbre);
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
		d = new DicoBinarbre("Dico_enorme.txt");
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
flot                                                                     
+-------------------------------------+                                  
combien                               oncle                              
+--------------------+                +-----------+                      
aide                 dernier          marier      vue                    
+-------------+      +------+         +-+         +------------------+   
agiter        combat depuis engager     mouvement travers            yeux
+----------+                +-+                   +--------+         +-+
abandonner ah                 enlever             prochain traverser y à

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
---- Temps d'exécution : 226208 µs
"vue" présent ? true
"XWXWX" présent ? false
---- Temps d'exécution : 47 µs
145 mots commençant par "trav" ( travail travailla travaillai travaillaient travaillais travaillait travaillant travaillas travaillasse travaillassent travaillasses travaillassiez travaillassions travaille travaillent travailler travaillera travaillerai travailleraient travaillerais travaillerait travailleras travaillerez travailleriez travaillerions travaillerons travailleront travailles travailleur travailleurs travailleuse travailleuses travaillez travailliez travaillions travaillisme travailliste travaillistes travaillons travailloter travaillâmes travaillât travaillâtes travaillèrent travaillé travaillée travaillées travaillés travaux travelage travelages traveller's travelling travellings travelo travers traversa traversable traversables traversai traversaient traversais traversait traversant traversas traversasse traversassent traversasses traversassiez traversassions traverse traversent traverser traversera traverserai traverseraient traverserais traverserait traverseras traverserez traverseriez traverserions traverserons traverseront traverses traversez traversier traversiers traversiez traversin traversine traversines traversins traversions traversière traversières traversons traversâmes traversât traversâtes traversèrent traversé traversée traversées traversés travertin travesti travestie travesties travestir travestira travestirai travestiraient travestirais travestirait travestiras travestirent travestirez travestiriez travestirions travestirons travestiront travestis travestisme travestismes travestissaient travestissais travestissait travestissant travestisse travestissement travestissements travestissent travestisses travestissez travestissiez travestissions travestissons travestit travestîmes travestît travestîtes traviole travée travées )
---- Temps d'exécution : 743 µs
			
			*/
	}

}
