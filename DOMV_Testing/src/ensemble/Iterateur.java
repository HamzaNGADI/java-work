package ensemble;

/**
 * Représente un itérateur sur un ensemble de données.
 * 
 * Un itérateur est une sorte de curseur qui se déplace sur un
 * ensemble de données et qui permet de parcourir tous les éléments
 * de l'ensemble. L'ordre de parcours n'est pas déterminé.
 */
public interface Iterateur {

	/**
	 * Indique si l'itérateur peut encore progresser dans son parcours
	 * des éléments de l'ensemble.
	 * @return true s'il existe un prochain élément dans l'ordre de parcours,
	 * false sinon
	 */
	boolean hasNext();

	/**
	 * Revoie le prochain élément dans l'ordre de parcours et déplace
	 * l'itérateur.
	 * @return le prochain élément dans l'ordre de parcours s'il existe,
	 * null sinon
	 */
	Object next();
	
}
