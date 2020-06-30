package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import reservation.Salle;
import reservation.SalleRessource;

public class salle {

	private SalleRessource s;
	@Before
	public void setUp() throws Exception {
		s = new SalleRessource();
	}

	@Test
	public void testsalleExist() {
		Salle s_ob = s.getSalle("I005");
		Salle s_att = new Salle("I005","TD");
		assertTrue(s_ob.equals(s_att));
	}
	
	@Test
	public void testnotexistsalle() {
		Salle s_ob = s.getSalle("I006");
		Salle s_att = null;
		assertEquals(s_ob,s_att);
	}
}
