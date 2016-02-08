/**
 * 
 */
package stri.java_connect.client;

import java.io.IOException;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;
import stri.java_connect.utils.MD5Hasher;

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
	public String inscription(Utilisateur u) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteInscrire(u.toString()));
	}
	
	/**
	 * @param courriel
	 * @param mdp
	 * @return 
	 * @throws IOException 
	 */
	public String connexion(String courriel, String mdp) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteConnexion(courriel, mdp));
	}
	
	/**
	 * @param courriel
	 * @param mdp
	 * @throws IOException 
	 */
	public String connexionMD5(String courriel, String mdp) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteConnexionHashMD5(courriel,MD5Hasher.hashString(mdp)));
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public String consulterProfils() throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteConsulterProfils());
	}
	
	/**
	 * @param courriel
	 * @throws IOException 
	 */
	public String consulterProfil(String courriel) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteConsulterProfil(courriel));
	}
	
	/**
	 * @param utilisateur
	 * @throws IOException 
	 */
	public String modificationProfil(Utilisateur utilisateur) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteModifierProfil(utilisateur.getCourriel(), utilisateur.toString()));
	}
	
	/**
	 * @param courriel
	 * @throws IOException 
	 */
	public String suppressionProfil(String courriel) throws IOException
	{
		// TODO
		return client.communiquer(ProtocoleAnnuaire.requeteSuppressionProfil(courriel));
	}
	
	/**
	 * 
	 */
	public void deconnexion()
	{
		client.fermer();
	}
}
