/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRessource {

	public Reservation getReservation(String numReservation) {
		Reservation reservation = null;
		for (Reservation current : ReservationBDD.getReservations()) {
			if (current.getNumReservation().equals(numReservation)) {
				reservation = current;
			}
		}
		return reservation;
	}

	public List<Reservation> getReservations() {
		System.out.println("getReservations");
		return ReservationBDD.getReservations();
	}

	public List<Reservation> searchReservationsByDate(String date) {
		System.out.println("searchReservationsByDate");
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation current : ReservationBDD.getReservations()) {
			if (current.getDate().equals(date)) {
				reservations.add(current);
			}
		}
		return reservations;
	}

	public List<Reservation> searchReservationsByDateAndHour(String date,
			String heure) {
		System.out.println("searchReservationsByDateAndHour");
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation current : ReservationBDD.getReservations()) {
			if (current.getDate().equals(date)
					&& current.getHeure().equals(heure)) {
				reservations.add(current);
			}
		}
		return reservations;
	}

	public List<Reservation> searchReservationsByCourseType(String typeCours) {
		System.out.println("searchReservationsByCourseType");
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation current : ReservationBDD.getReservations()) {
			if (current.getTypeCours().equals(typeCours)) {
				reservations.add(current);
			}
		}
		return reservations;
	}

	public List<Reservation> searchReservationsFromDate(String date) {
		System.out.println("searchReservationsFromDate");
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation current : ReservationBDD.getReservations()) {
			if (Integer.parseInt(current.getDate()) >= Integer.parseInt(date)) {
				reservations.add(current);
			}
		}
		return reservations;
	}

	public List<Salle> searchSallesLibresByDate(String date) {
		System.out.println("searchSallesLibresByDate");
		List<String> nomsSallesReservees = new ArrayList<String>();
		List<Salle> sallesReservees = new ArrayList<Salle>();
		for (Reservation currentR : ReservationBDD.getReservations()) {
			if (currentR.getDate().equals(date)) {
				String nomSalle = currentR.getSalle().getNom();
				nomsSallesReservees.add(nomSalle);
			}
		}
		if (nomsSallesReservees.size() != 0) {
			return ReservationBDD.getSalles();
		} else {
			for (Salle currentS : ReservationBDD.getSalles()) {
				if (!nomsSallesReservees.contains(currentS.getNom())) {
					sallesReservees.add(currentS);
				}
			}
			return sallesReservees;
		}
	}

	public String removeReservation(String numReservation) {
		Reservation reservation = null;
		for (Reservation current : ReservationBDD.getReservations()) {
			if (current.getNumReservation().equals(numReservation)) {
				reservation = current;
			}
		}
		if (reservation == null) {
			return "La reservation " + numReservation + " n_existe pas !!!";
		} else {
			ReservationBDD.getReservations().remove(reservation);
			return "La reservation " + numReservation + " a été bien suprimée !";
		}
	}

	public String createReservation(String description, String date,
			String heure, Double duree, String typeCours, String nomSalle,
			String prenomUtilisateur, String nomUtilisateur,
			String gradeUtilisateur, String emailUtilisateur) {

		Utilisateur utilisateur = null;
		Salle salle = null;
		for (Salle currentS : ReservationBDD.getSalles()) {
			if (currentS.getNom().equals(nomSalle)) {
				salle = currentS;
			}
		}
		if (salle == null) {
			return "La réservation de la salle est refuseé : La salle "
					+ nomSalle + "n_existe pas ! ";
		}
		for (Utilisateur currentU : ReservationBDD.getUtilisateurs()) {
			if (currentU.getPrenom().equals(prenomUtilisateur)
					&& currentU.getNom().equals(nomUtilisateur)) {
				utilisateur = currentU;
			}
		}
		if (utilisateur == null) {
			utilisateur = new Utilisateur(prenomUtilisateur, nomUtilisateur,
					gradeUtilisateur, emailUtilisateur);
			ReservationBDD.getUtilisateurs().add(utilisateur);
		}
		Reservation reservation = new Reservation();
		reservation
				.setNumReservation(Long.toString(System.currentTimeMillis()));
		reservation.setDescription(description);
		reservation.setDate(date);
		reservation.setHeure(heure);
		reservation.setDuree(duree);
		reservation.setTypeCours(typeCours);
		reservation.setSalle(salle);
		reservation.setUtilisateur(utilisateur);
		ReservationBDD.getReservations().add(reservation);
		return "La réservation est effectuée : " + reservation.getNumReservation();
	}

	public String updateReservation(String numReservation, String description,
			 String date, String heure, Double duree,
			 String typeCours, String nomSalle, String prenomUtilisateur,
			 String nomUtilisateur, String gradeUtilisateur, String emailUtilisateur) {
		Salle salle = null;
		Utilisateur utilisateur = null;
		Reservation reservation = null;
		for (Reservation currentR : ReservationBDD.getReservations()) {
			if (currentR.getNumReservation().equals(numReservation)) {
				reservation = currentR;
			}
		}
		if (reservation == null) {
			for (Salle currentS : ReservationBDD.getSalles()) {
				if (currentS.getNom().equals(nomSalle)) {
					salle = currentS;
				}
			}
			if (salle == null) {
				return "La reservation de la salle est refusée : La salle "
						+ nomSalle + "n_existe pas ! ";
			}
			for (Utilisateur currentU : ReservationBDD.getUtilisateurs()) {
				if (currentU.getPrenom().equals(prenomUtilisateur)
						&& currentU.getNom().equals(nomUtilisateur)) {
					utilisateur = currentU;
				}
			}
			if (utilisateur == null) {
				utilisateur = new Utilisateur(prenomUtilisateur,
						nomUtilisateur, gradeUtilisateur, emailUtilisateur);
				ReservationBDD.getUtilisateurs().add(utilisateur);
			}
			reservation = new Reservation();
			reservation.setNumReservation(Long.toString(System.currentTimeMillis()));
			reservation.setDescription(description);
			reservation.setDate(date);
			reservation.setHeure(heure);
			reservation.setDuree(duree);
			reservation.setTypeCours(typeCours);
			reservation.setSalle(salle);
			reservation.setUtilisateur(utilisateur);
			ReservationBDD.getReservations().add(reservation);
			return "La réservation est effectuée : " + reservation.getNumReservation();
		} else {
			for (Salle currentS : ReservationBDD.getSalles()) {
				if (currentS.getNom().equals(nomSalle)) {
					salle = currentS;
				}
			}
			if (salle == null) {
				return "La mise à jour de la réservation est refusée : La salle "
						+ nomSalle + "n_existe pas ! ";
			}
			for (Utilisateur currentU : ReservationBDD.getUtilisateurs()) {
				if (currentU.getPrenom().equals(prenomUtilisateur)
						&& currentU.getNom().equals(nomUtilisateur)) {
					utilisateur = currentU;
				}
			}
			if (utilisateur == null) {
				utilisateur = new Utilisateur(prenomUtilisateur,
						nomUtilisateur, gradeUtilisateur, emailUtilisateur);
				ReservationBDD.getUtilisateurs().add(utilisateur);
			}
			if (description.length() != 0) {
				reservation.setDescription(description);
			}
			if (date.length() != 0) {
				reservation.setDate(date);
			}
			if (heure.length() != 0) {
				reservation.setHeure(heure);
			}
			if (duree > 0) {
				reservation.setDuree(duree);
			}
			if (typeCours.length() != 0) {
				reservation.setTypeCours(typeCours);
			}
			reservation.setUtilisateur(utilisateur);
			return "La mise à jour de la réservation " + reservation.getNumReservation() 
					+ "est effectuée : ";
		}
	}
}