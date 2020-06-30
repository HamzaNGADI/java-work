package ensemble;

/**
 * Représente une relation d'ordre sur un sous-ensemble d'objets de même type.
 */
public interface Comparateur {
	
	/**
	 * Renvoie une valeur négative, nulle ou positive, selon que le premier
	 * paramètre est inférieur, égal ou supérieur au deuxième conformément
	 * à la relation d'ordre représentée.
	 * Si les objets fournis ne sont pas comparables entre eux, alors le
	 * résultat est la référence null.
	 * @param o1 premier paramètre du test
	 * @param o2 deuxième paramètre du test
	 * @return valeur négative si o1<o2, 0 si o1=o2, valeur positive si o1>o2,
	 * ou null si o1 et o2 ne sont pas comparables
	 */
	Integer compare(Object o1, Object o2);

}
