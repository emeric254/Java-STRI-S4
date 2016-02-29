/**
 * 
 */
package stri.java_connect.server;


import java.net.*;

import stri.java_connect.protocol.ControlleurProtocole;

/**
 * @author emeric
 *
 */
public class Serveur
{
	private final static int portDefaut = 12345;

    /**
     * Creation d'un serveur sur le port par defaut avec un controlleur de protocole pour assurer la communication
     * 
     * @param pProtocolServer le controlleur de protocole
     */
    public Serveur(ControlleurProtocole pProtocolServer)
    {
    	this(pProtocolServer, portDefaut);
    }
    
    /**
     * Creation d'un serveur sur un port specifique et avec un controlleur de protocole pour assurer la communication
     * 
     * @param pProtocolServer le controlleur de protocole
     * @param pPort le port sur lequel doit ecouter le serveur
     */
    public Serveur(ControlleurProtocole pProtocolServer, int pPort)
    {
    	ControlleurProtocole protocolServer = pProtocolServer;
    	ServerSocket socketEcoute = null;

        try
        {
            socketEcoute= new ServerSocket(pPort);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Nouveau serveur sur le port " + pPort);
        System.out.println(socketEcoute);

        while(true)
        {
            System.out.println("mise en attente d'un nouveau client");
            try
            {
                new ClientHandler(socketEcoute.accept(), protocolServer.clone()).start();
                System.out.println("lancement d'un nouveau ClientHandler reussi");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
