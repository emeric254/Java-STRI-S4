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
     * Traiter une requete pour generer une reponse
     * 
     * @param requete la requete a traiter
     * @return la reponse
     */
    public abstract String traiterRequete (String requete);

    /**
     * Extraire la methode d'une requete
     * 
     * @param requete la requete ou extraire la methode
     * @return la methode
     */
    public static String requeteMethode (String requete)
    {
        return requete.trim().split(" ")[0];
    }
    
    /**
     * Extraire l'URI d'une requete
     * 
     * @param requete la requete ou extraire l'URI
     * @return l'URI
     */
    public static String requeteURI (String requete)
    {
    	return requete.split(";")[0].replace(requeteMethode(requete), "").trim();
    }
    
    /**
     * Extraire le corps d'une requete
     * 
     * @param requete la requete ou extraire le corps
     * @return le corps
     */
    public static String requeteCorps (String requete)
    {
        return requete.contains(";") ? requete.trim().split(";",2)[1] : "";
    }

    /**
     * @param reponse
     * @return
     */
    public abstract String traiterReponse (String reponse);
    
    /**
     * Extraire le code d'une reponse
     * 
     * @param reponse la reponse ou extraire le code
     * @return le code
     */
    public static int reponseCode(String reponse)
    {
    	JSONObject js = new JSONObject(reponse);
    	return js.getInt("code");
    }
    
    /**
     * Extraire les donnees d'une reponse
     * 
     * @param reponse la reponse ou extraire les donnees
     * @return les donnees
     */
    public static String reponseDonnees(String reponse)
    {
    	JSONObject js = new JSONObject(reponse);
    	return js.getString("data");
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public abstract ControlleurProtocole clone();
}
