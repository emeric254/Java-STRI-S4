/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.modele.Annuaire;
import stri.java_connect.modele.Utilisateur;
import stri.java_connect.server.ControlleurProtocole;

/**
 * @author emeric
 *
 */
public class ControlleurProtocoleAnnuaire extends ControlleurProtocole
{
	boolean connecte;
	Utilisateur utilisateur;
	Annuaire annuaire;
	
	/**
	 * 
	 */
	public ControlleurProtocoleAnnuaire(Annuaire a)
	{
		// TODO completer ici
		connecte = false;
		utilisateur = null;
		annuaire = a;
	}

	@Override
	public String traiterRequete(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();
		
		if(ProtocoleAnnuaire.isRequeteConnexion(requete))
		{
			if(ProtocoleAnnuaire.validerRequeteConnexion(requete))
			{
				String temp = ControlleurProtocole.requeteCorps(requete);
				String courriel = "";// TODO recuperation courriel
				String motdepasse = "";// TODO recuperation motdepasse
				
				if(annuaire.existeUtilisateur(courriel))
				{
					utilisateur = annuaire.getUtilisateur(courriel);
					if(utilisateur.getMotDePasse().equals(motdepasse))
					{
						reponse = ProtocoleAnnuaire.ok(utilisateur.toString());
					}
					else
					{
						utilisateur = null;
						reponse = ProtocoleAnnuaire.erreurInterdit();
					}
				}
				else
				{
					// TODO remove comments when done
					//reponse = ProtocoleAnnuaire.erreurInterdit();
					
					// TODO remove this line when done
					reponse = ProtocoleAnnuaire.erreurImplementionManquante();
				}
			}
			else
			{
				reponse = ProtocoleAnnuaire.erreurRequete();
			}
		}
		else if(ProtocoleAnnuaire.isRequeteConsulter(requete))
		{
			if(ProtocoleAnnuaire.validerRequeteConsulterProfils(requete))
			{
				// TODO profil alleges ?
				reponse = ProtocoleAnnuaire.erreurImplementionManquante();
			}
			else if(ProtocoleAnnuaire.validerRequeteConsulterProfil(requete))
			{
				// TODO details profil
				reponse = ProtocoleAnnuaire.erreurImplementionManquante();
			}
			else
			{
				reponse = ProtocoleAnnuaire.erreurRequete();
			}
		}
		else if(ProtocoleAnnuaire.isRequeteInscrire(requete))
		{
			if(ProtocoleAnnuaire.validerRequeteInscrire(requete))
			{
				// TODO inscription
				reponse = ProtocoleAnnuaire.erreurImplementionManquante();
			}
			else
			{
				reponse = ProtocoleAnnuaire.erreurRequete();
			}
		}
		else if(ProtocoleAnnuaire.isRequeteModifier(requete))
		{
			if(utilisateur == null)
			{
				reponse = ProtocoleAnnuaire.erreurInterdit();
			}
			else if(ProtocoleAnnuaire.validerRequeteModifier(requete))
			{
				// TODO modification profil
				reponse = ProtocoleAnnuaire.erreurImplementionManquante();
			}
			else
			{
				reponse = ProtocoleAnnuaire.erreurRequete();
			}
		}
		else if(ProtocoleAnnuaire.isRequeteSuppression(requete))
		{
			if(utilisateur == null)
			{
				reponse = ProtocoleAnnuaire.erreurInterdit();
			}
			else if(ProtocoleAnnuaire.validerRequeteSuppression(requete))
			{
				// TODO suppression profil
				reponse = ProtocoleAnnuaire.erreurImplementionManquante();
			}
			else
			{
				reponse = ProtocoleAnnuaire.erreurRequete();
			}
		}
		else
		{
			reponse = ProtocoleAnnuaire.erreurRequete();
		}
		return reponse;
	}

	@Override
	public String traiterReponse(String reponse)
	{
		// TODO
		return null;
	}
}
