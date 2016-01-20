package stri.java_connect;

import stri.java_connect.modele.Utilisateur;

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
        System.out.println(test);
        
        test.setNom("nomDeTest");
        System.out.println(test);
        
        test.setIdentifiant("IDENTIFIANT");
        System.out.println(test);
        
        test.setMotDePasse("MOTDEPASSE");
        System.out.println(test);
        
        test.setPrivilege("utilisateur");
        System.out.println(test);
        
        test.setDateDiplome((long) 1234567890);
        System.out.println(test);
        
        test.setCourriel("test@test.test");
        System.out.println(test);
        
        test.setTelephone("0123456789");
        System.out.println(test);

        test.getCompetences().add("competence1");
        test.getCompetences().add("competence2");
        test.getCompetences().add("competence3");
        test.getCompetences().add("competence4");
        System.out.println(test);
        
        System.out.println("END");;
    }
}
