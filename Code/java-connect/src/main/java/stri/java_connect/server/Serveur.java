/**
 * 
 */
package stri.java_connect.server;


import java.net.*;

/**
 * @author emeric
 *
 */
public class Serveur
{
    private int port;
    ControlleurProtocole protocolServer;
    ServerSocket socketEcoute;

    /**
     * @param pProtocolServer
     */
    public Serveur(ControlleurProtocole pProtocolServer)
    {
    	port = 12345;
        protocolServer = pProtocolServer;

        try
        {
            this.socketEcoute= new ServerSocket(port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Nouveau serveur sur le port " + port);
        System.out.println(socketEcoute);

        while(true)
        {
            System.out.println("nouveau client");
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