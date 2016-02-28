package stri.java_connect.protocol;

import stri.java_connect.modele.AnnuaireMessagerie;
import stri.java_connect.modele.Utilisateur;

public class ControlleurProtocoleMessagerie extends ControlleurProtocole
{
	private AnnuaireMessagerie annuaire;
	private Utilisateur utilisateur;
	
	public ControlleurProtocoleMessagerie()
	{
		utilisateur = null;
		annuaire = new AnnuaireMessagerie();
	}

	@Override
	public String traiterRequete(String requete)
	{
		return null;
	}

	@Override
	public String traiterReponse(String reponse)
	{
		return null;
	}

	@Override
	public ControlleurProtocoleMessagerie clone()
	{
		return new ControlleurProtocoleMessagerie();
	}
}
