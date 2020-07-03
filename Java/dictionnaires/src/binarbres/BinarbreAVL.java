package binarbres;

/**
 * Arbre binaire de recherche AVL.
 * 
 * @param <E>
 *            type des éléments stockés dans l'arbre
 */
public class BinarbreAVL<E extends Comparable<E>> extends BinarbreDC<E> {

	/**
	 * Constructeur d'arbre binaire de recherche AVL réduit à une feuille.
	 * 
	 * @param e
	 *            valeur à encapsuler dans la racine
	 */
	public BinarbreAVL(E e) {
		super(e);
	}

	/* ----------------------------------------------------------------- */
	/* Rotations                                                         */
	/* ----------------------------------------------------------------- */

	/**
	 * Effectue une rotation gauche sur la racine.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	protected BinarbreDC<E> rotationG() {
		BinarbreDC<E> ancienD = right;
		if (right != null) {
			right = right.left;
			if (right != null) right.father = this;
			ancienD.father = father;
			if (father != null)
				if (father.left == this)
					 father.left = ancienD;
				else father.right = ancienD;
			ancienD.left = this;
			father = ancienD;
			updateHeight();
			
			// ATTENTION : l'update peut être bloqué par le nouveau père
			// dont la hauteur actuelle est peut-être correcte par hasard :
			if (father.father != null) father.father.updateHeight();
			
			return father;
		}
		return this;
	}

	/**
	 * Effectue une rotation droite sur la racine.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	protected BinarbreDC<E> rotationD() {
		BinarbreDC<E> ancienG = left;
		if (left != null) {
			left = left.right;
			if (left != null) left.father = this;
			ancienG.father = father;
			if (father != null)
				if (father.left == this)
					 father.left = ancienG;
				else father.right = ancienG;
			ancienG.right = this;
			father = ancienG;
			updateHeight();

			// ATTENTION : l'update peut être bloqué par le nouveau père
			// dont la hauteur actuelle est peut-être correcte par hasard :
			if (father.father != null) father.father.updateHeight();

			return father;
		}
		return this;
	}

	/**
	 * Effectue une double rotation gauche sur la racine.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	protected BinarbreDC<E> doubleRotationG() {
		if (right != null && right.left != null) {
			((BinarbreAVL<?>) right).rotationD();
			return rotationG();
		}
		return this;
	}

	/**
	 * Effectue une double rotation droite sur la racine.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	protected BinarbreDC<E> doubleRotationD() {
		if (left != null && left.right != null) {
			((BinarbreAVL<?>) left).rotationG();
			return rotationD();
		}
		return this;
	}

	/* ----------------------------------------------------------------- */
	/* Traitement des cas de rééquilibrage                               */
	/* ----------------------------------------------------------------- */
 
