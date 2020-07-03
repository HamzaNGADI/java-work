package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import reservation.Salle;
import reservation.Utilisateur;
import reservation.UtilisateurRessource;

public class utilisateurT {
	private UtilisateurRessource user; 
	
	@Before
	public void setUp() throws Exception {
		user = new UtilisateurRessource();
	}

	@Test
	public void testuserExist() {
		Utilisateur u_ob = user.getUtilisateur("Mounir", "Lallali");
		Utilisateur u_att = new Utilisateur("Mounir", "Lallali", "MdC", "mounir.lallali@univ-brest.fr");
		assertTrue(u_ob.equals(u_att));
	}
	
	@Test
	public void testnotexistuser() {
		Utilisateur u_o = user.getUtilisateur("hamza", "ngadi");
		Utilisateur u_att = null;
		assertEquals(u_o,u_att);
	}
	@Test
	public void testusercreate()
	{
		String userin = user.createUtilisateur("hamza", "ngadi", "etudiant_ubo", "hamza@ubo.info");
	   boolean ust_att = userin.contains("est ajouté");
		assertTrue(ust_att);
		
		Utilisateur uu_o = user.getUtilisateur("hamza", "ngadi");
		Utilisateur uu_att =new Utilisateur("hamza", "ngadi", "etudiant_ubo", "hamza@ubo.info"); 
		assertEquals(uu_o,uu_att);
		
	}
	@Test
	public void testusercreatenot()
	{
		
	}

}
