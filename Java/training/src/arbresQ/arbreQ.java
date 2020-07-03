package arbresQ;

import binarbres.Binarbre;
import binarbres.BinarbreDC;
import listes.Liste;

public interface arbreQ<E> {


	E getVal();

	
	E setVal(E e);

	
	boolean isLeaf();
	
	arbreQ<E> father();


	Liste<? extends arbreQ<E>> children( );
	
	
	arbreQ<E> cutChild(int i);
	BinarbreDC bina();
	Liste<arbreQ<E>> childr();
	arbreQ<E> getChild(int i) ;

	arbreQ<E> addChild(arbreQ<E> a);

	arbreQ<E> addChild(int i, arbreQ<E> a);


}
