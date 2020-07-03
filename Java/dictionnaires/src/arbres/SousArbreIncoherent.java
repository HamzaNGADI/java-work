package arbres;

/**
 * Exception soulevée quand on tente d'ajouter aux enfants d'un arbre un arbre
 * mis en oeuvre différemment.
 */
public class SousArbreIncoherent extends RuntimeException {

	private static final long serialVersionUID = -5954739789782488386L;

	public SousArbreIncoherent() {
		this("Impossible d'ajouter un arbre implémenté différemment des sous-arbres existants");
	}

	public SousArbreIncoherent(String arg0) {
		super(arg0);
	}

	public SousArbreIncoherent(Throwable arg0) {
		super(arg0);
	}

	public SousArbreIncoherent(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
