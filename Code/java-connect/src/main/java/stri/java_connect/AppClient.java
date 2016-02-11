/**
 * 
 */
package stri.java_connect;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.ihm.IHM;

/**
 * @author emeric, remi
 *
 */
public class AppClient
{
    /**
     * Main AppClient.java
     * 
     * @param args arguments de lancement
     */
	public static void main(String[] args)
	{
        new IHM(new ClientAnnuaire());
	}
}
