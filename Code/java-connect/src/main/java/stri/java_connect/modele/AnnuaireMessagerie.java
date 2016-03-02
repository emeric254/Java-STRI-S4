/**
 * 
 */
package stri.java_connect.modele;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author emeric
 *
 */
public class AnnuaireMessagerie
{
	private HashMap<String, String> annuaire;
	
	private HashMap<String, ArrayDeque<Message>> messages;

	public AnnuaireMessagerie()
	{
		annuaire = new HashMap<String, String>();
		messages = new HashMap<String, ArrayDeque<Message>>();
	}

	public HashMap<String, String> getAnnuaire()
	{
		return annuaire;
	}
	
	public String getUtilisateurs()
	{
		String temp = " { 'liste' : [";
		for (String courriel : annuaire.keySet())
			temp += "'" + courriel + "',";
		if (temp.endsWith(",")) // TODO a verifier
			temp = temp.substring(0, temp.length()-1); // TODO a verifier
		temp += "] }";
		return temp;
	}
	
	public boolean existeUtilisateur(String courriel)
	{
		return annuaire.keySet().contains(courriel);
	}
	
	public String getDetailsUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
	}
	
	public synchronized String supprimerUtilisateur(String courriel)
	{
		return annuaire.remove(courriel);
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
	
	// --- Messages
	
	public HashMap<String, ArrayDeque<Message>> getMessages()
	{
		return messages;
	}

	public synchronized void setMessages(HashMap<String, ArrayDeque<Message>> messages)
	{
		this.messages = messages;
	}

	public synchronized void ajoutMessage(String courrielDestinataire, String courrielAuteur, String texte)
	{
		ArrayDeque<Message> temp = getMessagesUtilisateur(courrielDestinataire);
		temp.add(new Message(courrielAuteur, texte)); // TODO a verifier
		// messages . put temp ?? necessaire ??
	}

	public ArrayDeque<Message> getMessagesUtilisateur(String courriel)
	{
		return messages.get(courriel);
	}

	public String getMessagesUtilisateurJsonString(String courriel)
	{
		ArrayDeque<Message> liste = messages.get(courriel);
		String temp = "{ 'liste' : [";
		if (liste != null)
			for (Message msg : liste)
				temp += msg.toString() + ",";
		if (temp.endsWith(",")) // TODO a verifier
			temp = temp.substring(0, temp.length()-1); // TODO a verifier
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
		ArrayDeque<Message> temp = getMessagesUtilisateur(courriel);
		for (Message msg : temp)
			if (msg.getTimestamp() == timestamp)
				return msg;
		return null;
	}
	
	public synchronized void supprimerMessagesUtilisateur(String courriel)
	{
		messages.remove(courriel);
	}
	
	public synchronized void supprimerMessageUtilisateur(String courriel, String idmsg)
	{
		ArrayDeque<Message> temp = getMessagesUtilisateur(courriel);
		try
		{
			Long timestamp = Long.parseLong(idmsg);
			for (Message msg : temp)
				if (msg.getTimestamp() == timestamp)
				{
					temp.remove(msg); // TODO a verifier
					break;
				}
		}
		catch (Exception e) { } // TODO exception a specialisee !
	}
}
