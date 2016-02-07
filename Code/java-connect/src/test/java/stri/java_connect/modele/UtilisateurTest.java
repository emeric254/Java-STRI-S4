package stri.java_connect.modele;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


import stri.java_connect.utils.JSONValidateur;


public class UtilisateurTest
{
	private final static String courriel = "courriel";
	private final static String mdp = "mdp";
	private final static String nom = "nom";
	private final static String telephone = "telephone";
	private final static String permissions = "permissions";
	private final static String privileges = "privileges";

	@Test
	public void testUtilisateur()
	{
		assertNotNull(new Utilisateur());
	}

	@Test
	public void testUtilisateurStringString()
	{
		Utilisateur u = new Utilisateur(courriel,mdp);
		assertNotNull(u);
		assertEquals(courriel, u.getCourriel() );
		assertEquals(mdp, u.getMotDePasse() );
	}

	@Test
	public void testUtilisateurStringStringString()
	{
		Utilisateur u = new Utilisateur(nom,telephone,courriel);
		assertNotNull(u);
		assertEquals(nom, u.getNom() );
		assertEquals(telephone, u.getTelephone() );
		assertEquals(courriel, u.getCourriel() );
	}

	@Test
	public void testSetMotDePasse()
	{
		Utilisateur u = new Utilisateur();
		u.setMotDePasse(mdp);
		assertEquals(mdp, u.getMotDePasse() );
		
		u = new Utilisateur(courriel,mdp);
		assertEquals(mdp, u.getMotDePasse() );
		
		u = new Utilisateur(nom,telephone,courriel);
		u.setMotDePasse(mdp);
		assertEquals(mdp, u.getMotDePasse() );
	}

	@Test
	public void testGetMotDePasse()
	{
		Utilisateur u = new Utilisateur(courriel,mdp);
		assertEquals(mdp, u.getMotDePasse() );
		
		u = new Utilisateur(nom,telephone,courriel);
		u.setMotDePasse(mdp);
		assertEquals(mdp, u.getMotDePasse() );
	}

	@Test
	public void testSetNom()
	{
		Utilisateur u = new Utilisateur();
		u.setNom(nom);
		assertEquals(nom, u.getNom() );
		
		u = new Utilisateur(courriel,mdp);
		u.setNom(nom);
		assertEquals(nom, u.getNom() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(nom, u.getNom() );
	}

	@Test
	public void testGetNom()
	{
		Utilisateur u = new Utilisateur();
		u.setNom(nom);
		assertEquals(nom, u.getNom() );

		u = new Utilisateur(courriel,mdp);
		u.setNom(nom);
		assertEquals(nom, u.getNom() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(nom, u.getNom() );
	}

	@Test
	public void testSetDateDiplome()
	{
		Utilisateur u = new Utilisateur();
		u.setDateDiplome((long) 123456789.0);
		assertEquals(new Long((long) 123456789.0), u.getDateDiplome() );

		u = new Utilisateur(courriel,mdp);
		u.setDateDiplome((long) 123456789.0);
		assertEquals(new Long((long) 123456789.0), u.getDateDiplome() );
		
		u = new Utilisateur(nom,telephone,courriel);
		u.setDateDiplome((long) 123456789.0);
		assertEquals(new Long((long) 123456789.0), u.getDateDiplome() );
	}

	@Test
	public void testSetDateDiplomeFromDate()
	{
		Date maintenant = new Date();
		Utilisateur u = new Utilisateur();
		u.setDateDiplomeFromDate(maintenant);
		assertEquals(maintenant, u.getDateDiplomeAsDate() );

		maintenant = new Date();
		u = new Utilisateur(courriel,mdp);
		u.setDateDiplomeFromDate(maintenant);
		assertEquals(maintenant, u.getDateDiplomeAsDate() );

		maintenant = new Date();
		u = new Utilisateur(nom,telephone,courriel);
		u.setDateDiplomeFromDate(maintenant);
		assertEquals(maintenant, u.getDateDiplomeAsDate() );
	}

	@Test
	public void testGetDateDiplome()
	{
		testSetDateDiplome();
		assert new Date().toString().length() > 0;
	}

	@Test
	public void testGetDateDiplomeAsDate()
	{
		testSetDateDiplomeFromDate();
		assert new Date().getTime() > 0;
	}

	@Test
	public void testSetTelephone()
	{
		Utilisateur u = new Utilisateur();
		u.setTelephone(telephone);
		assertEquals(telephone, u.getTelephone() );

		u = new Utilisateur(courriel,mdp);
		u.setTelephone(telephone);
		assertEquals(telephone, u.getTelephone() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(telephone, u.getTelephone() );
	}

	@Test
	public void testGetTelephone()
	{
		Utilisateur u = new Utilisateur();
		u.setTelephone(telephone);
		assertEquals(telephone, u.getTelephone() );

		u = new Utilisateur(courriel,mdp);
		u.setTelephone(telephone);
		assertEquals(telephone, u.getTelephone() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(telephone, u.getTelephone() );
	}

	@Test
	public void testSetCourriel()
	{
		Utilisateur u = new Utilisateur();
		u.setCourriel(courriel);
		assertEquals(courriel, u.getCourriel() );

		u = new Utilisateur(courriel,mdp);
		u.setCourriel(courriel);
		assertEquals(courriel, u.getCourriel() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(courriel, u.getCourriel() );
	}

	@Test
	public void testGetCourriel()
	{
		Utilisateur u = new Utilisateur();
		u.setCourriel(courriel);
		assertEquals(courriel, u.getCourriel() );

		u = new Utilisateur(courriel,mdp);
		u.setCourriel(courriel);
		assertEquals(courriel, u.getCourriel() );
		
		u = new Utilisateur(nom,telephone,courriel);
		assertEquals(courriel, u.getCourriel() );
	}

    @Ignore
	@Test
	public void testSetPermissionLecture()
	{
	   // TODO
		fail("Not yet implemented");
	}

    @Ignore
	@Test
	public void testGetPermissionLecture()
	{
	   // TODO
		fail("Not yet implemented");
	}

	@Test
	public void testSetPrivilege()
	{
		Utilisateur u = new Utilisateur();
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );

		u = new Utilisateur(courriel,mdp);
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );
		
		u = new Utilisateur(nom,telephone,courriel);
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );
	}

	@Test
	public void testGetPrivilege()
	{
		Utilisateur u = new Utilisateur();
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );

		u = new Utilisateur(courriel,mdp);
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );
		
		u = new Utilisateur(nom,telephone,courriel);
		u.setPrivilege(privileges);
		assertEquals(privileges, u.getPrivilege() );
	}

