package stri.java_connect.modele;

import java.util.ArrayDeque;

/**
 * Class Utilisateur
 */
public class Utilisateur {

  //
  // Fields
  //

  private String identifiant;
  private String motDePasse;
  
  private String nom;
  private Long dateDiplome;
  private String telephone;
  private String courriel;
  private String permissionLecture;

  private String privilege;

  private ArrayDeque<String> Competences;
  
  //
  // Constructors
  //
  public Utilisateur ()
  {
	  identifiant = "";
	  motDePasse = "";
	  
	  nom = "";
	  dateDiplome = (long) -1;
	  telephone = "";
	  courriel = "";
	  permissionLecture = "{"
	  		+ "\"nom\":\"anonyme\","
	  		+ "\"datediplome\":\"anonyme\","
	  		+ "\"telephone\":\"anonyme\","
	  		+ "\"courriel\":\"anonyme\","
	  		+ "\"competences\":\"anonyme\""
	  		+ "}";
	  
	  privilege = "anonyme";
	  
	  Competences = new ArrayDeque<String>();
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of identifiant
   * @param newVar the new value of identifiant
   */
  public void setIdentifiant (String newVar)
  {
    identifiant = newVar;
  }

  /**
   * Get the value of identifiant
   * @return the value of identifiant
   */
  public String getIdentifiant ()
  {
    return identifiant;
  }

  /**
   * Set the value of motDePasse
   * @param newVar the new value of motDePasse
   */
  public void setMotDePasse (String newVar)
  {
    motDePasse = newVar;
  }

  /**
   * Get the value of motDePasse
   * @return the value of motDePasse
   */
  public String getMotDePasse ()
  {
    return motDePasse;
  }

  /**
   * Set the value of nom
   * @param newVar the new value of nom
   */
  public void setNom (String newVar)
  {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  public String getNom ()
  {
    return nom;
  }

  /**
   * Set the value of dateDiplome
   * @param newVar the new value of dateDiplome
   */
  public void setDateDiplome (Long newVar)
  {
    dateDiplome = newVar;
  }

  /**
   * Get the value of dateDiplome
   * @return the value of dateDiplome
   */
  public Long getDateDiplome ()
  {
    return dateDiplome;
  }

  /**
   * Set the value of telephone
   * @param newVar the new value of telephone
   */
  public void setTelephone (String newVar)
  {
    telephone = newVar;
  }

  /**
   * Get the value of telephone
   * @return the value of telephone
   */
  public String getTelephone ()
  {
    return telephone;
  }

  /**
   * Set the value of courriel
   * @param newVar the new value of courriel
   */
  public void setCourriel (String newVar)
  {
    courriel = newVar;
  }

  /**
   * Get the value of courriel
   * @return the value of courriel
   */
  public String getCourriel ()
  {
    return courriel;
  }

  /**
   * Set the value of permissionLecture
   * @param newVar the new value of permissionLecture
   */
  public void setPermissionLecture (String newVar)
  {
    permissionLecture = newVar;
  }

  /**
   * Get the value of permissionLecture
   * @return the value of permissionLecture
   */
  public String getPermissionLecture ()
  {
    return permissionLecture;
  }

  /**
   * Set the value of privilege
   * @param newVar the new value of privilege
   */
  public void setPrivilege (String newVar)
  {
	  privilege = newVar;
  }

  /**
   * Get the value of privilege
   * @return the value of privilege
   */
  public String getPrivilege ()
  {
    return privilege;
  }

  /**
   * Get the value of Competences
   * @return the value of Competences
   */
  public ArrayDeque<String> getCompetences ()
  {
    return Competences;
  }

  /**
   * Set the value of Competences
   * @param newVar the new value of Competences
   */
  public void setCompetences (ArrayDeque<String> newVar)
  {
	  Competences = newVar;
  }

  //
  // Other methods
  //


  /**
   * Get Utilisateur JSON String representation
   * @return the Utilisateur object as a JSON formated String
   */
  @Override
  public String toString()
  {
	  String chaine = "{";
	  chaine += "\"identifiant\":";
	  chaine += "\"" + identifiant + "\",";
	  
	  chaine += "\"motdepasse\":";
	  chaine += "\"" + motDePasse + "\",";
	  
	  chaine += "\"nom\":";
	  chaine += "\"" + nom + "\",";
	  
	  chaine += "\"datediplome\":";
	  chaine += "\"" + dateDiplome + "\",";
	  
	  chaine += "\"telephone\":";
	  chaine += "\"" + telephone + "\",";
	  
	  chaine += "\"courriel\":";
	  chaine += "\"" + courriel + "\",";
	  
	  chaine += "\"permissionlecture\":";
	  chaine += permissionLecture + ",";
	  
	  chaine += "\"privilege\":";
	  chaine += "\"" + privilege + "\",";
	  
	  chaine += "\"competences\":";
	  chaine += "[";
	  for(String temp : Competences)
	  {
		  chaine += "\"" + temp + "\",";
	  }
	  chaine = chaine.substring(0, chaine.length()-1);
	  chaine += "]";
	  chaine += "}";
	  return chaine;
  }
}
