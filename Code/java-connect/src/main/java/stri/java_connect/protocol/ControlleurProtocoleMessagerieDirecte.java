package stri.java_connect.protocol;

/**
 * @author emeric, thomas
 *
 */
public class ControlleurProtocoleMessagerieDirecte extends ControlleurProtocole
{
	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleMessagerie.isOk(reponse))
			return ProtocoleMessagerie.extraireDonnees(reponse);
		return null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#clone()
	 */
	@Override
	public ControlleurProtocole clone()
	{
		return new ControlleurProtocoleMessagerieDirecte();
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteConnexion(java.lang.String)
	 */
	public String traiterRequeteConnexion(String requete)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteConsulter(java.lang.String)
	 */
	public String traiterRequeteConsulter(String requete)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteInscrire(java.lang.String)
	 */
	public String traiterRequeteInscrire(String requete)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteModification(java.lang.String)
	 */
	public String traiterRequeteModification(String requete)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.TraitementRequete#traiterRequeteSuppression(java.lang.String)
	 */
	public String traiterRequeteSuppression(String requete)
	{
		return null;
	}
}
