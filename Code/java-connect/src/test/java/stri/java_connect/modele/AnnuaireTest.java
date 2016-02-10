package stri.java_connect.modele;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.HashMap;


public class AnnuaireTest
{
	private final static String courriel = "courriel";
	private final static String mdp = "mdp";

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
		h.put(courriel, new Utilisateur());
		h.put("email", new Utilisateur());
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
		a.ajoutUtilisateur(new Utilisateur(courriel,mdp));
		assertTrue(a.existeUtilisateur(courriel));
	}

	@Test
	public void testAjoutUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur(courriel,mdp));
		assertTrue(a.existeUtilisateur(courriel));
		assertFalse(a.getCollectionTousUtilisateurs().size() == 0);
	}

	@Test
	public void testSuppresionUtilisateurString()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		a.ajoutUtilisateur(new Utilisateur(courriel,mdp));
		a.ajoutUtilisateur(new Utilisateur("other",mdp));
		a.suppresionUtilisateur(courriel);
		assertFalse(a.existeUtilisateur(courriel));
		assertTrue(a.getCollectionTousUtilisateurs().size() == 1);
	}

	@Test
	public void testSuppresionUtilisateurUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		Utilisateur u = new Utilisateur(courriel,mdp);
		a.ajoutUtilisateur(u);
		a.suppresionUtilisateur(u);
		assertFalse(a.existeUtilisateur(courriel));
		assertTrue(a.getCollectionTousUtilisateurs().size() == 0);
	}

	@Test
	public void testGetUtilisateur()
	{
		Annuaire a = new Annuaire();
		assertNotNull(a);
		Utilisateur u = new Utilisateur(courriel,mdp);
		a.ajoutUtilisateur(u);
		assertEquals(u, a.getUtilisateur(u.getCourriel()));
	}

	@Test
	public void testGetCollectionTousUtilisateurs()
	{
		HashMap<String, Utilisateur> h = new HashMap<String, Utilisateur>();
		Annuaire a = new Annuaire(h);
		assertNotNull(a);
		h.put(courriel, new Utilisateur());
		h.put("other", new Utilisateur());
		assertEquals(h.values(), a.getCollectionTousUtilisateurs());
	}

	@Test
	public void testGetArrayTousUtilisateurs()
	{
    	ArrayDeque<Utilisateur> l = new ArrayDeque<Utilisateur>();
    	l.add(new Utilisateur(courriel,mdp));
    	l.add(new Utilisateur());
    	Annuaire a = new Annuaire();
    	for (Utilisateur u : l)
    	{
    		a.ajoutUtilisateur(u);
    	}
    	assertEquals(l.size(), a.getArrayTousUtilisateurs().size());
	}

	@Test
	public void testGetArraySecuriseTousUtilisateurs()
	{
    	ArrayDeque<Utilisateur> l = new ArrayDeque<Utilisateur>();
    	Utilisateur test = new Utilisateur(courriel,mdp);
    	l.add(test);
    	l.add(new Utilisateur());
    	Annuaire a = new Annuaire();
    	for (Utilisateur u : l)
    	{
    		a.ajoutUtilisateur(u);
    	}
    	assertEquals(l.size(), a.getArrayTousUtilisateurs().size());
    	// test securite :
    	l = a.getArraySecuriseTousUtilisateurs();
    	test.addCompetence("nothing that never can't be imagined");
    	for (Utilisateur u : l)
    	{
    		assertNotEquals(test, u);
    		assertNotSame(test, u);
    	}
	}

}
