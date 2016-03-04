/**
 * 
 */
package stri.java_connect.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import stri.java_connect.protocol.ControlleurProtocole;

/**
 * @author emeric
 *
 */
public class ClientMessagerieDirecteHandler extends Thread
{
	private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;
    private ControlleurProtocole protocolServer;
    private boolean fini;
    private String entree; // requete

	public ClientMessagerieDirecteHandler(Socket pSocketService, ControlleurProtocole pProtocolServer)
	{
        socketService = pSocketService;
		protocolServer = pProtocolServer;
        try
        {
            fluxEntreeSocket= new BufferedReader(new InputStreamReader(socketService.getInputStream()));
            fluxSortieSocket= new PrintStream(socketService.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		fini = false;
		entree = null;
	}

    public void recevoir()
    {
        try
        {
            while(!fluxEntreeSocket.ready())
            {
            	// attente
            }
        	//
        	entree = fluxEntreeSocket.readLine();
            if (entree != null)
            	System.out.println("  > " + entree);
        }
        catch (Exception e)
        {
        	fini = true;
        }
    }

    public String traitement (String entree)
    {
		return protocolServer.traiterRequete(entree);
    }

    public void fermerService()
    {
        try
        {
            this.socketService.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void emettre(String sortie)
    {
        try
        {
        	fluxSortieSocket.println(sortie);
        }
        catch (Exception e)
        {
        	fini = true;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run()
    {   
        while(!fini)
        {
            recevoir();
        }
        fermerService();
    }
}
