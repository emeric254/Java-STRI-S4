
package stri.java_connect.client;

import java.io.*;
import java.net.*;

/**
 * @author emeric
 *
 */
public class Client
{
    //private String reponse;
    private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;

    /**
     * Creation d'un objet Client sur un port fournit
     * 
     * @param pPort le port sur lequel demarer
     */
    public Client(int pPort)
    {
        try
        {
            socketService= new Socket("127.0.0.1", pPort);
            fluxEntreeSocket = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
            fluxSortieSocket = new PrintStream(socketService.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Connexion établie sur 127.0.0.1 " + pPort);
    }

    /**
     * Communiquer un message au serveur et attendre sa reponse
     * 
     * @param message le message a communiquer
     * @return la reponse
     * @throws IOException une exception si la communciation echoue
     */
    public String communiquer(String message) throws IOException
    {
        fluxSortieSocket.println(message);
        fluxSortieSocket.flush();
        return fluxEntreeSocket.readLine();
    }
    
    /**
     * Fermer la connexion.
     * 
     */
    public void fermer()
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
}
