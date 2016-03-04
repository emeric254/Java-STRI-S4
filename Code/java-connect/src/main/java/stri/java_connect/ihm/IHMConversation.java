package stri.java_connect.ihm;

import java.net.SocketException;

//import org.apache.pivot.wtk.DesktopApplicationContext;

import stri.java_connect.client.ClientMessagerieDirecte;
import stri.java_connect.utils.IHMUtilitaires;

public class IHMConversation
{
	private String nom;

	public IHMConversation(String nomSource)
	{
		super();
		this.nom = nomSource;
	}

	/**
	 * Lancer une conversation en P2P
	 * 
	 */
	private void messagerieDirecte()
	{
		//ouverture d'une fenêtre de conversation 
		//String[] args = {nom,mail};
		//DesktopApplicationContext.main(Conversation.class, args);
		System.out.println("Messagerie directe");
		System.out.println("------------------");

		int port = 0;
		ClientMessagerieDirecte cldirect = null;
		do
		{
			String temp = IHMUtilitaires.saisie("Entrez le port a utiliser :");
			try
			{
				port = Integer.parseInt(temp);
			} catch (Exception e) { }

			try
			{
				cldirect = new ClientMessagerieDirecte(port);
			}
			catch (SocketException e)
			{
				port = 0;
			}
		} while (port == 0);
		
		if (cldirect != null)
		{
			cldirect.start();
			String temp = "";
			do
			{
				System.out.println();
				temp = IHMUtilitaires.saisie("Entrez votre message ou <!q> pour quitter : ");
				if (!"!q".equals(temp))
				{
					String addrport = IHMUtilitaires.saisie("Entrez <ipv4:port> : ");
					cldirect.emettreMsg(addrport, nom + "@[" + addrport + "] : " + temp);
				}
			} while (!"!q".equals(temp));
		}
	}
}
