package main;

//import ensemble.Comparateur;
import ensemble.ComparateurInteger;
import ensemble.Ensemble;
import ensemble.Iterateur;
import ensemble.TabEnsemble;

/**
 * Classe de test.
 * Contient une méthode main et des fonctions de test.
 */
class Programme {
	
	static void affiche(Ensemble e) {
		affiche(e, null);
	}
	
	static void affiche(Ensemble e, Object o) {
		String contient = "";
		if (o!=null) contient = (e.contains(o) ? ", contient " : ", ne contient pas ") + o;
		System.out.println(e.toString() + " " + e.size() + " élément"+(e.size()==0 ? "" : "s"+contient));
	}
	
	static void affiche(Iterateur it) {
		System.out.print("> ");
		while (it.hasNext()) System.out.print(it.next() + " ");
		System.out.println(">");
	}

	static void test1(Ensemble e) {
		affiche(e);
		for (int i=0; i<10; i++) e.add(i);
		for (int i=0; i<10; i++) e.add(i);
		affiche(e);
	}
	
	static void test2(Ensemble e) {
		affiche(e, 9);
		for (int i=0; i<10; i++) e.add(i);
		affiche(e, 9);
		e.remove(9); e.remove(9);
		affiche(e, 9);
		affiche(e, 0);
		e.remove(0); e.remove(0);
		affiche(e, 0);
		affiche(e, 4);
		e.remove(4); e.remove(4);
		affiche(e, 4);
		for (int i=0; i<10; i++) e.remove(i);
		affiche(e, 4);
	}
	
	static void test3(Ensemble e) {
		affiche(e);
		affiche(e.getIterateur());
		for (int i=0; i<10; i++) e.add(i);
		affiche(e);
		affiche(e.getIterateur());
	}
	
	static void test4(Ensemble e) {
		ComparateurInteger c = new ComparateurInteger();
		affiche(e);
		System.out.println("  -> MIN : "+e.min(c));
		System.out.println("  -> MAX : "+e.max(c));
		for (int i=0; i<10; i++) e.add(i);
		affiche(e);
		System.out.println("  -> MIN : "+e.min(c));
		System.out.println("  -> MAX : "+e.max(c));
		e.remove(0); e.remove(9);
		affiche(e);
		System.out.println("  -> MIN : "+e.min(c));
		System.out.println("  -> MAX : "+e.max(c));
		e.add('Z');
		affiche(e);
		System.out.println("  -> MIN : "+e.min(c));
		System.out.println("  -> MAX : "+e.max(c));
	}
	
	static void test5(Ensemble e) {
		Ensemble c = e.clone();
		affiche(e); affiche(c);
		for (int i=0; i<10; i++) e.add(i);
		affiche(e); affiche(c);
		for (int i=0; i<10; i++) c.add(i);
		affiche(e); affiche(c);
		e.remove(1); e.remove(2); e.remove(3); 
		c.remove(7); c.remove(8); c.remove(9); 
		affiche(e); affiche(c);
	}
	
	static void test6(Ensemble e1, Ensemble e2) {
		affiche(e1); affiche(e2);
//		System.out.print("UNION : "); affiche(Ensemble.union(e1, e2));
	//	System.out.print("INTER : "); affiche(Ensemble.inter(e1, e2));
		//System.out.print("DIFF  : "); affiche(Ensemble.diff(e1, e2));
		affiche(e1); affiche(e2);
		
		for (int i=0; i<7; i++) e1.add(i);
		for (int i=3; i<10; i++) e2.add(i);
		
		affiche(e1); affiche(e2);
		//System.out.print("UNION : "); affiche(Ensemble.union(e1, e2));
	//	System.out.print("INTER : "); affiche(Ensemble.inter(e1, e2));
		//System.out.print("DIFF  : "); affiche(Ensemble.diff(e1, e2));
		affiche(e1); affiche(e2);
	}
	
	
	static void test7(Ensemble e1, Ensemble e2) {
		affiche(e1);
		affiche(e2);
		for (int i=0; i<10; i++) e1.add(i);
		for (int i=0; i<10; i++) e2.add(i);
		affiche(e1);
		affiche(e2);
		System.out.print("égalité : " + e1.equals(e2));
	}
	
	public static void main(String[] args) {
		
		System.out.println("------- Tests de constructeurs, toString, add et size -------");
		test1(new TabEnsemble());

		System.out.println("\n------- Tests de remove et contains -------");
		test2(new TabEnsemble());

		System.out.println("\n------- Tests d'itérateurs -------");
		test3(new TabEnsemble());

		System.out.println("\n------- Tests de min et max -------");
		test4(new TabEnsemble());

		System.out.println("\n------- Tests de clone -------");
		test5(new TabEnsemble());

		System.out.println("\n------- Tests de union, inter et diff -------");
		//test6(new TabEnsemble(), new TabEnsemble());
		
		System.out.println("\n------- Tests de equals -------");
		test7(new TabEnsemble(), new TabEnsemble());
	}
	
/*

------- Tests de constructeurs, toString, add et size -------
[ ] 0 élément
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments

------- Tests de remove et contains -------
[ ] 0 élément
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments, contient 9
[ 0 1 2 3 4 5 6 7 8 ] 9 éléments, ne contient pas 9
[ 0 1 2 3 4 5 6 7 8 ] 9 éléments, contient 0
[ 8 1 2 3 4 5 6 7 ] 8 éléments, ne contient pas 0
[ 8 1 2 3 4 5 6 7 ] 8 éléments, contient 4
[ 8 1 2 3 7 5 6 ] 7 éléments, ne contient pas 4
[ ] 0 élément

------- Tests d'itérateurs -------
[ ] 0 élément
> >
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
> 0 1 2 3 4 5 6 7 8 9 >

------- Tests de min et max -------
[ ] 0 élément
  -> MIN : null
  -> MAX : null
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
  -> MIN : 0
  -> MAX : 9
[ 8 1 2 3 4 5 6 7 ] 8 éléments
  -> MIN : 1
  -> MAX : 8
[ 8 1 2 3 4 5 6 7 Z ] 9 éléments
  -> MIN : null
  -> MAX : null

------- Tests de clone -------
[ ] 0 élément
[ ] 0 élément
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
[ ] 0 élément
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
[ 0 9 8 7 4 5 6 ] 7 éléments
[ 0 1 2 3 4 5 6 ] 7 éléments

------- Tests de union, inter et diff -------
[ ] 0 élément
[ ] 0 élément
UNION : [ ] 0 élément
INTER : [ ] 0 élément
DIFF  : [ ] 0 élément
[ ] 0 élément
[ ] 0 élément
[ 0 1 2 3 4 5 6 ] 7 éléments
[ 3 4 5 6 7 8 9 ] 7 éléments
UNION : [ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
INTER : [ 6 5 4 3 ] 4 éléments
DIFF  : [ 0 1 2 ] 3 éléments
[ 0 1 2 3 4 5 6 ] 7 éléments
[ 3 4 5 6 7 8 9 ] 7 éléments

------- Tests de equals -------
[ ] 0 élément
[ ] 0 élément
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
[ 0 1 2 3 4 5 6 7 8 9 ] 10 éléments
égalité : true

*/

}
