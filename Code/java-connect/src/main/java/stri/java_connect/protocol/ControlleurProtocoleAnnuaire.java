/**
 * 
 */
package stri.java_connect.protocol;

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
	
	/**
	 * 
	 */
	public ControlleurProtocoleAnnuaire()
	{
		// TODO
		connecte = false;
		utilisateur = null;
	}

	@Override
	public String traiterRequete(String requete)
	{
		String reponse = ProtocoleAnnuaire.erreurServeur();
		
		if(ProtocoleAnnuaire.isRequeteConnexion(requete))
		{
			if(ProtocoleAnnuaire.validerRequeteConnexion(requete))
			{
				// TODO connexion
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
				// TODO liste profil
			}
			else if(ProtocoleAnnuaire.validerRequeteConsulterProfil(requete))
			{
				// TODO details profil
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
