package arbres;

import listes.*;

/**
 * Un arbre ArbreDC<E> a une racine et d'éventuels fils stockés dand une liste.
 * Chaque fils a la référence de son père (double chainage).
 * 
 * <b> Attention </b> : un arbre peut être un sous-arbre d'un autre arbre,
 * auquel cas il a un (unique) père. Il ne faut donc pas confondre la racine de
 * l'arbre et racine du plus grand arbre dont il est le sous-arbre
 * 
 * @param <E>
 *            type des éléments stockés dans l'arbre
 */
public class ArbreDC<E> implements Arbre<E> {

	/**
	 * Référence du père s'il existe ou null.
	 */
	protected ArbreDC<E> father;

	/**
	 * Liste éventuellement vide d'enfants.
	 */
	protected Liste<ArbreDC<E>> children;

	/**
	 * Valeur encapsulée.
	 */
	protected E value;

	/**
	 * Constructeur d'arbre réduit à une feuille.
	 * 
	 * @param e
	 *            valeur à encapsuler dans la racine
	 */
	public ArbreDC(E e) {
		father = null;
		children = new ListeDC<ArbreDC<E>>();
		value = e;
	}
	
	/* ----------------------------------------------------------------- */
	/* Fonctions utilitaires pour la représentation littérale des arbres */ 
	/* ----------------------------------------------------------------- */

	private static String seqChar(char c, int i) {
		String res = "";
		for (; i > 0; i--) res += c;
		return res;
	}

	private static <F> Liste<String> niveaux(Arbre<F> a) {
		String etiq = a.getVal().toString();
		if (a.isLeaf()) return new ListeDC<String>(etiq);
		Liste<String> res = niveaux(a.children().get(0));
		String traits = (a.children().size() == 1 ? "|" : "+");
		int largeurTrait = res.get(0).length();
		for (int i = 1; i < a.children().size(); i++) {
			int largeur1 = res.get(0).length();
			Liste<String> afusionner = niveaux(a.children().get(i));
			int largeur2 = afusionner.get(0).length();
			traits += seqChar('-', largeurTrait) + "+";
			for (int k = 0; k < res.size() && k < afusionner.size(); k++)
				res.set(k, res.get(k) + " " + afusionner.get(k));
			for (int k = res.size(); k < afusionner.size(); k++)
				res.add(k, seqChar(' ', largeur1 + 1) + afusionner.get(k));
			for (int k = afusionner.size(); k < res.size(); k++)
				res.set(k, res.get(k) + seqChar(' ', largeur2 + 1));
			largeurTrait = largeur2;
		}
		traits += seqChar(' ', res.get(res.size() - 1).length() - traits.length());
		res.add(0, traits).add(0, etiq + seqChar(' ', res.get(res.size() - 1).length() - etiq.length()));
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
	public E setVal(E e) {
		E bak = value;
		value = e;
		return bak;
	}

	@Override
	public boolean isLeaf() {
		return children.size() == 0;
	}

	@Override
	public int familySize() {
		int res = children.size();
		for (int i = 0; i < children.size(); i++)
			res += children.get(i).familySize();
		return res;
	}

	@Override
	public int height() {
		int max = 0;
		for (int i = 0; i < children.size(); i++) {
			int candidat = children.get(i).height() + 1;
			if (candidat > max) max = candidat;
		}
		return max;
	}

	@Override
	public Arbre<E> father() {
		return father;
	}

	@Override
	public Arbre<E> ancestor() {
		if (father == null) return null;
		ArbreDC<E> aieul = father;
		while (aieul.father != null) aieul = aieul.father;
		return aieul;
	}

	@Override
	public Liste<? extends Arbre<E>> children() {
		return children;
	}

	@Override
	public Liste<? extends Arbre<E>> ancestors() { 
		Liste<Arbre<E>> res = new ListeDC<Arbre<E>>();
		for (ArbreDC<E> aieul = father; aieul != null; aieul = aieul.father)
			res.add(0, aieul);
		return res;
	}

	@Override
	public Liste<? extends Arbre<E>> brothers() {
		Liste<Arbre<E>> res = new ListeDC<Arbre<E>>();
		if (father != null)
			for (int i = 0; i < father.children.size(); i++) {
				Arbre<E> candidat = father.children.get(i);
				if (this != candidat) res.add(candidat);
			}
		return res;
	}

	@Override
	public Liste<? extends Arbre<E>> gen(int n) {
		Liste<Arbre<E>> res = new ListeDC<Arbre<E>>();
		if (n <  0) return res;
		if (n == 0) return res.add(this);
		if (n == 1) return res.addAll(children);
		for (int i = 0; i < children.size(); i++)
			res.addAll(children.get(i).gen(n - 1));
		return res;
	}

	@Override
	public boolean contains(Arbre<E> a) { 
		if (a == this) return true;
		for (int i = 0; i < children.size(); i++)
			if (children.get(i).contains(a)) return true;
		return false;
	}

	@Override
	public Liste<? extends Arbre<E>> cutAll() {
		Liste<Arbre<E>> res = new ListeDC<Arbre<E>>();
		while (children.size() != 0) res.add(cutChild(0));
		return res;
	}

	@Override
	public Arbre<E> cutChild(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= children.size())
			throw new IndexOutOfBoundsException();
		ArbreDC<E> res = children.remove(i);
		res.father = null;
		return res;
	}

