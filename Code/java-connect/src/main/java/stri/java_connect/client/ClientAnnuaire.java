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
public class ClientAnnuaire
{
	private final static int portDefaut = 12345;
	private Client client;
	/**
	 * 
	 */
	public ClientAnnuaire()
	{
		this(portDefaut);
	}
	
	/**
	 * 
	 */
	public ClientAnnuaire(int pPort)
	{
		client = new Client(pPort);
	}
	
	/**
	 * @param u
	 * @throws IOException 
	 */
	public void inscription(Utilisateur u) throws IOException
	{
		// TODO
		System.out.println(client.communiquer(ProtocoleAnnuaire.requeteInscrire(u.toString())));
	}
	
	/**
	 * @param courriel
	 * @param mdp
	 * @throws IOException 
	 */
	public void connexion(String courriel, String mdp) throws IOException
	{
		// TODO
		System.out.println(client.communiquer(ProtocoleAnnuaire.requeteConnexion(courriel, mdp)));
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void consulterProfils() throws IOException
	{
		// TODO
		System.out.println(client.communiquer(ProtocoleAnnuaire.requeteConsulterProfils()));
	}
	
	/**
	 * @param courriel
	 * @throws IOException 
	 */
	public void consulterProfil(String courriel) throws IOException
	{
		// TODO
		System.out.println(client.communiquer(ProtocoleAnnuaire.requeteConsulterProfil(courriel)));
	}
}
