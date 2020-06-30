package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import reservation.Reservation;
import reservation.ReservationRessource;
import reservation.Salle;
import reservation.Utilisateur;
import reservation.UtilisateurRessource;

public class resT {
	
	ReservationRessource resr;

	@Before
	public void setUp() throws Exception {
		resr = new ReservationRessource();
	}

	@Test	
	public void testurescreate()
	{
		
		String res = resr.createReservation("reservation1", "7juin","14:00", (double)8, "TD", "I005","bolm", "john","etudiant", "hamza@ubo.info");
		boolean res_att = res.contains("La réservation est effectuée");
		assertTrue(res_att);
		
		Reservation resv = resr.getReservations().get(0);
		Salle sres = resv.getSalle();
		Salle s_att = new Salle("I005", "TD");
		assertTrue(sres.equals(s_att));
		
		Reservation rvcv = resr.searchReservationsByDateAndHour("7juin","14:00").get(0);
		/*String resnum = resv.getNumReservation();
		String resnumatt = Long.toString(System.currentTimeMillis());
		String resdis = resv.getDescription(), resdisatt = "reservation1";
		String resdate = resv.getDate(), resvdattatt = "7juin";
		String resh = resv.getHeure(), resvhtatt = "14:00";*/
		
		assertTrue(resv.equals(rvcv));
		
		UtilisateurRessource us = new UtilisateurRessource();
		Utilisateur uu_att = new Utilisateur("bolm", "john", "etudiant", "hamza@ubo.info");
		Utilisateur uures = us.getUtilisateur("bolm", "john");
		assertTrue(uures.equals(uu_att));
		
		Utilisateur ures = resv.getUtilisateur();
		Utilisateur u_att = new Utilisateur("bolm", "john", "etudiant", "hamza@ubo.info");
		assertTrue(ures.equals(u_att));
		
		
/*		salle si exist
		salle & reserv si exist
		salle & reser & user si exist
		*
		*/
		
	}

}
