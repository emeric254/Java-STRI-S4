package stri.java_connect;

import stri.java_connect.server.ServeurMessagerie;

/**
 * @author emeric, thomas
 *
 */
public class AppServeurMessagerie 
{
	private final static int portDefaut = 23456;
    /**
     * Main AppServeurMessagerie.java
     * 
     * @param args
     */
	public static void main(String[] args)
	{
		int port = portDefaut;
		if (args.length > 0)
		{
			port = Integer.parseInt(args[0]); //
			if (port<=0 || port>=65536)
			{
				port = portDefaut;
			}
		}
        System.out.println( "Lancement du serveur de messagerie sur le port " + port);
        //
        //lancer le serveur
        new ServeurMessagerie(port).start();
        //
        System.out.println("Fin");
	}
}
