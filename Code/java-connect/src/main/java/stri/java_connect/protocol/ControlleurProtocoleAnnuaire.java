/**
 * 
 */
package stri.java_connect.protocol;

import stri.java_connect.modele.Annuaire;
import stri.java_connect.modele.Utilisateur;

/**
 * @author emeric
 *
 */
public class ControlleurProtocoleAnnuaire extends ControlleurProtocole
{
	private Utilisateur utilisateur;
	private Annuaire annuaire;
	
	/**
	 * 
	 */
	public ControlleurProtocoleAnnuaire(Annuaire a)
	{
		utilisateur = null;
		annuaire = a;
	}

	@Override
	public String traiterRequete(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();
		
		if (ProtocoleAnnuaire.isRequeteConnexion(requete))
		{
			if (ProtocoleAnnuaire.validerRequeteConnexion(requete))
			{
				Utilisateur u = new Utilisateur();
				u.fromJSONString(ControlleurProtocole.requeteCorps(requete));
				
				if (annuaire.existeUtilisateur(u.getCourriel()) && utilisateur == null)
				{
					utilisateur = annuaire.getUtilisateur(u.getCourriel());
					
					if (utilisateur.getMotDePasse().equals(u.getMotDePasse()))
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
					utilisateur = null;
					reponse = ProtocoleAnnuaire.erreurInterdit();
				}
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else if (ProtocoleAnnuaire.isRequeteConsulter(requete))
		{
			if (ProtocoleAnnuaire.validerRequeteConsulterProfils(requete))
			{
				// TODO profil alleges ? gestion permissions ? ...
				reponse = ProtocoleAnnuaire.ok(annuaire.getArrayTousUtilisateurs().toString());
			}
			else if (ProtocoleAnnuaire.validerRequeteConsulterProfil(requete))
			{
				// TODO remplacer ça par fonction dans ProtocoleAnnuaire :
				String courriel = ControlleurProtocole.requeteURI(requete).replace("/profils/", "");
				
				if (annuaire.existeUtilisateur(courriel))
				{
					Utilisateur temp = annuaire.getUtilisateur(courriel);
					// TODO gestion permissions !
					reponse = ProtocoleAnnuaire.ok(temp.toString());
				}
				else
					reponse = ProtocoleAnnuaire.erreurRequete();
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else if (ProtocoleAnnuaire.isRequeteInscrire(requete))
		{
			if (ProtocoleAnnuaire.validerRequeteInscrire(requete))
			{
				Utilisateur u = new Utilisateur();
				u.fromJSONString(ControlleurProtocole.requeteCorps(requete));
				
				if(annuaire.existeUtilisateur(u.getCourriel()) || utilisateur != null)
					reponse = ProtocoleAnnuaire.erreurInterdit();
				else
				{
					annuaire.ajoutUtilisateur(utilisateur = u);
					reponse = ProtocoleAnnuaire.ok(utilisateur.toString());
				}
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else if (ProtocoleAnnuaire.isRequeteModifier(requete))
		{
			if (utilisateur == null)
				reponse = ProtocoleAnnuaire.erreurInterdit();
			else if (ProtocoleAnnuaire.validerRequeteModifierProfil(requete))
			{
				Utilisateur u = new Utilisateur();
				u.fromJSONString(ControlleurProtocole.requeteCorps(requete));
				
				if (annuaire.existeUtilisateur(u.getCourriel()))
				{
					Utilisateur temp = annuaire.getUtilisateur(u.getCourriel());
					
					if ( ( utilisateur.getPrivilege().equals("utilisateur") 
							&& temp.getCourriel().equals(utilisateur.getCourriel())
							&& temp.getMotDePasse().equals(utilisateur.getMotDePasse()) )
						|| utilisateur.getPrivilege().equals("administrateur") )
					{
						if (utilisateur.getCourriel().equals(u.getCourriel()))
							utilisateur = u;
						annuaire.ajoutUtilisateur(u);
						reponse = ProtocoleAnnuaire.ok(u.toString());
					}
					else
						reponse = ProtocoleAnnuaire.erreurInterdit();
				}
				else
					reponse = ProtocoleAnnuaire.erreurRequete();
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else if (ProtocoleAnnuaire.isRequeteSuppression(requete))
		{
			if (utilisateur == null)
				reponse = ProtocoleAnnuaire.erreurInterdit();
			else if (ProtocoleAnnuaire.validerRequeteSuppressionProfil(requete))
			{
				// TODO remplacer ca pars fonction dans ProtocoleAnnuaire :
				String courriel = ControlleurProtocole.requeteURI(requete).replace("/profils/", "");
				
				if (annuaire.existeUtilisateur(courriel))
				{
					if (utilisateur.getCourriel().equals(courriel))
					{
						annuaire.suppresionUtilisateur(courriel);
						utilisateur = null;
						reponse = ProtocoleAnnuaire.erreurDeconnexion();
					}
					else if (utilisateur.getPrivilege().equals("administrateur"))
					{
						annuaire.suppresionUtilisateur(courriel);
						reponse = ProtocoleAnnuaire.ok();
					}
					else
						reponse = ProtocoleAnnuaire.erreurInterdit();
				}
				else
					reponse = ProtocoleAnnuaire.erreurRequete();
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else
			reponse = ProtocoleAnnuaire.erreurRequete();
		return reponse;
	}

	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleAnnuaire.isOk(reponse))
			return ProtocoleAnnuaire.extraireDonnees(reponse);
		return null;
	}
}
