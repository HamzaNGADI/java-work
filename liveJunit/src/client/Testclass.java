package client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import reservation.*;

import org.junit.*;
import org.junit.Assert.*;
import org.junit.Test;

public class Testclass {
	
	@Test
	public void testNull()
	{
		
		UtilisateurRessource urc = new UtilisateurRessource();
		System.out.println(urc.createUtilisateur("hamza", "ngadi", "etuduant", "hamza@brest.info"));

	//	String us = urc.createUtilisateur("hamza", "ngadi", "etudiant_ubo", "hamza@ubo.info");
		Utilisateur user2 = urc.getUtilisateur("anis", "ngadi");
	//	assertFalse("user null", user2 == null);
		
		//assertFalse("user exist", !us.contains("est ajouté"));
		
        List<Utilisateur> auc = ReservationBDD.getUtilisateurs();
		
		assertFalse("BDD null", !(auc.size() != 0 && auc.size()>1));

	}
	
}
