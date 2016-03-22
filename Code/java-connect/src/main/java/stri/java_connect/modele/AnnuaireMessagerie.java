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

	/**
	 * Recupere dans annuaire
	 * @return annuaire
	 */
	public HashMap<String, String> getAnnuaire()
	{
		return annuaire;
	}
	
	/**
	 * Recupere les donnees utilisateur
	 * @return temp
	 */
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
	
	/**
	 * Teste si l'utilisateur existe en fonction du courriel
	 * @param courriel
	 * @return
	 */
	public boolean existeUtilisateur(String courriel)
	{
		return annuaire.keySet().contains(courriel);
	}
	
	/**
	 * Recupere le details des utilisateurs
	 * @param courriel
	 * @return
	 */
	public String getDetailsUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
	}
	
	/**
	 * Suppression de l'utilisateur
	 * @param courriel
	 * @return
	 */
	public synchronized String supprimerUtilisateur(String courriel)
	{
		return annuaire.remove(courriel);
	}

	/**
	 * Initialise l'annuaire
	 * @param annuaire
	 */
	public synchronized void setAnnuaire(HashMap<String, String> annuaire)
	{
		this.annuaire = annuaire;
	}
	
	/**
	 * Ajout d'un utilisateur
	 * @param courriel
	 * @param ipPort
	 */
	public synchronized void ajoutUtilisateur(String courriel, String ipPort)
	{
		annuaire.put(courriel, ipPort);
	}
	
	/**
	 * Suppression d'un utilisateur
	 * @param courriel
	 */
	public synchronized void enleverUtilisateur(String courriel)
	{
		annuaire.remove(courriel);
	}
	
	// --- Messages
	
	public HashMap<String, ArrayDeque<Message>> getMessages()
	{
		return messages;
	}

	/**
	 * Construction du message
	 * @param messages
	 */
	public synchronized void setMessages(HashMap<String, ArrayDeque<Message>> messages)
	{
		this.messages = messages;
	}

	/**
	 * Ajout du message avec les parametres
	 * @param courrielDestinataire
	 * @param courrielAuteur
	 * @param texte
	 */
	public synchronized void ajoutMessage(String courrielDestinataire, String courrielAuteur, String texte)
	{
		ArrayDeque<Message> temp = getMessagesUtilisateur(courrielDestinataire);
		if (temp == null)
			temp = new ArrayDeque<Message>();
		temp.add(new Message(courrielAuteur, texte));
		messages.put(courrielDestinataire, temp);
	}

	/**
	 * Recupere le message a envoyer
	 * @param courriel
	 * @return
	 */
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

	/**
	 * Creation du message
	 * @param courriel
	 * @param timestamp
	 * @return
	 */
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
	
	/**
	 * Suppression du message
	 * @param courriel
	 */
	public synchronized void supprimerMessagesUtilisateur(String courriel)
	{
		messages.remove(courriel);
	}
	
	/**
	 * Suppression du message
	 * @param courriel
	 * @param idmsg
	 */
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
