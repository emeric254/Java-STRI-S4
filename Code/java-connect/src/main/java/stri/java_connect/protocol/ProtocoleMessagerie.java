/**
 * 
 */
package stri.java_connect.protocol;

import java.util.Date;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleMessagerie extends ProtocoleGenerique
{
	private final static String utilisateursURI = "/utilisateurs";
	private final static String messagerieURI = "/messagerie";
	
	/**
	 * @param idutilisateur
	 * @param msg
	 * @return
	 */
	public static String requeteEnvoiMessageDiffere(String idutilisateur, String msg)
	{
		return null;
	}
	
	/**
	 * @return
	 */
	public static String requeteConsulterListeUtilisateurConnectes()
	{
		return "CONSULTER "+ utilisateursURI;
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public static String requeteDetailsUtilisateurConnecte(String courriel)
	{
		return "CONSULTER "+ utilisateursURI + "/" + courriel;
	}
	
	/**
	 * @return
	 */
	public static String requeteConsulterListeMessagesManques()
	{
		return "CONSULTER "+ messagerieURI;
	}
	
	/**
	 * @param idmsg
	 * @return
	 */
	public static String requeteConsulterDetailsMessagesManque(String idmsg)
	{
		return "CONSULTER "+ messagerieURI + "/" + idmsg;
	}
	
	/**
	 * @return
	 */
	public static String requeteSupprimerListeMessagesManques()
	{
		return "SUPPRIMER "+ messagerieURI;
	}
	
	/**
	 * @param idmsg
	 * @return
	 */
	public static String requeteSupprimerMessageManque(String idmsg)
	{
		return "SUPPRIMER "+ messagerieURI + "/" + idmsg;
	}
	
	//
	
	/**
	 * @param msg
	 * @return
	 */
	public static String requeteMessageDirect(String msg)
	{
		return "MESSAGE " + new Date().getTime() + " " + msg;
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isMessageDirect(String requete)
	{
		return requete.startsWith("MESSAGE ") && extraireDateMessageDirect(requete) != null;
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static String extraireTimestampMessageDirect(String requete)
	{
		return requete.substring(8).split(" ",1)[0];
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static String extraireMessageMessageDirect(String requete)
	{
		return requete.substring(8).split(" ",2)[1];
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static Date extraireDateMessageDirect(String requete)
	{
		try
		{
			return new Date(Long.parseLong(extraireTimestampMessageDirect(requete)));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}
	
	//
}
