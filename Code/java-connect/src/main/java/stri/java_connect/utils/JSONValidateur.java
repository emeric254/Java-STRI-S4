/**
 * 
 */
package stri.java_connect.utils;

import org.json.JSONException;
import org.json.JSONObject;

import stri.java_connect.modele.Utilisateur;

/**
 * @author emeric
 *
 */
public abstract class JSONValidateur
{
	/**
	 * Valide que le contenu d'une chaine est bien du JSON
	 * 
	 * @param jsonAvalider la chaine a valider
	 * @return true si c'est bien du JSON, false sinon.
	 */
	public static boolean valider(final String jsonAvalider)
	{
	    try
	    {
	    	JSONObject.testValidity(new JSONObject(jsonAvalider));
	    }
	    catch(JSONException e)
	    {
	    	// JSON invalide
	    	return false;
	    }
		return true;
	}

	/**
	 * Valide que la représentation d'un object Utilisateur est bien du JSON
	 * 
	 * @param utilisateur l'utilisateur a verifier
	 * @return true si sa representation est bien du JSON, false sinon.
	 */
	public static boolean valider(final Utilisateur utilisateur)
	{
		// valider le chaine qui represente l'utilisateur
	    return valider(utilisateur.toString());
	}
}
