/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.utils.CourrielValidateur;
import stri.java_connect.utils.JSONValidateur;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleAnnuaire extends ProtocoleGenerique
{
	private final static String profilsURI = "/profils";
	private final static String competencesURI = "/compete";
	
	//-------------------------------------------------------------------------
	// constructeurs de requete
	
	/**
	 * Requete de consultation de la liste des profils
	 * 
	 * @return la requete
	 */
	public static String requeteConsulterProfils()
	{
		return consulterMethod + profilsURI;
	}
	
	/**
	 * Requete de consultation des details d'un profil
	 * 
	 * @param courriel le courriel du profil a consulter
	 * @return la requete
	 */
	public static String requeteConsulterProfil(String courriel)
	{
		return consulterMethod + profilsURI + "/" + courriel;
	}

	/**
	 * Requete d'inscription d'un profil utilisateur
	 * 
	 * @param utilisateurJson le profil utilisateur a inscrire
	 * @return la requete
	 */
	public static String requeteInscrireProfil(String utilisateurJson)
	{
		return inscrireMethod + profilsURI + " ;" + utilisateurJson;
	}

	/**
	 * Requete d'inscription d'un profil utilisateur
	 * 
	 * @param utilisateurJson le profil utilisateur a inscrire
	 * @return la requete
	 */
	public static String requeteInscrireLike(String utilisateurJson, String competence)
	{
		return inscrireMethod + profilsURI + competencesURI + "/" + competence + " ; ";
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
		return modifierMethod + profilsURI + "/" + courriel + ";"  + utilisateurJson;
	}
	
	/**
	 * Requete de suppression d'un profil utilisateur
	 * 
	 * @param courriel le courriel du profil a supprimer
	 * @return la requete
	 */
	public static String requeteSuppressionProfil(String courriel)
	{
		return supprimerMethod + profilsURI + "/" + courriel;
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
	 * Valider la requete de l'inscription d'un profil
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete d'inscription valide, false sinon
	 */
	public static boolean validerRequeteInscrireProfil(String requete)
	{
		// TODO valider modele aussi ?
		return ControlleurProtocole.requeteURI(requete).equals(profilsURI) &&
				JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	/**
	 * Valider la requete de l'inscription d'un like
	 * 
	 * @param requete la requete a valider
	 * @return true si c'est une requete d'inscription valide, false sinon
	 */
	public static boolean validerRequeteInscrireLike(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith(profilsURI + competencesURI + "/" ) &&
				JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
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
