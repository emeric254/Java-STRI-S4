package stri.java_connect.bdd;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.modele.Utilisateur;

/**
 * @author Thomas, Rémi
 *
 */
public class Sqlite
{
	private String nomBDD;
	private Connection connexion;
	private Statement statement;
	
	/**
	 * Creation d'un objet Sqlite
	 * 
	 * @param dbFileName le nom de la base de donnees (nom du fichier la reprensentant)
	 */
	public Sqlite(String dbFileName) throws ClassNotFoundException
	{
		nomBDD = dbFileName;
		Class.forName("org.sqlite.JDBC");
		dbConnect();
	}
	
	/**
	 * Connexion a la base de donnees
	 * 
	 */
	public void dbConnect()
	{
		connexion = null;
		try
		{
			connexion = DriverManager.getConnection("jdbc:sqlite:./" + nomBDD);
			statement = connexion.createStatement();
			statement.setQueryTimeout(20);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Initialisation de la base de donnees
	 * 
	 */
	public void init()
	{
		if(executerMaj("drop table if exists person") && executerMaj("create table person (id integer, name string)"))
			System.out.println("partie Tables reussie");

		if(executerMaj("insert into person values(1, 'leo')") && executerMaj("insert into person values(2, 'yui')"))
			System.out.println("partie Insertion reussie");
		try
		{
			ResultSet rs = executerRequete("select * from person");
			while(rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Création de l'ensemble des tables
	 */
	public void creationTables()
	{
		String req = "CREATE TABLE utlisateur (courriel string primary key not null," +
				" motDePasse string NOT NULL," +
				" nom string NOT NULL," +
				" privilege string NOT NULL," +
				" telephone string NOT NULL," +
				" dateDiplome long NOT NULL," +
				" permissionLecture string NOT NULL);";
		executerMaj(req);
		req = "CREATE TABLE competence (nomCompetence string, courriel string," +
				" foreign key (courriel) references utilisateur(courriel)," +
				" primary key(competence,courriel);";
		executerMaj(req);
		req = "CREATE TABLE Message (courrielSource string not null," +
				" courrielDestinataire string not null," +
				" dateMessage timestamp not null," +
				" message text," +
				" foreing key (corrielSource) references utilisateur(courriel)," +
				" foreing key (courrielDestinataire) references utilisateur(courriel)," +
				" primary key (courrielSource,courrielDestinataire,dateMessage)" +
				" );";
		executerMaj(req);
		req = "CREATE TABLE aimer (courrielLikant string not null," +
				" nomCompetence string not null," +
				" courrielPropCompetence string not null," +
				" foreing key (courrielLikant) references utilisateur(courriel)," +
				" foreing key (courrielPropCompetence) references utilisateur(courriel)," +
				" foreing key (nomCompetence) references competence(nomCompetence)," +
				" primary key (courrielLikant,courrielPropCompetence,idCompetence)" +
				" );";
		executerMaj(req);
	}
	
	/**
	 * Suppression de l'ensemble des tables
	 */
	public void suppressionTables()
	{
		String req = "DROP TABLE IF EXISTS Utilisateur;";
		executerMaj(req);
		req = "DROP TABLE IF EXISTS competence;";
		executerMaj(req);
		req = "DROP TABLE IF EXISTS envoyerMessage;";
		executerMaj(req);
		req = "DROP TABLE IF EXISTS aimer;";
		executerMaj(req);
	}
	
	/**
	 * Insertion dans la base de donnée d'un nouvel utilisateur
	 * 
	 * @param courriel
	 * @param motDePasse
	 * @param nom
	 * @param privilege
	 * @param telephone
	 * @param dateDiplome
	 * @param permissionLecture
	 */
	public void insertUtilisateur(String courriel, String motDePasse, String nom, String privilege, String telephone, long dateDiplome, String permissionLecture)
	{
		String req ="insert into Utilisateur (courriel, motDePasse, nom, privilege, telephone, dateDiplome, permissionLecture)" +
				" values ("+courriel+","+motDePasse+","+nom+","+privilege+","+telephone+","+dateDiplome+","+permissionLecture+");";
		executerRequete(req);
	}
	
	
	/**
	 * Ajout d'une nouvelle compétence associée à un utilisateur
	 * 
	 * @param courriel
	 * @param competence
	 */
	public void insertNouvelleCompetence(String courriel, String competence)
	{
		String req = "INSERT INTO Competence(nomCompetence,courriel) VALUES ("+competence+","+courriel+");";
		executerRequete(req);
	}
	
	/**
	 * Ajout d'un nouvel utilisateur dans la base de donnée
	 * @param user
	 */
	public void ajouterUtilisateur(Utilisateur user)
	{
		insertUtilisateur(user.getCourriel(),user.getMotDePasse(),user.getNom(),user.getPrivilege(),user.getTelephone(),user.getDateDiplome(),user.getPermissionLecture());
		for (String competence : user.getCompetences())
		{
			insertNouvelleCompetence(user.getCourriel(),competence);
		}
	}
	
	/**
	 * Chargement de l'annuaire complet
	 * 
	 * @return
	 */
	public ClientAnnuaire chargerAnnuaire()
	{
		ClientAnnuaire client = new ClientAnnuaire();
		ResultSet resultSet2;
		String req = "SELECT * from utilisateur;";
		ResultSet resultSet = executerRequete(req);
		try {
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setCourriel(resultSet.getString("courriel"));
                utilisateur.setMotDePasse(resultSet.getString("motDePasse"));
                utilisateur.setPermissionLecture(resultSet.getString("permissionLecture")); 
                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setTelephone(resultSet.getString("telephone"));
                utilisateur.setDateDiplome(resultSet.getLong("dateDiplome"));
                // Ajout des compétences
                req = "SELECT * FROM competence WHERE courriel = " + utilisateur.getCourriel() + ";";
                resultSet2 = executerRequete(req);
                while ( resultSet2.next())
                {
                	utilisateur.addCompetence(resultSet2.getString("nomCompetence"));
                }	
                client.inscription(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	
	/**
	 * Suppression d'un utilisateur
	 * 
	 * @param courriel
	 */
	public void supprimerUtilisateur(String courriel)
	{
		String req = "DELETE FROM Utilisateur WHERE courriel = \""+courriel+"\";";
		executerRequete(req);
	}

	
	
	/*********************
	 * Pour la version 2 *
	 *********************/
	
	/**
	 * Insertion d'un nouveau message
	 * 
	 * @param courrielSource
	 * @param CourrielDestinataire
	 * @param dateMessage
	 * @param message
	 */
	public void insertMessage (String courrielSource, String courrielDestinataire, Timestamp dateMessage, String message)
	{
		String req = "INSERT INTO Message (courrielSource, courrielDestinataire, dateMessage, message) " +
				"VALUES ("+courrielSource+","+courrielDestinataire+","+dateMessage+","+message+");";
		executerRequete(req);
	}
	
	
	/**
	 * Récupération de tous les messages reçus
	 * 
	 * @param courriel
	 */
	public void lectureMessage(String courriel)
	{
		String req = "SELECT * FROM Message WHERE courrielDestinataire = \""+courriel+"\";";
		executerRequete(req);
		//TODO : traiter les messages
		
		// Suppression des messages lues
		
	}
	
	public void supprimerMessage(String courrielSource, String courrielDestinataire, Timestamp dateMessage)
	{
		String req = "DELETE FROM message WHERE courrielSource = '"+courrielSource+"', courrielDestinataire ='"+courrielDestinataire+"', dateMessage = "+dateMessage+";";
		executerRequete(req);
	}
	

	/*********************
	 * Pour la version 3 *
	 *********************/
	
	/**
	 * Récupération des tous les likes associés à une utilisateur
	 * 
	 * @param user
	 */
	public void chargerLike(Utilisateur user)
	{
		String req = "SELECT courrielLikant,nomCompetence FROM aimer WHERE courrielPropCompetence ='" + user.getCourriel() +"';";
		ResultSet resultSet = executerRequete(req);
		while (resultSet.next())
		{
			user.addLike(resultSet.getString("nomCompetence"), resultSet.getString("courrielLikant");
		}
	}
	
	/**
	 * Ecriture dans la base de donnée d'un nouveau like
	 * 
	 * @param courrielLikant
	 * @param courrielPropCompetence
	 * @param nomCompetence
	 */
	public void ajouterLike(String courrielLikant, String courrielPropCompetence, String nomCompetence)
	{
		String req = "INSERT INTO  aimer (courrielLikant, courrielPropCompetence, nomCompetence) VALUES ('"+courrielLikant+
				"','"+courrielPropCompetence+"','"+nomCompetence+"');";
		executerRequete(req);
	}
	
	public void supprimerLike(String courrielLikant, String courrielPropCompetence, String nomCompetence)
	{
		String req = "DELETE FROM  aimer WHERE courrielikant = '"+courrielLikant+"' AND " +
				"courrielPropCompetence ='"+courrielPropCompetence+
				"', nomCompetence = '"+nomCompetence+"');";
		executerRequete(req);
	}
	
	
	
	/**
	 * Fermeture de la base de donnees
	 * 
	 */
	public void close()
	{
		if(connexion != null)
		{
			try
			{
				connexion.close();
			}
			catch(SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Executer une requete sur la base de donnees
	 * 
	 * @param sql la requete
	 * @return true si reussie, false si erreur
	 */
	public ResultSet executerRequete(String sql)
	{
		ResultSet rs = null;
		try
		{
			rs = statement.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return rs;
	}
	
	/**
	 * Executer une mise a jour de la (structure de la) base de donnees
	 * 
	 * @param sql la requete
	 * @return true si reussie, false si erreur
	 */
	public boolean executerMaj(String sql)
	{
		boolean state = true;
		try
		{
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			state = false;
		}
		return state;
	}
	
	/**
	 * Reset de la base de donnees
	 * 
	 */
	public void reset()
	{
		close();
		File temp = new File("./" + nomBDD);
		temp.delete();
		dbConnect();
	}
}
