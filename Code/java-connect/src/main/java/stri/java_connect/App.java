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

        JSONObject testJson = new JSONObject(test);
        JSONObject.testValidity(testJson);
        

        Utilisateur test2 = new Utilisateur("id", "mdp");

        JSONObject testJson1 = new JSONObject(test2);
        JSONObject.testValidity(testJson1);
        System.out.println(testJson1.toString());
        
        JSONObject testJson2 = new JSONObject(test2.toString());
        JSONObject.testValidity(testJson2);
        System.out.println(testJson2.toString());

        Utilisateur test3 = new Utilisateur("nom", "telephone", "courriel");
        
        JSONObject testJson3 = new JSONObject(test3.toString());
        JSONObject.testValidity(testJson3);
        
        test.fromJSONString(testJson3.toString());
        System.out.println("END");
    }
}
