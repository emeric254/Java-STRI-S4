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
    private int port;

    /**
     * @param pProtocolServer
     */
    public Serveur(ControlleurProtocole pProtocolServer)
    {
    	port = 12345;
    	ControlleurProtocole protocolServer = pProtocolServer;
    	ServerSocket socketEcoute = null;

        try
        {
            socketEcoute= new ServerSocket(port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Nouveau serveur sur le port " + port);
        System.out.println(socketEcoute);

        while(true)
        {
            System.out.println("attente nouveau client");
            try
            {
                new ClientHandler(socketEcoute.accept(), protocolServer).start();
                System.out.println("nouvelle session ouverte");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param pProtocolServer
     * @param pPort
     */
    public Serveur(ControlleurProtocole pProtocolServer, int pPort)
    {
    	this(pProtocolServer);
    	port = pPort;
    }
}