/**
 * 
 */
package stri.java_connect.ihm;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;
import stri.java_connect.utils.CourrielValidateur;
import stri.java_connect.utils.IHMUtilitaires;

/**
 * @author emeric, remi
 *
 */
public class IHM
{
	private ClientAnnuaire client;
    private Utilisateur utilisateur;
	
	/**
	 * @param pClient
	 */
	public IHM(ClientAnnuaire pClient)
	{
		client = pClient;
		utilisateur = null;
		afficherAccueil();
	}
	
	/**
	 * 
	 */
	private void afficherAccueil()
	{
	    String choix = "";
	    IHMUtilitaires.cleanTerminal();
	    do
	    {
	        System.out.println(" = = = Menu principal (vous n'etes pas connecté) = = =  \n Saisissez votre choix :");
	        //
	        System.out.println("\n =>  1 - Créer un compte (nouvel utilisateur).");
	        System.out.println("\n =>  2 - Se connecter.");
	        System.out.println("\n =>  3 - Afficher la liste de tous les étudiants.");
	        System.out.println("\n =>  0 - Quitter.");

	        choix = IHMUtilitaires.saisie();

	        if ("1".equals(choix))
	        {
	    	    IHMUtilitaires.cleanTerminal();
	            try
	            {
					afficherInscription();
				}
	            catch (IOException e)
	            {
	            	utilisateur = null;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				choix = "0";
	        }
	        else if ("2".equals(choix))
	        {
	    	    IHMUtilitaires.cleanTerminal();
	            try
	            {
					afficherConnexion();
				}
	            catch (IOException e)
	            {
	            	utilisateur = null;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				choix = "0";
	        }
	        else if ("3".equals(choix))
	        {
	    	    IHMUtilitaires.cleanTerminal();
	    	    try
	    	    {
					afficherListeProfils();
				}
	    	    catch (IOException e)
	    	    {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else if ("0".equals(choix))
	        {
	        	client.deconnexion();
	    	    IHMUtilitaires.cleanTerminal();
	        }
	        else
	        {
	    	    IHMUtilitaires.cleanTerminal();
	        	System.out.println("Veuillez choisir quelque chose de valide !");
	        }
	    } while (! "0".equals(choix) );
	}
	
	/**
	 * @throws IOException
	 */
	private void afficherListeProfils() throws IOException
	{
		String temp = client.consulterProfils();
		try
		{
			JSONArray j = ProtocoleAnnuaire.extraireJSONArray(temp);
			for (int i = 0; i < j.length(); i++)
			{
				Utilisateur u = new Utilisateur();
				u.fromJSONString(j.getJSONObject(i).toString());
				afficherLigneProfilUtilisateur(u);
			}
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void afficherMenuPrincipal()
    {
        String choix = "";
	    IHMUtilitaires.cleanTerminal();
        do
        {
            System.out.println(" = = = Menu principal (vous etes connecté) = = = ");
            System.out.println("Bonjour " + utilisateur.getNom() + "\n Saisissez votre choix :");
            //
            System.out.println("\n =>  1 - Afficher la liste de tous les étudiants.");
            System.out.println("\n =>  2 - Consulter votre profil.");
            System.out.println("\n =>  3 - Modifier votre profil.");
            System.out.println("\n =>  4 - Consulter les détails d'un étudiant.");
            System.out.println("\n =>  5 - Chercher un étudiant.");
            //System.out.println("\n =>  6 - Messagerie.");
            System.out.println("\n =>  0 - Quitter.");

	        choix = IHMUtilitaires.saisie();

	        if ("1".equals(choix))
	        {
	            try
	            {
					afficherListeProfils();
				}
	            catch (IOException e)
	            {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
	        }
	        else if ("2".equals(choix))
            {
                afficherProfil();
            }
	        else if ("3".equals(choix))
	        {
	        	try
	        	{
					afficherModificationProfil();
				}
	        	catch (IOException e)
	        	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else if ("4".equals(choix))
	        {
	        	afficherDetailsProfil();
	        }
	        else if ("5".equals(choix))
	        {
	        	System.out.println("Pas encore implemente");
	        }
	        else if ("0".equals(choix))
	        {
	        	client.deconnexion();
	        }
	        else
	        {
	        	System.out.println("Veuillez choisir quelque chose de valide !");
	        }
        } while (! "0".equals(choix));
    }
	

    /**
     * 
     */
    private void afficherDetailsProfil()
    {
    	String temp = "";
        do
        {
            temp = IHMUtilitaires.saisie("Entrez l'adresse mail du profil :");
        } while ( !CourrielValidateur.valider(temp) );
        try
        {
			temp = client.consulterProfil(temp);
			Utilisateur u = new Utilisateur();
			try
			{
				u.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(temp).toString());
				afficherProfilUtilisateur(u);
			}
			catch (JSONException e)
			{
				System.out.println("Pas d'utilisateur correspondant ...");
			}
		}
        catch (IOException e)
        {
        	System.out.println("Cette addresse n'existe pas ...");
		}
	}

	/**
     * @throws IOException 
     * 
     */
    private void afficherInscription() throws IOException
    {
        String temp ="";
        utilisateur = new Utilisateur();
        
        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre adresse mail :");
        } while ( !CourrielValidateur.valider(temp) || ProtocoleAnnuaire.isOk(client.consulterProfil(temp)) );
        
        utilisateur.setCourriel(temp);
        temp = "";

        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre mot de passe :");
        } while ( temp.length() <= 0 );
        
        utilisateur.setMotDePasse(temp);
        temp = "";

        do
        {
            temp = IHMUtilitaires.saisie("Choisissez la visibilite de votre profil : \n  0 - visibilite minimale\n  1 - visibilite normale");
        } while ( !("0".equals(temp) || "1".equals(temp)) );
        
        if("1".equals(temp))
        	utilisateur.setPermissionLecture("anonyme");
        else 
        	utilisateur.setPermissionLecture("utilisateur");
        temp = "";
        
        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre nom et prenom :");
        } while ( temp.length() <= 0 );
        
        utilisateur.setNom(temp);
        temp = "";

        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre numero de telephone :");
            // TODO telephone validator
        } while ( temp.length() <= 0 );
        
        utilisateur.setTelephone(temp);
        temp = "";

        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre dernière année de diplomation :");
            // TODO date validator
            try
            {
            	Long.parseLong(temp);
            }
            catch(NumberFormatException e)
            {
            	temp = "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.setDateDiplome(Long.parseLong(temp));
        temp = "";

        do
        {
            temp = IHMUtilitaires.saisie("Entrez votre/vos compétences (Une au minumum) :");
        } while ( temp.length() <= 0 );
        
        utilisateur.addCompetence(temp);
    	temp= "";

        do
        {
            temp = IHMUtilitaires.saisie("En avez vous une autre à saisir ? (O/N)");
            if ("O".equalsIgnoreCase(temp))
            {
                do
                {
                    temp = IHMUtilitaires.saisie("Saisir la nouvelle compétence :");
                } while ( temp.length() <= 0 );
                utilisateur.addCompetence(temp);
            }
        } while (!"N".equalsIgnoreCase(temp));

        String reponse = client.inscription(utilisateur);
        
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
        afficherMenuPrincipal();
    }

    /**
     * @throws IOException 
     * 
     */
    private void afficherModificationProfil() throws IOException
    {
        String temp ="";

        temp = IHMUtilitaires.saisie("Changer de mot de passe ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre mot de passe :");
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.setMotDePasse(temp);
        }
        temp = "";
        

        temp = IHMUtilitaires.saisie("Changer de nom ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre nom et prenom :");
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.setNom(temp);
        }
        temp = "";

        temp = IHMUtilitaires.saisie("Changer la visibilite de votre profil ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Choisissez la visibilite de votre profil : \n  0 - visibilite minimale\n  1 - visibilite normale");
	        } while ( !(temp.equals("0") || "1".equals(temp)) );
	        
	        if("1".equals(temp))
	        	utilisateur.setPermissionLecture("anonyme");
	        else 
	        	utilisateur.setPermissionLecture("utilisateur");
	    }
	    temp = "";

        temp = IHMUtilitaires.saisie("Changer de numero de telephone ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre numero de telephone :");
	            // TODO telephone validator
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.setTelephone(temp);
	    }
	    temp = "";

