package stri.java_connect.protocol;

import static org.junit.Assert.*;

import org.junit.Test;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.utils.MD5Hasher;

public class ProtocoleAnnuaireTest
{

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
		String requete = ProtocoleAnnuaire.requeteConsulterProfil("test@test.test");
		assertEquals("CONSULTER", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteConnexion()
	{
		String requete = ProtocoleAnnuaire.requeteConnexion("test@test.test", "motdepasse");
		assertEquals("CONNEXION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("test@test.test:motdepasse", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteConnexionHashMD5()
	{
		String requete = ProtocoleAnnuaire.requeteConnexionHashMD5("test@test.test", "motdepasse");
		assertEquals("CONNEXION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("test@test.test:MD5:"+MD5Hasher.hashString("motdepasse"), ControlleurProtocole.requeteURI(requete));
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
		String requete = ProtocoleAnnuaire.requeteModifierProfil("test@test.test", utilisateurJson);
		assertEquals("MODIFIER", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
		assertEquals(utilisateurJson, ControlleurProtocole.requeteCorps(requete));
	}

	@Test
	public void testRequeteSuppressionProfil()
	{
		String requete = ProtocoleAnnuaire.requeteSuppressionProfil("test@test.test");
		assertEquals("SUPPRESSION", ControlleurProtocole.requeteMethode(requete));
		assertEquals("/profils/test@test.test", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testIsRequeteConsulter()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
		assertFalse(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConnexion("test@test.test","motdepasse")));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertTrue(ProtocoleAnnuaire.isRequeteConsulter(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
	}

	@Test
	public void testIsRequeteConnexion()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteConnexion(ProtocoleAnnuaire.requeteConnexion("test@test.test","motdepasse")));
	}

	@Test
	public void testIsRequeteInscrire()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConnexion("test@test.test","motdepasse")));
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteInscrire(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
	}

	@Test
	public void testIsRequeteModifier()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConnexion("test@test.test","motdepasse")));
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteModifier(ProtocoleAnnuaire.requeteModifierProfil("test@test.test", new Utilisateur().toString())));
	}

	@Test
	public void testIsRequeteSuppression()
	{
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConnexion("test@test.test","motdepasse")));
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConsulterProfils()));
		assertFalse(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		//
		assertTrue(ProtocoleAnnuaire.isRequeteSuppression(ProtocoleAnnuaire.requeteSuppressionProfil("test@test.test")));
	}

	@Test
	public void testValiderRequeteConsulterProfils()
	{
		assertFalse(ProtocoleAnnuaire.validerRequeteConsulterProfils(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		//
		assertTrue(ProtocoleAnnuaire.validerRequeteConsulterProfils(ProtocoleAnnuaire.requeteConsulterProfils()));
	}

	@Test
	public void testValiderRequeteConsulterProfil()
	{
		assertFalse(ProtocoleAnnuaire.validerRequeteConsulterProfil(ProtocoleAnnuaire.requeteConsulterProfils()));
		//
		assertTrue(ProtocoleAnnuaire.validerRequeteConsulterProfil(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
	}

	@Test
	public void testValiderRequeteConnexion()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteConnexion(ProtocoleAnnuaire.requeteConnexion("test@test.test", "motdepasse")));
		assertTrue(ProtocoleAnnuaire.validerRequeteConnexion(ProtocoleAnnuaire.requeteConnexionHashMD5("test@test.test", "motdepasse")));
	}

	@Test
	public void testValiderTypeMotDePasseHashMD5()
	{
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(""));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(":"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("::"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5(":::"));
		assertFalse(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("test"));
		//
		assertTrue(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("MD5:" + MD5Hasher.hashString("motdepasse")));
		assertTrue(ProtocoleAnnuaire.validerTypeMotDePasseHashMD5("MD5:" + MD5Hasher.hashString("motdepasse","salt")));
	}

	@Test
	public void testValiderRequeteInscrire()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteInscrire(ProtocoleAnnuaire.requeteInscrire(new Utilisateur().toString())));
	}

	@Test
	public void testValiderRequeteModifierProfil()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteModifierProfil(ProtocoleAnnuaire.requeteModifierProfil("test@test.test", new Utilisateur().toString())));
	}

	@Test
	public void testValiderRequeteSuppressionProfil()
	{
		assertTrue(ProtocoleAnnuaire.validerRequeteSuppressionProfil(ProtocoleAnnuaire.requeteSuppressionProfil("test@test.test")));
	}

	@Test
	public void testErreurImplementionManquante()
	{
		assertEquals(-2, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.erreurImplementionManquante()));
	}

	@Test
	public void testErreurServeur()
	{
		assertEquals(-1, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.erreurServeur()));
	}

	@Test
	public void testOk()
	{
		assertEquals(0, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.ok()));
	}

	@Test
	public void testOkString()
	{
		assertEquals(0, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.ok("test")));
		assertEquals("test", ControlleurProtocole.reponseDonnees(ProtocoleAnnuaire.ok("test")));
	}

	@Test
	public void testErreurRequete()
	{
		assertEquals(1, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.erreurRequete()));
	}

	@Test
	public void testErreurInterdit()
	{
		assertEquals(2, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.erreurInterdit()));
	}

	@Test
	public void testErreurDeconnexion()
	{
		assertEquals(3, ControlleurProtocole.reponseCode(ProtocoleAnnuaire.erreurDeconnexion()));
	}

	@Test
	public void testIsErreurImplementionManquante()
	{
		assertTrue(ProtocoleAnnuaire.isErreurImplementionManquante(ProtocoleAnnuaire.erreurImplementionManquante()));
	}

	@Test
	public void testIsErreurServeur()
	{
		assertTrue(ProtocoleAnnuaire.isErreurServeur(ProtocoleAnnuaire.erreurServeur()));
	}

	@Test
	public void testIsOk()
	{
		assertTrue(ProtocoleAnnuaire.isOk(ProtocoleAnnuaire.ok()));
		assertTrue(ProtocoleAnnuaire.isOk(ProtocoleAnnuaire.ok("test")));
	}

	@Test
	public void testIsErreurRequete()
	{
		assertTrue(ProtocoleAnnuaire.isErreurRequete(ProtocoleAnnuaire.erreurRequete()));
	}

	@Test
	public void testIsErreurInterdit()
	{
		assertTrue(ProtocoleAnnuaire.isErreurInterdit(ProtocoleAnnuaire.erreurInterdit()));
	}

	@Test
	public void testIsErreurDeconnexion()
	{
		assertTrue(ProtocoleAnnuaire.isErreurDeconnexion(ProtocoleAnnuaire.erreurDeconnexion()));
	}

	@Test
	public void testValideDonnees()
	{
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.erreurDeconnexion()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.erreurImplementionManquante()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.erreurInterdit()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.erreurRequete()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.erreurServeur()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.ok()));
		assertTrue(ProtocoleAnnuaire.valideDonnees(ProtocoleAnnuaire.ok("test")));
	}

	@Test
	public void testExtraireDonnees()
	{
		assertEquals("test", ProtocoleAnnuaire.extraireDonnees(ProtocoleAnnuaire.ok("test")));
	}

}
