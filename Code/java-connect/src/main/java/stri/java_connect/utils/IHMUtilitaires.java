/**
 * 
 */
package stri.java_connect.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    /**
     * Effectuer uen saisie utilisateur
     * 
     * @return la saisie de l'utilisateur
     */
    public static String saisie()
    {
    	String temp = "";
        try
        {
        	temp = new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        catch(IOException e)
        {
        	temp= "";
        }
        return temp;
    }
    
    /**
     * Effectuer uen saisie utilisateur
     * 
     * @param affichage quelque chose a afficher a l'utilisateur
     * @return la saisie de l'utilisateur
     */
    public static String saisie(String affichage)
    {
    	System.out.println(affichage);
    	return saisie();
    }
}
