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
 * @author emeric, remi
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

	/**
	 * Traitement du client pour le chat
	 * @param pSocketService
	 * @param pProtocolServer
	 */
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

    /**
     * Reception du message de chat
     */
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

    /**
     * Traitement de la requete qui entre
     * @param entree
     * @return
     */
    public String traitement (String entree)
    {
		return protocolServer.traiterRequete(entree);
    }

    /**
     * Fermeture du socket
     */
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

    /**
     * Emission du message
     * @param sortie
     */
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
