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
	
	public static boolean valider(Utilisateur u)
	{
	    return valider(u.toString());
	}
}
