package stri.java_connect.bdd;

import java.io.File;
import java.sql.*;

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
				"dateDiplome long NOT NULL," +
				" permissionLecture string NOT NULL);";
		executerRequete(req);
		req = "CREATE TABLE competence (idCompetence integer auto_increment not_null," +
				"nomCompetence string, idCompetence  primary key);";
		executerRequete(req);
		req = "CREATE TABLE posseder (courriel string," +
				" idCompetence integer);";
		executerRequete(req);
		req = "CREATE TABLE Message (courrielSource string not null," +
				" courrielDestinataire string not null," +
				" dateMessage timestamp not null," +
				" message text," +
				" foreing key (corrielSource) references utilisateur(courriel)," +
				" foreing key (courrielDestinataire) references utilisateur(courriel)," +
				" primary key (courrielSource,courrielDestinataire,dateMessage)" +
				" );";
		executerRequete(req);
		req = "CREATE TABLE aimer (courrielLikant string not null," +
				" idCompetence string not null," +
				" courrielPropCompetence string not null," +
				" foreing key (courrielLikant) references utilisateur(courriel)," +
				" foreing key (courrielPropCompetence) references utilisateur(courriel)," +
				" primary key (courrielLikant,courrielPropCompetence,idCompetence)" +
				" );";
		executerRequete(req);
	}
	
	/**
	 * Suppression de l'ensemble des tables
	 */
	public void suppressionTables()
	{
		String req = "DROP TABLE IF EXISTS Utilisateur;";
		executerRequete(req);
		req = "DROP TABLE IF EXISTS competence;";
		executerRequete(req);
		req = "DROP TABLE IF EXISTS posseder;";
		executerRequete(req);
		req = "DROP TABLE IF EXISTS envoyerMessage;";
		executerRequete(req);
		req = "DROP TABLE IF EXISTS aimer;";
		executerRequete(req);
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
		Boolean present = false;
		int idCompetence =-1;
		/* On regarde si elle existe déjà */
		String req = "SELECT idCompetence FROM Competence WHERE nomCompetence = "+competence+";";
		ResultSet resultSet = executerRequete(req);
		try {
            if (resultSet.next()) {
                present = true;
                idCompetence = resultSet.getInt("idCompetence");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		if (present) // Si la compétence existe déjà
		{
			req = "INSERT INTO Posseder(courriel,idCompetence) VALUES ("+courriel+","+idCompetence+");";
			executerRequete(req);
		}
		else // Si la compétence est nouvelle
		{
			// ajout competence
			req = "INSERT INTO Competence(nomCompetence) VALUES ("+competence+");";
			executerRequete(req);
			// récupération de l'id de cette nouvelle compétence
			req = "SELECT idCompetence FROM Competence WHERE nomCompetence = "+competence+";";
			resultSet = executerRequete(req);
			try {
	            if (resultSet.next()) {
	                present = true;
	                idCompetence = resultSet.getInt("idCompetence");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			req = "INSERT INTO Posseder(courriel,idCompetence) VALUES ("+courriel+","+idCompetence+");";
			executerRequete(req);
		}
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
	public void insertMessage (String courrielSource, String CourrielDestinataire, Timestamp dateMessage, String message)
	{
		String req = "INSERT INTO Message (courrielSource, courrielDestinataire, dateMessage, message) " +
				"VALUES ("+courrielSource+","+CourrielDestinataire+","+dateMessage+","+message+");";
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
