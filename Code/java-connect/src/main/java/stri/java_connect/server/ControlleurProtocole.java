/**
 * 
 */
package stri.java_connect.server;

/**
 * @author emeric
 *
 */
public abstract class ControlleurProtocole
{
    public abstract String traiterRequete (String requete);

    public static String requeteMethode (String requete)
    {
        return requete.trim().split(" ")[0];
    }
    
    public static String requeteCorps (String requete)
    {
        return requete.trim().split(" ")[1];
    }
}
