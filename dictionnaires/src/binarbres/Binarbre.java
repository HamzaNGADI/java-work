package binarbres;

import listes.Liste;

/**
 * Arbre binaire de recherche.
 * 
 * Un arbre Binarbre<E> est un arbre binaire de recherche, i.e. dont les noeuds
 * ont 0, 1 ou 2 fils désignés par "fils gauche" et "fils droit", dont les
 * valeurs sont ordonnables grâce à une relation d'ordre totale, et dont tous
 * les noeuds respectent les propriétés fondamentales suivantes :
 * 
 * - toutes les valeurs du fils gauche sont strictement inférieures à la valeur
 * du noeud
 * 
 * - toutes les valeurs du fils droit sont strictement supérieures à la valeur
 * du noeud
 *
 * @param <E>
 *            type des éléments stockés dans l'arbre
 */
public interface Binarbre<E extends Comparable<E>> {

	/**
	 * Renvoie la valeur de la racine de l'arbre.
	 * 
	 * @return valeur stockée dans la racine
	 */
	E getVal();

	/**
	 * Indique si l'arbre est réduit à une feuille (i.e. s'il n'a pas d'enfants).
	 * 
	 * @return vrai si l'arbre est une feuille (faux sinon)
	 */
	boolean isLeaf();

	/**
	 * Renvoie le nombre de descendants depuis la racine (racine exclue).
	 * 
	 * @return nombre de descendants de l'arbre
	 */
	int familySize();

	/**
	 * Renvoie la hauteur de l'arbre (i.e. longueur du plus grand chemin qui mène de
	 * la racine à une feuille).
	 * 
	 * @return hauteur de l'arbre
	 */
	int height();

	/**
	 * Renvoie le facteur d'équilibrage, i.e. la différence entre la hauteur de la
	 * branche droite et celle de la branche gauche.
	 * 
	 * @return facteur d'équilibrage
	 */
	int balance();

	/**
	 * Renvoie le père de l'arbre s'il existe, ou null sinon.
	 * 
	 * @return référence de père ou null
	 */
	Binarbre<E> father();

	/**
	 * Renvoie le plus lointain ancêtre de l'arbre s'il existe, ou null sinon.
	 * 
	 * @return référence du plus lointain ancêtre ou null
	 */
	Binarbre<E> ancestor();

	/**
	 * Renvoie le fils gauche s'il existe, ou null sinon.
	 * 
	 * @return fils gauche ou null
	 */
	Binarbre<E> getLeft();

	/**
	 * Renvoie le fils droit s'il existe, ou null sinon.
	 * 
	 * @return fils droit ou null
	 */
	Binarbre<E> getRight();

	/**
	 * Renvoie le frère s'il existe, ou null sinon.
	 * 
	 * @return frère ou null
	 */
	Binarbre<E> brother();

	/**
	 * Renvoie la liste des aieux.
	 * 
	 * @return liste des aieux
	 */
	Liste<? extends Binarbre<E>> ancestors();

	/**
	 * Renvoie la liste des déscendants de la nième génération.
	 * 
	 * Par convention, la liste renvoyée est vide quand n est négatif et elle
	 * contient l'arbre lui-même quand n vaut 0. La première génération correspond
	 * aux enfants. La deuxième correspond aux petits enfants, etc. Pour tout n
	 * strictement positif, la nième génération existe. Au pire elle ne contient pas
	 * de membres (i.e. liste vide renvoyée).
	 * 
	 * @param n
	 *            numéro de génération
	 * @return liste des descendants de génération n
	 */
	Liste<? extends Binarbre<E>> gen(int n);

	/**
	 * Indique si une valeur est présente dans l'arbre ou non.
	 * 
	 * @param e
	 *            valeur recherchée
	 * @return vrai si e apparaît dans l'abre, faux sinon
	 */
	boolean containsVal(E e);

	/**
	 * Indique si un ensemble de valeurs est inclus dans l'arbre ou non.
	 * 
	 * @param liste
	 *            liste des valeurs à rechercher
	 * @return vrai si tous les éléments de l apparaissent dans l'arbre
	 */
	boolean containsAll(Liste<? extends E> liste);

	/**
	 * Renvoie la plus grande valeur de l'arbre.
	 * 
	 * @return plus grande valeur de l'arbre
	 */
	E maxVal();

	/**
	 * Renvoie la plus petite valeur de l'arbre.
	 * 
	 * @return plus petite valeur de l'arbre
	 */
	E minVal();

	/**
	 * Ajoute un élément dans l'arbre.
	 * 
	 * @param e
	 *            élément à ajouter
	 * @return nouvelle racine de l'arbre
	 */
	Binarbre<E> addVal(E e);

	/**
	 * Ajoute un ensemble d'éléments dans l'arbre.
	 * 
	 * @param l
	 *            liste des éléments à ajouter
	 * @return nouvelle racine de l'arbre
	 */
	@SuppressWarnings("unchecked")
	Binarbre<E> addAll(E... l);

	/**
	 * Ajoute un ensemble d'éléments dans l'arbre.
	 * 
	 * @param l
	 *            liste des éléments à ajouter
	 * @return nouvelle racine de l'arbre
	 */
	Binarbre<E> addAll(Liste<E> l);

	/**
	 * Supprime un élément de l'arbre.
	 * 
	 * @param e
	 *            élément à supprimer
	 * @return nouvelle racine de l'arbre ou null
	 */
	Binarbre<E> cutVal(E e);

	/**
	 * Supprime un ensemble d'éléments de l'arbre.
	 * 
	 * @param l
	 *            liste des éléments à supprimer
	 * @return nouvelle racine de l'arbre ou null
	 */
	Binarbre<E> cutAll(@SuppressWarnings("unchecked") E... l);

	/**
	 * Supprime un ensemble d'éléments de l'arbre.
	 * 
	 * @param l
	 *            liste des éléments à supprimer
	 * @return nouvelle racine de l'arbre ou null
	 */
	Binarbre<E> cutAll(Liste<E> l);

	/**
	 * Renvoie la liste des éléments stockés dans l'arbre dans l'ordre correspondant
	 * à son parcours préfixe.
	 * 
	 * @return liste des éléments contenus dans l'arbre
	 */
	Liste<E> prefixe();

	/**
	 * Renvoie la liste des éléments stockés dans l'arbre dans l'ordre correspondant
	 * à son parcours infixe.
	 * 
	 * @return liste des éléments contenus dans l'arbre
	 */
	Liste<E> infixe();

	/**
	 * Renvoie la liste des éléments stockés dans l'arbre dans l'ordre correspondant
	 * à son parcours postfixe.
	 * 
	 * @return liste des éléments contenus dans l'arbre
	 */
	Liste<E> postfixe();

	/**
	 * Renvoie la liste des éléments stockés dans l'arbre dans l'ordre correspondant
	 * à son parcours en largeur (i.e. niveau par niveau depuis la racine).
	 * 
	 * @return liste des éléments contenus dans l'arbre
	 */
	Liste<E> largeur();

	/**
	 * Reconfigure l'arbre avec la hauteur minimale.
	 * 
	 * @return nouvelle racine de l'arbre
	 */
	Binarbre<E> equilibre();
}
