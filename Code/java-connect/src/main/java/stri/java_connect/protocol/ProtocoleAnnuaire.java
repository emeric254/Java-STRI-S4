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
		return "INSCRIRE " + utilisateurJson;
	}

	/**
	 * @param utilisateurJson
	 * @return
	 */
	public static String requeteModifier(String utilisateurJson)
	{
		return "MODIFIER " + utilisateurJson;
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
	public static boolean isRequeteSupression(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("SUPRESSION");
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
	public static boolean validerRequeteSupression(String requete)
	{
		return ControlleurProtocole.requeteURI(requete).startsWith("/profils/");
	}
}
