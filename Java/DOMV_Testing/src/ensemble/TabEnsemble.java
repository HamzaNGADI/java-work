package ensemble;

import java.util.Arrays;

/**
 * Représente un ensemble d'objets quelconques grâce à un tableau.
 * 
 * Les éléments de l'ensemble sont stockés dans un tableau réalloué par blocs.
 * Quand le tableau est plein, il est réalloué avec n cases en plus. Ces
 * n cases constituent un bloc.<P>
 * 
 * De même, quand les cases libres du tableau constituent un bloc entier,
 * le tableau est réalloué avec un bloc de cases en moins.
 */
public class TabEnsemble implements Ensemble {

	/**
	 * Représente un itérateur sur un ensemble de type TabEnsemble.
	 * 
	 * L'itérateur est matérialisé par l'indice du prochain élément
	 * à lire dans le tableau.
	 */
	private class TabEnsembleIterateur implements Iterateur {

		/**
		 * Indice du prochain élément à lire dans l'ordre de parcours
		 * de l'itérateur.
		 */
		private int next;

		/**
		 * Initialise un itérateur avec comme premier élément à lire
		 * l'élément d'indice 0.
		 */
		public TabEnsembleIterateur() {
			this.next = 0;
		}

		@Override
		public boolean hasNext() {
			// Le prochain élément à lire n'existe que s'il ne coïncide pas
			// avec le prochain indice d'écriture dans le tableau de
			// l'ensemble associé à l'itérateur (accessible via TabEnsemble.this) :
			return this.next != TabEnsemble.this.prochainIndice;
		}

		@Override
		public Object next() {
			if (!this.hasNext()) return null; // pas de prochain élément
			Object res = TabEnsemble.this.tab[this.next]; // lecture
			this.next++; // mise à jour de l'itérateur
			return res;
		}

	}

	/**
	 * Constante représentant la taille d'un bloc d'allocation. 
	 */
	private static final int TAILLEBLOC = 5;
	
	/**
	 * Tableau stockant les éléments de l'ensemble.
	 */
	private Object[] tab;

	/**
	 * Prochain indice d'écriture dans le tableau tab.
	 */
	private int prochainIndice;
	
	/**
	 * Initialise un ensemble vide en créant un tableau de
	 * la taille d'un bloc.
	 */
	public TabEnsemble() {
		this.tab = new Object[TAILLEBLOC]; // initialement, TAILLEBLOC cases
		this.prochainIndice = 0; // prochaine case à remplir : première case
	}

	@Override
	public void add(Object o) {
		if (o == null) return; // référence null interdite dans un ensemble
		if (this.contains(o)) return; // objet déjà présent
		this.tab[this.prochainIndice++] = o; // écriture au prochain indice (et mise à jour)
		if (this.prochainIndice == this.tab.length) // le tableau est plein
			this.tab = Arrays.copyOf(this.tab, this.tab.length + TAILLEBLOC);
	}

	@Override
	public void remove(Object o) {
		for (int i = 0; i < this.prochainIndice; i++) // recherche de l'objet
			if (this.tab[i].equals(o)) { // on a trouvé
				this.tab[i] = this.tab[this.prochainIndice - 1]; // dernière case libérée
				this.prochainIndice--; // mise à jour du prochain indice d'écriture
				if (this.prochainIndice + TAILLEBLOC < this.tab.length) // trop d'espace libre
					this.tab = Arrays.copyOf(this.tab, this.tab.length - TAILLEBLOC);
			}
	}

	@Override
	public int size() {
		return this.prochainIndice;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < this.prochainIndice; i++) // recherche de l'objet
			if (this.tab[i].equals(o)) return true; // on a trouvé
		return false; // on n'a pas trouvé
	}

	@Override
	public Iterateur getIterateur() {
		return new TabEnsembleIterateur(); // nouvelle instance de la classe encapsulée
	}

	@Override
	public Object min(Comparateur c) {
		if (this.prochainIndice == 0) return null; // ensemble vide
		Object min = this.tab[0]; // premier élément
		for (int i = 1; i < this.prochainIndice; i++) { // parcours du reste
			Integer comparaison = c.compare(min, this.tab[i]);
			if (comparaison == null) return null; // il n'y a pas de minimum
			if (comparaison > 0) min = this.tab[i]; // on a trouvé mieux
		}
		return min;
	}

	@Override
	public Object max(Comparateur c) {
		if (this.prochainIndice == 0) return null; // ensemble vide
		Object max = this.tab[0]; // premier élément
		for (int i = 1; i < this.prochainIndice; i++) { // parcours du reste
			Integer comparaison = c.compare(max, this.tab[i]);
			if (comparaison == null) return null; // il n'y a pas de maximum
			if (comparaison < 0) max = this.tab[i]; // on a trouvé mieux
		}
		return max;
	}

	@Override
	public String toString() {
		String res = "[ ";
		for (int i = 0; i < this.prochainIndice; i++) res += (this.tab[i] + " ");
		return res + "]";
	}

	@Override
	public TabEnsemble clone() {
		TabEnsemble clone = new TabEnsemble();
		clone.tab = this.tab.clone(); // copie du tableau
		clone.prochainIndice = this.prochainIndice; // copie de l'indice
		return clone;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TabEnsemble) || this.size() != ((TabEnsemble) o).size()) 
			return false;
		TabEnsemble e = (TabEnsemble) o;
		for (int i = 0; i < this.prochainIndice; i++) 
			if (!this.tab[i].equals(e.tab[i]))
				return false;
		return true;
	}

	
}
