/**
 * 
 */
package stri.java_connect.protocol;

import java.util.Date;

import stri.java_connect.utils.CourrielValidateur;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleMessagerie extends ProtocoleGenerique
{
	public final static String utilisateursURI = "/utilisateurs";
	public final static String messagerieURI = "/messagerie";

	/**
	 * @param idutilisateur
	 * @param msg
	 * @return creation de la requete d'envoi de message differe
	 */
	public static String requeteEnvoiMessageDiffere(String idutilisateur, String msg)
	{
		return inscrireMethod + messagerieURI + "/" + idutilisateur + " ;" + msg;
	}

	/**
	 * Inscription d'un utilisateur pour la messagerie
	 * @param idutilisateur
	 * @param details
	 * @return requete a envoyer
	 */
	public static String requeteInscrireUtilisateur(String idutilisateur, String details)
	{
		return inscrireMethod + utilisateursURI + "/" + idutilisateur + " ;" + details;
	}

	/**
	 * Demande de consultation des utilisateurs
	 * @return la requete
	 */
	public static String requeteConsulterListeUtilisateurConnectes()
	{
		return consulterMethod + utilisateursURI;
	}
	
	/**
	 * Demande des details de l'utilisateur choisi
	 * @param courriel
	 * @return la requete
	 */
	public static String requeteConsulterDetailsUtilisateurConnecte(String courriel)
	{
		return consulterMethod + utilisateursURI + "/" + courriel;
	}
	
	/**
	 * Demande pour consulter les messages manques
	 * @return la requete
	 */
	public static String requeteConsulterListeMessagesManques()
	{
		return consulterMethod + messagerieURI;
	}
	
	/**
	 * Consulter le detail des messages manques
	 * @param idmsg
	 * @return la requete
	 */
	public static String requeteConsulterDetailsMessagesManque(String idmsg)
	{
		return consulterMethod + messagerieURI + "/" + idmsg;
	}
	
	/**
	 * Supprimer la liste des messages manques
	 * @return la requete
	 */
	public static String requeteSupprimerListeMessagesManques()
	{
		return supprimerMethod + messagerieURI;
	}
	
	/**
	 * Deconnexion du serveur
	 * @return la requete
	 */
	public static String requeteDeconexion()
	{
		return supprimerMethod + utilisateursURI;
	}
	
	/**
	 * Suppression des messages manques
	 * @param idmsg
	 * @return la requete
	 */
	public static String requeteSupprimerMessageManque(String idmsg)
	{
		return supprimerMethod + messagerieURI + "/" + idmsg;
	}
	
	//
	// validateur

	/**
	 * Valide l'envoi de la requete d'envoi de message differe
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteEnvoiMessageDiffere(String requete)
	{
		if(ControlleurProtocole.requeteURI(requete).startsWith(messagerieURI + "/"))
			return CourrielValidateur.valider(ControlleurProtocole.requeteURI(requete).replace(messagerieURI + "/", ""));
		return false;
	}
	
	/**
	 * Valider la requete de deconnexion 
	 * @return la requete
	 */
	public static boolean validerRequeteDeconexion(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith(utilisateursURI);
	}

	/**
	 * Valide la requete d'inscription d'un utilisateur
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteInscrireUtilisateur(String requete)
	{
		if(ControlleurProtocole.requeteURI(requete).startsWith(utilisateursURI + "/"))
			return CourrielValidateur.valider(ControlleurProtocole.requeteURI(requete).replace(utilisateursURI + "/", ""));
		return false;
	}

	/**
	 * Valide la requete pour consulter la liste des utilisateurs connectes
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteConsulterListeUtilisateurConnectes(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).equals(utilisateursURI);
	}
	
	/**
	 * Valide la requete pour consulter le details de l'utilisateur connecte
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteConsulterDetailsUtilisateurConnecte(String requete)
	{
		if(ControlleurProtocole.requeteURI(requete).startsWith(utilisateursURI + "/"))
			return CourrielValidateur.valider(ControlleurProtocole.requeteURI(requete).replace(utilisateursURI + "/", ""));
		return false;
	}

	/**
	 * Valide la requete pour consulter la liste des messages manques
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteConsulterListeMessagesManques(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).equals(messagerieURI);
	}

	/**
	 * Valide la requete pour consulter le details des messages manque
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteConsulterDetailsMessagesManque(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith(messagerieURI + "/");
	}

	/**
	 * Valide la requete pour supprimer la liste des messages manques
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteSupprimerListeMessagesManques(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).equals(messagerieURI);
	}

	/**
	 * Valide la requete pour supprimer le message manque
	 * @param requete
	 * @return la requete
	 */
	public static boolean validerRequeteSupprimerMessageManque(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith(messagerieURI);
	}

	//
	
	/**
	 * Permet d'extraire les messages manques
	 * @param requete
	 * @return la requete
	 */
	public static String extraireIdMessageManqueURI(String requete)
	{
		return requete.replace(messagerieURI + "/", "");
	}

	/**
	 * Recupere le message direct
	 * @param msg
	 * @return la requete
	 */
	public static String requeteMessageDirect(String msg)
	{
		return "MESSAGE " + new Date().getTime() + " " + msg;
	}
	
	/**
	 * Tester si la requete est un message direct
	 * @param requete
	 * @return la requete
	 */
	public static boolean isMessageDirect(String requete)
	{
		return requete.startsWith("MESSAGE ") && extraireDateMessageDirect(requete) != null && extraireMessageMessageDirect(requete) != null;
	}
	
	/**
	 * Extrait l'heure du message
	 * @param requete
	 * @return la requete
	 */
	public static String extraireTimestampMessageDirect(String requete)
	{
		return requete.substring(8).split(" ",2)[0];
	}
	
	/**
	 * Recupere le message direct
	 * @param requete
	 * @return la requete
	 */
	public static String extraireMessageMessageDirect(String requete)
	{
		return requete.substring(8).split(" ",2)[1];
	}

	/**
	 * Recuperer la date du message direct
	 * @param requete
	 * @return la requete
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
