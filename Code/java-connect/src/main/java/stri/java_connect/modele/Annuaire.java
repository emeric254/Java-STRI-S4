/**
 * 
 */
package stri.java_connect.modele;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import stri.java_connect.modele.Utilisateur;

/**
 * @author emeric
 *
 */
public class Annuaire
{
	private HashMap<String, Utilisateur> annuaire;
	
	//
	
	/**
	 * 
	 */
	public Annuaire()
	{
		annuaire = new HashMap<String, Utilisateur>();
	}

	/**
	 * @param initialCapacity
	 */
	public Annuaire(int initialCapacity)
	{
		annuaire = new HashMap<String, Utilisateur>(initialCapacity);
	}

	/**
	 * @param pAnnuaire
	 */
	public Annuaire(HashMap<String, Utilisateur> pAnnuaire)
	{
		annuaire = pAnnuaire;
	}
	
	//
	
	/**
	 * 
	 */
	public void reset()
	{
		annuaire.clear();
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public boolean existeUtilisateur(String courriel)
	{
		return annuaire.containsKey(courriel); // && annuaire.get(courriel) != null  ?
	}
	
	/**
	 * @param u
	 */
	public void ajoutUtilisateur(Utilisateur u)
	{
		annuaire.put(u.getCourriel(), u);
	}
	
	/**
	 * @param courriel
	 */
	public void suppresionUtilisateur(String courriel)
	{
		annuaire.remove(courriel);
	}
	
	public void suppresionUtilisateur(Utilisateur u)
	{
		suppresionUtilisateur(u.getCourriel());
	}
	
	/**
	 * @param courriel
	 * @return
	 */
	public Utilisateur getUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
	}
	
	/**
	 * @return
	 */
	public Collection<Utilisateur> getCollectionTousUtilisateurs()
	{
		return annuaire.values();
	}
	
	/**
	 * @return
	 */
	public ArrayDeque<Utilisateur> getArrayTousUtilisateurs()
	{
		return new ArrayDeque<Utilisateur>(annuaire.values());
	}
	
	/**
	 * @return
	 */
	public ArrayDeque<Utilisateur> getArraySecuriseTousUtilisateurs()
	{
		ArrayDeque<Utilisateur> l = new ArrayDeque<Utilisateur>();
		for (Utilisateur utilisateur : annuaire.values())
		{
			l.add(utilisateur.clone());
		}
		return l;
	}
}
