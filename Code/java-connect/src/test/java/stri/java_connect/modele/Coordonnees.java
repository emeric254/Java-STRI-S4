package stri.java_connect.modele;

/**
 * Class Coordonnees
 */
public class Coordonnees {

  //
  // Fields
  //

  private String nom;
  private Long dateDiplome;
  private String telephone;
  private String courriel;
  private String permissionLecture;
  
  //
  // Constructors
  //
  public Coordonnees ()
  {
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of nom
   * @param newVar the new value of nom
   */
  private void setNom (String newVar)
  {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  private String getNom ()
  {
    return nom;
  }

  /**
   * Set the value of dateDiplome
   * @param newVar the new value of dateDiplome
   */
  private void setDateDiplome (Long newVar)
  {
    dateDiplome = newVar;
  }

  /**
   * Get the value of dateDiplome
   * @return the value of dateDiplome
   */
  private Long getDateDiplome ()
  {
    return dateDiplome;
  }

  /**
   * Set the value of telephone
   * @param newVar the new value of telephone
   */
  private void setTelephone (String newVar)
  {
    telephone = newVar;
  }

  /**
   * Get the value of telephone
   * @return the value of telephone
   */
  private String getTelephone ()
  {
    return telephone;
  }

  /**
   * Set the value of courriel
   * @param newVar the new value of courriel
   */
  private void setCourriel (String newVar)
  {
    courriel = newVar;
  }

  /**
   * Get the value of courriel
   * @return the value of courriel
   */
  private String getCourriel ()
  {
    return courriel;
  }

  /**
   * Set the value of permissionLecture
   * @param newVar the new value of permissionLecture
   */
  private void setPermissionLecture (String newVar)
  {
    permissionLecture = newVar;
  }

  /**
   * Get the value of permissionLecture
   * @return the value of permissionLecture
   */
  private String getPermissionLecture ()
  {
    return permissionLecture;
  }

  //
  // Other methods
  //

}
