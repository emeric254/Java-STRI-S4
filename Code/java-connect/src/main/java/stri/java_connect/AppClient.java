/**
 * 
 */
package stri.java_connect;

import java.io.IOException;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.modele.Utilisateur;

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
        ClientAnnuaire c = new ClientAnnuaire();
        try
        {
        	// liste profils vide :
			c.consulterProfils();
			
			// connexion invalide :
			//c.connexion("invalide@test.test", "frjhfrbgy");
			
			// inscription, reussie et donc auto-connexion :
			c.inscription(new Utilisateur("test@test.test","123456"));
			//c.connexion("test@test.test", "123456");
			
			// inscription mais on est deja connecte :
			//c.inscription(new Utilisateur("deuxieme@qwerty.azerty","9876543210"));
			
			// liste profils :
			c.consulterProfils();
			
			// consulter profil :
			c.consulterProfil("test@test.test");
		}
        catch (IOException e)
        {
			e.printStackTrace();
		}
	}

}
