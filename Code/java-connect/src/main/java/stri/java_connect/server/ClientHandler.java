/**
 * 
 */
package stri.java_connect.server;


import java.io.*;
import java.net.*;

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

    public void run()
    {
        try
        {
            while(!fluxEntreeSocket.ready());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        while(!fini)
            communiquer();
        fermerService();
    }
}
