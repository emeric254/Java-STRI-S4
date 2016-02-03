/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.server.ControlleurProtocole;
import stri.java_connect.utils.CourrielValidateur;
import stri.java_connect.utils.JSONValidateur;
import stri.java_connect.utils.MD5Hasher;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleAnnuaire
{
	//-------------------------------------------------------------------------
	// constructeurs de requete
	
	/**
	 * @return
	 */
	public static String requeteConsulterProfils()
	{
		return "CONSULTER /profils";
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public static String requeteConsulterProfil(String courriel)
	{
		return "CONSULTER /profils/" + courriel;
	}
	
	/**
	 * @param courriel
	 * @param motDePasse
	 * @return
	 */
	public static String requeteConnexion(String courriel, String motDePasse)
	{
		return "CONNEXION " + courriel + ":" + motDePasse;
	}
	
	/**
	 * @param courriel
	 * @param motDePasse
	 * @return
	 */
	public static String requeteConnexionHashMD5(String courriel, String motDePasse)
	{
		return "CONNEXION " + courriel + ":MD5:" + MD5Hasher.hashString(motDePasse);
	}

	/**
	 * @param utilisateurJson
	 * @return
	 */
	public static String requeteInscrire(String utilisateurJson)
	{
		return "INSCRIRE \n" + utilisateurJson;
	}

	/**
	 * @param utilisateurJson
	 * @return
	 */
	public static String requeteModifier(String courriel, String utilisateurJson)
	{
		return "MODIFIER /profils/" + courriel + "\n"  + utilisateurJson;
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public static String requeteSuppressionProfil(String courriel)
	{
		return "SUPPRESSION /profils/" + courriel;
	}
	
	//-------------------------------------------------------------------------
	// testeurs de type de requete
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isRequeteConsulter(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONSULTER");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isRequeteConnexion(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONNEXION");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isRequeteInscrire(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("INSCRIRE");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isRequeteModifier(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("MODIFIER");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean isRequeteSuppression(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("SUPPRESSION");
	}

	//-------------------------------------------------------------------------
	// testeur de validite du corps requete
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteConsulterProfils(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).equals("/profils");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteConsulterProfil(String requete)
	{
		if(ControlleurProtocole.requeteURI(requete).startsWith("/profils/"))
		{
			return CourrielValidateur.valider(ControlleurProtocole.requeteCorps(requete));
		}
		return false;
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteConnexion(String requete)
	{
		if(ControlleurProtocole.requeteCorps(requete).contains(":"))
		{
			String courriel = ControlleurProtocole.requeteCorps(requete).split(":",2)[0];
			// TODO test mot de passe vide ?
			return CourrielValidateur.valider(courriel);
		}
		return false;
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerTypeMotDePasseHashMD5(String mdp)
	{
		return mdp.startsWith("MD5:");
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteInscrire(String requete)
	{
		// TODO valider modele aussi ?
		return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteModifier(String requete)
	{
		// TODO valider modele aussi ?
		return ControlleurProtocole.requeteURI(requete).startsWith("/profils/");
		//return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	/**
	 * @param requete
	 * @return
	 */
	public static boolean validerRequeteSuppression(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith("/profils/");
	}

	//-------------------------------------------------------------------------
	// createur de reponses

	/**
	 * @return
	 */
	public static String erreurImplementionManquante()
	{
		return "{\"code\":-2,\"data\":\"implementation manquante\"}";
	}
	
	/**
	 * @return
	 */
	public static String erreurServeur()
	{
		return "{\"code\":-1,\"data\":\"erreur du serveur\"}";
	}
	

	/**
	 * @return
	 */
	public static String ok(String donnees)
	{
		return "{\"code\":0,\"data\":\"" + donnees + "\"}";
	}
	

	/**
	 * @return
	 */
	public static String erreurRequete()
	{
		return "{\"code\":1,\"data\":\"mauvaise requete\"}";
	}
	

	/**
	 * @return
	 */
	public static String erreurInterdit()
	{
		return "{\"code\":2,\"data\":\"interdit\"}";
	}
	

	/**
	 * @return
	 */
	public static String erreurDeconnexion()
	{
		return "{\"code\":3,\"data\":\"connexion perdue\"}";
	}
}
