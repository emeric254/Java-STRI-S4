/**
 * 
 */
package stri.java_connect.ihm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    private final BufferedReader fluxEntreeStandard = new BufferedReader(new InputStreamReader(System.in));
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
	        System.out.println(" = = = Menu principal (vous n'etes pas connecté) = = = ");
	        System.out.println("\n Saisir votre choix :");
	        //
	        System.out.println("\n =>  1 - Créer un compte (nouvel utilisateur).");
	        System.out.println("\n =>  2 - Se connecter.");
	        System.out.println("\n =>  3 - Afficher la liste de tous les étudiants.");
	        //
	        System.out.println("\n =>  0 - Quitter.");
	        
	        try
	        {
	            choix = fluxEntreeStandard.readLine();
	        }
	        catch(Exception e)
	        {
	            choix = "";
	        }

	        System.out.println("[DEBUG] Vous avez choisi : " + choix);

	        if (choix.equals("1"))
	        {
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
	        }
	        else if (choix.equals("2"))
	        {
	            //connexion();
	        }
	        else if (choix.equals("3"))
	        {
	            //afficherTousLesUtilisateur();
	        }
	        else if (choix.equals("0"))
	        {
	        	client.deconnexion();
	        }
	        else
	        {
	        	System.out.println("Veuillez choisir quelque chose de valide !");
	        }
	    } while (! choix.equals("4") );
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
            System.out.println("Bonjour " + utilisateur.getNom());
            System.out.println("\n Saisir votre choix :");
            //
            System.out.println("\n =>  1 - Afficher la liste de tous les étudiants.");
            System.out.println("\n =>  2 - Modifier votre profil.");
            System.out.println("\n =>  3 - Connaitre le détail d'un étudiant.");
            //System.out.println("\n =>  4 - Messagerie.");
            //
            System.out.println("\n =>  0 - Quitter.");
            
            try
            {
                choix = fluxEntreeStandard.readLine();

            } catch(Exception e)
            {
                choix = "erreur";
            }
            
	        System.out.println("[DEBUG] Vous avez choisi : " + choix);

	        if (choix.equals("1"))
	        {
	            //afficherTousLesUtilisateur();
	        }
	        else if (choix.equals("2"))
            {
                //modifierProfil();
            }
	        else if (choix.equals("3"))
	        {
	        	//detailsEtudiant();
	        }
	        else if (choix.equals("0"))
	        {
	        	client.deconnexion();
	        }
	        else
	        {
	        	System.out.println("Veuillez choisir quelque chose de valide !");
	        }
        } while (! choix.equals("0"));
    }
	

    /**
     * @throws IOException 
     * 
     */
    private void afficherInscription() throws IOException
    {
        String temp ="";
        utilisateur = new Utilisateur();
        
        //~ Formulaire de création d'un nouveau compte
        
        do
        {
            System.out.println("Entrez votre adresse mail (servira d'identifiant) : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( !CourrielValidateur.valider(temp) || ProtocoleAnnuaire.isOk(client.consulterProfil(temp)) );
        
        utilisateur.setCourriel(temp);
        temp = "";

        do
        {
        	System.out.println("Entrez votre mot de passe : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.setMotDePasse(temp);
        temp = "";

        do
        {
            System.out.println("Entrez votre nom (prenom suivi du nom) : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.setNom(temp);
        temp = "";

        do
        {
            System.out.println("Entrez votre numéro de téléphone : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.setTelephone(temp);
        temp = "";

        do
        {
            System.out.println("Entrez votre dernière année de diplomation : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            	Long.parseLong(temp);
            }
            catch(NumberFormatException e)
            {
            	temp = "";
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.setDateDiplome(Long.parseLong(temp));
        temp = "";

        do
        {
            System.out.println("Entrez votre/vos compétences (Une au minumum) : ");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
        } while ( temp.length() <= 0 );
        
        utilisateur.addCompetence(temp);
    	temp= "";

        do
        {
            System.out.println("En avez vous une autre à saisir ? (O/N)");
            try
            {
            	temp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
            	temp= "";
            }
            if (temp.toUpperCase().equals("O"))
            {
                do
                {
                	System.out.println("Saisir la nouvelle compétence");
                    try
                    {
                    	temp = fluxEntreeStandard.readLine();
                    }
                    catch(IOException e)
                    {
                    	temp= "";
                    }
                } while ( temp.length() <= 0 );
                utilisateur.addCompetence(temp);
            }
        } while (!temp.toUpperCase().equals("N"));

        client.inscription(utilisateur);
    }
    
    private void connexion() throws IOException
    {
        String mail = "", mdp = "";
        do
        {
            System.out.println("Entrez votre adresse mail (identifiant) : ");
            try
            {
                mail = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
                mail = "";
            }
            
            System.out.println("Entrez votre mot de passe : ");
            try
            {
                mdp = fluxEntreeStandard.readLine();
            }
            catch(IOException e)
            {
                mdp = "";
            }
        } while (true); // TODO : condition
        ClientAnnuaire.connexion(mail, mdp);
    }
    
    private void afficherProfil()
    {
        afficherProfilUtilisateur(utilisateur)
    }	
    
    private void afficherProfilUtilisateur(Utilisateur pUtilisateur)
    {
    	System.out.println("Informations détaillées de votre profil");
    	System.out.println("  Courriel : " + pUtilisateur.getCourriel());
    	System.out.println("  Nom : " + pUtilisateur.getNom());
    	System.out.println("  Téléphone : " + pUtilisateur.getTelephone());
    	System.out.println("  Date du diplôme : " + pUtilisateur.getDateDiplome());
    	System.out.println("  Compétences : ");
    	// TODO affichage liste competences
    	/*
    	for(String temp : pUtilisateur.getCompetences())
  	  	{
  		  chaine += glt + temp + vgl;
  	  	}
  	  	*/
    }	
}
