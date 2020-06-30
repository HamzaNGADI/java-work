package listes;

/**
 * Liste d'éléments accessibles par indices.
 * 
 * @param <E>
 *            type des éléments de la liste
 */
public interface Liste<E> {

	/**
	 * Renvoie vrai si la liste est vide.
	 * 
	 * @return vrai ou faux selon que la liste est vide ou non
	 */
	boolean isEmpty();

	/**
	 * Renvoie le nombre d'éléments dans la liste.
	 * 
	 * @return taille de la liste
	 */
	int size();

	/**
	 * Vide la liste.
	 * 
	 * @return référence du receveur (this)
	 */
	Liste<E> clear();

	/**
	 * Renvoie l'élément à l'indice i.
	 * 
	 * @param i
	 *            indice de l'élément recherché
	 * @return élément stocké à l'indice i
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	E get(int i) throws HorsBornes;

	/**
	 * Remplace un élément existant par un autre.
	 * 
	 * @param i
	 *            indice de l'élément à remplacer
	 * @param e
	 *            nouvel élément à l'indice i
	 * @return ancien élément à l'indice i
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	E set(int i, E e) throws HorsBornes;

	/**
	 * Renvoie l'indice de la première occurrence d'un élément ou -1.
	 * 
	 * @param o
	 *            élément recherché
	 * @return indice de o ou -1
	 */
	int contains(Object o);

	/**
	 * Indique si tous les éléments fournis apparaissent dans la liste.
	 * 
	 * @param l
	 *            liste des éléments à rechercher
	 * @return vrai si tous les éléments de l apparaissent dans la liste, faux sinon
	 */
	boolean containsAll(Liste<?> l);

	/**
	 * Test d'égalité.
	 * 
	 * Deux listes sont identiques si elles ont exactement les mêmes éléments dans
	 * le même ordre.
	 * 
	 * @param o
	 *            objet à tester
	 * @return vrai si o est identique à this, faux sinon
	 */
	boolean equals(Object o);

	/**
	 * Ajoute un élément en fin de liste.
	 * 
	 * @param e
	 *            élément à rajouter
	 * @return référence du receveur (this)
	 */
	Liste<E> add(E e);

	/**
	 * Ajoute un élément à un certain indice.
	 * 
	 * @param i
	 *            indice d'insertion
	 * @param e
	 *            élément à rajouter
	 * @return référence du receveur (this)
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	Liste<E> add(int i, E e) throws HorsBornes;

	/**
	 * Ajoute tous les éléments fournis en fin de liste.
	 * 
	 * @param l
	 *            liste des éléments à rajouter
	 * @return référence du receveur (this)
	 */
	Liste<E> addAll(Liste<? extends E> l);

	/**
	 * Ajoute tous les éléments fournis à un certain indice.
	 * 
	 * @param i
	 *            indice d'insertion
	 * @param l
	 *            liste des éléments à rajouter
	 * @return référence du receveur (this)
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	Liste<E> addAll(int i, Liste<? extends E> l) throws HorsBornes;

	/**
	 * Supprime l'élément d'un certain indice fourni.
	 * 
	 * @param i
	 *            indice de l'élément à supprimer
	 * @return élément supprimé
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	E remove(int i) throws HorsBornes;

	/**
	 * Renvoie une sous-liste.
	 * 
	 * @param deb
	 *            indice de début de la sous-liste
	 * @param fin
	 *            indice de fin de la sous-liste
	 * @return liste constituée des éléments compris entre deb (inclu) et fin
	 *         (inclu)
	 * @throws HorsBornes
	 *             soulevée si l'indice est erroné
	 */
	Liste<E> subList(int deb, int fin) throws HorsBornes;
}
