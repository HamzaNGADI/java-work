package arbres;

/**
 * Exception soulevée quand on tente d'ajouter aux enfants d'un arbre un arbre
 * figurant parmi les enfants d'un autre arbre.
 */
public class GreffeDeSousArbre extends RuntimeException {

	private static final long serialVersionUID = -5954731789782488386L;

	public GreffeDeSousArbre() {
		this("Impossible d'ajouter un arbre qui a déjà un père");
	}

	public GreffeDeSousArbre(String arg0) {
		super(arg0);
	}

	public GreffeDeSousArbre(Throwable arg0) {
		super(arg0);
	}

	public GreffeDeSousArbre(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
