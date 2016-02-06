package stri.java_connect;

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
        ServeurAnnuaire s = new ServeurAnnuaire(test);
        //s.start();
        s.startBlocking();
        //
        System.out.println("END");
    }
}
