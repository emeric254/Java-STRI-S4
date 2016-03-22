package stri.java_connect.protocol;

public interface TraitementRequete
{
	/**
	 * Traiter une requete de connexion
	 * 
	 * @param requete
	 * @return
	 */
	public String traiterRequeteConnexion(String requete);
	
	/**
	 * Traiter une requete de consultation
	 * 
	 * @param requete
	 * @return
	 */
	public String traiterRequeteConsulter(String requete);
	
	/**
	 * Traiter une requete d'inscription
	 * 
	 * @param requete
	 * @return
	 */
	public String traiterRequeteInscrire(String requete);
	
	/**
	 * Traiter une requete de modification
	 * 
	 * @param requete
	 * @return
	 */
	public String traiterRequeteModification(String requete);
	
	/**
	 * Traiter une requete de suppression
	 * 
	 * @param requete
	 * @return
	 */
	public String traiterRequeteSuppression(String requete);
}