	/**
	 * Corrige une éventuelle situation de déséquilibre correspondant à un facteur
	 * d'équilibrage égal à 2 ou -2.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	private BinarbreDC<E> reequilibre() {
		BinarbreDC<E> res = this;
		int balance = balance();
		if (balance == -2) // sous-arbre gauche trop long
			if  (   left.balance() <=   0) res = rotationD();
			else /* left.balance() == 1 */ res = doubleRotationD();
		if (balance == 2) // sous-arbre gauche trop long
			if  (   right.balance() >= 0)  res = rotationG();
			else /* right.balance()==-1 */ res = doubleRotationG();
		return res;
	}

	/* ----------------------------------------------------------------- */
	/* Redéfinitions                                                     */
	/* ----------------------------------------------------------------- */
 
	@Override
	protected BinarbreDC<E> buildLeaf(E e) {
		return new BinarbreAVL<E>(e);
	}

	@Override
	public Binarbre<E> addVal(E e) {
		Binarbre<E> res = super.addVal(e);
		// On rééquilibre au besoin :
		return ((BinarbreAVL<E>) res).reequilibre();
	}

	@Override
	public Binarbre<E> cutVal(E e) {
		Binarbre<E> res = super.cutVal(e);
		if (res == null) return null;
		// On rééquilibre au besoin :
		return ((BinarbreAVL<E>) res).reequilibre();
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) {
		Binarbre<Integer> av1 = new BinarbreAVL<Integer>(4);
		System.out.println(av1);
		av1 = av1.addVal(3);
		System.out.println(av1);
		av1 = av1.addVal(1);
		System.out.println(av1);
		av1 = av1.addVal(6);
		System.out.println(av1);
		av1 = av1.addVal(7);
		System.out.println(av1);
		av1 = av1.addVal(5);
		System.out.println(av1);
		av1 = av1.addVal(2);
		System.out.println(av1);
		System.out.println("#############");
		Binarbre<Integer> a1 = new BinarbreDC<Integer>(1).addAll(2, 3, 4, 5);
		Binarbre<Integer> a2 = new BinarbreDC<Integer>(5).addAll(4, 3, 2, 1);
		Binarbre<Integer> a3 = new BinarbreDC<Integer>(10).addAll(5, 15, 12, 8, 9, 4, 2, 10, 5, 12, 10, 16, 14, 11);
		av1 = new BinarbreAVL<Integer>(1).addAll(2, 3, 4, 5);
		Binarbre<Integer> av2 = new BinarbreAVL<Integer>(5).addAll(4, 3, 2, 1);
		Binarbre<Integer> av3 = new BinarbreAVL<Integer>(10).addAll(5, 15, 12, 8, 9, 4, 2, 10, 5, 12, 10, 16, 14, 11);
		System.out.println("***** Bin / BinAVL :\n" + a1 + "***\n" + av1);
		System.out.println("***** Bin / BinAVL :\n" + a2 + "***\n" + av2);
		System.out.println("***** Bin / BinAVL :\n" + a3 + "***\n" + av3);
		a3.addAll(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
		av3 = av3.addAll(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
		System.out.println("############# ON IMPRIME EN PLUS L'EQUILIBRAGE");
		BinarbreDC.printBalance = true;
		System.out.println("***** Bin / BinAVL :\n" + a3 + "***\n" + av3);
		System.out.println("############# RETOUR AFFICHAGE NORMAL");
		BinarbreDC.printBalance = false;
		av3 = av3.cutVal(53);
		System.out.println("***** Retrait de 53 : \n" + av3);
		av3 = av3.cutVal(2);
		System.out.println("***** Retrait de 2 : \n" + av3);
		av3 = av3.cutVal(9);
		System.out.println("***** Retrait de 9 : \n" + av3);
		av3 = av3.cutVal(10);
		System.out.println("***** Retrait de 10 : \n" + av3);
		av3 = av3.cutVal(5);
		System.out.println("***** Retrait de 5 : \n" + av3);
		av3 = av3.cutVal(11);
		System.out.println("***** Retrait de 11 : \n" + av3);
		av3 = av3.cutVal(18);
		System.out.println("***** Retrait de 18 : \n" + av3);
		av3 = av3.cutVal(16);
		System.out.println("***** Retrait de 16 : \n" + av3);
		av3 = av3.cutAll(19, 20, 21);
		System.out.println("***** Retrait de 19, 20, 21 : \n" + av3);
		av3 = av3.cutAll(22, 24);
		System.out.println("***** Retrait de 22, 24 : \n" + av3);
		av3 = av3.cutAll(12, 17);
		System.out.println("***** Retrait de 12, 17 : \n" + av3);
		av3 = av3.cutAll(8, 4, 13);
		System.out.println("***** Retrait de 8, 4, 13 : \n" + av3);
		av3 = av3.cutVal(15);
		System.out.println("***** Retrait de 15 : \n" + av3);
		av3 = av3.cutVal(14);
		System.out.println("***** Retrait de 14 : \n" + av3);
		av3 = av3.cutVal(23);
		System.out.println("***** Retrait de 23 : \n" + av3);
		/*
4

4
|
3

3  
+-+
1 4

3    
+-+  
1 4  
  +-+
    6

3    
+-+  
1 6  
  +-+
  4 7

4    
+-+  
3 6  
| +-+
1 5 7

4      
+---+  
2   6  
+-+ +-+
1 3 5 7

#############
***** Bin / BinAVL :
1        
+-+      
  2      
  +-+    
    3    
    +-+  
      4  
      +-+
        5
***
2    
+-+  
1 4  
  +-+
  3 5

***** Bin / BinAVL :
5
|
4
|
3
|
2
|
1
***
4    
+---+
2   5
+-+  
1 3  

***** Bin / BinAVL :
10            
+-----+       
5     15      
+-+   +-----+ 
4 8   12    16
| +-+ +--+    
2   9 11 14   
***
10            
+-----+       
8     15      
+---+ +-----+ 
4   9 12    16
+-+   +--+    
2 5   11 14   

############# ON IMPRIME EN PLUS L'EQUILIBRAGE
***** Bin / BinAVL :
10(7)                                          
+------------+                                 
5(0)         15(6)                             
+-----+      +------------+                    
4(-1) 8(1)   12(1)        16(8)                
|    +-+    +-----+      +-+                  
2(0)   9(0) 11(0) 14(-1)   17(7)              
                   |       +-+                
                   13(0)     18(6)            
                              +-+              
                                19(5)          
                                +-+            
                                  20(4)        
                                  +-+          
                                    21(3)      
                                    +-+        
                                      22(2)    
                                      +-+      
                                        23(1)  
                                        +-+    
                                          24(0)
***
10(1)                                                     
+--------------+                                          
8(-1)          18(0)                                      
+---------+    +-----------------------+                  
4(0)      9(0) 14(0)                   22(0)              
+----+         +-----------+           +-----------+      
2(0) 5(0)      12(0)       16(0)       20(0)       23(1)  
               +-----+     +-----+     +-----+     +-+    
               11(0) 13(0) 15(0) 17(0) 19(0) 21(0)   24(0)

############# RETOUR AFFICHAGE NORMAL
***** Retrait de 53 : 
10                          
+-----+                     
8     18                    
+---+ +-----------+         
4   9 14          22        
+-+   +-----+     +-----+   
2 5   12    16    20    23  
      +--+  +--+  +--+  +-+ 
      11 13 15 17 19 21   24

***** Retrait de 2 : 
10                          
+-----+                     
8     18                    
+---+ +-----------+         
4   9 14          22        
+-+   +-----+     +-----+   
  5   12    16    20    23  
      +--+  +--+  +--+  +-+ 
      11 13 15 17 19 21   24

***** Retrait de 9 : 
18                        
+---------------+         
10              22        
+---+           +-----+   
5   14          20    23  
+-+ +-----+     +--+  +-+ 
4 8 12    16    19 21   24
    +--+  +--+            
    11 13 15 17           

***** Retrait de 10 : 
18                      
+-------------+         
8             22        
+-+           +-----+   
5 14          20    23  
| +-----+     +--+  +-+ 
4 12    16    19 21   24
  +--+  +--+            
  11 13 15 17           

***** Retrait de 5 : 
18                      
+-------------+         
14            22        
+-------+     +-----+   
8       16    20    23  
+-+     +--+  +--+  +-+ 
4 12    15 17 19 21   24
  +--+                  
  11 13                 

***** Retrait de 11 : 
18                     
+------------+         
14           22        
+------+     +-----+   
8      16    20    23  
+-+    +--+  +--+  +-+ 
4 12   15 17 19 21   24
  +-+                  
    13                 

***** Retrait de 18 : 
17                  
+---------+         
14        22        
+------+  +-----+   
8      16 20    23  
+-+    |  +--+  +-+ 
4 12   15 19 21   24
  +-+               
    13              

***** Retrait de 16 : 
17                
+-------+         
12      22        
+-+     +-----+   
8 14    20    23  
| +--+  +--+  +-+ 
4 13 15 19 21   24

***** Retrait de 19, 20, 21 : 
17           
+-------+    
12      23   
+-+     +--+ 
8 14    22 24
| +--+       
4 13 15      

***** Retrait de 22, 24 : 
12        
+-+       
8 17      
| +-----+ 
4 14    23
  +--+    
  13 15   

***** Retrait de 12, 17 : 
14       
+----+   
8    15  
+-+  +-+ 
4 13   23

***** Retrait de 8, 4, 13 : 
15   
+--+ 
14 23

***** Retrait de 15 : 
14  
+-+ 
  23

***** Retrait de 14 : 
23

***** Retrait de 23 : 
null

		 */
	}

}