	@Override
	public ArbreDC<E> getChild(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= children.size())
			throw new IndexOutOfBoundsException();
		return children.get(i);
	}

	@Override
	public Arbre<E> addChild(Arbre<E> a)
			throws NullPointerException, ArbreCyclique, GreffeDeSousArbre, SousArbreIncoherent {
		return addChild(children.size(), a);
	}

	@Override
	public Arbre<E> addChild(int i, Arbre<E> a) throws NullPointerException, IndexOutOfBoundsException, ArbreCyclique,
			GreffeDeSousArbre, SousArbreIncoherent {
		if (a == null)
			throw new NullPointerException();
		if (i < 0 || i > children.size())
			throw new IndexOutOfBoundsException();
		ArbreDC<E> al;
		try {
			al = (ArbreDC<E>) a;
		} catch (ClassCastException e) {
			throw new SousArbreIncoherent();
		}
		if (al.father != null)
			throw new GreffeDeSousArbre();
		if (al.contains(this))
			throw new ArbreCyclique();
		al.father = this;
		children.add(i, al);
		return this;
	}

	Liste<E> prefixeITER() {
		Liste<E> res = new ListeDC<E>();
		Liste<Arbre<E>> pile = new ListeDC<>(this);
		while (!pile.isEmpty()) {
			Arbre<E> a = pile.remove(0);
			res.add(a.getVal());
			pile.addAll(0, a.children());
		}
		return res;
	}

	Liste<E> prefixeREC() {
		Liste<E> res = new ListeDC<E>();
		res.add(this.getVal());
		for (int i = 0; i < children.size(); i++)
			res.addAll(children.get(i).prefixeREC());
		return res;
	}

	@Override
	public Liste<E> prefixe() {
		// return this.prefixeREC();
		return this.prefixeITER();
	}
	
	Liste<E> postfixeITER() { 
		Liste<E> res = new ListeDC<E>();
		Liste<Arbre<E>> pile = new ListeDC<>(this);
		while (!pile.isEmpty()) {
			Arbre<E> a = pile.remove(0);
			if (a.isLeaf()) res.add(a.getVal());
			else {
				pile.add(0, new ArbreDC<E>(a.getVal()));
				pile.addAll(0, a.children());
			}
		}
		return res;
	}

	Liste<E> postfixeREC() {
		Liste<E> res = new ListeDC<E>();
		for (int i = 0; i < children.size(); i++)
			res.addAll(children.get(i).postfixeREC());
		res.add(this.getVal());
		return res;
	}

	@Override
	public Liste<E> postfixe() {
		// return this.postfixeREC();
		return this.postfixeITER();
	}

	@Override
	public Liste<E> largeur() { 
		Liste<E> res = new ListeDC<E>();
		Liste<Arbre<E>> file = new ListeDC<>(this);
		while (!file.isEmpty()) {
			Arbre<E> a = file.remove(0);
			res.add(a.getVal());
			file.addAll(a.children());
		}
		return res;
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) throws Exception {
		Arbre<Integer> a7 = new ArbreDC<Integer>(7);
		Arbre<Integer> a8 = new ArbreDC<Integer>(8);
		Arbre<Integer> a10 = new ArbreDC<Integer>(10);
		Arbre<Integer> a11 = new ArbreDC<Integer>(11);
		Arbre<Integer> a12 = new ArbreDC<Integer>(12);
		Arbre<Integer> a5 = new ArbreDC<Integer>(5);
		Arbre<Integer> a2 = new ArbreDC<Integer>(2);
		Arbre<Integer> a3 = new ArbreDC<Integer>(3);
		Arbre<Integer> a4 = new ArbreDC<Integer>(4);
		Arbre<Integer> a9 = new ArbreDC<Integer>(9);
		a9.addChild(a10).addChild(a11).addChild(a12);
		a4.addChild(a8).addChild(a9);
		a3.addChild(a7);
		a2.addChild(a3).addChild(a4).addChild(a5);
		System.out.println(a2);
		System.out.println("Valeur de la racine 8 : " + a8.getVal());
		System.out.println("Mise à jour 8->120565 (ancienne valeur : "
				+ a8.setVal(120565) + ")");
		System.out.println("Mise à jour 7->99 (ancienne valeur : "
				+ a7.setVal(99) + ")");
		System.out.println("Arbre à jour :\n" + a2);
		System.out.println("Nombre de descendants de 2 : " + a2.familySize()
				+ " ; hauteur de 2 : " + a2.height());
		System.out.println("Nombre de descendants de 4 : " + a4.familySize()
				+ " ; hauteur de 4 : " + a4.height());
		System.out.println("Nombre de descendants de 12 : " + a12.familySize()
				+ " ; hauteur de 12 : " + a12.height());
		System.out.println("Père de 9 : \n" + a9.father());
		System.out.println("Père de 2 : \n" + a2.father());

		Arbre<Integer> a39 = new ArbreDC<Integer>(39);
		Arbre<Integer> a41 = new ArbreDC<Integer>(41);
		a39.addChild(a41);
		System.out.println("Arbre 2 :\n" + a2);
		System.out.println("Arbre 9 :\n" + a9);
		System.out.println("Arbre 39 :\n" + a39);
		a3.addChild(a39);
		System.out.println("Arbre 2 :\n" + a2);
		System.out.println("Père de 39 :\n"+ a39.father());
		System.out.println("*** Changement de place pour 9 ***");
		a7.addChild(a4.cutChild(1));
		System.out.println("Arbre 2 :\n" + a2);
		System.out.println("*** Changement de place pour 3 ***");
		a4.addChild(1, a2.cutChild(0));
		System.out.println("Arbre 2 :\n" + a2);

		System.out.println("#### ARBRE DE REFERENCE :\n" + a2);
		System.out.println("#### Ancêtres de 9 :\n" + a9.ancestors());
		System.out.println("#### Premier ancêtre de 9 :\n" + a9.ancestor());
		System.out.println("#### FRERES DE 11 :\n" + a11.brothers());
		System.out.println("#### FRERES DE 9 :\n" + a9.brothers());
		System.out.println("#### FRERES DE 39 :\n" + a39.brothers());
		System.out.println("#### FRERES DE 3 :\n" + a3.brothers());
		System.out.println("#### FRERES DE 2 :\n" + a2.brothers());

		System.out.println("#### ARBRE DE REFERENCE :\n" + a2);
		System.out.println("#### Génération 0 :\n" + a2.gen(0));
		System.out.println("#### Génération 1 :\n" + a2.gen(1));
		System.out.println("#### Génération 2 :\n" + a2.gen(2));
		System.out.println("#### Génération 3 :\n" + a2.gen(3));
		System.out.println("#### Génération 4 :\n" + a2.gen(4));

		System.out.println("#### ARBRE DE REFERENCE :\n" + a2);
		System.out.println("Parcours préfixe de l'arbre 2     : " + a2.prefixe());
		System.out.println("Parcours postfixe de l'arbre 2    : " + a2.postfixe());
		System.out.println("Parcours par niveaux de l'arbre 2 : " + a2.largeur());

		System.out.println("**** valeur du père de 3  : "+ a3.father().getVal());
		System.out.println("**** valeur du père de 4  : "+ a4.father().getVal());
		System.out.println("**** valeur du père de 39 : "+ a39.father().getVal());
		System.out.println("**** valeur du père de 5  : "+ a5.father().getVal());
		System.out.println("*** Découpage des enfants de 2 ***");
		a2.cutAll();
		System.out.println("Arbre 2 :\n" + a2);
		System.out.println("**** père de 3  : " + a3.father());
		System.out.println("**** père de 4  : " + a4.father());
		System.out.println("**** père de 39 : " + a39.father());
		System.out.println("**** père de 5  : " + a5.father());
		/*
2             
+-+----------+
3 4          5
| +-+         
7 8 9         
    +--+--+   
    10 11 12  

Valeur de la racine 8 : 8
Mise à jour 8->120565 (ancienne valeur : 8)
Mise à jour 7->99 (ancienne valeur : 7)
Arbre à jour :
2                   
+--+---------------+
3  4               5
|  +------+         
99 120565 9         
          +--+--+   
          10 11 12  

Nombre de descendants de 2 : 9 ; hauteur de 2 : 3
Nombre de descendants de 4 : 5 ; hauteur de 4 : 2
Nombre de descendants de 12 : 0 ; hauteur de 12 : 0
Père de 9 : 
4              
+------+       
120565 9       
       +--+--+ 
       10 11 12

Père de 2 : 
null
Arbre 2 :
2                   
+--+---------------+
3  4               5
|  +------+         
99 120565 9         
          +--+--+   
          10 11 12  

Arbre 9 :
9       
+--+--+ 
10 11 12

Arbre 39 :
39
| 
41

Arbre 2 :
2                      
+-----+---------------+
3     4               5
+--+  +------+         
99 39 120565 9         
   |         +--+--+   
   41        10 11 12  

Père de 39 :
3    
+--+ 
99 39
   | 
   41

*** Changement de place pour 9 ***
Arbre 2 :
2                   
+-----------+------+
3           4      5
+--------+  |       
99       39 120565  
|        |          
9        41         
+--+--+             
10 11 12            

*** Changement de place pour 3 ***
Arbre 2 :
2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     

#### ARBRE DE REFERENCE :
2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     

#### Ancêtres de 9 :
( 2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     
 4                 
+------+          
120565 3          
       +--------+ 
       99       39
       |        | 
       9        41
       +--+--+    
       10 11 12   
 3          
+--------+ 
99       39
|        | 
9        41
+--+--+    
10 11 12   
 99      
|       
9       
+--+--+ 
10 11 12
 )
#### Premier ancêtre de 9 :
2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     

#### FRERES DE 11 :
( 10
 12
 )
#### FRERES DE 9 :
( )
#### FRERES DE 39 :
( 99      
|       
9       
+--+--+ 
10 11 12
 )
#### FRERES DE 3 :
( 120565
 )
#### FRERES DE 2 :
( )
#### ARBRE DE REFERENCE :
2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     

#### Génération 0 :
( 2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     
 )
#### Génération 1 :
( 4                 
+------+          
120565 3          
       +--------+ 
       99       39
       |        | 
       9        41
       +--+--+    
       10 11 12   
 5
 )
#### Génération 2 :
( 120565
 3          
+--------+ 
99       39
|        | 
9        41
+--+--+    
10 11 12   
 )
#### Génération 3 :
( 99      
|       
9       
+--+--+ 
10 11 12
 39
| 
41
 )
#### Génération 4 :
( 9       
+--+--+ 
10 11 12
 41
 )
#### ARBRE DE REFERENCE :
2                   
+------------------+
4                  5
+------+            
120565 3            
       +--------+   
       99       39  
       |        |   
       9        41  
       +--+--+      
       10 11 12     

Parcours préfixe de l'arbre 2     : ( 2 4 120565 3 99 9 10 11 12 39 41 5 )
Parcours postfixe de l'arbre 2    : ( 120565 10 11 12 9 99 41 39 3 4 5 2 )
Parcours par niveaux de l'arbre 2 : ( 2 4 5 120565 3 99 39 9 41 10 11 12 )
**** valeur du père de 3  : 4
**** valeur du père de 4  : 2
**** valeur du père de 39 : 3
**** valeur du père de 5  : 2
*** Découpage des enfants de 2 ***
Arbre 2 :
2

**** père de 3  : 4                 
+------+          
120565 3          
       +--------+ 
       99       39
       |        | 
       9        41
       +--+--+    
       10 11 12   

**** père de 4  : null
**** père de 39 : 3          
+--------+ 
99       39
|        | 
9        41
+--+--+    
10 11 12   

**** père de 5  : null

		*/
	}
}
