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
    //private String reponse;
    private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;

    /**
     * @param pPort
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
