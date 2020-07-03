/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

public class Reservation {
	private String date;
	private String description;
	private Double duree;
	private String heure;
	private String numReservation;
	private Salle salle;
	private String typeCours;
	private Utilisateur utilisateur;

	public String getNumReservation() {
		return numReservation;
	}

	public void setNumReservation(String numReservation) {
		this.numReservation = numReservation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getTypeCours() {
		return typeCours;
	}

	public void setTypeCours(String typeCours) {
		this.typeCours = typeCours;
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public boolean equals(Object o)
	{ 
		return o instanceof Reservation && this.numReservation == ((Reservation)o).numReservation &&  this.description == ((Reservation)o).description && this.date == ((Reservation)o).date && this.heure == ((Reservation)o).heure && this.duree == ((Reservation)o).duree && this.typeCours == ((Reservation)o).typeCours && this.salle.equals(((Reservation)o).salle) && this.utilisateur.equals(((Reservation)o).utilisateur);
	}
}
