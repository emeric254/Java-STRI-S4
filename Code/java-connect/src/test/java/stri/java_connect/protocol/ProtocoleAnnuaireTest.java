package stri.java_connect.protocol;

import static org.junit.Assert.*;

import org.junit.Test;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.utils.MD5Hasher;

public class ProtocoleAnnuaireTest
{
	private final static String courriel = "test@test.test";
	private final static String mdp = "motdepasse";
	private final static String mockstr = "test";

	@Test
	public void testRequeteConsulterProfils()
	{
		String requete = ProtocoleAnnuaire.requeteConsulterProfils();
		assertEquals("CONSULTER", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteConsulterProfil()
	{
		String requete = ProtocoleAnnuaire.requeteConsulterProfil(courriel);
		assertEquals("CONSULTER", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteInscrire()
	{
		String utilisateurJson = new Utilisateur().toString();
		String requete = ProtocoleAnnuaire.requeteInscrire(utilisateurJson);
		assertEquals("INSCRIRE", ControlleurProtocole.requeteMethode(requete));
		assertEquals("", ControlleurProtocole.requeteURI(requete));
		assertEquals(utilisateurJson, ControlleurProtocole.requeteCorps(requete));
	}

	@Test
	public void testRequeteModifier()
	{
		String utilisateurJson = new Utilisateur().toString();
		String requete = ProtocoleAnnuaire.requeteModifierProfil(courriel, utilisateurJson);
		assertEquals("MODIFIER", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
		assertEquals(utilisateurJson, ControlleurProtocole.requeteCorps(requete));
	}

	@Test
	public void testRequeteSuppressionProfil()
	{
		String requete = ProtocoleAnnuaire.requeteSuppressionProfil(courriel);
		assertEquals("SUPPRESSION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testIsRequeteConsulter()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
		assertFalse(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConnexion(courriel,mdp)));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertTrue(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
	}

	@Test
	public void testIsRequeteConnexion()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConnexion(courriel,mdp)));
	}

	@Test
	public void testIsRequeteInscrire()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConnexion(courriel,mdp)));
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
	}

	@Test
	public void testIsRequeteModifier()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConnexion(courriel,mdp)));
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteModifierProfil(courriel, new Utilisateur().toString())));
	}

	@Test
	public void testIsRequeteSuppression()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConnexion(courriel,mdp)));
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteSuppressionProfil(courriel)));
	}

	@Test
	public void testValiderRequeteConsulterProfils()
	{
		assertFalse(ProtocoleAnnuaire.validerRequeteConsulterProfils(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
		//
		assertTrue(ProtocoleAnnuaire.validerRequeteConsulterProfils(ProtocoleAnnuaire.requeteConsulterProfils()));
	}

	@Test
	public void testValiderRequeteConsulterProfil()
	{
		assertFalse(ProtocoleAnnuaire.validerRequeteConsulterProfil(ProtocoleAnnuaire.requeteConsulterProfils()));
		//
		assertTrue(ProtocoleAnnuaire.validerRequeteConsulterProfil(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
	}

	@Test
	public void testValiderRequeteConnexion()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteConnexion(ProtocoleAnnuaire.requeteConnexion(courriel, mdp)));
		assertTrue(ProtocoleAnnuaire.validerRequeteConnexion(ProtocoleAnnuaire.requeteConnexionHashMD5(courriel, mdp)));
	}

	@Test
	public void testValiderTypeMotDePasseHashMD5()
	{
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(""));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(":"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("::"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(":::"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(mockstr));
		//
		assertTrue(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("MD5:" + MD5Hasher.hashString(mdp)));
		assertTrue(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("MD5:" + MD5Hasher.hashString(mdp,mockstr)));
	}

	@Test
	public void testValiderRequeteInscrire()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteInscrire(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
	}

	@Test
	public void testValiderRequeteModifierProfil()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteModifierProfil(ProtocoleAnnuaire.requeteModifierProfil(courriel, new Utilisateur().toString())));
	}

	@Test
	public void testValiderRequeteSuppressionProfil()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteSuppressionProfil(ProtocoleAnnuaire.requeteSuppressionProfil(courriel)));
	}
}
