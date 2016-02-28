/**
 * 
 */
package stri.java_connect.modele;

import java.util.HashMap;

/**
 * @author emeric
 *
 */
public class AnnuaireMessagerie
{
	private HashMap<String, String> annuaire;
	
	private HashMap<String, Message[]> messages;

	public AnnuaireMessagerie()
	{
		annuaire = new HashMap<String, String>();
		messages = new HashMap<String, Message[]>();
	}

	public HashMap<String, String> getAnnuaire()
	{
		return annuaire;
	}

	public synchronized void setAnnuaire(HashMap<String, String> annuaire)
	{
		this.annuaire = annuaire;
	}
	
	public synchronized void ajoutUtilisateur(String courriel, String ipPort)
	{
		annuaire.put(courriel, ipPort);
	}
	
	public synchronized void enleverUtilisateur(String courriel)
	{
		annuaire.remove(courriel);
	}
	
	//---
	
	public HashMap<String, Message[]> getMessages()
	{
		return messages;
	}

	public synchronized void setMessages(HashMap<String, Message[]> messages)
	{
		this.messages = messages;
	}

	public Message[] getMessagesUtilisateur(String courriel)
	{
		return messages.get(courriel);
	}
	
	public synchronized void supprimerMessagesUtilisateur(String courriel)
	{
		messages.remove(courriel);
	}
}
