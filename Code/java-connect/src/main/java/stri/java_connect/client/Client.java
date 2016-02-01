/**
 * 
 */
package stri.java_connect.client;

import java.io.*;
import java.net.*;

/**
 * @author emeric
 *
 */
public class Client
{
    private int port;
    String reponse;
    Socket socketService;
    PrintStream fluxSortieSocket;
    BufferedReader fluxEntreeSocket;

    /**
     * @param pPort
     */
    public Client(int pPort)
    {
    	port = pPort;
        try
        {
            socketService= new Socket("127.0.0.1", port);
            fluxEntreeSocket = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
            fluxSortieSocket = new PrintStream(socketService.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Connexion établie sur 127.0.0.1 " + port);
    }

    /**
     * @param message
     * @return
     * @throws IOException
     */
    public String communiquer(String message) throws IOException
    {
        fluxSortieSocket.println(message);
        fluxSortieSocket.flush();
        return fluxEntreeSocket.readLine();
    }
    
    /**
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
