/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.server.ControlleurProtocole;
import stri.java_connect.utils.CourrielValidateur;
import stri.java_connect.utils.JSONValidateur;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleAnnuaire
{
	//-------------------------------------------------------------------------
	// constructeurs de requete
	
	public static String requeteConsulterProfils()
	{
		return "CONSULTER /profils";
	}
	
	public static String requeteConsulterProfil(String courriel)
	{
		return "CONSULTER /profils/" + courriel;
	}
	
	public static String requeteConnexion(String courriel, String motDePasse)
	{
		return "CONNEXION " + courriel + ":" + motDePasse;
	}

	public static String requeteInscrire(String utilisateurJson)
	{
		return "INSCRIRE " + utilisateurJson;
	}

	public static String requeteModifier(String utilisateurJson)
	{
		return "MODIFIER " + utilisateurJson;
	}
	
	//-------------------------------------------------------------------------
	// testeurs de type de requete
	
	public static boolean isRequeteConsulter(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONSULTER");
	}
	
	public static boolean isRequeteConnexion(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("CONNEXION");
	}
	
	public static boolean isRequeteInscrire(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("INSCRIRE");
	}
	
	public static boolean isRequeteModifier(String requete)
	{
		return ControlleurProtocole.requeteMethode(requete).equals("MODIFIER");
	}

	//-------------------------------------------------------------------------
	// testeur de validite du corps requete
	
	public static boolean validerRequeteConsulterProfils(String requete)
	{
		return ControlleurProtocole.requeteCorps(requete).equals("/profils");
	}
	
	public static boolean validerRequeteConsulterProfil(String requete)
	{
		if(ControlleurProtocole.requeteCorps(requete).startsWith("/profils/"))
		{
			String courriel = ControlleurProtocole.requeteCorps(requete).substring(9);
			return CourrielValidateur.valider(courriel);
		}
		return false;
	}
	
	public static boolean validerRequeteConnexion(String requete)
	{
		if(ControlleurProtocole.requeteCorps(requete).contains(":"))
		{
			// TODO valider mot de passe aussi ?
			String courriel = ControlleurProtocole.requeteCorps(requete).split(":")[0];
			return CourrielValidateur.valider(courriel);
		}
		return false;
	}
	
	public static boolean validerRequeteInscrire(String requete)
	{
		// TODO valider modele aussi ?
		return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
	
	public static boolean validerRequeteModifier(String requete)
	{
		// TODO valider modele aussi ?
		return JSONValidateur.valider(ControlleurProtocole.requeteCorps(requete));
	}
}
