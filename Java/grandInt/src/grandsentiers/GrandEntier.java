package grandsentiers;

/**
 * Représente un entier naturel constant.
 * 
 * <p>
 * 
 * Un grand entier est un entier naturel (<i>i.e.</i> positif ou nul) qui n’est pas stocké
 * sur un nombre fini d’octets comme les byte (1 octet : 2<sup>8</sup> = 256 valeurs possibles),
 * les short (2 octets : 2<sup>16</sup> = 67108864 valeurs possibles),
 * les int (4 octets : 2<sup>32</sup> = 4294967296 valeurs possibles) et 
 * les long (8 octets : 2<sup>64</sup> = 18446744073709551616 valeurs possibles).
 * 
 * <p>
 * 
 * Ce type de données permet de manipuler de très grands nombres sans perte de précision.
 * On pourra par exemple expliciter avec un grand entier la valeur factorielle 65.
 * Avec les types de base, on ne pourrait qu’avoir une valeur fausse pour cause de débordement
 * (long par exemple) ou une valeur approchée (double par exemple) :
 * 
 * <ul>
 *  <li>65! = -9223372036854775808 (long : erreur due au dépassement de capacité)</li>
 *  <li>65! = 8.247650592082472E90 (double : approximation)</li>
 *  <li>65! = 8247650592082470666723170306785496252186258551345437492922123134388955774976000000000000000 (valeur exacte représentable par un grand entier)</li>
 * </ul>
 */

public interface GrandEntier extends Comparable<GrandEntier> {
	
	/**
	 * Teste l'égalité de <tt>this</tt> au paramètre fourni. 
	 * @param o objet comparé à <tt>this</tt>
	 * @return vrai si <tt>this</tt> est égal à <tt>o</tt>
	 */
	boolean equals(Object o);

	/**
	 * Indique le nombre chiffres <i>significatifs</i> de <tt>this</tt>. <br>
	 * Exemples :
	 * <ul>
	 *  <li>12 : 2 chiffres significatifs</li>
	 *  <li>0012 : 2 chiffres significatifs</li>
	 *  <li>1 : 1 chiffre significatif</li>
	 *  <li>01 : 1 chiffre significatif</li>
	 *  <li>0 : 1 chiffre significatif</li>
	 *  <li>00 : 1 chiffre significatif</li>
	 * </ul>
	 * @return nombre de chiffres significatifs de <tt>this</tt>
	 */
	int size();

	/**
	 * Renvoie le chiffre de poids <i>i</i> en base 10. <br>
	 * 
	 * Par exemple, le nombre 3108 se décompose en base 10 de la manière suivante : <br>
	 * 3108 = 8&times;10<sup>0</sup> + 0&times;10<sup>1</sup> + 1&times;10<sup>2</sup> + 3&times;10<sup>3</sup> <br>
	 * <ul>
	 *  <li>chiffre de poids 0 : 8</li>
	 *  <li>chiffre de poids 1 : 0</li>
	 *  <li>chiffre de poids 2 : 1</li>
	 *  <li>chiffre de poids 3 : 3</li>
	 *  <li>chiffre de poids supérieur à 3 : 0</li>
	 * </ul>
	 * 
	 * @param i poids du chiffre recherché
	 * @return chiffre de poids <i>i</i>
	 */
	int digit(int i);

	/**
	 * Compare <tt>this</tt> au paramètre fourni. 
	 * @param g objet comparé à <tt>this</tt>
	 * @return -1, 0 ou 1 selon que <tt>this</tt> est inférieur, égal, ou supérieur à <tt>g</tt>
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	int compareTo(GrandEntier g);

	/**
	 * Crée un entier correspondant à la somme de <tt>this</tt> et du paramètre fourni. 
	 * @param g grand entier à additionner à <tt>this</tt>
	 * @return grand entier correspondant à <tt>this</tt>+<tt>g</tt>
	 */
	GrandEntier add(GrandEntier g);

	/**
	 * Crée un entier correspondant au produit de <tt>this</tt> et du paramètre fourni. 
	 * @param g grand entier à multiplier à <tt>this</tt>
	 * @return grand entier correspondant à <tt>this</tt>&times;<tt>g</tt>
	 */
	GrandEntier mult(GrandEntier g);
	
}
