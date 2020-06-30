package arbres;

/**
 * Exception soulevée quand on tente d'ajouter aux enfants d'un arbre un de ses
 * ascendants.
 */
public class ArbreCyclique extends RuntimeException {

	private static final long serialVersionUID = -2810872919439596121L;

	public ArbreCyclique() {
		this("Tentative de création d'arbre cyclique");
	}

	public ArbreCyclique(String arg0) {
		super(arg0);
	}

	public ArbreCyclique(Throwable arg0) {
		super(arg0);
	}

	public ArbreCyclique(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
