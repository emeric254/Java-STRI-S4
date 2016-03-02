/**
 * 
 */
package stri.java_connect.client;

import java.net.ServerSocket;

import stri.java_connect.protocol.ControlleurProtocole;

/**
 * @author emeric
 *
 */
public class ClientMessagerieDirecte extends Thread
{
	private final static int portDefaut = 12347;

	private ControlleurProtocole protoclecontrolleur;
	private int port;

    public ClientMessagerieDirecte()
    {
    	this(portDefaut);
    }

	public ClientMessagerieDirecte(int pPort)
	{
		port = pPort;
		protoclecontrolleur = new ControlleurProtocoleMessagerie(null);
	}

	@Override
	public void run()
	{
    	ServerSocket socketEcoute = null;
        try
        {
            socketEcoute= new ServerSocket(port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("En attente de conversation sur le port " + port);
        System.out.println(socketEcoute);

        while(true)
        {
            System.out.println("mise en attente d'une nouvelle conversation");
            try
            {
                new ClientMessagerieDirecteHandler(socketEcoute.accept(), protoclecontrolleur.clone()).start();
                System.out.println("lancement d'un nouveau ClientMessagerieDirecteHandler reussi");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
	}

	// TODO
	//~ public static void emettreMsg(int port, String msg)
	//~ {
		//~ Client cl = new Client(port);
		//~ cl.communiquer(msg);
		//~ cl.fermer();
	//~ }
}
