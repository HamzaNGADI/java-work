package tests;

import grandsentiers.*;

public class Essais {

	public static void main(String[] args) {
		GrandEntier g1 = new ListeGrandEntier(65);
		GrandEntier g2 = new ListeGrandEntier(359);
		
		System.out.println(g1 + " < " + g2 + " : " + (g1.compareTo(g2) < 0) );
		
		System.out.println(g1 + " + " + g2 + " : " + g1.add(g2));
		
		System.out.println(g1 + " * " + g2 + " : " + g1.mult(g2));
		
		System.out.println("6! = "+ListeGrandEntier.factorielle(new ListeGrandEntier(6))); 
		System.out.println(g1+"! = "+ListeGrandEntier.factorielle(g1)); 
		System.out.println(g2+"! = "+ListeGrandEntier.factorielle(g2)); 
	}

}

/*

65 < 359 : true
65 + 359 : 424
65 * 359 : 23335
6! = 720
65! = 8247650592082470666723170306785496252186258551345437492922123134388955774976000000000000000
359! = 11064352561441140772794417601568662317289223904622621392176691022616403474410507993666065774108504124895458559825258433120836936236567571619835401947188041548701080995557939212341482156821940982666459301266757246250772639797528280096713223878619714684787792445634526211406792369392666417716474496596819756812428700694092811830933701024255326152927731377895253879698442084536887778629079695296410229137687188214131541276680673787068159346889477926631302932488189062907430182810944001416919364902319625701993041224445437051774191160099083346045860382903886867783454005991186233875107785036748960508729146793628325128352789004309283598186650249621832386898155481784044278970843136000000000000000000000000000000000000000000000000000000000000000000000000000000000000000

*/
