package listes;

/**
 * Un itérateur encapsule une sorte de curseur pointant entre 2 éléments d'une
 * liste.
 * 
 * Le curseur peut donc être avant la tête, après la queue, ou entre 2 éléments
 * successifs.
 * 
 * @param <E>
 *            type des éléments parcourus
 */
public interface Iterateur<E> {

	/**
	 * Indicateur de présence d'élément après le curseur.
	 * 
	 * @return faux si l'itérateur est positionné après le dernier élément, vrai
	 *         sinon
	 */
	boolean hasNext();

	/**
	 * Indicateur de présence d'élément avant le curseur.
	 * 
	 * @return faux si l'itérateur est positionné avant le premier élément, vrai
	 *         sinon
	 */
	boolean hasPrevious();

	/**
	 * Renvoie la valeur de l'élément suivant le curseur, déplace le curseur, et
	 * rend la position de l'élément renvoyé accessible à la modification par set ou
	 * remove.
	 * 
	 * @return élément suivant le curseur
	 * 
	 * @throws java.util.NoSuchElementException
	 *             soulevée si l'itérateur est positionné après le dernier élément
	 */
	E next() throws java.util.NoSuchElementException;

	/**
	 * Renvoie la valeur de l'élément précédent le curseur, déplace le curseur, et
	 * rend la position de l'élément renvoyé accessible à la modification par set ou
	 * remove.
	 * 
	 * @return élément précédent le curseur
	 * 
	 * @throws java.util.NoSuchElementException
	 *             soulevée si l'itérateur est positionné avant le premier élément
	 */
	E previous() throws java.util.NoSuchElementException;

	/**
	 * Supprime le dernier élément renvoyé par next ou previous.
	 * 
	 * ATTENTION : next ou previous doit avoir été invoqué UNE et UNE SEULE fois
	 * juste avant (exception dans le cas contraire).
	 * 
	 * @throws IllegalStateException
	 *             soulevée si aucun élément n'est accessible à la modification
	 */
	void remove() throws IllegalStateException;

	/**
	 * Met à jour le dernier élément renvoyé par next ou previous.
	 * 
	 * ATTENTION : next ou previous doit avoir été invoqué UNE et UNE SEULE fois
	 * juste avant (exception dans le cas contraire).
	 * 
	 * @param e
	 *            nouvelle valeur
	 * 
	 * @throws IllegalStateException
	 *             soulevée si aucun élément n'est accessible à la modification
	 */
	void set(E e) throws IllegalStateException;
}
