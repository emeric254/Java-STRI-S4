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
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO port = argument
        System.out.println( "Lancement du serveur sur le port [12345]" );
        //
        // TODO chargement annuaire ?
        // TODO annuaire = sqlite ?
        Annuaire test = new Annuaire();
        //
        new ServeurAnnuaire(test).start(); // blocking !!!
        //
        System.out.println("Fin");

	}

}
