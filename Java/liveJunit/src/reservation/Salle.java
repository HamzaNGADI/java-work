/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

public class Salle {
	private String nom;
	private String type;

	public Salle() {
	}

	public Salle(String nom, String type) {
		this.nom = nom;
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(Salle salle) {
		return (this.nom.equals(salle.getNom()) && this.type.equals(salle.getType())); 
	}
	public boolean equals(Object o)
	{ 
		return o instanceof Salle && this.nom == ((Salle)o).nom &&  this.type == ((Salle)o).type;
	}
}
