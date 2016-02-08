/**
 * 
 */
package stri.java_connect.utils;

/**
 * @author emeric
 *
 */
public abstract class IHMUtilitaires
{
    /**
     * Ecris 25 sauts de ligne pour simuler un effacement du terminal :)
     * 
     */
    public static void cleanTerminal()
    {
	    for (int i=0; i< 25; i++)
	    {
	        System.out.println("");
	    }
	}
}
