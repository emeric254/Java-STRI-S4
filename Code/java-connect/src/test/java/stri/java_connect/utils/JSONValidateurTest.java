package stri.java_connect.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import stri.java_connect.modele.Utilisateur;

public class JSONValidateurTest
{

	@Test
	public void testValiderString()
	{
		assertFalse(JSONValidateur.valider(""));
		assertFalse(JSONValidateur.valider(" "));
		assertFalse(JSONValidateur.valider("{"));
		assertFalse(JSONValidateur.valider("}"));
		assertFalse(JSONValidateur.valider("["));
		assertFalse(JSONValidateur.valider("test"));
		assertFalse(JSONValidateur.valider("[]"));
		assertFalse(JSONValidateur.valider("{1234567890}"));
		//
		assertTrue(JSONValidateur.valider("{}"));
		assertTrue(JSONValidateur.valider("{\"test\":\"test\"}"));
	}

	@Test
	public void testValiderUtilisateur()
	{
		Utilisateur u = new Utilisateur();
		assertTrue(JSONValidateur.valider(u));
		u.setCourriel("test");
		assertTrue(JSONValidateur.valider(u));
		u.setDateDiplome((long) 1234567890);
		assertTrue(JSONValidateur.valider(u));
		u.setDateDiplomeFromDate(new Date());
		assertTrue(JSONValidateur.valider(u));
		u.setMotDePasse("test");
		assertTrue(JSONValidateur.valider(u));
		u.setNom("test");
		assertTrue(JSONValidateur.valider(u));
		u.setPermissionLecture("test");
		assertTrue(JSONValidateur.valider(u));
		u.setPrivilege("test");
		assertTrue(JSONValidateur.valider(u));
		u.setTelephone("test");
		assertTrue(JSONValidateur.valider(u));
		for (int i = 0; i < 10; i++)
		{
			u.addCompetence("test");
			assertTrue(JSONValidateur.valider(u));
		}
	}

}
