package stri.java_connect;

import org.apache.pivot.wtk.DesktopApplicationContext;

import stri.java_connect.ihm.*;

import java.io.IOException;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;

/**
 * @author emeric, remi, thomas
 *
 */
public class App 
{
    /**
     * Main App.java
     * 
     * @param args arguments de lancement
     */
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //
		DesktopApplicationContext.main(MenuPrincipal.class, args);
    }
    
    /**
     * Initialiser l'annuaire du serveur
     * 
     * @throws IOException exception si la communication echoue
     */
    private static void initAnnuaire() throws IOException
    {
        ClientAnnuaire client = new ClientAnnuaire();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setCourriel("remi.barbaste@univ-tlse3.fr");
        utilisateur.setMotDePasse("remimdp");
        utilisateur.setPermissionLecture("anonyme"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Remi BARBASTE");
        utilisateur.setTelephone("0123456798");
        utilisateur.setDateDiplome(Long.parseLong("2015"));
        utilisateur.addCompetence("Mathématiques");
        String reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
       
        client = new ClientAnnuaire();
        utilisateur = new Utilisateur();
        utilisateur.setCourriel("emeric.tosi@univ-tlse3.fr");
        utilisateur.setMotDePasse("emericmdp");
        utilisateur.setPermissionLecture("anonyme"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Emeric TOSI");
        utilisateur.setTelephone("1234657890");
        utilisateur.setDateDiplome(Long.parseLong("2014"));
        utilisateur.addCompetence("Informatique");
        utilisateur.addCompetence("Base de données");
        reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
       
        client = new ClientAnnuaire();
        utilisateur = new Utilisateur();
        utilisateur.setCourriel("thomas.maury@univ-tlse3.fr");
        utilisateur.setMotDePasse("thomasmdp");
        utilisateur.setPermissionLecture("utilisateur"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Thomas Maury");
        utilisateur.setTelephone("2345678901");
        utilisateur.setDateDiplome(Long.parseLong("2012"));
        utilisateur.addCompetence("français");
        utilisateur.addCompetence("SVT");
        utilisateur.addCompetence("sport");
        reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
    }
}
