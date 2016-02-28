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
		return inscrireMethod + messagerieURI + "/" + idutilisateur + " " + msg;
	}
	
	/**
	 * @return
	 */
	public static String requeteConsulterListeUtilisateurConnectes()
	{
		return consulterMethod + utilisateursURI;
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public static String requeteDetailsUtilisateurConnecte(String courriel)
	{
		return consulterMethod + utilisateursURI + "/" + courriel;
	}
	
	/**
	 * @return
	 */
	public static String requeteConsulterListeMessagesManques()
	{
		return consulterMethod + messagerieURI;
	}
	
	/**
	 * @param idmsg
	 * @return
	 */
	public static String requeteConsulterDetailsMessagesManque(String idmsg)
	{
		return consulterMethod + messagerieURI + "/" + idmsg;
	}
	
	/**
	 * @return
	 */
	public static String requeteSupprimerListeMessagesManques()
	{
		return supprimerMethod + messagerieURI;
	}
	
	/**
	 * @param idmsg
	 * @return
	 */
	public static String requeteSupprimerMessageManque(String idmsg)
	{
		return supprimerMethod + messagerieURI + "/" + idmsg;
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
		return requete.startsWith("MESSAGE ") && extraireDateMessageDirect(requete) != null && extraireMessageMessageDirect(requete) != null;
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static String extraireTimestampMessageDirect(String requete)
	{
		return requete.substring(8).split(" ",2)[0];
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
