/**
 * 
 */
package stri.java_connect.server;

import org.json.JSONObject;

/**
 * @author emeric
 *
 */
public abstract class ControlleurProtocole
{
    /**
     * @param requete
     * @return
     */
    public abstract String traiterRequete (String requete);

    /**
     * @param requete
     * @return
     */
    public static String requeteMethode (String requete)
    {
        return requete.trim().split(" ")[0];
    }
    
    /**
     * @param requete
     * @return
     */
    public static String requeteURI (String requete)
    {
    	return requete.trim().split("\n",2)[0].split(" ")[1];
    }
    
    /**
     * @param requete
     * @return
     */
    public static String requeteCorps (String requete)
    {
        return requete.trim().split("\n",2)[1];
    }

    /**
     * @param reponse
     * @return
     */
    public abstract String traiterReponse (String reponse);
    
    /**
     * @param reponse
     * @return
     */
    public static String reponseCode(String reponse)
    {
    	JSONObject js = new JSONObject(reponse);
    	return "" + js.getInt("code");
    }
    
    /**
     * @param reponse
     * @return
     */
    public static String reponseDonnees(String reponse)
    {
    	JSONObject js = new JSONObject(reponse);
    	return js.getString("data");
    }
}
