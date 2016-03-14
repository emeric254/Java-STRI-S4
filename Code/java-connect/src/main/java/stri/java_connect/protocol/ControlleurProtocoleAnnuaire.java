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
	 * Creation du controleur de protocole Annuaire
	 * 
	 * @param a un objet Annuaire deja existant
	 */
	public ControlleurProtocoleAnnuaire(Annuaire a)
	{
		utilisateur = null;
		annuaire = a;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteConnexion(java.lang.String)
	 */
	public String traiterRequeteConnexion(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();

		if (ProtocoleAnnuaire.validerRequeteConnexion(requete))
		{
			String identifiants = ControlleurProtocole.requeteURI(requete);
			String courriel = identifiants.split(":")[0];
			
			if (annuaire.existeUtilisateur(courriel) && utilisateur == null)
			{
				utilisateur = annuaire.getUtilisateur(courriel);
				if (utilisateur.getMotDePasse().equals(identifiants.substring(courriel.length()+1)))
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
		
		return reponse;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteConsulter(java.lang.String)
	 */
	public String traiterRequeteConsulter(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();

		if (ProtocoleAnnuaire.validerRequeteConsulterProfils(requete))
		{
			if (utilisateur != null)
				reponse = ProtocoleAnnuaire.ok(annuaire.getArraySecuriseTousUtilisateursUtilisateur().toString());
			else
				reponse = ProtocoleAnnuaire.ok(annuaire.getArraySecuriseTousUtilisateursAnonyme().toString());
		}
		else if (ProtocoleAnnuaire.validerRequeteConsulterProfil(requete))
		{
			// TODO remplacer ça par fonction dans ProtocoleAnnuaire :
			String courriel = ControlleurProtocole.requeteURI(requete).replace("/profils/", "");
			
			if (annuaire.existeUtilisateur(courriel))
			{
				Utilisateur temp = annuaire.getUtilisateur(courriel);
				
				if(utilisateur != null)
					reponse = ProtocoleAnnuaire.ok(temp.toStringUtilisateur());
				else
					reponse = ProtocoleAnnuaire.ok(temp.toStringAnonyme());
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else
			reponse = ProtocoleAnnuaire.erreurRequete();
		
		return reponse;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteInscrire(java.lang.String)
	 */
	public String traiterRequeteInscrire(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();

		if (ProtocoleAnnuaire.validerRequeteInscrireProfil(requete))
		{
			Utilisateur u = new Utilisateur();
			u.fromJSONString(ControlleurProtocole.requeteCorps(requete));
			
			if(annuaire.existeUtilisateur(u.getCourriel()) || utilisateur != null)
			{
				reponse = ProtocoleAnnuaire.erreurInterdit();
			}
			else
			{
				annuaire.ajoutUtilisateur(utilisateur = u);
				reponse = ProtocoleAnnuaire.ok(utilisateur.toString());
			}
		}
		else if (ProtocoleAnnuaire.validerRequeteLike(requete))
		{
			String courrielCible = ControlleurProtocole.requeteURI(requete).replace("/profils/", "").split("/",2)[0];
			if(annuaire.existeUtilisateur(courrielCible))
			{
				String competenceCible = ControlleurProtocole.requeteURI(requete).replace("/profils/", "").split("/",2)[1].replace("competences/", "");
				Utilisateur cible = annuaire.getUtilisateur(courrielCible);
				cible.addLike(competenceCible, utilisateur.getCourriel());
				annuaire.ajoutUtilisateur(cible);
				reponse = ProtocoleAnnuaire.ok();
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else
			reponse = ProtocoleAnnuaire.erreurRequete();
		
		return reponse;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteModification(java.lang.String)
	 */
	public String traiterRequeteModification(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();

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
		
		return reponse;
	}
	
	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteSuppression(java.lang.String)
	 */
	public String traiterRequeteSuppression(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();

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
		else if (ProtocoleAnnuaire.validerRequeteLike(requete))
		{
			String courrielCible = ControlleurProtocole.requeteURI(requete).replace("/profils/", "").split("/",2)[0];
			if(annuaire.existeUtilisateur(courrielCible))
			{
				String competenceCible = ControlleurProtocole.requeteURI(requete).replace("/profils/", "").split("/",2)[1].replace("competences/", "");
				Utilisateur cible = annuaire.getUtilisateur(courrielCible);
				if (cible.getLikes().containsKey(utilisateur.getCourriel()))
						cible.supprimerLike(competenceCible, utilisateur.getCourriel());
				reponse = ProtocoleAnnuaire.ok();
			}
			else
				reponse = ProtocoleAnnuaire.erreurRequete();
		}
		else
			reponse = ProtocoleAnnuaire.erreurRequete();
		
		return reponse;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#traiterReponse(java.lang.String)
	 */
	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleAnnuaire.isOk(reponse))
			return ProtocoleAnnuaire.extraireDonnees(reponse);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#clone()
	 */
	@Override
	public ControlleurProtocoleAnnuaire clone()
	{
		return new ControlleurProtocoleAnnuaire(annuaire);
	}
}
