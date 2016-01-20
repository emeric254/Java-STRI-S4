package stri.java_connect;

import org.json.JSONObject;

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

        System.out.println(test);
        
        JSONObject testJson = new JSONObject(test);
        JSONObject.testValidity(testJson);
        System.out.println(testJson.toString());
        
        System.out.println("END");
    }
}
