/**
 * 
 */
package stri.java_connect.client;

import java.io.IOException;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;

/**
 * @author emeric
 *
 */
public class ControlleurClient
{
	private final static int portDefaut = 12345;
	Client client;
	
	/**
	 * 
	 */
	public ControlleurClient()
	{
		this(portDefaut);
	}
	
	/**
	 * 
	 */
	public ControlleurClient(int pPort)
	{
		client = new Client(pPort);
		//
		// TODO ici ihm etc ...
		try
		{
			System.out.println(client.communiquer(ProtocoleAnnuaire.requeteInscrire(new Utilisateur("test@test.test","motdepasse").toString())));
			System.out.println(client.communiquer(ProtocoleAnnuaire.requeteConsulterProfils()));
			System.out.println(client.communiquer(ProtocoleAnnuaire.requeteConsulterProfil("test@test.test")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
