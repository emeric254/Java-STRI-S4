package stri.java_connect.ihm;

import org.apache.pivot.wtk.DesktopApplicationContext;

import stri.java_connect.utils.IHMUtilitaires;

public class IHMConversation {
	
	private String nomSource;
	private String mailSource;
	private String portSource;
	
	private String nomDestination;
	private String mailDestination;
	private String portDestination;
	
	public IHMConversation(String nomSource, String mailSource,
			String portSource, String nomDestination, String mailDestination,
			String portDestination) {
		super();
		this.nomSource = nomSource;
		this.mailSource = mailSource;
		this.portSource = portSource;
		this.nomDestination = nomDestination;
		this.mailDestination = mailDestination;
		this.portDestination = portDestination;
	}
	
	/**
	 * Lancer une conversation en P2P
	 * 
	 */
	private void MessagerieDirecte()
	{
		
		
		//ouverture d'une fenêtre de conversation 
		//String[] args = {nom,mail};
		//DesktopApplicationContext.main(Conversation.class, args);
	}
// !q => quitte la conv
	/*
faire appel à un truc qui s'appelle communiquer (avec le mess à envoyer ou null) et communiquer retourne ce qui est reçus ou null si rien
	
Filer message dans clientMessagerie
	*/
	
	/**
	 * 
	 */
	private void envoyerMessage(String Message)
	{
		///// Faire appel à la fonction qui envoie le message
	}
	
	/**
	 * 
	 */
	private String recevoirMessage()
	{
		//retourne le message recu pour  ensuite l'afficher 
		//return String
	}
}
