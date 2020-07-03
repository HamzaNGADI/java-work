package listes;

/**
 * Implémentation de liste par double-chaînage.
 *
 * @param <E> type des éléments de la liste
 */
public class ListeDC<E> implements Liste<E> {

	/**
	 * Chaînons constitutifs de la chaîne.
	 */
	protected class Chainon {
		public E valeur;
		public Chainon suivant;
		public Chainon precedent;

		public Chainon(E valeur) {
			this.valeur = valeur;
			this.suivant = null;
			this.precedent = null;
		}
	}

	/**
	 * Référence du chaînon de tête.
	 */
	protected Chainon tete;
	
	/**
	 * Référence du chaînon de queue.
	 */
	protected Chainon queue;
	
	/**
	 * Référence du dernier chaînon adressé.
	 */
	protected Chainon courant;
	
	/**
	 * Indice du chaînon courant ou -1 s'il n'existe pas.
	 */
	protected int indiceCourant;
	
	/**
	 * Taille de la liste.
	 */
	protected int taille;

	public ListeDC() {
		tete    = null;
		queue   = null;
		courant = null;
		indiceCourant = -1;
		taille = 0;
	}

	@SafeVarargs
	public ListeDC(E... init) {
		this();
		for (E e : init) this.add(e);
	}

	@Override
	public boolean isEmpty() {
		return taille == 0;
	}

	@Override
	public int size() {
		return taille;
	}

	@Override
	public Liste<E> clear() {
		tete = queue = courant = null;
		indiceCourant = -1;
		taille = 0;
		return this;
	}

	@Override
	public E get(int i) throws HorsBornes {
		if (i < 0 || i >= taille) throw new HorsBornes();
		positionne(i);
		return courant.valeur;
	}

	@Override
	public E set(int i, E e) throws HorsBornes {
		if (i < 0 || i >= taille) throw new HorsBornes();
		positionne(i);
		E res = courant.valeur;
		courant.valeur = e;
		return res;
	}

	@Override
	public int contains(Object o) {
		int i; Chainon curseur;
		for (curseur = tete, i = 0; curseur != null; curseur = curseur.suivant, i++)
			if (curseur.valeur.equals(o)) return i;
		return -1;
	}

