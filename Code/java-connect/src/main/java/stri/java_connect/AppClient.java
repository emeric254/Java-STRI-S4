/**
 * 
 */
package stri.java_connect;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.ihm.IHM;

/**
 * @author emeric
 *
 */
public class AppClient
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        new IHM(new ClientAnnuaire());
	}
}
