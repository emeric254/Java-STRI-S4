/**
 * 
 */
package stri.java_connect.server;


import java.io.*;
import java.net.*;

import stri.java_connect.protocol.ControlleurProtocole;

/**
 * @author emeric
 *
 */
public class ClientHandler extends Thread
{
	private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;
    private ControlleurProtocole protocolServer;
    private boolean fini;
    private String entree; // requete
    private String sortie; // reponse
    
	/**
	 * Creation d'un thread pour un client pour communiquer avec lui
	 * 
	 * @param pSocketService le socket pour la communication
	 * @param pProtocolServer le controlleur de protocole pour gerer la communication
	 */
	public ClientHandler(Socket pSocketService, ControlleurProtocole pProtocolServer)
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
		sortie = null;
	}

    /**
     * Communication avec le client
     * 
     */
    public void communiquer()
    {
        try
        {
            entree = fluxEntreeSocket.readLine();
            if(entree != null) // TODO suffisant or not ? maybe test if len() > 0 ?
            {
                System.out.println(entree);
                sortie = traitement(entree);
                System.out.println(sortie);
                fluxSortieSocket.println(sortie);
            }
            else
                fini = true; // entree @ null == flux brise == fin
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fini = true; // erreur == fin
        }
    }
    
    /**
     * Traitement de la requete du client pour generer une reponse
     * 
     * @param entree la requete du client
     * @return la reponse
     */
    public String traitement (String entree)
    {
		return protocolServer.traiterRequete(entree);
    }

    /**
     * Arreter la communciation en fermant le socket
     * 
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

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run()
    {
        try
        {
            while(!fluxEntreeSocket.ready())
        	{
            	// attente ...
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        while(!fini)
        {
            communiquer();
        }
        
        fermerService();
    }
}
