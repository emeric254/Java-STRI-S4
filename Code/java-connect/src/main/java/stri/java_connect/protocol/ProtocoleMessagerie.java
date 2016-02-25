/**
 * 
 */
package stri.java_connect.protocol;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleMessagerie extends ProtocoleGenerique
{
	private final static String utilisateursURI = "/utilisateurs";
	private final static String messagerieURI = "/messagerie";
	
	public static String requeteEnvoiMessageDiffere(String idutilisateur, String msg)
	{
		return null;
	}
	
	public static String requeteConsulterListeUtilisateurConnectes()
	{
		return "CONSULTER "+ utilisateursURI;
	}
	
	public static String requeteDetailsUtilisateurConnecte(String courriel)
	{
		return "CONSULTER "+ utilisateursURI + "/" + courriel;
	}
	
	public static String requeteConsulterListeMessagesManques()
	{
		return "CONSULTER "+ messagerieURI;
	}
	
	public static String requeteConsulterDetailsMessagesManque(String idmsg)
	{
		return "CONSULTER "+ messagerieURI + "/" + idmsg;
	}
	
	public static String requeteSupprimerListeMessagesManques()
	{
		return "SUPPRIMER "+ messagerieURI;
	}
	
	public static String requeteSupprimerMessageManque(String idmsg)
	{
		return "SUPPRIMER "+ messagerieURI + "/" + idmsg;
	}
	
	//
	
	public static String requeteMessageDirect(String msg)
	{
		// TODO message direct
		return null;
	}
	
	//
}
