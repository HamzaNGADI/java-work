package listes;

/**
 * Implémentation de liste étendue par double-chaînage.
 *
 * @param <E> type des éléments de la liste
 */
public class ListeDCE<E extends Comparable<E>> extends ListeDC<E> implements ListeE<E> {

	/**
	 * Mise en oeuvre de l'itérateur de liste.
	 */
	private class IterateurDCE implements Iterateur<E> {
		@SuppressWarnings("unused") // à supprimer...
		private ListeDC<E>.Chainon next;
		private ListeDC<E>.Chainon elementModifiable;

		IterateurDCE() {
			next=ListeDCE.this.tete;
	        elementModifiable = null;
		}

		@Override
		public boolean hasNext() {
	        return next != null;
		}

		@Override
		public boolean hasPrevious() {
	        if(next==null) return ListeDCE.this.queue != null;
			return next.precedent!=null; 
		}

		@Override
		public E next() throws java.util.NoSuchElementException {
	        if(hasNext())
	        {
	        	elementModifiable = next;
	        	next=next.suivant;
	        }
	        else throw new java.util.NoSuchElementException();
          return elementModifiable.valeur;
		}

		@Override
		public E previous() throws java.util.NoSuchElementException {
	        if(hasPrevious())
	        {
	        	 if(next==null) next=ListeDCE.this.queue;
	        	 else
	 	        	next=next.precedent;
    	        elementModifiable = next;
	        	 
	        }
	        else throw new java.util.NoSuchElementException();
          return elementModifiable.valeur;

		}

		@Override
		public void remove() throws IllegalStateException {
	        if(elementModifiable == null)
	           throw new IllegalStateException();
	    
	        if(elementModifiable.precedent != null)
	        {
	        	elementModifiable.precedent.suivant=elementModifiable.suivant;
	        }
	        else{
	           ListeDCE.this.tete=elementModifiable.suivant;
	    
	        }
	        if(elementModifiable.suivant != null)
	        {

	        	elementModifiable.suivant.precedent=elementModifiable.precedent;
	        }
	        else{
	        	 ListeDCE.this.queue=elementModifiable.precedent;
	        }
	        if(elementModifiable==next)
	        {
	        	next=next.suivant;
	        }
	        elementModifiable = null;
			
			
            // -----------------------------------------------------------------------------
			// Dernières lignes de code fournies concernant taille, courant et indiceCourant
            // -----------------------------------------------------------------------------
			ListeDCE.this.taille--;
			ListeDCE.this.courant = ListeDCE.this.tete;
			ListeDCE.this.indiceCourant = (ListeDCE.this.taille == 0 ? -1 : 0);
		}

		@Override
		public void set(E e) throws IllegalStateException {
			if (elementModifiable == null)
				throw new IllegalStateException();
			elementModifiable.valeur = e;
			elementModifiable = null;
		}
	}

	public ListeDCE() {
		super();
	}

	@SuppressWarnings("unchecked")
	public ListeDCE(E... init) {
		super(init);
	}
	
	@Override
	public void reverse() {
        if(size()==0 || size()==1) return;
        Chainon te =  tete;
        Chainon qu = queue;
        int i=0;
        while(i<size()/2)
        {
        	E val = te.valeur;
        	te.valeur=qu.valeur;
        	qu.valeur=val;
        	
        	qu=qu.precedent;
        	te=te.suivant;
        	i++;
        }
	}

	@Override
	public Object[] toArray() {
		
//		if(size()==0) return null;

	   Object oe[]=new Object[taille];
       int i=0;
        for(Chainon te =  tete; te != null; te=te.suivant)
        {
        	oe[i]=te.valeur;
        	i++;
        }

		return oe; 
	}

	@Override
	public Iterateur<E> iterator() {
		return new IterateurDCE();
	}

	@Override
	public E max() throws java.util.NoSuchElementException {
        
		if(size()==0) return null;

		E emax=tete.valeur;
		 for(Chainon t =  tete; t != null; t=t.suivant)
	     {
			 if(emax.compareTo(t.valeur)==1) emax = t.valeur;
	     }
		
		return emax; 
	}

	@Override
	public E min() throws java.util.NoSuchElementException {
        
		if(size()==0) return null;

		E emin=tete.valeur;
		 for(Chainon t =  tete; t != null; t=t.suivant)
	     {
			 if(emin.compareTo(t.valeur)==-1) emin = t.valeur;
	     }
		
		return emin; 
	}

	@Override
	public ListeE<E> subSet(E borneMin, E borneMax) {
		if(size()==0) return null;

		ListeE<E> l = (ListeE<E>) new ListeDCE();
		for(Chainon t =  tete; t != null; t=t.suivant)
	     {
			 if(t.valeur.compareTo(borneMin)==1 && t.valeur.compareTo(borneMax)==-1) l.add(t.valeur);
	     }
		
		return l; 
	}

