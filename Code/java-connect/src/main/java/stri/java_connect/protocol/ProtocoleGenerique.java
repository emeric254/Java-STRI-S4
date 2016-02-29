/**
 * 
 */
package stri.java_connect.protocol;

import org.json.JSONArray;
import org.json.JSONObject;

import stri.java_connect.utils.MD5Hasher;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleGenerique
{
	public final static String consulterMethod = "CONSULTER ";
	public final static String supprimerMethod = "SUPPRIMER ";
	public final static String inscrireMethod = "INSCRIRE ";
	public final static String modifierMethod = "MODIFIER ";
	
	protected final static String code = "{\"code\" : ";
	protected final static String data =  ", \"data\" : \"";
	protected final static String fin = "\"}";

	
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
	
	
	//-------------------------------------------------------------------------
	// createurs de reponses

	/**
	 * Creation de la reponse pour une erreur d'implementation manquante
	 * 
	 * @return la reponse
	 */
	public static String erreurImplementionManquante()
	{
		return code + -2 + data + "implementation manquante" + fin;
	}
	
	/**
	 * Creation de la reponse pour une erreur serveur
	 * 
	 * @return la reponse
	 */
	public static String erreurServeur()
	{
		return code + -1 + data + "erreur du serveur" + fin;
	}
	

	/**
	 * Creation de la reponse ok de confirmation (sans donnees)
	 * 
	 * @return la reponse
	 */
	public static String ok()
	{
		return code + 0 + data + fin;
	}
	

	/**
	 * Creation de la reponse ok de confirmation (avec donnees)
	 * 
	 * @param donnees les donnees a envoyer dans la reponse
	 * @return la reponse
	 */
	public static String ok(String donnees)
	{
		return code + 0 + ", \"data\" : " + donnees + " }";
	}
	

	/**
	 * Creation de la reponse pour une erreur de requete
	 * 
	 * @return la reponse
	 */
	public static String erreurRequete()
	{
		return code + 1 + data + "mauvaise requete" + fin;
	}
	

	/**
	 * Creation de la reponse pour une erreur de droits
	 * 
	 * @return la reponse
	 */
	public static String erreurInterdit()
	{
		return code + 2 + data + "interdit" + fin;
	}
	

	/**
	 * Creation de la reponse pour une erreur de connexion perdue
	 * 
	 * @return la reponse
	 */
	public static String erreurDeconnexion()
	{
		return code + 3 + data + "connexion perdue" + fin;
	}

	//-------------------------------------------------------------------------
	// testeurs de reponses

	/**
	 * Tester si une reponse est une erreur d'implementation manquante
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurImplementionManquante(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == -2;
	}
	
	/**
	 * Tester si une reponse est une erreur serveur
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurServeur(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == -1;
	}
	

	/**
	 * Tester si une reponse est une confirmation
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est une confirmation, false sinon
	 */
	public static boolean isOk(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 0;
	}
	

	/**
	 * Tester si une reponse est une erreur de requete
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurRequete(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 1;
	}
	

	/**
	 * Tester si une reponse est une erreur de droits
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurInterdit(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 2;
	}
	

	/**
	 * Tester si une reponse est une erreur de connexion perdue
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurDeconnexion(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 3;
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
		return ControlleurProtocole.requeteMethode(requete).equals("SUPPRIMER");
	}

	//-------------------------------------------------------------------------
	// reponse ok - donnees

	/**
	 * Valide les donnees d'une reponse
	 * 
	 * @param reponse la reponse dont on veut valider les donnees
	 * @return true si les donnees sont valides, false sinon
	 */
	public static boolean valideDonnees(String reponse)
	{
		// TODO voir si on peut faire mieux
		return ControlleurProtocole.reponseDonnees(reponse).length() >= 0;
	}

	/**
	 * Extrait les donnees d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire les donnees
	 * @return les donnees de la reponse
	 */
	public static String extraireDonnees(String reponse)
	{
		return ControlleurProtocole.reponseDonnees(reponse);
	}

	/**
	 * Extrait le JSONObject d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire le JSONObject
	 * @return le JSONObject
	 */
	public static JSONObject extraireJSONObject(String reponse)
	{
    	JSONObject js = new JSONObject(reponse);
    	return js.getJSONObject("data");
	}

	/**
	 * Extrait le JSONArray d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire le JSONArray
	 * @return le JSONArray
	 */
	public static JSONArray extraireJSONArray(String reponse)
	{
    	JSONObject js = new JSONObject(reponse);
    	return js.getJSONArray("data");
	}
}
