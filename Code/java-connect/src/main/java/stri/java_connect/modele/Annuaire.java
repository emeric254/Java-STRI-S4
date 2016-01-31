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
	
	public Annuaire()
	{
		annuaire = new HashMap<String, Utilisateur>();
	}

	public Annuaire(int initialCapacity)
	{
		annuaire = new HashMap<String, Utilisateur>(initialCapacity);
	}

	public Annuaire(HashMap<String, Utilisateur> pAnnuaire)
	{
		annuaire = pAnnuaire;
	}
	
	//
	
	public void reset()
	{
		annuaire.clear();
	}
	
	public boolean existeUtilisateur(String courriel)
	{
		return annuaire.containsKey(courriel); // && annuaire.get(courriel) != null  ?
	}
	
	public void ajoutUtilisateur(Utilisateur u)
	{
		annuaire.put(u.getCourriel(), u);
	}
	
	public void suppresionUtilisateur(String c)
	{
		annuaire.remove(c);
	}
	
	public void suppresionUtilisateur(Utilisateur u)
	{
		suppresionUtilisateur(u.getCourriel());
	}
	
	public Utilisateur getUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
	}
	
	public Collection<Utilisateur> getCollectionTousUtilisateurs()
	{
		return annuaire.values();
	}
	
	public ArrayDeque<Utilisateur> getArrayTousUtilisateurs()
	{
		return new ArrayDeque<Utilisateur>(annuaire.values());
	}
	
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
