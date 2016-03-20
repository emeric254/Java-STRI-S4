package stri.java_connect.protocol;

/**
 * @author emeric
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

	public String traiterRequeteConnexion(String requete)
	{
		return null;
	}

	public String traiterRequeteConsulter(String requete)
	{
		return null;
	}

	public String traiterRequeteInscrire(String requete)
	{
		return null;
	}

	public String traiterRequeteModification(String requete)
	{
		return null;
	}

	public String traiterRequeteSuppression(String requete)
	{
		return null;
	}
}
