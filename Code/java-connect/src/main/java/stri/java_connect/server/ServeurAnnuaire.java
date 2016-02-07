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
	
	/**
	 * Creation d'un Serveur Annuaire sur le port par defaut avec un annuaire vide
	 * 
	 */
	public ServeurAnnuaire()
	{
		this(portDefaut, new Annuaire());
	}
	
	/**
	 * Creation d'un Serveur Annuaire sur un port specifique avec un annuaire vide
	 * 
	 * @param pPort le port sur lequel doit ecouter le serveur
	 */
	public ServeurAnnuaire(int pPort)
	{
		this(pPort, new Annuaire());
	}
	
	/**
	 * Creation d'un Serveur Annuaire sur le port par defaut avec un annuaire fournit
	 * 
	 * @param pAnnuaire l'annuaire a charger pour le serveur
	 */
	public ServeurAnnuaire(Annuaire pAnnuaire)
	{
		this(portDefaut,pAnnuaire);
	}
	
	/**
	 * Creation d'un Serveur Annuaire sur un port specifique avec un annuaire fournit
	 * 
	 * @param pPort le port sur lequel doit ecouter le serveur
	 * @param pAnnuaire l'annuaire a charger pour le serveur
	 */
	public ServeurAnnuaire(int pPort, Annuaire pAnnuaire)
	{
		port = pPort;
		controlleur = new ControlleurProtocoleAnnuaire(pAnnuaire);
		//start();
	}

	/**
	 * Lancement du serveur
	 * 
	 */
	public void start()
	{
		new Serveur(controlleur,port);
    }
}
