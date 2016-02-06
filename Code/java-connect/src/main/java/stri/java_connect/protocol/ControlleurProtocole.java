/**
 * 
 */
package stri.java_connect.protocol;

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
    	return requete.split("\n")[0].replace(requeteMethode(requete), "").trim();
    }
    
    /**
     * @param requete
     * @return
     */
    public static String requeteCorps (String requete)
    {
        return requete.contains("\n") ? requete.trim().split("\n",2)[1] : "";
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
    public static int reponseCode(String reponse)
    {
    	JSONObject js = new JSONObject(reponse);
    	return js.getInt("code");
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
