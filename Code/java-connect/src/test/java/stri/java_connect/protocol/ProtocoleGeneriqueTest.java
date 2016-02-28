package stri.java_connect.protocol;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.utils.MD5Hasher;

public class ProtocoleGeneriqueTest
{
	private final static String courriel = "test@test.test";
	private final static String mdp = "motdepasse";
	private final static String mockstr = "test";

	@Test
	public void testRequeteConnexion()
	{
		String requete = ProtocoleAnnuaire.requeteConnexion(courriel, mdp);
		assertEquals("CONNEXION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("test@test.test:motdepasse", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteConnexionHashMD5()
	{
		String requete = ProtocoleAnnuaire.requeteConnexionHashMD5(courriel, mdp);
		assertEquals("CONNEXION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("test@test.test:MD5:"+MD5Hasher.hashString(mdp), ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testErreurImplementionManquante()
	{
		assertEquals(-2, ControlleurProtocole.reponseCode(ProtocoleGenerique.erreurImplementionManquante()));
	}

	@Test
	public void testErreurServeur()
	{
		assertEquals(-1, ControlleurProtocole.reponseCode(ProtocoleGenerique.erreurServeur()));
	}

	@Test
	public void testOk()
	{
		assertEquals(0, ControlleurProtocole.reponseCode(ProtocoleGenerique.ok()));
	}

	@Test
	public void testOkString()
	{
		assertEquals(0, ControlleurProtocole.reponseCode(ProtocoleGenerique.ok(mockstr)));
		assertEquals(mockstr, ControlleurProtocole.reponseDonnees(ProtocoleGenerique.ok(mockstr)));
	}

	@Test
	public void testErreurRequete()
	{
		assertEquals(1, ControlleurProtocole.reponseCode(ProtocoleGenerique.erreurRequete()));
	}

	@Test
	public void testErreurInterdit()
	{
		assertEquals(2, ControlleurProtocole.reponseCode(ProtocoleGenerique.erreurInterdit()));
	}

	@Test
	public void testErreurDeconnexion()
	{
		assertEquals(3, ControlleurProtocole.reponseCode(ProtocoleGenerique.erreurDeconnexion()));
	}

	@Test
	public void testIsErreurImplementionManquante()
	{
		assertTrue(ProtocoleGenerique.isErreurImplementionManquante(ProtocoleGenerique.erreurImplementionManquante()));
	}

	@Test
	public void testIsErreurServeur()
	{
		assertTrue(ProtocoleGenerique.isErreurServeur(ProtocoleGenerique.erreurServeur()));
	}

	@Test
	public void testIsOk()
	{
		assertTrue(ProtocoleGenerique.isOk(ProtocoleGenerique.ok()));
		assertTrue(ProtocoleGenerique.isOk(ProtocoleGenerique.ok(mockstr)));
	}

	@Test
	public void testIsErreurRequete()
	{
		assertTrue(ProtocoleGenerique.isErreurRequete(ProtocoleGenerique.erreurRequete()));
	}

	@Test
	public void testIsErreurInterdit()
	{
		assertTrue(ProtocoleGenerique.isErreurInterdit(ProtocoleGenerique.erreurInterdit()));
	}

	@Test
	public void testIsErreurDeconnexion()
	{
		assertTrue(ProtocoleGenerique.isErreurDeconnexion(ProtocoleGenerique.erreurDeconnexion()));
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
	public void testValideDonnees()
	{
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.erreurDeconnexion()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.erreurImplementionManquante()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.erreurInterdit()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.erreurRequete()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.erreurServeur()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.ok()));
		assertTrue(ProtocoleGenerique.valideDonnees(ProtocoleGenerique.ok(mockstr)));
	}

	@Test
	public void testExtraireDonnees()
	{
		assertEquals(mockstr, ProtocoleGenerique.extraireDonnees(ProtocoleGenerique.ok(mockstr)));
	}

	@Test
	public void testExtraireJSONObject()
	{
		assertEquals(new JSONObject("{ 'test' : 'test' }").toString(), ProtocoleGenerique.extraireJSONObject("{ 'data' : { 'test' : 'test' } }").toString());
	}

	@Test
	public void testExtraireJSONArray()
	{
		assertEquals(new JSONArray("[ 'test' , 'test' ]").toString(), ProtocoleGenerique.extraireJSONArray("{ 'data' : [ 'test' , 'test' ] }").toString());
	}
}
