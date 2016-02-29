package stri.java_connect.protocol;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ProtocoleMessagerieTest
{
	private final static String idutilisateur = "machin@machin.lol";
	private final static String msg = "message de test";
	private final static String idmsg = "1234567890";

	@Test
	public void testRequeteEnvoiMessageDiffere()
	{
		assertEquals("INSCRIRE /messagerie/" + idutilisateur + " ;" + msg, ProtocoleMessagerie.requeteEnvoiMessageDiffere(idutilisateur, msg));
	}

	@Test
	public void testRequeteConsulterListeUtilisateurConnectes()
	{
		assertEquals("CONSULTER /utilisateurs", ProtocoleMessagerie.requeteConsulterListeUtilisateurConnectes());
	}

	@Test
	public void testRequeteConsulterDetailsUtilisateurConnecte()
	{
		assertEquals("CONSULTER /utilisateurs/" + idutilisateur, ProtocoleMessagerie.requeteConsulterDetailsUtilisateurConnecte(idutilisateur));
	}

	@Test
	public void testRequeteConsulterListeMessagesManques()
	{
		assertEquals("CONSULTER /messagerie", ProtocoleMessagerie.requeteConsulterListeMessagesManques());
	}

	@Test
	public void testRequeteConsulterDetailsMessagesManque()
	{
		assertEquals("CONSULTER /messagerie/" + idmsg, ProtocoleMessagerie.requeteConsulterDetailsMessagesManque(idmsg));
	}

	@Test
	public void testRequeteSupprimerListeMessagesManques()
	{
		assertEquals("SUPPRIMER /messagerie", ProtocoleMessagerie.requeteSupprimerListeMessagesManques());
	}

	@Test
	public void testRequeteSupprimerMessageManque()
	{
		assertEquals("SUPPRIMER /messagerie/" + idmsg, ProtocoleMessagerie.requeteSupprimerMessageManque(idmsg));
	}

	@Test
	public void testRequeteMessageDirect()
	{
		assertTrue(ProtocoleMessagerie.requeteMessageDirect(msg).startsWith("MESSAGE "));
		assertTrue(ProtocoleMessagerie.requeteMessageDirect(msg).endsWith(msg));
	}

	@Test
	public void testIsMessageDirect()
	{
		String msgDirect = ProtocoleMessagerie.requeteMessageDirect(msg);
		assertTrue(ProtocoleMessagerie.isMessageDirect(msgDirect));
	}

	@Test
	public void testExtraireTimestampMessageDirect()
	{
		Date now = new Date();
		assertTrue(Long.parseLong(ProtocoleMessagerie.extraireTimestampMessageDirect(ProtocoleMessagerie.requeteMessageDirect(msg))) >= now.getTime());
	}

	@Test
	public void testExtraireMessageMessageDirect()
	{
		assertEquals(msg, ProtocoleMessagerie.extraireMessageMessageDirect(ProtocoleMessagerie.requeteMessageDirect(msg)));
	}

	@Test
	public void testExtraireDateMessageDirect()
	{
		Date now = new Date();
		assertTrue(ProtocoleMessagerie.extraireDateMessageDirect(ProtocoleMessagerie.requeteMessageDirect(msg)).getTime() >= now.getTime());
	}

}
