	
	//appeler classe client fonction lancer serv écoute
	//thread serv thread com autres gens initier co qui call classe client normale pour init vers classe messagerie
	//serv écoute dans client messagerie
	//au lieu de co affi, utiliser truc message indirecte
	//connexion, requete envoi mess, requete inscrire user (se co au server) apparaitre co dans annuaire serv, requete mess différé envoi message
	// idutilisateur socket + port

/**
 * 
 */
package stri.java_connect.client;

import java.io.IOException;
import java.util.Date;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleMessagerie;
import stri.java_connect.utils.MD5Hasher;

/**
 * @author thomas, emeric
 *
 */
public class ClientMessagerie
{
	private final static int portDefaut = 12340;
	private Client client;
	
	/**
	 * Creation d'un objet ClientMessagerie sur le port par defaut
	 * 
	 */
	public ClientMessagerie()
	{
		this(portDefaut);
	}
	
	/**
	 * Creation d'un objet ClientMessagerie sur un port fournit
	 * 
	 * @param pPort le port sur lequel demarer
	 */
	public ClientMessagerie(int pPort)
	{
		client = new Client(pPort);
	}
	
	/**
	 * Inscrire un utilisateur sur le serveur
	 * 
	 * @param courriel de l'utilisateur, adresse et port du serveur
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String inscription(String courriel, String adresse, int port) throws IOException //sinscrire au serv param string address int port
	{
		return client.communiquer(ProtocoleMessagerie.requeteInscrireUtilisateur(courriel, adresse + port));
	}
	
	/**
	 * Connecter a un compte utilisateur
	 * 
	 * @param courriel le courriel du compte
	 * @param mdp le mot de passe du compte
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String connexion(String courriel, String mdp) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConnexion(courriel, mdp));
	}
	
	/**
	 * Connecter a un compte utilisateur avec mot de passe qui sera envoye sous forme de hash MD5 
	 * 
	 * @param courriel le courriel du compte
	 * @param mdp le mot de passe du compte
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String connexionMD5(String courriel, String mdp) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConnexionHashMD5(courriel,MD5Hasher.hashString(mdp)));
	}
	
	/**
	 * Envoi de message differe
	 * 
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 * 
	 */
	public String envoiMessageDiffere(String courriel, String msg) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteEnvoiMessageDiffere(courriel, msg));
	}

	/**
	 * Consulter la liste des utilisateurs connectes
	 * 
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String consulterListeUtilisateurConnectes() throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConsulterListeUtilisateurConnectes());
	}
	
	/**
	 * Consulter le details des utilisateurs connectes
	 * 
	 * @param courriel le courriel du compte a consulter
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String consulterDetailsUtilisateurConnecte(String courriel) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConsulterDetailsUtilisateurConnecte(courriel));
	}
	
	/**
	 * Consulter liste des messages manques
	 * 
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String consulterListeMessagesManques() throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConsulterListeMessagesManques());
	}

	/**
	 * Consulter le details des messages manques
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String consulterDetailsMessagesManque(String idmsg) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteConsulterDetailsMessagesManque(idmsg));
	}	

	
	/**
	 * Supprimer liste des messages manques
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String supprimerListeMessagesManques() throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteSupprimerListeMessagesManques());
	}		

	
	/**
	 * Supprimer les messages manques
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String supprimerMessageManque(String idmsg) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteSupprimerMessageManque(idmsg));
	}
	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String idMessageManqueURI(String requete) throws IOException
	{
		return ProtocoleMessagerie.extraireIdMessageManqueURI(requete);
	}	

	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String requeteMessageDirect(String requete) throws IOException
	{
		return client.communiquer(ProtocoleMessagerie.requeteMessageDirect(requete));
	}
	
	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public boolean isMessageDirect(String requete) throws IOException
	{
		return ProtocoleMessagerie.isMessageDirect(requete);
	}	
	
	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String extraireTimestampMessageDirect(String requete) throws IOException
	{
		return ProtocoleMessagerie.extraireTimestampMessageDirect(requete);
	}	
	
	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public String extraireMessageMessageDirect(String requete) throws IOException
	{
		return ProtocoleMessagerie.extraireMessageMessageDirect(requete);
	}	
		
	
	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */	
	public Date extraireDateMessageDirect(String requete) throws IOException
	{
		return ProtocoleMessagerie.extraireDateMessageDirect(requete);
	}

	
	/**
	 * Se deconnecter du serveur.
	 * 
	 */
	public void deconnexion()
	{
		client.fermer();
	}
}
