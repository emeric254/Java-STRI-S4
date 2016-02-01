package stri.java_connect.modele;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;


public class AnnuaireTest
{

	@Test
	public void testAnnuaire()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
	}

	@Test
	public void testAnnuaireInt()
	{
		Annuaire a = new Annuaire(123456);
		assertNotNull(a);
	}

	@Test
	public void testAnnuaireHashMapOfStringUtilisateur()
	{
		HashMap<String, Utilisateur> h = new HashMap<String, Utilisateur>();
		Annuaire a = new Annuaire(h);
		assertNotNull(a);
		h.put("test", new Utilisateur());
		h.put("test2", new Utilisateur());
		assertEquals(h.values(), a.getCollectionTousUtilisateurs());
	}

	@Test
	public void testReset()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur());
		a.ajoutUtilisateur(new Utilisateur());
		assertFalse(a.getCollectionTousUtilisateurs().size() == 0);
		a.reset();
		assertTrue(a.getCollectionTousUtilisateurs().size() == 0);
	}

	@Test
	public void testExisteUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur("courriel","mdp"));
		assertTrue(a.existeUtilisateur("courriel"));
	}

	@Test
	public void testAjoutUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur("courriel","mdp"));
		assertTrue(a.existeUtilisateur("courriel"));
		assertFalse(a.getCollectionTousUtilisateurs().size() == 0);
	}

	@Test
	public void testSuppresionUtilisateurString()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur("courriel","mdp"));
		a.ajoutUtilisateur(new Utilisateur("courriel2","mdp"));
		a.suppresionUtilisateur("courriel");
		assertFalse(a.existeUtilisateur("courriel"));
		assertTrue(a.getCollectionTousUtilisateurs().size() == 1);
	}

	@Test
	public void testSuppresionUtilisateurUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		Utilisateur u = new Utilisateur("courriel","mdp");
		a.ajoutUtilisateur(u);
		a.suppresionUtilisateur(u);
		assertFalse(a.existeUtilisateur("courriel"));
		assertTrue(a.getCollectionTousUtilisateurs().size() == 0);
	}

	@Test
	public void testGetUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		Utilisateur u = new Utilisateur("courriel","mdp");
		a.ajoutUtilisateur(u);
		assertEquals(u, a.getUtilisateur(u.getCourriel()));
	}

	@Test
	public void testGetCollectionTousUtilisateurs()
	{
		HashMap<String, Utilisateur> h = new HashMap<String, Utilisateur>();
		Annuaire a = new Annuaire(h);
		assertNotNull(a);
		h.put("test", new Utilisateur());
		h.put("test2", new Utilisateur());
		assertEquals(h.values(), a.getCollectionTousUtilisateurs());
	}

    @Ignore
	@Test
	public void testGetArrayTousUtilisateurs()
	{
	   // TODO
		fail("Not yet implemented");
	}

    @Ignore
	@Test
	public void testGetArraySecuriseTousUtilisateurs()
	{
	   // TODO
		fail("Not yet implemented");
	}

}
