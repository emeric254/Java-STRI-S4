/**
 * 
 */
package stri.java_connect.server;

import stri.java_connect.modele.Annuaire;
import stri.java_connect.protocol.ControlleurProtocoleAnnuaire;

/**
 * @author emeric
 *
 */
public class ServeurAnnuaire
{
	private final static int portDefaut = 12345;
	private int port;
	private ControlleurProtocoleAnnuaire controlleur;
	private Annuaire annuaire;
	
	/**
	 * 
	 */
	public ServeurAnnuaire()
	{
		this(portDefaut, new Annuaire());
	}
	
	/**
	 * @param pPort
	 */
	public ServeurAnnuaire(int pPort)
	{
		this(pPort, new Annuaire());
	}
	
	/**
	 * @param pAnnuaire
	 */
	public ServeurAnnuaire(Annuaire pAnnuaire)
	{
		this(portDefaut,pAnnuaire);
	}
	
	/**
	 * @param pPort
	 * @param pAnnuaire
	 */
	public ServeurAnnuaire(int pPort, Annuaire pAnnuaire)
	{
		port = pPort;
		annuaire = pAnnuaire;
		controlleur = new ControlleurProtocoleAnnuaire(annuaire);
	}

	public void start()
	{
		new Serveur(controlleur,port);
    }
}
