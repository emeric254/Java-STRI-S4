
/**
 * 
 */
package stri.java_connect.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author emeric
 *
 */
public abstract class JSONLoader
{
    /**
     * retourne un objet String depuis l'objet JSONObject qui le contient et la clef qui correpond
     * 
     * @param json le JSONObject
     * @param key la clef de l'objet String
     * @return l'objet String
     */
    public static String readStringJSONObject(JSONObject json, String key)
    {
        String temp = "";
        try
        {
            temp = json.getString(key);
        }
        catch (JSONException e)
        {
            // nothing read
            temp = "";
        }
        return temp;
    }
    
    /**
     * retourne un objet String depuis l'objet JSONObject sous forme de String qui le contient et la clef qui correpond
     * 
     * @param jsonString le JSONObject sous forme de String
     * @param key la clef de l'objet String
     * @return l'objet String
     */
    public static String readStringJSONObjectAsString(String jsonString, String key)
    {
        JSONObject json = new JSONObject(jsonString);
        return readStringJSONObject(json, key);
    }
}
