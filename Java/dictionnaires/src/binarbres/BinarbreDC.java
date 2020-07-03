package binarbres;

import listes.Liste;
import listes.ListeDC;

/**
 * Un arbre BinarbreDC<E> a une racine, un éventuel fils droit et un éventuel
 * fils gauche. Chaque fils a la référence de son père (double chainage).
 * 
 * <b> Attention </b> : un arbre peut être un sous-arbre d'un autre arbre,
 * auquel cas il a un (unique) père. Il ne faut donc pas confondre la racine de
 * l'arbre et racine du plus grand arbre dont il est le sous-arbre
 * 
 * @param <E>
 *            type des éléments stockés dans l'arbre
 */
public class BinarbreDC<E extends Comparable<E>> implements Binarbre<E> {

	/**
	 * Référence du père s'il existe ou null.
	 */
	protected BinarbreDC<E> father;

	/**
	 * Référence du fils gauche s'il existe ou null.
	 */
	protected BinarbreDC<E> left;

	/**
	 * Référence du fils droit s'il existe ou null.
	 */
	protected BinarbreDC<E> right;

	/**
	 * Valeur encapsulée.
	 */
	protected E value;

	/**
	 * Hauteur de l'arbre.
	 */
	protected int height;

	/* ----------------------------------------------------------------- */
	/* Attribut spécifique dédié aux fonctions utilitaires pour la       */
	/* représentation littérale des arbres                               */
	/* ----------------------------------------------------------------- */

	/**
	 * Indique si le résultat de toString doit faire apparaître sur chaque noeud le
	 * facteur d'équilibrage.
	 */
	public static boolean printBalance = false;

	/* ----------------------------------------------------------------- */

	/**
	 * Constructeur d'arbre binaire de recherche réduit à une feuille.
	 * 
	 * @param e
	 *            valeur à encapsuler dans la racine
	 */
	public BinarbreDC(E e) {
		father = null;
		left = null;
		right = null;
		value = e;
		height = 0;
	}

	/* ----------------------------------------------------------------- */
	/* Fonctions utilitaires pour la représentation littérale des arbres */
	/* ----------------------------------------------------------------- */

	private static String seqChar(char c, int i) {
		String res = "";
		for (; i > 0; i--)
			res += c;
		return res;
	}

	private static <F extends Comparable<F>> Liste<String> niveaux(BinarbreDC<F> a) {
		String etiq = a.getVal().toString();
		if (printBalance)
			etiq += "(" + a.balance() + ")";
		if (a.isLeaf())
			return new ListeDC<String>(etiq);
		Liste<String> niveauxL = (a.getLeft() == null ? new ListeDC<String>() : niveaux(a.getLeft()));
		Liste<String> niveauxR = (a.getRight() == null ? new ListeDC<String>() : niveaux(a.getRight()));
		Liste<String> res = new ListeDC<String>();
		int largeurL = (niveauxL.size() == 0 ? 1 : niveauxL.get(0).length());
		int largeurR = (niveauxR.size() == 0 ? -1 : niveauxR.get(0).length());
		for (int k = 0; k < niveauxL.size() && k < niveauxR.size(); k++)
			res.add(k, niveauxL.get(k) + " " + niveauxR.get(k));
		for (int k = niveauxL.size(); k < niveauxR.size(); k++)
			res.add(k, seqChar(' ', largeurL + 1) + niveauxR.get(k));
		for (int k = niveauxR.size(); k < niveauxL.size(); k++)
			res.add(k, niveauxL.get(k) + seqChar(' ', largeurR + 1));
		String traits = (a.getRight() == null ? "|" : "+" + seqChar('-', largeurL) + "+");
		traits += seqChar(' ', largeurL + 1 + largeurR - traits.length());
		res.add(0, traits).add(0, etiq + seqChar(' ', largeurL + 1 + largeurR - etiq.length()));
		return res;
	}

	/* ----------------------------------------------------------------- */

	@Override
	public String toString() {
		Liste<String> niveaux = niveaux(this);
		String res = "";
		for (int i = 0; i < niveaux.size(); i++)
			res += niveaux.get(i) + "\n";
		return res;
	}

	@Override
	public E getVal() {
		return value;
	}

	@Override
	public boolean isLeaf() {
		return left == null && right == null;
	}

	@Override
	public int familySize() {
		return    (right == null ? 0 : 1 + right.familySize())
				+ (left  == null ? 0 : 1 + left. familySize());
	}

	@Override
	public int height() { 
		return height;
	}

	@Override
	public int balance() { 
		return    (right == null ? 0 : 1 + right.height())
				- (left  == null ? 0 : 1 + left. height());
	}

