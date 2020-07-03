package arbres;

import listes.*;

/**
 * Arbre quelconque.
 *
 * @param <E>
 *            type des éléments stockés dans l'arbre
 */
public interface Arbre<E> {

	/**
	 * Renvoie la valeur de la racine de l'arbre.
	 * 
	 * @return valeur stockée dans la racine
	 */
	E getVal();

	/**
	 * Met à jour la valeur de la racine de l'arbre et renvoie la valeur précédente.
	 * 
	 * @param e
	 *            nouvelle valeur de la racine
	 * @return ancienne valeur de la racine
	 */
	E setVal(E e);

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
	 * Renvoie le père de l'arbre s'il existe, ou null sinon.
	 * 
	 * @return référence de père ou null
	 */
	Arbre<E> father();

	/**
	 * Renvoie le plus lointain ancêtre de l'arbre s'il existe, ou null sinon.
	 * 
	 * @return référence du plus lointain ancêtre ou null
	 */
	Arbre<E> ancestor();

	/**
	 * Renvoie la liste des enfants.
	 * 
	 * @return liste des enfants
	 */
	Liste<? extends Arbre<E>> children();

	/**
	 * Renvoie la liste des aieux.
	 * 
	 * @return liste des aieux
	 */
	Liste<? extends Arbre<E>> ancestors();

	/**
	 * Renvoie la liste des frères.
	 * 
	 * @return liste des frères
	 */
	Liste<? extends Arbre<E>> brothers();

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
	Liste<? extends Arbre<E>> gen(int n);

	/**
	 * Indique si un arbre est un sous-arbre du receveur.
	 * 
	 * @param a
	 *            arbre à tester
	 * @return vrai si this est un descendant de a, faux sinon
	 */
	boolean contains(Arbre<E> a);

	/**
	 * Supprime tous les enfants et renvoie la liste des orphelins.
	 * 
	 * @return liste des anciens enfants
	 */
	Liste<? extends Arbre<E>> cutAll();

	/**
	 * Supprime un enfant donné et le renvoie en résultat.
	 * 
	 * @param i
	 *            indice de l'enfant à supprimer
	 * @return ancien enfant d'indice i
	 * @throws IndexOutOfBoundsException
	 *             soulevée quand i est invalide
	 */
	Arbre<E> cutChild(int i) throws IndexOutOfBoundsException;

	/**
	 * Renvoie la référence d'un certain enfant.
	 * 
	 * @param i
	 *            indice de l'enfant à récupérer
	 * @return enfant d'indice i
	 * @throws IndexOutOfBoundsException
	 *             soulevée quand i est invalide
	 */
	Arbre<E> getChild(int i) throws IndexOutOfBoundsException;

	/**
	 * Ajoute un enfant.
	 * 
	 * @param a
	 *            enfant à rajouter
	 * @return reférence du receveur (this)
	 * @throws NullPointerException
	 *             soulevée quand a vaut null
	 * @throws ArbreCyclique
	 *             soulevée quand a contient this
	 * @throws GreffeDeSousArbre
	 *             soulevée quand a a déjà un père
	 * @throws SousArbreIncoherent
	 *             soulevée quand a implémenté différemment de this
	 */
	Arbre<E> addChild(Arbre<E> a) throws NullPointerException, ArbreCyclique, GreffeDeSousArbre, SousArbreIncoherent;

	/**
	 * Ajoute un enfant à une certaine position.
	 * 
	 * @param i
	 *            indice d'insertion de a
	 * @param a
	 *            enfant à rajouter
	 * @return reférence du receveur (this)
	 * @throws NullPointerException
	 *             soulevée quand a vaut null
	 * @throws IndexOutOfBoundsException
	 *             soulevée quand i est invalide
	 * @throws ArbreCyclique
	 *             soulevée quand a contient this
	 * @throws GreffeDeSousArbre
	 *             soulevée quand a a déjà un père
	 * @throws SousArbreIncoherent
	 *             soulevée quand a implémenté différemment de this
	 */
	Arbre<E> addChild(int i, Arbre<E> a) throws NullPointerException, IndexOutOfBoundsException, ArbreCyclique,
			GreffeDeSousArbre, SousArbreIncoherent;

	/**
	 * Renvoie la liste des éléments stockés dans l'arbre dans l'ordre correspondant
	 * à son parcours préfixe.
	 * 
	 * @return liste des éléments contenus dans l'arbre
	 */
	Liste<E> prefixe();

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
}