        temp = IHMUtilitaires.saisie("Changer d'annee de diplomation ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre dernière année de diplomation :");
	            // TODO date validator
	            try
	            {
	            	Long.parseLong(temp);
	            }
	            catch(NumberFormatException e)
	            {
	            	temp = "";
	            }
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.setDateDiplome(Long.parseLong(temp));
	    }
	    temp = "";

        temp = IHMUtilitaires.saisie("Changer toutes vos competences ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre/vos compétences (Une au minumum) :");
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.addCompetence(temp);
	    	temp= "";
	
	        do
	        {
	            temp = IHMUtilitaires.saisie("En avez vous une autre à saisir ? (O/N)");
	            if ("O".equalsIgnoreCase(temp))
	            {
	                do
	                {
	                    temp = IHMUtilitaires.saisie("Saisir la nouvelle compétence :");
	                } while ( temp.length() <= 0 );
	                utilisateur.addCompetence(temp);
	            }
	        } while (!"N".equalsIgnoreCase(temp));
	    }
	    temp = "";

        temp = IHMUtilitaires.saisie("Ajouter une competence ? (O/N)");
        if ("O".equalsIgnoreCase(temp))
        {
	        do
	        {
	            temp = IHMUtilitaires.saisie("Entrez votre compétences :");
	        } while ( temp.length() <= 0 );
	        
	        utilisateur.addCompetence(temp);
	    	temp= "";
	
	        do
	        {
	            temp = IHMUtilitaires.saisie("En avez vous une autre à saisir ? (O/N)");
	            if ("O".equalsIgnoreCase(temp))
	            {
	                do
	                {
	                    temp = IHMUtilitaires.saisie("Saisir la nouvelle compétence :");
	                } while ( temp.length() <= 0 );
	                utilisateur.addCompetence(temp);
	            }
	        } while (!"N".equalsIgnoreCase(temp));
	    }
	    temp = "";

        String reponse = client.modificationProfil(utilisateur);

        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
        afficherMenuPrincipal();
    }
    
    /**
     * @throws IOException
     */
    private void afficherConnexion() throws IOException
    {
        String mail = "", mdp = "", reponse = "";
        do
        {
            mail = IHMUtilitaires.saisie("Entrez votre adresse mail :");
            mdp = IHMUtilitaires.saisie("Entrez votre mot de passe :");
            
            reponse = client.connexion(mail, mdp);
        } while (!ProtocoleAnnuaire.isOk(reponse));
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
        afficherMenuPrincipal();
    }
    
    /**
     * 
     */
    private void afficherProfil()
    {
        afficherProfilUtilisateur(utilisateur);
    }	
    
    /**
     * @param pUtilisateur
     */
    private void afficherProfilUtilisateur(Utilisateur pUtilisateur)
    {
    	System.out.println("Informations détaillées du profil");
    	if (pUtilisateur.getNom().length() > 0)
    		System.out.println(" > Nom : " + pUtilisateur.getNom());
    	if (pUtilisateur.getCourriel().length() > 0)
    		System.out.println(" > Courriel : " + pUtilisateur.getCourriel());
    	if (pUtilisateur.getTelephone().length() > 0)
    		System.out.println(" > Téléphone : " + pUtilisateur.getTelephone());
    	if (pUtilisateur.getDateDiplome() > 0)
    		System.out.println(" > Date du diplôme : " + pUtilisateur.getDateDiplome());
    	if (pUtilisateur.getCompetences().size() > 0)
		{
    		System.out.println(" > Compétences : ");
	    	for(String temp : pUtilisateur.getCompetences())
	  	  	{
	  		  System.out.println("    - " + temp);
	  	  	}
		}
    }
    
    /**
     * @param pUtilisateur
     */
    private void afficherLigneProfilUtilisateur(Utilisateur pUtilisateur)
    {
    	if (pUtilisateur.getNom().length() > 0)
    		System.out.print("- Nom : " + pUtilisateur.getNom());
    	if (pUtilisateur.getCourriel().length() > 0)
    		System.out.print("; Courriel : " + pUtilisateur.getCourriel());
    	if (pUtilisateur.getTelephone().length() > 0)
    		System.out.print("; Téléphone : " + pUtilisateur.getTelephone());
    	if (pUtilisateur.getDateDiplome() > 0)
    		System.out.print("; Date du diplôme : " + pUtilisateur.getDateDiplome());
    	if (pUtilisateur.getCompetences().size() > 0)
    	{
    		System.out.print("; Compétences : ");
	    	for(String temp : pUtilisateur.getCompetences())
	  	  	{
	  		  System.out.print(temp + " ");
	  	  	}
    	}
    	System.out.println();
    }
}