	@Override
	public BinarbreDC<E> father() {
		return father;
	}

	@Override
	public BinarbreDC<E> ancestor() { 
		if (father == null) return null;
		BinarbreDC<E> aieul = father;
		while (aieul.father != null) aieul = aieul.father;
		return aieul;
	}

	@Override
	public BinarbreDC<E> getLeft() {
		return left;
	}

	@Override
	public BinarbreDC<E> getRight() {
		return right;
	}

	@Override
	public BinarbreDC<E> brother() {
		if (father == null) return null;
		if (father.left == this) return father.right;
		return father.left;
	}

	@Override
	public Liste<? extends BinarbreDC<E>> ancestors() {
		Liste<BinarbreDC<E>> res = new ListeDC<BinarbreDC<E>>();
		for (BinarbreDC<E> aieul = father; aieul != null; aieul = aieul.father)
			res.add(0, aieul);
		return res;
	}

	@Override
	public Liste<? extends BinarbreDC<E>> gen(int n) {
		Liste<BinarbreDC<E>> res = new ListeDC<BinarbreDC<E>>();
		if (n <  0) return res;
		if (n == 0) return res.add(this);
		if (n == 1) {
			if (left  != null) res.add(left);
			if (right != null) res.add(right);
			return res;
		}
		if (left  != null) res.addAll(left. gen(n - 1));
		if (right != null) res.addAll(right.gen(n - 1));
		return res;
	}

	@Override
	public boolean containsVal(E e) {
		int comparaison = e.compareTo(this.value);
		if (comparaison == 0) return true;
		if (comparaison < 0 && left != null)
			return left.containsVal(e);
		if (comparaison > 0 && right != null)
			return right.containsVal(e);
		return false;
	}

	@Override
	public boolean containsAll(Liste<? extends E> liste) {
		for (int i = 0; i < liste.size(); i++)
			if (!containsVal(liste.get(i))) return false;
		return true;
	}

	@Override
	public E maxVal() {
		if (right == null) return value;
		return right.maxVal();
	}

	@Override
	public E minVal() {
		if (left == null) return value;
		return left.minVal();
	}

	/* ----------------------------------------------------------------- */
	/* Fonctions auxiliraires de addVal et de cutVal                     */
	/* ----------------------------------------------------------------- */

	/**
	 * Crée un arbre réduit à une feuille de valeur e.
	 * 
	 * Cette méthode doit être utilisée à la place de (new BinarbreDC<E>(e)) dans
	 * addVal et peut être redéfinie dans les classes dérivées.
	 * 
	 * @param e
	 *            valeur à encapsuler dans la racine
	 * @return nouvrel arbre réduit à une feuille
	 */
	protected BinarbreDC<E> buildLeaf(E e) {
		return new BinarbreDC<E>(e);
	}

	/**
	 * Met à jour la hauteur de tous les ancêtres d'un noeud impacté par l'ajout ou
	 * la suppression d'une feuille.
	 */
	protected void updateHeight() {
		int bakHeight = height;
		int hg = (left  == null ? -1 : left.height );
		int hd = (right == null ? -1 : right.height);
		height = (hg < hd ? hd : hg) + 1;
		if (father != null && bakHeight != height)
			father.updateHeight();
	}

	/* ----------------------------------------------------------------- */

