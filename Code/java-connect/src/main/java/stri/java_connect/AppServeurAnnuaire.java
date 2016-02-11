/**
 * 
 */
package stri.java_connect;

import stri.java_connect.modele.Annuaire;
import stri.java_connect.server.ServeurAnnuaire;

/**
 * @author emeric
 *
 */
public class AppServeurAnnuaire
{
	private final static int portDefaut = 12345;

    /**
     * Main AppServeurAnnuaire.java
     * 
     * @param args arguments de lancement, le premier est le port d'ecoute du serveur
     */
	public static void main(String[] args)
	{
		int port = portDefaut;
		if (args.length > 0)
		{
			port = Integer.parseInt(args[0]); //
			if (port<=0 || port>=65536)
			{
				port = portDefaut;
			}
		}
        System.out.println( "Lancement du serveur sur le port " + port);
        //
        // TODO chargement annuaire ?
        // TODO annuaire = sqlite ?
        Annuaire test = new Annuaire();
        //
        new ServeurAnnuaire(port, test).start(); // blocking !!!
        //
        System.out.println("Fin");
	}
}