	@Test
	public void testGetCompetences()
	{
		Utilisateur u = new Utilisateur();
		ArrayDeque<String> competences = new ArrayDeque<String>();
		competences.add("1");
		competences.add("2");
		competences.add("3");
		u.setCompetences(competences.clone());
		assertEquals(competences.toString(), u.getCompetences().toString());
	}

	@Test
	public void testSetCompetences()
	{
		Utilisateur u = new Utilisateur();
		ArrayDeque<String> competences = new ArrayDeque<String>();
		competences.add("1");
		competences.add("2");
		competences.add("3");
		u.setCompetences(competences.clone());
		assertEquals(competences.toString(), u.getCompetences().toString());
	}

	@Test
	public void testAddCompetence()
	{
		Utilisateur u = new Utilisateur();
		ArrayDeque<String> competences = new ArrayDeque<String>();
		competences.add("1");
		competences.add("2");
		competences.add("3");
		u.setCompetences(competences.clone());
		assertEquals(competences.toString(), u.getCompetences().toString());
		u.addCompetence("4");
		u.addCompetence("5");
		assertNotEquals(competences.toString(), u.getCompetences().toString());
	}

	@Test
	public void testToString()
	{
		Utilisateur u = new Utilisateur();
		assertNotNull(u.toString());
		assertTrue(JSONValidateur.valider(u.toString()));

		u = new Utilisateur(courriel,mdp);
		assertNotNull(u.toString());
		assertTrue(JSONValidateur.valider(u.toString()));
		
		u = new Utilisateur(nom,telephone,courriel);
		assertNotNull(u.toString());
		assertTrue(JSONValidateur.valider(u.toString()));
	}

	@Test
	public void testFromJSONString()
	{
		Utilisateur u = new Utilisateur(nom,telephone,courriel);
		u.setDateDiplomeFromDate(new Date());
		u.setMotDePasse(mdp);
		u.setPermissionLecture(permissions);
		u.setPrivilege(privileges);
		ArrayDeque<String> competences = new ArrayDeque<String>();
		competences.add("1");
		competences.add("2");
		competences.add("3");
		u.setCompetences(competences);
		Utilisateur b = new Utilisateur();
		b.fromJSONString(u.toString());
		assertEquals(u, b);
	}

	@Test
	public void testClone()
	{
		Utilisateur u = new Utilisateur();
		assertEquals(u, u.clone());
		assertNotSame(u, u.clone());
	}

}
