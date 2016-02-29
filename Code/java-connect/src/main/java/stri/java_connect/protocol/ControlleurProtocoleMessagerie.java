package stri.java_connect.protocol;

import stri.java_connect.modele.AnnuaireMessagerie;
import stri.java_connect.modele.Utilisateur;

public class ControlleurProtocoleMessagerie extends ControlleurProtocole
{
	private AnnuaireMessagerie annuaire;
	private Utilisateur utilisateur;
	
	public ControlleurProtocoleMessagerie(AnnuaireMessagerie pAnnuaire)
	{
		utilisateur = null;
		annuaire = pAnnuaire;
	}

	@Override
	public String traiterRequete(String requete)
	{
		String reponse = ProtocoleMessagerie.erreurServeur();
		
		if (ProtocoleMessagerie.isRequeteConnexion(requete))
		{
			if (utilisateur == null && ProtocoleMessagerie.validerReponseConnexion(requete))
			{
				// TODO en faire une fonction
				Client cl = new Client("12345");
				try
				{
					String temp = c.communiquer(requete);
					if (ProtocoleGenerique.validerReponseConnexion(temp))
					{
						utilisateur = new Utilisateur();
						utilisateur.fromJSONString(ProtocoleGenerique.extraireDonnees(temp));
					}
					else
						reponse = temp;
				}
				catch (Exception e)
				{
					reponse = ProtocoleGenerique.erreurServeur();
				}
				cl.fermer();
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
					Message msg = ProtocoleMessagerie.getMessageUtilisateur(utilisateur.getCourriel(), ProtocoleMessagerie.extraireIdMessageManqueURI(requete));
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
					if (utilisateur.getCourriel() == ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.utilisateursURI + "/", ""))
					{
						annuaire.ajoutUtilisateur(utilisateur.getCourriel(), ControlleurProtocole.extraireDonnees(requete));
						reponse = ProtocoleGenerique.ok();
					}
					else
						reponse = ProtocoleGenerique.erreurInterdit();
				}
				else if (ProtocoleMessagerie.validerRequeteEnvoiMessageDiffere())
				{
					String courriel = ControlleurProtocole.requeteURI(requete).replace(ProtocoleMessagerie.utilisateursURI + "/", "");
					// TODO en faire une fonction
					Client cl = new Client("12345");
					try
					{
						String temp = c.communiquer(ProtocoleAnnuaire.requeteConsulterProfil(courriel));
						if (ControlleurProtocole.reponseCode(temp) == 0)
						{
							annuaire.ajoutMessage(courriel, utilisateur.getCourriel(), ControlleurProtocole.requeteCorps(requete)); // TODO a verifier !
						}
						else
							reponse = ProtocoleGenerique.erreurRequete();
					}
					catch (Exception e)
					{
						reponse = ProtocoleGenerique.erreurServeur();
					}
					cl.fermer();
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
					annuaire.supprimerMessageUtilisateur(utilisateur.getCourriel(), ProtocoleMessagerie.messagerieURI + "/", "");
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

	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleMessagerie.isOk(reponse))
			return ProtocoleMessagerie.extraireDonnees(reponse);
		return null;
	}

	public void UtilisateurDeconnecte()
	{
		annuaire.supprimerUtilisateur(utilisateur.getCourriel());
		utilisateur = null;
	}

	@Override
	public ControlleurProtocoleMessagerie clone()
	{
		return new ControlleurProtocoleMessagerie(annuaire);
	}
}
