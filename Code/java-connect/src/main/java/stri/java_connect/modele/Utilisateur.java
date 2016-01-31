package stri.java_connect.modele;

import java.util.ArrayDeque;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class Utilisateur
 */
public class Utilisateur
{

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
	  permissionLecture = "anonyme";
	  // TODO permissions plus detaillees
	  /*
	  permissionLecture = "{"
	  		+ "'nom':'anonyme',"
	  		+ "'datediplome':'anonyme',"
	  		+ "'telephone':'anonyme',"
	  		+ "'courriel':'anonyme',"
	  		+ "'competences':'anonyme'"
	  		+ "}";
	   */
	  privilege = "anonyme";
	  Competences = new ArrayDeque<String>();
  };
  
  public Utilisateur (String pId, String pMdp)
  {
	  this();
	  setIdentifiant(pId);
	  setMotDePasse(pMdp);
  };
  
  public Utilisateur (String pNom, String pTelephone, String pCourriel)
  {
	  this();
	  setNom(pNom);
	  setTelephone(pTelephone);
	  setCourriel(pCourriel); 
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
   * Set the value of dateDiplome
   * @param newVar the new value of dateDiplome
   */
  public void setDateDiplomeFromDate (Date newVar)
  {
    setDateDiplome(newVar.getTime());
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
   * Get the value of dateDiplome as a Date object
   * @return the value of dateDiplome as a Date object
   */
  public Date getDateDiplomeAsDate ()
  {
    return new Date(getDateDiplome());
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
	  // clone to avoid some security issues
	  return Competences.clone();
  }

  /**
   * Set the value of Competences
   * @param newVar the new value of Competences
   */
  public void setCompetences (ArrayDeque<String> newVar)
  {
	  Competences = newVar;
  }

  /**
   * Add a Competence
   * @param newVar the new Competence to add
   */
  public void addCompetence (String newVar)
  {
	  Competences.add(newVar);
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
	  chaine += "\"identifiant\": \"" + identifiant + "\",";
	  
	  chaine += "\"motdepasse\": \"" + motDePasse + "\",";
	  
	  chaine += "\"nom\": \"" + nom + "\",";
	  
	  chaine += "\"datediplome\": \"" + dateDiplome + "\",";
	  
	  chaine += "\"telephone\": \"" + telephone + "\",";
	  
	  chaine += "\"courriel\": \"" + courriel + "\",";
	  
	  chaine += "\"permissionlecture\":" + permissionLecture + ",";
	  
	  chaine += "\"privilege\": \"" + privilege + "\",";
	  
	  chaine += "\"competences\": [";
	  for(String temp : Competences)
	  {
		  chaine += "\"" + temp + "\",";
	  }
	  if(Competences.size() > 0)
		  chaine = chaine.substring(0, chaine.length()-1);
	  chaine += "] }";
	  return chaine;
  }
  
  public void fromJSONString(String json)
  {
	  // TODO import from json string
	  JSONObject details = new JSONObject(json);
	  setIdentifiant(details.getString("identifiant"));
	  setMotDePasse(details.getString("motdepasse"));
	  setCourriel(details.getString("courriel"));
	  setPermissionLecture(details.getString("permissionlecture"));
	  setPrivilege(details.getString("privilege"));
	  JSONArray listeCompetences = details.getJSONArray("competences");
	  for (Object object : listeCompetences)
	  {
		  Competences.add((String) object);
	  }
  }
  
  @Override
  public Utilisateur clone()
  {
	  Utilisateur copie = new Utilisateur();
	  copie.fromJSONString(this.toString());
	  return copie;
  }
}
