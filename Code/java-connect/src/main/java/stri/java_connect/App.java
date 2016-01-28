package stri.java_connect;


import stri.java_connect.modele.Utilisateur;
import stri.java_connect.utils.JSONValidateur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Utilisateur test = new Utilisateur();
        
        test.setNom("nomDeTest");
        test.setIdentifiant("IDENTIFIANT");
        test.setMotDePasse("MOTDEPASSE");
        test.setPrivilege("utilisateur");
        test.setDateDiplome((long) 1234567890);
        test.setCourriel("test@test.test");
        test.setTelephone("0123456789");
        test.getCompetences().add("competence1");
        test.getCompetences().add("competence2");
        test.getCompetences().add("competence3");
        test.getCompetences().add("competence4");

        JSONValidateur.valider(test);
        

        Utilisateur test2 = new Utilisateur("id", "mdp");

        JSONValidateur.valider(test2.toString());

        JSONValidateur.valider(test2);

        Utilisateur test3 = new Utilisateur("nom", "telephone", "courriel");
        
        JSONValidateur.valider(test3);

        System.out.println("END");
    }
}
