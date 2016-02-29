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
	
	public String getUtilisateurs()
	{
		String temp = " { 'liste' : [";
		for (String courriel : annuaire.getKeys())
			temp += "'" + courriel + "',";
		// TODO a verifier
		if (temp.endWith(","))
			temp = temp.substring(0, temp.size()-1);
		temps += "] }";
		return temp;
	}
	
	public String existeUtilisateur(String courriel)
	{
		// TODO a verifier
		return annuaire.contains(courriel);
	}
	
	public String getDetailsUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
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

	public String getMessagesUtilisateurJsonString(String courriel)
	{
		Message[] messages = messages.get(courriel);
		String temp = "{ 'liste' : [";
		for (Message msg : messages)
			temp += msg.toString() + ",";
		// TODO a verifier
		if (temp.endWith(","))
			temp = temp.substring(0, temp.size()-1);
		temp += "] }";
		return temp;
	}

	public Message getMessageUtilisateur(String courriel, String timestamp)
	{
		try
		{
			return getMessageUtilisateur(courriel, Long.parseLong(timestamp));
		}
		catch (Exception e) { } // TODO exception a specialisee !
		return null;
	}

	public Message getMessageUtilisateur(String courriel, Long timestamp)
	{
		Messages[] temp = getMessagesUtilisateur(courriel);
		for (Message msg : temp)
			if (msg.getTimestamp == timestamp)
				return msg;
		return null;
	}
	
	public synchronized void supprimerMessagesUtilisateur(String courriel)
	{
		messages.remove(courriel);
	}
}
