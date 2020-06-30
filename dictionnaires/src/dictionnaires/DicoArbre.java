package dictionnaires;

import arbres.*;
import listes.*;

import java.io.*;

/**
 * Mise en oeuvre de dictionnaire par un arbre quelconque de lettres.
 * 
 * Un mot du dictionnaire correspond à un chemin qui mène de la racine à une
 * feuille.
 */
public class DicoArbre implements Dictionnaire {

	/**
	 * Arbre quelconque de lettres.
	 */
	Arbre<Character> arbre;

	/**
	 * Constructeur : crée un dictionnaire vide.
	 */
	public DicoArbre() {
		arbre = new ArbreDC<Character>('#');
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
	public DicoArbre(String file) throws FileNotFoundException, IOException {
		this();
		BufferedReader handler = new BufferedReader(new FileReader(file));
		String ligne;
		while ((ligne = handler.readLine()) != null)
			this.addWords(ligne);
		handler.close();
	}

	/* ----------------------------------------------------------------- */
	/* Fonctions récursive auxiliaires                                   */
	/* ----------------------------------------------------------------- */
	
	/**
	 * Indique la position d'un fil d'un arbre de lettres dont la racine porte un
	 * certain caractère.
	 * 
	 * La position peut être occupée ou non. Si elle n'est pas occupée, le résultat
	 * indique la position d'insertion.
	 * 
	 * @param a
	 *            arbre dont on recherche la position d'un fils
	 * @param c
	 *            caractère de la racine du fils recherché
	 * @return indice d'un fils de a, existant ou non, dont la racine est c
	 */
	private static int position(Arbre<Character> a, Character c) {
		int i = 0;
		while (i < a.children().size() && a.getChild(i).getVal().compareTo(c) < 0) i++;
		return i;
	}

	/**
	 * Indique si un arbre de lettre possède à une certaine position un fils dont la
	 * racine porte un certain caractère.
	 * 
	 * @param a
	 *            arbre dont on recherche le fils
	 * @param c
	 *            caractère de la racine du fils recherché
	 * @param position
	 *            position du fils recherché
	 * @return vrai si a a un fils d'indice position dont la racine est c, faux
	 *         sinon
	 */
	private static boolean existe(Arbre<Character> a, Character c, int position) {
		return (position < a.children().size() && a.getChild(position).getVal().compareTo(c) == 0);
	}
	
	/**
	 * Renvoie une liste de mots correspondant aux mots d'un arbre de lettres
	 * complétés par un certain préfixe.
	 * 
	 * Attention : le préfixe fourni peut être une chaîne vide ou non. Quand la
	 * préfixe fourni est non vide, sa première lettre n'est pas prise en compte
	 * (typiquement caractère # non constitutif de mot).
	 * 
	 * @param a
	 *            arbre à parcourir récursivement
	 * @param prefix
	 *            préfixe des mots recherchés (joue le rôle d'accumulateur)
	 * @return mots de a préfixés par prefix
	 */
	private static Liste<String> genListe(Arbre<Character> a, String prefix) {
        Liste<String> st = new ListeDC<String>();
        Liste<String> pres = new ListeDC<String>();
       
        if(a.children().size()!=0 && prefix.length()!=0)
              pres.add(prefix.substring(1));
        else
        {
        	for(int i=0; i<a.children().size(); i++)
        	{
        		st.addAll(genListe(a.getChild(i), ""+a.getVal()));
        	}
        }
        
        
		return st;
	}

	/**
	 * Rajoute un mot à un arbre de lettres.
	 * 
	 * @param s
	 *            mot à ajouter lettre par lettre dans l'arbre
	 * @param a
	 *            arbre à parcourir récursivement
	 */
	private static void addWord(String s, Arbre<Character> a) {
       if(a==null || s.trim().length()==0)
         return;
       char ss = s.charAt(0);
       if(!existe(a,ss,position(a,ss)))
        {
    	   a.addChild(position(a,ss), new ArbreDC<Character>(ss)) ;
   	    }
       addWord(s.substring(1),a.getChild(position(a,ss)));
	}

	/**
	 * Supprime un mot d'un arbre de lettres.
	 * 
	 * Supprimer un mot qui existe dans l'arbre revient à supprimer une branche non
	 * fourchue de l'arbre, éventuellement réduite à une feuille.
	 * 
	 * Pour supprimer un mot, on parcourt l'arbre en suivant les lettres du mot à
	 * supprimer. Si on arrive sur un point au bout de chemin, le mot existe et doit
	 * être supprimé. Pour le supprimer, il faut revenir au dernier branchement
	 * rencontré et supprimer la branche concernée. Au lieu de revenir en arrière,
	 * on mémorise le dernier carrefour rencontré et la direction prise.
	 * 
	 * @param s
	 *            mot à supprimer de l'arbre
	 * @param a
	 *            arbre à parcourir récursivement
	 * @param pereDuFilsACouper
	 *            dernier carrefour rencontré
	 * @param filsACouper
	 *            direction prise au dernier carrefour rencontré
	 */
	private static void cutWord(String s, Arbre<Character> a, Arbre<Character> pereDuFilsACouper, int filsACouper) {
		if (s.equals("")) { // le mot doit être supprimé
			if (pereDuFilsACouper != null)
				pereDuFilsACouper.cutChild(filsACouper);
			return;
		}
		char c = s.charAt(0);
		int position = position(a, c);
		if (existe(a, c, position)) 
			if (a.children().size() > 1) // c'est un carrefour
				cutWord(s.substring(1), a.getChild(position), a, position);
			else // ce n'est pas un carrefour : on garde la mémoire du dernier carrefour rencontré
				cutWord(s.substring(1), a.getChild(position), pereDuFilsACouper, filsACouper); 
		// ELSE : on ne fait rien : le mot n'apparaît pas dans le dictionnaire...
	}
	
	/**
	 * Compte le nombre de points dans un arbre de lettres
	 * 
	 * @param a
	 *            arbre à parcourir récursivement
	 * @return nombre de points dans a
	 */
	private static int nbPoints(Arbre<Character> a) {
        int r = 0;
        if(a.getVal().equals(".")) r++;
        for(int i=0;i<a.children().size();i++)
        {
        	r+=nbPoints(a.getChild(i));
        }
        
		return r; 
	}

	/**
	 * Renvoie s'il existe le sous-arbre d'un arbre de lettres situé au bout d'un
	 * chemin correspondant à un mot.
	 * 
	 * @param s
	 *            mot correspondant au chemin à parcourir
	 * @param a
	 *            arbre à parcourir récursivement
	 * @return sous-arbre de a situé au bout de s s'il existe, null sinon
	 */
	private static Arbre<Character> sousArbre(String s, Arbre<Character> a) {
        Arbre<Character> ac = new ArbreDC<Character>(null);
        int p=position(a,s.charAt(0));
		if(existe(a,s.charAt(0), p))
		{
			ac = sousArbre(s.substring(1), a.getChild(p));
			return ac;
		}
		
		return null; 
	}

	/* ----------------------------------------------------------------- */

	@Override
	public String toString() {
		String res = "";
		Liste<String> words = genListe(arbre, "");
		for (int i = 0; i < words.size(); i++)
			res += words.get(i) + "\n";
		return res;
	}

	@Override
	public Dictionnaire addWords(String... words) {
		for (int i = 0; i < words.length; i++)
			addWord(words[i] + '.', arbre);
		return this;
	}
	
	@Override
	public Dictionnaire cutWords(String... words) {
		for (int i = 0; i < words.length; i++)
			cutWord(words[i] + '.', arbre, null, -1);
		return this;
	}

	@Override
	public int size() {
		return nbPoints(arbre);
	}

	@Override
	public boolean exists(String word) {
		return sousArbre(word + '.', arbre) != null;
	}

	@Override
	public Liste<String> commonPrefix(String prefix) {
		Arbre<Character> a = sousArbre(prefix, arbre);
		Liste<String> sufix = (a == null ? new ListeDC<String>() : genListe(a, ""));
		Liste<String> res = new ListeDC<String>();
		for (int i = 0; i < sufix.size(); i++)
			res.add(prefix + sufix.get(i));
		return res;
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) throws Exception {
		DicoArbre d = new DicoArbre();
		
		System.out.println("-------------------------------------------------------");
		System.out.println("----              Tests fonctionnels               ----");
		System.out.println("-------------------------------------------------------");

		System.out.println(d + "(" + d.size() + " mots)");
		d = new DicoArbre("Dico_essais.txt");
		System.out.println(d + "(" + d.size() + " mots)");
		System.out.println(d.arbre);
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
		d = new DicoArbre("Dico_enorme.txt");
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
#                                        
+-------+---+---+---+-+---+-+-+---+-+---+
a       c   d   e   f m   o p t   v y   à
+-+-+-+ |   |   |   | +-+ | | |   | +-+ |
b g h i o   e   n   l a o n r r   u . e .
| | | | |   +-+ +-+ | | | | | |   |   |  
a i . d m   p r g l o r u c o a   e   u  
| |   | |   | | | | | | | | | |   |   |  
n t   e b   u n a e t i v l c v   .   x  
| |   | +-+ | | | | | | | | | |       |  
d e   . a i i i g v . e e e h e       .  
| |     | | | | | |   | | | | |          
o r     t e s e e e   r m . a r          
| |     | | | | | |   | |   | |          
n .     . n . r r r   . e   i s          
|         |   | | |     |   | +-+        
n         .   . . .     n   n . e        
|                       |   |   |        
e                       t   .   r        
|                       |       |        
r                       .       .        
|                                        
.                                        

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
---- Temps d'exécution : 547683 µs
"vue" présent ? true
"XWXWX" présent ? false
---- Temps d'exécution : 70 µs
145 mots commençant par "trav" ( travail travailla travaillai travaillaient travaillais travaillait travaillant travaillas travaillasse travaillassent travaillasses travaillassiez travaillassions travaille travaillent travailler travaillera travaillerai travailleraient travaillerais travaillerait travailleras travaillerez travailleriez travaillerions travaillerons travailleront travailles travailleur travailleurs travailleuse travailleuses travaillez travailliez travaillions travaillisme travailliste travaillistes travaillons travailloter travaillâmes travaillât travaillâtes travaillèrent travaillé travaillée travaillées travaillés travaux travelage travelages traveller's travelling travellings travelo travers traversa traversable traversables traversai traversaient traversais traversait traversant traversas traversasse traversassent traversasses traversassiez traversassions traverse traversent traverser traversera traverserai traverseraient traverserais traverserait traverseras traverserez traverseriez traverserions traverserons traverseront traverses traversez traversier traversiers traversiez traversin traversine traversines traversins traversions traversière traversières traversons traversâmes traversât traversâtes traversèrent traversé traversée traversées traversés travertin travesti travestie travesties travestir travestira travestirai travestiraient travestirais travestirait travestiras travestirent travestirez travestiriez travestirions travestirons travestiront travestis travestisme travestismes travestissaient travestissais travestissait travestissant travestisse travestissement travestissements travestissent travestisses travestissez travestissiez travestissions travestissons travestit travestîmes travestît travestîtes traviole travée travées )
---- Temps d'exécution : 771 µs
			
			*/
	}

}
