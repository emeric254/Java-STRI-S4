/**
 * 
 */
package stri.java_connect.protocol;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleMessagerie
{
	private final static String utilisateursURI = "/utilisateurs";
	private final static String messagerieURI = "/messagerie";
	private final static String code = "{\"code\" : ";
	private final static String data =  ", \"data\" : \"";
	private final static String fin = "\"}";

	public static String requeteConnexion(String courriel, String mdp)
	{
		return null;
	}
	
	public static String requeteEnvoiMessageDiffere(String idutilisateur, String msg)
	{
		return null;
	}
	
	public static String requeteConsulterListeUtilisateurConnectes()
	{
		return null;
	}
	
	public static String requeteDetailsUtilisateurConnecte(String couriel)
	{
		return null;
	}
	
	public static String requeteConsulterListeMessagesManques()
	{
		return null;
	}
	
	public static String requeteConsulterDetailsMessagesManque(String idmsg)
	{
		return null;
	}
	
	public static String requeteSupprimerListeMessagesManques()
	{
		return null;
	}
	
	public static String requeteSupprimerMessageManque(String idmsg)
	{
		return null;
	}
	
	//
	
	public static String requeteMessageDirect(String msg)
	{
		return null;
	}
	
	//
}
