package stri.java_connect.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import stri.java_connect.modele.Utilisateur;

public class JSONValidateurTest
{
	private final static String mockstr = "test";

	@Test
	public void testValiderString()
	{
		assertFalse(JSONValidateur.valider(""));
		assertFalse(JSONValidateur.valider(" "));
		assertFalse(JSONValidateur.valider("{"));
		assertFalse(JSONValidateur.valider("}"));
		assertFalse(JSONValidateur.valider("["));
		assertFalse(JSONValidateur.valider(mockstr));
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
		u.setCourriel(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.setDateDiplome((long) 1234567890);
		assertTrue(JSONValidateur.valider(u));
		u.setDateDiplomeFromDate(new Date());
		assertTrue(JSONValidateur.valider(u));
		u.setMotDePasse(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.setNom(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.setPermissionLecture(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.setPrivilege(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.setTelephone(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.addCompetence(mockstr);
		assertTrue(JSONValidateur.valider(u));
		u.addLike(mockstr, mockstr);
		u.addLike(mockstr, mockstr);
		u.addLike(mockstr, mockstr);
		assertTrue(JSONValidateur.valider(u));
	}

}
