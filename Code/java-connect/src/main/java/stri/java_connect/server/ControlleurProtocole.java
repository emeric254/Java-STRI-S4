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
    
    public static String [] requeteParametres (String requete)
    {
    	// TODO messages aren't like that, rework this function
        String temp = requete.trim().substring(requeteMethode(requete).length()+1).trim();
        while ( temp.contains("  "))
            temp = temp.replace("  ", " ");
        return temp.split(" ");
    }
}
