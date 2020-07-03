package dictionnaires;

import listes.Liste;

/**
 * Dictionnaire de mots.
 * 
 * Le terme dictionnaire ne désigne pas ici un ensemble de couples
 * mot/définition, mais un simple ensemble de mots.
 * 
 * La fonctionnalité principale de ce dictionnaire est donc la recherche de
 * mots.
 */
public interface Dictionnaire {

	/**
	 * Rajoute des mots au dictionnaire.
	 * 
	 * @param words
	 *            liste de mots à rajouter
	 * @return receveur mis à jour (this)
	 */
	Dictionnaire addWords(String... words);

	/**
	 * Supprime des mots du dictionnaire.
	 * 
	 * @param words
	 *            liste de mots à supprimer
	 * @return receveur mis à jour (this)
	 */
	Dictionnaire cutWords(String... words);

	/**
	 * Renvoie le nombre de mots du dictionnaire.
	 * 
	 * @return nombre de mots
	 */
	int size();

	/**
	 * Indique si un mot est présent dans le dictionnaire.
	 * 
	 * @param word
	 *            mot à rechercher
	 * @return vrai si le mot est présent
	 */
	boolean exists(String word);

	/**
	 * Renvoie la liste des mots du dictionnaire qui commencent par un certain
	 * préfixe.
	 * 
	 * @param prefix
	 *            préfixe des mots recherchés
	 * @return liste des mots qui commencent par prefix
	 */
	Liste<String> commonPrefix(String prefix);
}
