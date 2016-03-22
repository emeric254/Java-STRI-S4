/**
 * 
 */
package stri.java_connect.client;

import java.io.IOException;

import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;
import stri.java_connect.utils.MD5Hasher;

/**
 * @author emeric
 *
 */
public class ClientAnnuaire
{
	private final static int portDefaut = 12345;
	private Client client;

	/**
	 * Creation d'un objet ClientAnnuaire sur le port par defaut
	 * 
	 */
	public ClientAnnuaire()
	{
		this(portDefaut);
	}

	/**
	 * Creation d'un objet ClientAnnuaire sur un port fournit
	 * 
	 * @param pPort le port sur lequel demarer
	 */
	public ClientAnnuaire(int pPort)
	{
		client = new Client(pPort);
	}

	/**
	 * Inscrire un utilisateur
	 * 
	 * @param u l'utilisateur a inscrire
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String inscription(Utilisateur u) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteInscrireProfil(u.toString()));
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
		return client.communiquer(ProtocoleAnnuaire.requeteConnexion(courriel, mdp));
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
		return client.communiquer(ProtocoleAnnuaire.requeteConnexionHashMD5(courriel,MD5Hasher.hashString(mdp)));
	}

	/**
	 * Consulter la liste des profils
	 * 
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 * 
	 */
	public String consulterProfils() throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteConsulterProfils());
	}

	/**
	 * Consulter un profil
	 * 
	 * @param courriel le courriel du compte a consulter
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String consulterProfil(String courriel) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteConsulterProfil(courriel));
	}

	/**
	 * Mettre a jour les details d'un objet/compte Utilisateur
	 * 
	 * @param utilisateur l'objet Utilisateur mis a jour
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String modificationProfil(Utilisateur utilisateur) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteModifierProfil(utilisateur.getCourriel(), utilisateur.toString()));
	}

	/**
	 * Supprimer un compte/objet Utilisateur
	 * 
	 * @param courriel le courriel du compte a supprimer
	 * @return la reponse a cette requete
	 * @throws IOException l'exception si la communication echoue
	 */
	public String suppressionProfil(String courriel) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteSuppressionProfil(courriel));
	}


	/**
	 * Ajout d'un like sur une competence
	 * 
	 * @param courriel le courriel du compte sur lequel est ajoute le like
	 * @param competence la competence liker
	 * @return
	 * @throws IOException si l'ajout echoue
	 */
	public String inscriptionLike(String courriel, String competence) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteInscrireLike(courriel, competence));
	}


	/**
	 * Suppression d'un like sur une competence
	 * 
	 * @param courriel le courriel du compte sur lequel est supprime le like
	 * @param competence la competence choisie
	 * @return
	 * @throws IOException si l'ajout echoue
	 */
	public String suppressionLike(String courriel, String competence) throws IOException
	{
		return client.communiquer(ProtocoleAnnuaire.requeteSuppressionLike(courriel, competence));
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
