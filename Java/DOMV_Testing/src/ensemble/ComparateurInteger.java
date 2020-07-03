package ensemble;

/**
 * Comparateur d'objets de type Integer.
 * Représente la relation d'ordre sur les entiers relatifs.
 */

public class ComparateurInteger implements Comparateur {
	@Override
	public Integer compare(Object o1, Object o2) {
		if (!(o1 instanceof Integer)) return null;
		if (!(o2 instanceof Integer)) return null;
		return (Integer)o1-(Integer)o2;
	}	
}
