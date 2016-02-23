/**
 * 
 */
package stri.java_connect.server;

import stri.java_connect.protocol.ControlleurProtocoleMessagerie;

/**
 * @author emeric
 *
 */
public class ServeurMessagerie
{
	private final static int portDefaut = 12346;
	private int port;
	
	/**
	 * Creation d'un Serveur Messagerie sur le port par defaut
	 * 
	 */
	public ServeurMessagerie()
	{
		this(portDefaut);
	}
	
	/**
	 * Creation d'un Serveur Messagerie sur un port specifique
	 * 
	 * @param pPort le port sur lequel doit ecouter le serveur
	 */
	public ServeurMessagerie(int pPort)
	{
		port = pPort;
	}
	
	/**
	 * Lancement du serveur
	 * 
	 */
	public void start()
	{
		new Serveur(new ControlleurProtocoleMessagerie(), port);
    }
}
