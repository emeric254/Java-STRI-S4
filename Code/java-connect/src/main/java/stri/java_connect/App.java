package stri.java_connect;

import stri.java_connect.client.ControlleurClient;
import stri.java_connect.modele.Annuaire;
import stri.java_connect.modele.Utilisateur;
import stri.java_connect.server.ServeurAnnuaire;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //
        Annuaire test = new Annuaire();
        test.ajoutUtilisateur(new Utilisateur("test","mdp"));
        //
        new ServeurAnnuaire(test).start();
        //
        new ControlleurClient();
        //
        System.out.println("END");
    }
}
