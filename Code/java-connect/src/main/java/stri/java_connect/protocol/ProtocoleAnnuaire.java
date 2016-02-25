/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.utils.CourrielValidateur;
import stri.java_connect.utils.JSONValidateur;
import stri.java_connect.utils.MD5Hasher;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleAnnuaire extends ProtocoleGenerique
{
	private final static String profilsURI = "/profils";
	
	//-------------------------------------------------------------------------
	// constructeurs de requete
	
	/**
	 * Requete de consultation de la liste des profils
	 * 
	 * @return la requete
	 */
	public static String requeteConsulterProfils()
	{
		return "CONSULTER " + profilsURI;
	}
	
	/**
	 * Requete de consultation des details d'un profil
	 * 
	 * @param courriel le courriel du profil a consulter
	 * @return la requete
	 */
	public static String requeteConsulterProfil(String courriel)
	{
		return "CONSULTER "+ profilsURI + "/" + courriel;
	}
	
	/**
	 * Requete de connexion
	 * 
	 * @param courriel le courriel la connexion
	 * @param motDePasse le mot de passe pour la connexion
	 * @return la requete
	 */
	public static String requeteConnexion(String courriel, String motDePasse)
	{
		return "CONNEXION " + courriel + ":" + motDePasse;
	}
	
	/**
	 * Requete de connexion, le mot de passe sera hash en MD5
	 * 
	 * @param courriel le courriel la connexion
	 * @param motDePasse le mot de passe pour la connexion
	 * @return la requete
	 */
	public static String requeteConnexionHashMD5(String courriel, String motDePasse)
	{
		return "CONNEXION " + courriel + ":MD5:" + MD5Hasher.hashString(motDePasse);
	}

	/**
	 * Requete d'inscription d'un profil utilisateur
	 * 
	 * @param utilisateurJson le profil utilisateur a inscrire
	 * @return la requete
	 */
	public static String requeteInscrire(String utilisateurJson)
	{
		return "INSCRIRE ;" + utilisateurJson;
	}

	/**
	 * Requete de modification d'un profil utilisateur
	 * 
	 * @param courriel le courriel de l'utilisateur a modifier
	 * @param utilisateurJson le profil utilisateur mis a jour
	 * @return la requete
	 */
	public static String requeteModifierProfil(String courriel, String utilisateurJson)
	{
		return "MODIFIER " + profilsURI + "/" + courriel + ";"  + utilisateurJson;
	}
	
	/**
	 * Requete de suppression d'un profil utilisateur
	 * 
	 * @param courriel le courriel du profil a supprimer
	 * @return la requete
	 */
	public static String requeteSuppressionProfil(String courriel)
	{
		return "SUPPRESSION " + profilsURI + "/" + courriel;
	}
	
	//-------------------------------------------------------------------------
	// testeurs de type de requete
	
	/**
	 * Tester si la requete est une requete de consultation
	 * 
	 * @param requete la requete a tester
	 * @return true si c'est une requete de consultation, false sinon
	 */
	public static boolean isRequeteConsulter(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONSULTER");
	}
	
	/**
	 * Tester si la requete est une requete de connexion
	 * 
	 * @param requete la requete a tester
	 * @return true si c'est une requete de connexion, false sinon
	 */
	public static boolean isRequeteConnexion(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONNEXION");
	}
	
	/**
	 * Tester si la requete est une requete d'inscription
	 * 
	 * @param requete la requete a tester
	 * @return true si c'est une requete d'inscription, false sinon
	 */
	public static boolean isRequeteInscrire(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("INSCRIRE");
	}
	
	/**
	 * Tester si la requete est une requete de modification
	 * 
	 * @param requete la requete a tester
	 * @return true si c'est une requete de modification, false sinon
	 */
	public static boolean isRequeteModifier(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("MODIFIER");
	}
	
	/**
	 * Tester si la requete est une requete de suppression
	 * 
	 * @param requete la requete a tester
	 * @return true si c'est une requete de suppression, false sinon
	 */
	public static boolean isRequeteSuppression(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("SUPPRESSION");
	}

	//-------------------------------------------------------------------------
	// valideurs de corps requete
	
	/**
	 * Valider la requete de consultation de la liste des profils
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete de consultation de la liste des profils valide, false sinon
	 */
	public static boolean validerRequeteConsulterProfils(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).equals(profilsURI);
	}
	
	/**
	 * Valider la requete de consultation des details d'un profil
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete de consultation des details d'un profil valide, false sinon
	 */
	public static boolean validerRequeteConsulterProfil(String requete)
	{
		if(ControlleurProtocole.requeteURI(requete).startsWith(profilsURI + "/"))
		{
			return CourrielValidateur.valider(ControlleurProtocole.requeteURI(requete).replace(profilsURI + "/", ""));
		}
		return false;
	}
	
	/**
	 * Valider la requete de connexion
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete de connexion valide, false sinon
	 */
	public static boolean validerRequeteConnexion(String requete)
	{
		// HACK !!! utilisation de requeteURI(requete) pour récuperer le couple d'indentification
		if(ControlleurProtocole.requeteURI(requete).contains(":"))
		{
			String courriel = ControlleurProtocole.requeteURI(requete).split(":",2)[0];
			// TODO test mot de passe vide ?
			return CourrielValidateur.valider(courriel);
		}
		return false;
	}
	
	/**
	 * Valider que le type de mot de passe d'une connexion est un hash MD5
	 * 
	 * @param mdp le mot de passe brut
	 * @return true si c'est un mot de passe sous forme de hash MD5, false sinon
	 */
	public static boolean validerTypeMotDePasseHashMD5(String mdp)
	{
		return mdp.startsWith("MD5:");
	}
	
	/**
	 * Valider la requete de'inscription
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete d'inscription valide, false sinon
	 */
	public static boolean validerRequeteInscrire(String requete)
	{
		// TODO valider modele aussi ?
		return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	/**
	 * Valider la requete de modification de profil
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete de modification de profil valide, false sinon
	 */
	public static boolean validerRequeteModifierProfil(String requete)
	{
		// TODO valider modele aussi ?
		return ControlleurProtocole.requeteURI(requete).startsWith(profilsURI + "/");
		//return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	/**
	 * Valider la requete de suppression d'un profil
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete de suppression d'un profil valide, false sinon
	 */
	public static boolean validerRequeteSuppressionProfil(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith(profilsURI + "/");
	}
}
