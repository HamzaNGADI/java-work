/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

import java.util.ArrayList;
import java.util.List;

public class SalleRessource {

	public List<Salle> getSalles() {
		System.out.println("getSalles");
		return ReservationBDD.getSalles();
	}

	public Salle getSalle(String nom) {
		System.out.println("getSalle");
		for (Salle current : ReservationBDD.getSalles()) {
			if (current.getNom().equals(nom)) {
				return current;
			}
		}
		return null;
	}

	public Salle searchSalleByName(String nom) {
		System.out.println("searchSalleByName");
		for (Salle current : ReservationBDD.getSalles()) {
			if (current.getNom().equals(nom)) {
				return current;
			}
		}
		return null;
	}

	public List<Salle> searchSallesByType(String type) {
		List<Salle> listSalles = new ArrayList<Salle>();
		System.out.println("searchSallesByType");
		for (Salle current : ReservationBDD.getSalles()) {
			if (current.getType().equals(type)) {
				listSalles.add(current);
			}
		}
		return listSalles;
	}

	public ReservationRessource getReservationResource() {
		return new ReservationRessource();
	}
}
