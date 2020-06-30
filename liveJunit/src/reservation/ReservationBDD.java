/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationBDD {
	private static List<Salle>       salles       = new ArrayList<Salle>();
    private static List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    private static List<Reservation> reservations = new ArrayList<Reservation>();

    static {
        salles.add(new Salle("Micro 1.4", "TP"));
        salles.add(new Salle("Micro 2.2", "TP"));
        salles.add(new Salle("I005", "TD"));
        salles.add(new Salle("F", "Amphi"));
    }

    static {
        utilisateurs.add(new Utilisateur("Mounir", "Lallali", "MdC", "mounir.lallali@univ-brest.fr"));
    }

    public static List<Salle> getSalles() {
        return salles;
    }

    public static List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }
}
