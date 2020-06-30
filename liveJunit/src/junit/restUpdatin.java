package junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import reservation.Reservation;
import reservation.ReservationRessource;
import reservation.Salle;
import reservation.Utilisateur;

import org.hamcrest.*;

import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

import static org.hamcrest.MatcherAssert.assertThat;


public class restUpdatin {

	ReservationRessource rc;
	@Before
	public void setUp() throws Exception {
		rc = new ReservationRessource();
	}

	@Test
	public void resupdate() {
		/*String res = rc.createReservation("reservation1", "7juin","14:00", (double)8, "TD", "I005","bolm", "john","etudiant", "hamza@ubo.info");
		boolean res_att = res.contains("La réservation est effectuée");
		assertTrue(res_att);
		String numr = rc.getReservations().get(0).getNumReservation();*/
		
		String rup = rc.updateReservation("", "reservation1", "7juin","14:00", (double)8, "TD", "I005","bolm", "john","etudiant", "hamza@ubo.info");
		boolean resu = rup.contains("La réservation est effectuée");
		assertTrue(resu);
		String numr = rc.getReservations().get(0).getNumReservation();
		
		Salle srec = rc.getReservations().get(0).getSalle();
		Salle s_att = new Salle("I005", "TD");
		assertTrue(srec.equals(s_att));
		
		Utilisateur utres =  rc.getReservations().get(0).getUtilisateur();
		Utilisateur uatt = new Utilisateur("bolm", "john","etudiant", "hamza@ubo.info");
		assertTrue(utres.equals(uatt));
		
		Reservation r = rc.searchReservationsByDateAndHour("7juin","14:00").get(0);
		assertTrue(numr.equals(r.getNumReservation()));


	}

	@Test
	public void listre() {
		List<Integer> ls = Arrays.asList(1,2,3,4,5);
		List<Integer> lsatt = Arrays.asList(1,2,3,4,5);
		
		assertTrue(ls.size()==lsatt.size());
		assertTrue(ls.equals(lsatt));
		

	}
	
}
