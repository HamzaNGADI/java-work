package ensemble;

/**
 * Représente un ensemble d'objets quelconques.
 * 
 * Les objets ne sont présents dans un ensemble qu'en une seule occurrence.
 * Les doublons d'objets sont donc interdits.
 * La détection de doublons d'objets est basée sur la méthode equals.
 * 
 * La référence null est interdite dans un ensemble.
 */
public interface Ensemble extends Cloneable {
	
	/**
	 * Ajoute un objet à l'ensemble, seulement si la référence fournie
	 * n'est pas null et si l'objet n'est pas déjà présent (selon equals).
	 * @param o référence de l'objet à ajouter dans l'ensemble
	 */
	void add(Object o);

	/**
	 * Retire un objet de l'ensemble, seulement si la référence fournie
	 * désigne un objet effectivement présent (selon equals).
	 * @param o référence de l'objet à retirer de l'ensemble
	 */
	void remove(Object o);

	/**
	 * Indique le nombre d'éléments dans l'ensemble.
	 * @return cardinal de l'ensemble
	 */
	int size();

	/**
	 * Indique si un objet est présent (selon equals) dans l'ensemble.
	 * @param o référence de l'objet recherché
	 * @return vrai ou faux selon que l'objet est présent ou non
	 */
	boolean contains(Object o);

	/**
	 * Indique si deux ensembles sont égaux. 
	 * @param o référence de l'objet à comparer
	 * @return vrai ou faux selon que les deux objets sont identiques
	 */
	boolean equals(Object o);
	
	/**
	 * Renvoie un itérateur positionné sur l'ensemble.
	 * @return nouvel itérateur
	 */
	Iterateur getIterateur();

	/**
	 * Renvoie la plus petite valeur de l'ensemble conformément à
	 * une relation d'ordre définie par un comparateur.
	 * Tous les éléments de l'ensemble doivent être comparables selon
	 * le comparateur fourni, faute de quoi le résultat est null.
	 * @param c comparateur à utiliser pour déterminer le min
	 * @return plus petite valeur selon c si elle existe, null sinon
	 */
	Object min(Comparateur c);

	/**
	 * Renvoie la plus grande valeur de l'ensemble conformément à
	 * une relation d'ordre définie par un comparateur.
	 * Tous les éléments de l'ensemble doivent être comparables selon
	 * le comparateur fourni, faute de quoi le résultat est null.
	 * @param c comparateur à utiliser pour déterminer le max
	 * @return plus grande valeur selon c si elle existe, null sinon
	 */
	Object max(Comparateur c);

	/**
	 * Renvoie une copie en profondeur de l'ensemble.
	 * Les modifications apportées sur le clone ne doivent pas avoir
	 * d'incidence sur l'ensemble d'origine.
	 * Cette version de clone est compatible avec la méthode clone
	 * de la classe Object. Elle est covariante (son type de retour
	 * est plus précis que le type de retour d'origine, i.e. Object)
	 * et elle est moins contrainte (elle n'a pas de clause throws et
	 * elle est publique).
	 * @return copie de l'ensemble
	 */
	Ensemble clone();

	/**
	 * Fonction utilitaire d'ensemble qui représente l'union.
	 * @param e1 première opérande de l'union
	 * @param e2 deuxième opérande de l'union
	 * @return ensemble contenant tous les éléments de e1 et tous
	 * les éléments de e2
	 */
	/*static Ensemble union(Ensemble e1, Ensemble e2) {
		Ensemble resultat = e1.clone(); // copie de e1
		// parcours de e2 :
		for (Iterateur ite2 = e2.getIterateur(); ite2.hasNext();)
			resultat.add(ite2.next()); // ajout de tous les éléments de e2
		return resultat;
	}*/

	/**
	 * Fonction utilitaire d'ensemble qui représente l'intersection.
	 * @param e1 première opérande de l'intersection
	 * @param e2 deuxième opérande de l'intersection
	 * @return ensemble contenant tous les éléments présents à la fois
	 * dans e1 et dans e2
	 */
/*	static Ensemble inter(Ensemble e1, Ensemble e2) {
		Ensemble resultat = e1.clone(); // copie de e1
		// parcours de e1 :
		for (Iterateur ite1 = e1.getIterateur(); ite1.hasNext();) {
			Object o1 = ite1.next(); // objet de e1 à tester
			// objet non présent dans e2 à enlever du résultat :
			if (!e2.contains(o1)) resultat.remove(o1);
		}
		return resultat;
	}*/

	/**
	 * Fonction utilitaire d'ensemble qui représente la différence.
	 * @param e1 première opérande de la différence
	 * @param e2 deuxième opérande de la différence
	 * @return ensemble contenant tous les éléments présents
	 * dans e1 mais pas dans e2
	 */
/*	static Ensemble diff(Ensemble e1, Ensemble e2) {
		Ensemble resultat = e1.clone(); // copie de e1
		// parcours de e1 :
		for (Iterateur ite1 = e1.getIterateur(); ite1.hasNext();) {
			Object o1 = ite1.next(); // objet de e1 à tester
			// objet présent dans e2 à enlever du résultat :
			if (e2.contains(o1)) resultat.remove(o1);
		}
		return resultat;
	}*/

}
