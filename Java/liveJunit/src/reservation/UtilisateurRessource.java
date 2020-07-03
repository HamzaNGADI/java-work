/*
 * Mounir Lallali
 * mounir.lallali@univ-brest.fr
 */

package reservation;

import java.util.List;

public class UtilisateurRessource {

	public UtilisateurRessource() {}

	public List<Utilisateur> getUtilisateurs() {
		System.out.println("getUtilisateurs");
		return ReservationBDD.getUtilisateurs();
	}

	public Utilisateur getUtilisateur(String prenom, String nom) {
		System.out.println("getUtilisateur");
		for (Utilisateur current : ReservationBDD.getUtilisateurs()) {
			if (current.getPrenom().equals(prenom)
					&& current.getNom().equals(nom)) {
				return current;
			}
		}
		return null;
	}

	public Utilisateur searchUtilisateursByName(String prenom, String nom) {
		System.out.println("searchUtilisateursByName");
		for (Utilisateur current : ReservationBDD.getUtilisateurs()) {
			if (current.getPrenom().equals(prenom)
					&& current.getNom().equals(nom)) {
				return current;
			}
		}
		return null;
	}

	public String createUtilisateur(String prenom, String nom, String grade,
			String email) {
		Utilisateur utilisateur = null;
		for (Utilisateur current : ReservationBDD.getUtilisateurs()) {
			if (current.getPrenom().equals(prenom)
					&& current.getNom().equals(nom)) {
				utilisateur = current;
			}
		}
		if (utilisateur == null) {
			utilisateur = new Utilisateur(prenom, nom, grade, email);
			ReservationBDD.getUtilisateurs().add(utilisateur);
			return "L'utilisateur :" + prenom + " " + nom + " est ajouté";
		} else {
			return "L'utilisateur :" + prenom + " " + nom + " existe déjà !!!";
		}
	}
}
