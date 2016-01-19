package stri.java_connect.modele;

/**
 * Class Utilisateur
 */
public class Utilisateur {

  //
  // Fields
  //

  private String identifiant;
  private String motDePasse;
  
  //
  // Constructors
  //
  public Utilisateur ()
  {
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
  private void setIdentifiant (String newVar)
  {
    identifiant = newVar;
  }

  /**
   * Get the value of identifiant
   * @return the value of identifiant
   */
  private String getIdentifiant ()
  {
    return identifiant;
  }

  /**
   * Set the value of motDePasse
   * @param newVar the new value of motDePasse
   */
  private void setMotDePasse (String newVar)
  {
    motDePasse = newVar;
  }

  /**
   * Get the value of motDePasse
   * @return the value of motDePasse
   */
  private String getMotDePasse ()
  {
    return motDePasse;
  }

  //
  // Other methods
  //

}
