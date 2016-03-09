package stri.java_connect.protocol;

import stri.java_connect.client.Client;
import stri.java_connect.modele.AnnuaireMessagerie;
import stri.java_connect.modele.Message;
import stri.java_connect.modele.Utilisateur;

/**
 * @author emeric
 *
 */
public class ControlleurProtocoleMessagerie extends ControlleurProtocole
{
	private AnnuaireMessagerie annuaire;
	private Utilisateur utilisateur;
	private Client cl;

	/**
	 * Le controleur verifie si la requete correspond au protocole messagerie et la traite
	 * 
	 * @param pAnnuaire
	 */
	public ControlleurProtocoleMessagerie(AnnuaireMessagerie pAnnuaire)
	{
		cl = new Client(12345);
		utilisateur = null;
		annuaire = pAnnuaire;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#traiterRequete(java.lang.String)
	 */
	@Override
	public String traiterRequete(String requete)
	{
		String reponse = ProtocoleMessagerie.erreurServeur();
		if (ProtocoleMessagerie.isRequeteConnexion(requete))
		{
			if (utilisateur == null && ProtocoleAnnuaire.validerRequeteConnexion(requete))
			{
				try
				{
					System.out.println("debug " + requete);
					String temp = cl.communiquer(requete);
					if (ProtocoleGenerique.isOk(temp))
					{
						utilisateur = new Utilisateur();
						utilisateur.fromJSONString(ProtocoleGenerique.extraireJSONObject(temp).toString());
						//
						reponse = ProtocoleGenerique.ok();
					}
					else
						reponse = temp;
				}
				catch (Exception e)
				{
					utilisateur = null;
					reponse = ProtocoleGenerique.erreurServeur();
				}
			}
			else
				reponse = ProtocoleGenerique.erreurRequete();
		}
		else if (utilisateur != null) // il faut etre connecte !
		{
			if (ProtocoleMessagerie.isRequeteConsulter(requete))
			{
				if (ProtocoleMessagerie.validerRequeteConsulterDetailsMessagesManque(requete))
				{
					Message msg = annuaire.getMessageUtilisateur(utilisateur.getCourriel(), ProtocoleMessagerie.extraireIdMessageManqueURI(requete));
					if (msg != null)
						reponse = ProtocoleMessagerie.ok(msg.toString()); // TODO a verifier
					else
						reponse = ProtocoleMessagerie.erreurRequete();
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterListeMessagesManques(requete))
				{
					reponse = ProtocoleMessagerie.ok(annuaire.getMessagesUtilisateurJsonString(utilisateur.getCourriel())); // TODO a verifier
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterListeUtilisateurConnectes(requete))
				{ 
					reponse = ProtocoleMessagerie.ok(annuaire.getAnnuaire().keySet().toString()); // TODO a verifier
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterDetailsUtilisateurConnecte(requete))
				{
					String courriel = ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.utilisateursURI + "/", "");
					if (annuaire.existeUtilisateur(courriel))
						reponse = ProtocoleMessagerie.ok("'" + annuaire.getDetailsUtilisateur(courriel) + "'"); // TODO a verifier
					else
						reponse = ProtocoleMessagerie.erreurRequete();
				}
				else
					reponse = ProtocoleMessagerie.erreurRequete();
			}
			else if (ProtocoleMessagerie.isRequeteInscrire(requete))
			{
				if (ProtocoleMessagerie.validerRequeteInscrireUtilisateur(requete))
				{
					//
					if (utilisateur.getCourriel().equals(ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.utilisateursURI + "/", "")))
					{
						annuaire.ajoutUtilisateur(utilisateur.getCourriel(), ControlleurProtocole.requeteCorps(requete));
						reponse = ProtocoleGenerique.ok();
					}
					else
						reponse = ProtocoleGenerique.erreurInterdit();
				}
				else if (ProtocoleMessagerie.validerRequeteEnvoiMessageDiffere(requete))
				{
					String courriel = ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.messagerieURI + "/", "");
					try
					{
						String temp = cl.communiquer(ProtocoleAnnuaire.requeteConsulterProfil(courriel));
						if (ControlleurProtocole.reponseCode(temp) == 0)
						{
							annuaire.ajoutMessage(courriel, utilisateur.getCourriel(), ControlleurProtocole.requeteCorps(requete)); // TODO a verifier !
							reponse = ProtocoleGenerique.ok();
						}
						else
							reponse = ProtocoleGenerique.erreurRequete();
					}
					catch (Exception e)
					{
						reponse = ProtocoleGenerique.erreurServeur();
					}
				}
			}
			else if (ProtocoleMessagerie.isRequeteSuppression(requete))
			{
				if (ProtocoleMessagerie.validerRequeteSupprimerListeMessagesManques(requete))
				{
					annuaire.supprimerMessagesUtilisateur(utilisateur.getCourriel());
				}
				else if (ProtocoleMessagerie.validerRequeteSupprimerMessageManque(requete))
				{
					annuaire.supprimerMessageUtilisateur(utilisateur.getCourriel(), ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.messagerieURI + "/", ""));
				}
				else if (ProtocoleMessagerie.validerRequeteDeconexion(requete))
				{
					deconnexionUtilisateur();
					reponse = ProtocoleMessagerie.ok();
				}
				else
					reponse = ProtocoleMessagerie.erreurRequete();
			}
			else
				reponse = ProtocoleMessagerie.erreurRequete();
		}
		else
			reponse = ProtocoleMessagerie.erreurInterdit();
		return reponse;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#traiterReponse(java.lang.String)
	 */
	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleMessagerie.isOk(reponse))
			return ProtocoleMessagerie.extraireDonnees(reponse);
		return null;
	}

	/**
	 * Deconnexion de l'utilisateur sur le chat
	 */
	public void deconnexionUtilisateur() // TODO hack this !
	{
		annuaire.supprimerUtilisateur(utilisateur.getCourriel());
		utilisateur = null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#clone()
	 */
	@Override
	public ControlleurProtocoleMessagerie clone()
	{
		return new ControlleurProtocoleMessagerie(annuaire);
	}
}