	@Override
	public Binarbre<E> addVal(E e) {
		int comparaison = e.compareTo(this.value);
		if (comparaison < 0)
			if (left != null) left = (BinarbreDC<E>) left.addVal(e);
			else {
				left = buildLeaf(e);
				left.father = this;
				updateHeight();
			}
		if (comparaison > 0)
			if (right != null) right = (BinarbreDC<E>) right.addVal(e);
			else {
				right = buildLeaf(e);
				right.father = this;
				updateHeight();
			}
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Binarbre<E> addAll(E... l) {
		Binarbre<E> res = this;
		for (int i = 0; i < l.length; i++)
			res = res.addVal(l[i]);
		return res;
	}

	@Override
	public Binarbre<E> addAll(Liste<E> l) {
		Binarbre<E> res = this;
		for (int i = 0; i < l.size(); i++)
			res = res.addVal(l.get(i));
		return res;
	}

	@Override
	public Binarbre<E> cutVal(E e) {
		int comparaison = e.compareTo(this.value);
		if (comparaison == 0) {
			if (left != null) {
				BinarbreDC<E> maxLeft = left;
				while (maxLeft.right != null)
					maxLeft = maxLeft.right;
				value = maxLeft.value;
				maxLeft.cutVal(maxLeft.value);
			} else if (right != null) {
				BinarbreDC<E> minRight = right;
				while (minRight.left != null)
					minRight = minRight.left;
				value = minRight.value;
				minRight.cutVal(minRight.value);
			} else { // c'est une feuille
				if (father != null) {
					if (father.left == this)
						 father.left = null;
					else father.right = null;
					father.updateHeight();
				}
				father = null;
				return null;
			}
		}
		if (comparaison < 0 && left != null)
			left = (BinarbreDC<E>) left.cutVal(e);
		if (comparaison > 0 && right != null)
			right = (BinarbreDC<E>) right.cutVal(e);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Binarbre<E> cutAll(E... l) {
		Binarbre<E> candidat = this;
		for (int i = 0; i < l.length & candidat != null ; i++)
			candidat = candidat.cutVal(l[i]);
		return candidat;
	}

	@Override
	public Binarbre<E> cutAll(Liste<E> l) {
		Binarbre<E> candidat = this;
		for (int i = 0; i < l.size() & candidat != null ; i++)
			candidat = candidat.cutVal(l.get(i));
		return candidat;
	}

	@Override
	public Liste<E> prefixe() { 
		Liste<E> res = new ListeDC<E>();
		res.add(value);
		if (left  != null) res.addAll(left.prefixe());
		if (right != null) res.addAll(right.prefixe());
		return res;
	}

	@Override
	public Liste<E> infixe() {
		Liste<E> res = new ListeDC<E>();
		if (left  != null) res.addAll(left.infixe());
		res.add(value);
		if (right != null) res.addAll(right.infixe());
		return res;
	}

	@Override
	public Liste<E> postfixe() {
		Liste<E> res = new ListeDC<E>();
		if (left  != null) res.addAll(left.postfixe());
		if (right != null) res.addAll(right.postfixe());
		res.add(value);
		return res;
	}

	@Override
	public Liste<E> largeur() {
		Liste<E> res = new ListeDC<E>();
		Liste<Binarbre<E>> file = new ListeDC<>(this);
		while (!file.isEmpty()) {
			Binarbre<E> a = file.remove(0);
			res.add(a.getVal());
			if (a.getLeft()  != null) file.add(a.getLeft());
			if (a.getRight() != null) file.add(a.getRight());
		}
		return res;
	}

	/* ----------------------------------------------------------------- */
	/* Fonction auxiliraire de equilibre                                 */
	/* ----------------------------------------------------------------- */

	/**
	 * Crée un nouvel arbre équilibré à partir des éléments d'une liste.
	 * 
	 * @param l
	 *            liste des éléments à stocker dans l'arbre
	 * @return arbre équilibré contenant les éléments de l
	 */
	private BinarbreDC<E> creeArbre(Liste<E> l) {
		if (l.size() == 1) return buildLeaf(l.get(0));
		if (l.size() == 2)
			return (BinarbreDC<E>) buildLeaf(l.get(0)).addVal(l.get(1));
		int milieu = l.size() / 2;
		BinarbreDC<E> res = buildLeaf(l.get(milieu));
		res.left  = creeArbre(l.subList(0, milieu - 1));
		res.right = creeArbre(l.subList(milieu + 1, l.size() - 1));
		res.left.father  = res;
		res.right.father = res;
		res.updateHeight();
		return res;
	}

	/* ----------------------------------------------------------------- */

	@Override
	public Binarbre<E> equilibre() {
		BinarbreDC<E> a = creeArbre(infixe());
		if (left  != null) left.father  = null;
		if (right != null) right.father = null;
		value  = a.value;
		left   = a.left;
		right  = a.right;
		height = a.height;
		if (left  != null) left.father  = this;
		if (right != null) right.father = this;
		return this;
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) {
		Binarbre<Integer> a1 = new BinarbreDC<Integer>(1).addAll(2,3,4,5);
		Binarbre<Integer> a2 = new BinarbreDC<Integer>(5).addAll(4,3,2,1);
		Binarbre<Integer> a3 = new BinarbreDC<Integer>(10).addAll(5, 12, 11, 8, 9, 4, 2, 10, 5, 11, 10); 
		System.out.println(a1+"*** a1 : "+a1.familySize()+" descendants ; hauteur : "+a1.height()+" contient 3 et 9 ? "+a1.containsVal(3)+a1.containsVal(9));
		System.out.println(a2+"*** a2 : "+a2.familySize()+" descendants ; hauteur : "+a2.height()+" contient 3 et 9 ? "+a2.containsVal(3)+a2.containsVal(9));
		System.out.println(a3+"*** a3 : "+a3.familySize()+" descendants ; hauteur : "+a3.height()+" contient 3 et 9 ? "+a3.containsVal(3)+a3.containsVal(9));
		System.out.println("*** generation 0 dans a3 :\n"+a3.gen(0));
		System.out.println("*** generation 1 dans a3 :\n"+a3.gen(1));
		System.out.println("*** generation 2 dans a3 :\n"+a3.gen(2));
		System.out.println("*** generation 3 dans a3 :\n"+a3.gen(3));
		System.out.println("*** generation 4 dans a3 :\n"+a3.gen(4));
		System.out.println("*** generation 5 dans a3 :\n"+a3.gen(5));
		System.out.println("*** generation 6 dans a3 :\n"+a3.gen(6));
		System.out.println(a1+"*** a1 : "+a1.prefixe());
		System.out.println("*** a1 : "+a1.infixe());
		System.out.println("*** a1 : "+a1.postfixe());
		System.out.println("*** a1 : "+a1.largeur());
		System.out.println(a2+"*** a2 : "+a2.prefixe());
		System.out.println("*** a2 : "+a2.infixe());
		System.out.println("*** a2 : "+a2.postfixe());
		System.out.println("*** a2 : "+a2.largeur());
		System.out.println(a3+"*** a3 : "+a3.prefixe());
		System.out.println("*** a3 : "+a3.infixe());
		System.out.println("*** a3 : "+a3.postfixe());
		System.out.println("*** a3 : "+a3.largeur());
		System.out.println("############# ON IMPRIME EN PLUS L'EQUILIBRAGE");
		BinarbreDC.printBalance=true;
		System.out.println("EQUILIBRAGE arbre 1 noeud :\n"+new BinarbreDC<Integer>(1).equilibre());
		System.out.println("EQUILIBRAGE arbre 2 noeud :\n"+new BinarbreDC<Integer>(1).addVal(2).equilibre());
		System.out.println("EQUILIBRAGE de :\n"+a1+"=>\n"+a1.equilibre()+"(hauteur : "+a1.height()+")");
		System.out.println("EQUILIBRAGE de :\n"+a2+"=>\n"+a2.equilibre()+"(hauteur : "+a2.height()+")");
		System.out.println("EQUILIBRAGE de :\n"+a3.addAll(13,14,15,16,17,18,19)+"=>\n"+a3.equilibre()+"(hauteur : "+a3.height()+")");
		System.out.println("#############");
		System.out.println("a3 :\n"+a3+"** - 3 :"); a3 = a3.cutVal(3); System.out.println(a3+"(hauteur : "+a3.height()+")"); 
		System.out.println("** - 9 :"); a3 = a3.cutVal(9); System.out.println(a3+"(hauteur : "+a3.height()+")"); 
		System.out.println("** - 14 :"); a3 = a3.cutVal(14); System.out.println(a3+"(hauteur : "+a3.height()+")"); 
		System.out.println("** - 16 :"); a3 = a3.cutVal(16); System.out.println(a3+"(hauteur : "+a3.height()+")"); 
		System.out.println("** - 12 :"); a3 = a3.cutVal(12); System.out.println(a3+"(hauteur : "+a3.height()+")"); 
		System.out.println("EQUILIBRAGE :\n"+a3.equilibre()+"(hauteur : "+a3.height()+")");
		/*

1        
+-+      
  2      
  +-+    
    3    
    +-+  
      4  
      +-+
        5
*** a1 : 4 descendants ; hauteur : 4 contient 3 et 9 ? truefalse
5
|
4
|
3
|
2
|
1
*** a2 : 4 descendants ; hauteur : 4 contient 3 et 9 ? truefalse
10      
+-----+ 
5     12
+-+   | 
4 8   11
| +-+   
2   9   
*** a3 : 7 descendants ; hauteur : 3 contient 3 et 9 ? falsetrue
*** generation 0 dans a3 :
( 10      
+-----+ 
5     12
+-+   | 
4 8   11
| +-+   
2   9   
 )
*** generation 1 dans a3 :
( 5    
+-+  
4 8  
| +-+
2   9
 12
| 
11
 )
*** generation 2 dans a3 :
( 4
|
2
 8  
+-+
  9
 11
 )
*** generation 3 dans a3 :
( 2
 9
 )
*** generation 4 dans a3 :
( )
*** generation 5 dans a3 :
( )
*** generation 6 dans a3 :
( )
1        
+-+      
  2      
  +-+    
    3    
    +-+  
      4  
      +-+
        5
*** a1 : ( 1 2 3 4 5 )
*** a1 : ( 1 2 3 4 5 )
*** a1 : ( 5 4 3 2 1 )
*** a1 : ( 1 2 3 4 5 )
5
|
4
|
3
|
2
|
1
*** a2 : ( 5 4 3 2 1 )
*** a2 : ( 1 2 3 4 5 )
*** a2 : ( 1 2 3 4 5 )
*** a2 : ( 5 4 3 2 1 )
10      
+-----+ 
5     12
+-+   | 
4 8   11
| +-+   
2   9   
*** a3 : ( 10 5 4 2 8 9 12 11 )
*** a3 : ( 2 4 5 8 9 10 11 12 )
*** a3 : ( 2 4 9 8 5 11 12 10 )
*** a3 : ( 10 5 12 4 8 11 2 9 )
############# ON IMPRIME EN PLUS L'EQUILIBRAGE
EQUILIBRAGE arbre 1 noeud :
1(0)

EQUILIBRAGE arbre 2 noeud :
1(1)  
+-+   
  2(0)

EQUILIBRAGE de :
1(4)        
+-+         
  2(3)      
  +-+       
    3(2)    
    +-+     
      4(1)  
      +-+   
        5(0)
=>
3(0)         
+------+     
1(1)   4(1)  
+-+    +-+   
  2(0)   5(0)
(hauteur : 2)
EQUILIBRAGE de :
5(-4)
|    
4(-3)
|    
3(-2)
|    
2(-1)
|   
1(0)
=>
3(0)         
+------+     
1(1)   4(1)  
+-+    +-+   
  2(0)   5(0)
(hauteur : 2)
EQUILIBRAGE de :
10(5)                               
+------------+                      
5(0)         12(6)                  
+-----+      +-----+                
4(-1) 8(1)   11(0) 13(6)            
|    +-+          +-+              
2(0)   9(0)         14(5)          
                     +-+            
                       15(4)        
                       +-+          
                         16(3)      
                         +-+        
                           17(2)    
                           +-+      
                             18(1)  
                             +-+    
                               19(0)
=>
12(0)                                       
+--------------------+                      
8(0)                 16(0)                  
+---------+          +-----------+          
4(0)      10(0)      14(0)       18(0)      
+----+    +----+     +-----+     +-----+    
2(0) 5(0) 9(0) 11(0) 13(0) 15(0) 17(0) 19(0)
(hauteur : 3)
#############
a3 :
12(0)                                       
+--------------------+                      
8(0)                 16(0)                  
+---------+          +-----------+          
4(0)      10(0)      14(0)       18(0)      
+----+    +----+     +-----+     +-----+    
2(0) 5(0) 9(0) 11(0) 13(0) 15(0) 17(0) 19(0)
** - 3 :
12(0)                                       
+--------------------+                      
8(0)                 16(0)                  
+---------+          +-----------+          
4(0)      10(0)      14(0)       18(0)      
+----+    +----+     +-----+     +-----+    
2(0) 5(0) 9(0) 11(0) 13(0) 15(0) 17(0) 19(0)
(hauteur : 3)
** - 9 :
12(0)                                    
+-----------------+                      
8(0)              16(0)                  
+---------+       +-----------+          
4(0)      10(1)   14(0)       18(0)      
+----+    +-+     +-----+     +-----+    
2(0) 5(0)   11(0) 13(0) 15(0) 17(0) 19(0)
(hauteur : 3)
** - 14 :
12(0)                                
+-----------------+                  
8(0)              16(0)              
+---------+       +-------+          
4(0)      10(1)   13(1)   18(0)      
+----+    +-+     +-+     +-----+    
2(0) 5(0)   11(0)   15(0) 17(0) 19(0)
(hauteur : 3)
** - 16 :
12(0)                              
+-----------------+                
8(0)              15(1)            
+---------+       +-----+          
4(0)      10(1)   13(0) 18(0)      
+----+    +-+           +-----+    
2(0) 5(0)   11(0)       17(0) 19(0)
(hauteur : 3)
** - 12 :
11(0)                            
+---------------+                
8(-1)           15(1)            
+---------+     +-----+          
4(0)      10(0) 13(0) 18(0)      
+----+                +-----+    
2(0) 5(0)             17(0) 19(0)
(hauteur : 3)
EQUILIBRAGE :
11(0)                         
+--------------+              
5(0)           17(0)          
+------+       +-------+      
2(1)   8(1)    13(1)   18(1)  
+-+    +-+     +-+     +-+    
  4(0)   10(0)   15(0)   19(0)
(hauteur : 3)

		 */
	}

}
