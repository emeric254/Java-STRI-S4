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
			//
			// TODO se connecter a l'autre serveur
			//
			reponse = ProtocoleMessagerie.erreurImplementionManquante();
		}
		else if (utilisateur != null)
		{
			if (ProtocoleMessagerie.isRequeteConsulter(requete))
			{
				if (ProtocoleMessagerie.validerRequeteConsulterDetailsMessagesManque(requete))
				{
					//
					// TODO 
					//
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterListeMessagesManques(requete))
				{
					// TODO 
					reponse = ProtocoleMessagerie.ok(annuaire.getMessagesUtilisateur(utilisateur.getCourriel()).toString());
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterListeUtilisateurConnectes(requete))
				{
					// TODO 
					reponse = ProtocoleMessagerie.ok(annuaire.getAnnuaire().keySet().toString());
				}
				else if (ProtocoleMessagerie.validerRequeteConsulterDetailsUtilisateurConnecte(requete))
				{
					//
					// TODO 
					//
				}
				else
					reponse = ProtocoleMessagerie.erreurRequete();
			}
			else if (ProtocoleMessagerie.isRequeteInscrire(requete))
			{
				// TODO
				reponse = ProtocoleMessagerie.erreurImplementionManquante();
			}
			else if (ProtocoleMessagerie.isRequeteSuppression(requete))
			{
				if (ProtocoleMessagerie.validerRequeteSupprimerListeMessagesManques(requete))
				{
					annuaire.supprimerMessagesUtilisateur(utilisateur.getCourriel());
				}
				else if (ProtocoleMessagerie.validerRequeteSupprimerMessageManque(requete))
				{
					// TODO
					reponse = ProtocoleMessagerie.erreurImplementionManquante();
					//
					/*
					if(utilisateur != null)
						annuaire.supprimerMessagesUtilisateur(utilisateur.getCourriel());
					else
						reponse = ProtocoleMessagerie.erreurInterdit();
					*/
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
		return null;
	}

	@Override
	public ControlleurProtocoleMessagerie clone()
	{
		return new ControlleurProtocoleMessagerie(annuaire);
	}
}
