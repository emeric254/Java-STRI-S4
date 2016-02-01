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
	 * @param jsonAvalider
	 * @return
	 */
	public static boolean valider(String jsonAvalider)
	{
	    try
	    {
	    	JSONObject.testValidity(new JSONObject(jsonAvalider));
	    }
	    catch(JSONException e)
	    {
	    	return false;
	    }
		return true;
	}
	
	/**
	 * @param u
	 * @return
	 */
	public static boolean valider(Utilisateur u)
	{
	    return valider(u.toString());
	}
}