	@Override
	public boolean containsAll(Liste<?> l) {
		for (int k = 0; k < l.size(); k++)
			if (contains(l.get(k)) == -1) return false;
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o instanceof ListeDC<?>) {
			ListeDC<?> l = (ListeDC<?>) o;
			if (l.taille != this.taille) return false; 
			ListeDC<?>.Chainon cl; Chainon ct; // variables de parcours
			for (ct = this.tete, cl = l.tete; ct != null; ct = ct.suivant, cl = cl.suivant)
			if (!ct.valeur.equals(cl.valeur)) return false;
			return true;
		}
		if (o instanceof Liste<?>) {
			Liste<?> l = (Liste<?>) o;
			if (l.size() != this.taille) return false; 
			int il; Chainon ct; // variables de parcours
			for (ct = this.tete, il = 0; ct != null; ct = ct.suivant, il++)
			if (!ct.valeur.equals(l.get(il))) return false;
			return true;
		}
		return false;
	}

	@Override
	public Liste<E> add(E e) {
		return add(taille, e);
	}

	@Override
	public Liste<E> add(int i, E e) throws HorsBornes {
		if (i < 0 || i > taille) throw new HorsBornes();
		Chainon nouveauChainon = new Chainon(e);
		if (i == taille) { // on ajoute à la fin (comprend le cas liste vide !)
			nouveauChainon.precedent = queue;
			queue = nouveauChainon;
			indiceCourant = taille;
		} else {
			positionne(i);
			nouveauChainon.suivant = courant;
			nouveauChainon.precedent = courant.precedent;
			nouveauChainon.suivant.precedent = nouveauChainon;
		}
		if (i == 0) // on ajoute au début (comprend le cas liste vide !)
			tete = nouveauChainon;
		else
			nouveauChainon.precedent.suivant = nouveauChainon;
		courant = nouveauChainon;
		taille++;
		return this;
	}

	@Override
	public Liste<E> addAll(Liste<? extends E> l) {
		return addAll(taille, l);
	}

	@Override
	public Liste<E> addAll(int i, Liste<? extends E> l) throws HorsBornes {
		for (int k = 0; k < l.size(); k++, i++) add(i, l.get(k));
		return this;
	}

	@Override
	public E remove(int i) throws HorsBornes {
		if (i < 0 || i >= taille) throw new HorsBornes();
		positionne(i);
		E res = courant.valeur;
		if (courant.precedent != null)
			courant.precedent.suivant = courant.suivant;
		else // on est en train de supprimer la tete :
			tete = tete.suivant;
		if (courant.suivant != null) {
			courant.suivant.precedent = courant.precedent;
			courant = courant.suivant;
		} else { // on est en train de supprimer la queue :
			courant = courant.precedent;
			indiceCourant--;
			queue = courant;
		}
		taille--;
		return res;
	}

	@Override
	public Liste<E> subList(int deb, int fin) throws HorsBornes {
		if (deb < 0 || deb >= taille || fin < 0 || fin >= taille)
			throw new HorsBornes();
		ListeDC<E> res = new ListeDC<E>();
		int i; Chainon curseur; // variables de parcours
		for (curseur = tete, i = 0; i < deb; curseur = curseur.suivant, i++) {}
		for (; i <= fin; curseur = curseur.suivant, i++) res.add(curseur.valeur);
		return res;
	}

	@Override
	public String toString() {
		String res = "( ";
		for (Chainon curseur = tete; curseur != null; curseur = curseur.suivant)
			res += curseur.valeur + " ";
		return res + ")";
	}

	/* ---------------------------------------------------------------------------------------- */

	protected void positionne(int i) {
		if (i < indiceCourant) // on se positionne depuis tete ou courant
			if (i < indiceCourant - i) // on se positionne depuis tete
				for (courant = tete, indiceCourant = 0; indiceCourant < i; indiceCourant++)
					courant = courant.suivant;
			else // on se positionne depuis courant en décrémentant
				for (; indiceCourant > i; indiceCourant--)
					courant = courant.precedent;
		else // on se positionne depuis queue ou courant
			if (i - indiceCourant < taille - 1 - i) // on se positionne courant en incrémentant
				for (; indiceCourant < i; indiceCourant++)
					courant = courant.suivant;
			else // on se positionne depuis queue
				for (courant = queue, indiceCourant = taille - 1; indiceCourant > i; indiceCourant--)
					courant = courant.precedent;
	}

	/* ---------------------------------------------------------------------------------------- */
	
	public static void main(String[] args) {
		ListeDC<Integer> l = new ListeDC<Integer>(4, -8, 55);
		System.out.println(l.add(7).add(-12));
		System.out.println(l.add(0, 17).add(0, 18).add(3, 3));
		System.out.println(l.addAll(new ListeDC<Integer>(1, 2, 3)));
		System.out.println(l.addAll(0, new ListeDC<Integer>(-1, -2, -3)));
		System.out.println(l.addAll(5, new ListeDC<Integer>(8, 88)));
		System.out.println(l.addAll(5, new ListeDC<Integer>()));
		System.out.println("3  : " + l.contains(3));
		System.out.println("-3 : " + l.contains(-3));
		System.out.println("16 : " + l.contains(16));
		ListeDC<Integer> l2 = new ListeDC<Integer>(55, 18, 18, 2);
		ListeDC<Integer> l3 = new ListeDC<Integer>(55, -17, 18, 2);
		ListeDC<Integer> l4 = new ListeDC<Integer>(55, 18, 18, 2);
		System.out.println(l2 + " ? " + l.containsAll(l2));
		System.out.println(l3 + " ? " + l.containsAll(l3));
		System.out.println(l2 + " == " + l4 + " ? " + l2.equals(l4));
		System.out.println(l3 + " == " + l4 + " ? " + l3.equals(l4));
		System.out.println(l4 + " remove(2) : " + l4.remove(2) + " -> " + l4);
		System.out.println(l4 + " remove(2) : " + l4.remove(2) + " -> " + l4);
		System.out.println(l4 + " remove(0) : " + l4.remove(0) + " -> " + l4);
		System.out.println(l4 + " remove(0) : " + l4.remove(0) + " -> " + l4);
		System.out.println(l + " 1->5 : " + l.subList(1, 5));
		System.out.println(l + " 5->1 : " + l.subList(5, 1));
		System.out.println(l + " clear : " + l.clear() + " clear : " + l.clear());
		/*
			( 4 -8 55 7 -12 )
			( 18 17 4 3 -8 55 7 -12 )
			( 18 17 4 3 -8 55 7 -12 1 2 3 )
			( -1 -2 -3 18 17 4 3 -8 55 7 -12 1 2 3 )
			( -1 -2 -3 18 17 8 88 4 3 -8 55 7 -12 1 2 3 )
			( -1 -2 -3 18 17 8 88 4 3 -8 55 7 -12 1 2 3 )
			3  : 8
			-3 : 2
			16 : -1
			( 55 18 18 2 ) ? true
			( 55 -17 18 2 ) ? false
			( 55 18 18 2 ) == ( 55 18 18 2 ) ? true
			( 55 -17 18 2 ) == ( 55 18 18 2 ) ? false
			( 55 18 18 2 ) remove(2) : 18 -> ( 55 18 2 )
			( 55 18 2 ) remove(2) : 2 -> ( 55 18 )
			( 55 18 ) remove(0) : 55 -> ( 18 )
			( 18 ) remove(0) : 18 -> ( )
			( -1 -2 -3 18 17 8 88 4 3 -8 55 7 -12 1 2 3 ) 1->5 : ( -2 -3 18 17 8 )
			( -1 -2 -3 18 17 8 88 4 3 -8 55 7 -12 1 2 3 ) 5->1 : ( )
			( -1 -2 -3 18 17 8 88 4 3 -8 55 7 -12 1 2 3 ) clear : ( ) clear : ( )
		 */
	}

}
