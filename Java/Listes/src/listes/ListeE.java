package listes;

/**
 * Liste étendue par de nouvelles fonctions, notamment liées à l'ordre des
 * éléments.
 *
 * @param <F>
 *            type des éléments de la liste contraints à être comparables entre
 *            eux
 */
public interface ListeE<F extends Comparable<F>> extends Liste<F> {

	/**
	 * Inverse l'ordre des éléments de la liste.
	 */
	void reverse();

	/**
	 * Renvoie la liste sous forme de tableau.
	 * 
	 * @return tableau contenant les éléments de la liste dans l'ordre.
	 */
	Object[] toArray();

	/**
	 * Crée et renvoie un nouvel itérateur positionné au début de la liste.
	 * 
	 * @return nouvel itérateur
	 */
	Iterateur<F> iterator();

	/**
	 * Renvoie le plus grand élément de la liste selon la relation d'ordre naturelle
	 * des élément F.
	 * 
	 * @return plus grand élément de la liste
	 * @throws java.util.NoSuchElementException
	 *             soulevée quand la liste est vide
	 */
	F max() throws java.util.NoSuchElementException;

	/**
	 * Renvoie le plus petit élément de la liste selon la relation d'ordre naturelle
	 * des élément F.
	 * 
	 * @return plus petit élément de la liste
	 * @throws java.util.NoSuchElementException
	 *             soulevée quand la liste est vide
	 */
	F min() throws java.util.NoSuchElementException;

	/**
	 * Renvoie une sous-liste d'éléments compris entre deux bornes.
	 * 
	 * @param borneMin
	 *            plus petite valeur incluse
	 * @param borneMax
	 *            plus grande valeur incluse
	 * @return sous-liste contenant les éléments compris entre borneMin et borneMax
	 */
	ListeE<F> subSet(F borneMin, F borneMax);

	/**
	 * Trie les éléments de la liste par ordre croissant.
	 */
	void sort();
}
