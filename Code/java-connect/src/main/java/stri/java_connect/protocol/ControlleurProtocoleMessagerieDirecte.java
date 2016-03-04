package stri.java_connect.protocol;

public class ControlleurProtocoleMessagerieDirecte extends ControlleurProtocole
{
	//
	
	@Override
	public String traiterRequete(String requete)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String traiterReponse(String reponse)
	{
		if(ProtocoleMessagerie.isOk(reponse))
			return ProtocoleMessagerie.extraireDonnees(reponse);
		return null;
	}

	@Override
	public ControlleurProtocole clone()
	{
		return new ControlleurProtocoleMessagerieDirecte();
	}
}
