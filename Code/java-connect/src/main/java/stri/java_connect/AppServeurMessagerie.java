package stri.java_connect;

public class AppServeurMessagerie 
{
	private final static int portDefaut = 12345;

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
        // lien vers appServeur
        //
        //lancer le controleur
        
        //
        System.out.println("Fin");
	}
}
