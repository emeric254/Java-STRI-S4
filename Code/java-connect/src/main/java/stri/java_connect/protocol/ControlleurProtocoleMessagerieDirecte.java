package stri.java_connect.protocol;

/**
 * @author emeric
 *
 */
public class ControlleurProtocoleMessagerieDirecte extends ControlleurProtocole
{
	//
	
	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#traiterRequete(java.lang.String)
	 */
	@Override
	public String traiterRequete(String requete)
	{
		// TODO Auto-generated method stub
		return null;
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

	/* (non-Javadoc)
	 * @see stri.java_connect.protocol.ControlleurProtocole#clone()
	 */
	@Override
	public ControlleurProtocole clone()
	{
		return new ControlleurProtocoleMessagerieDirecte();
	}
}