	@Override
	public void sort() {
        
		if(size()==0) return;
        Chainon te =  tete;
        Chainon tee = te.suivant;
        while(te != null)
        {
        	tee = te.suivant;
        	while(tee != null)
        	{
        		if(te.valeur.compareTo(tee.valeur)==1)
        		{

                	E val = te.valeur;
                	te.valeur=tee.valeur;
                	tee.valeur=val;
        		}
        		tee=tee.suivant;
        	}
        	te=te.suivant;
        }
	}

	/* ---------------------------------------------------------------------------------------- */

	public static void main(String[] args) {
		ListeDCE<Integer> l1 = new ListeDCE<Integer>();
		System.out.print(l1 + " renversée : ");
		l1.reverse();
		System.out.println(l1);
		l1 = new ListeDCE<Integer>(1);
		System.out.print(l1 + " renversée : ");
		l1.reverse();
		System.out.println(l1);
		l1 = new ListeDCE<Integer>(1, -2, 17, 0, 15);
		System.out.print(l1 + " renversée : ");
		l1.reverse();
		System.out.print(l1);
		l1.reverse();
		System.out.println(" re-renversée : " + l1);

		ListeDCE<Integer> l2 = new ListeDCE<Integer>();
		System.out.print(l2 + " en tableau : ");
		for (Object o : l2.toArray())
			System.out.print("|" + o);
		System.out.println("|");
		l2 = new ListeDCE<Integer>(-10, -20, -30);
		System.out.print(l2 + " en tableau : ");
		for (Object o : l2.toArray())
			System.out.print("|" + o);
		System.out.println("|");
		
		System.out.println("------------------------------------------------------------------");
		
		l2 = new ListeDCE<Integer>(-10, 5, 7, 0, 0, -2, 18, 53, 32, -41, -30);
		Iterateur<Integer> it1 = l2.iterator();
		Iterateur<Integer> it2 = l2.iterator();
		while (it1.hasNext())
			it1.next();
		System.out.println("Parcours de : " + l2 + " :");
		for (int i = 0; i < l2.size(); i++) {
			it1.hasPrevious();
			it2.hasNext();
			System.out.println(l2.get(i) + " " + it1.previous() + " " + it2.next());
		}
		it1 = l2.iterator();
		System.out.println("Suppression des nombres négatifs et incrément des nombres pairs dans " + l2 + " :");
		while (it1.hasNext()) {
			Integer val = it1.next();
			if (val < 0)
				it1.remove();
			else if (val % 2 == 0)
				it1.set(val + 1);
		}
		System.out.println("-> " + l2 + " (tete : " + l2.tete.valeur + " ; queue : " + l2.queue.valeur + ")");

		l2 = new ListeDCE<Integer>(-10, 5, 7, 0, 0, -2, 18, 53, 32, -41, -30);
		System.out.println("maximum / minimum dans " + l2 + " : " + l2.max() + " / " + l2.min());
		System.out.println("sous-ensemble compris entre -30 et 7 dans " + l2 + " : " + l2.subSet(-30, 7));

		System.out.print(l2 + " triée : ");
		l2.sort();
		System.out.println(l2);
		/*
			( ) renversée : ( )
			( 1 ) renversée : ( 1 )
			( 1 -2 17 0 15 ) renversée : ( 15 0 17 -2 1 ) re-renversée : ( 1 -2 17 0 15 )
			( ) en tableau : |
			( -10 -20 -30 ) en tableau : |-10|-20|-30|
			Parcours de : ( -10 5 7 0 0 -2 18 53 32 -41 -30 ) :
			-10 -30 -10
			5 -41 5
			7 32 7
			0 53 0
			0 18 0
			-2 -2 -2
			18 0 18
			53 0 53
			32 7 32
			-41 5 -41
			-30 -10 -30
			Suppression des nombres négatifs et incrément des nombres pairs dans ( -10 5 7 0 0 -2 18 53 32 -41 -30 ) :
			-> ( 5 7 1 1 19 53 33 ) (tete : 5 ; queue : 33)
			maximum / minimum dans ( -10 5 7 0 0 -2 18 53 32 -41 -30 ) : 53 / -41
			sous-ensemble compris entre -30 et 7 dans ( -10 5 7 0 0 -2 18 53 32 -41 -30 ) : ( -10 5 0 0 -2 -30 )
			( -10 5 7 0 0 -2 18 53 32 -41 -30 ) triée : ( -41 -30 -10 -2 0 0 5 7 18 32 53 )
		 */
	}

}
