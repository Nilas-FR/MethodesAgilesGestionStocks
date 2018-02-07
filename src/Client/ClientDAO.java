package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Commande.Commande;
import Commande.CommandeDAO;
import variables.Variables;

/**
 * Classe d'accès aux données contenues dans la table article
 * @version 1.1
 * */
public class ClientDAO {

	/**
	 * Paramètres de connexion à la base de données MySQL
	 * URL, LOGIN et PASS sont des constantes
	 */
	private final static String URL  = Variables.URL;
	private final static String LOGIN= Variables.LOGIN;
	private final static String PASS = Variables.PASS;

	/**
	 * Constructeur de la classe
	 */
	public ClientDAO() {
		// chargement du pilote de bases de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	

	/**
	 * Permet d'ajouter un client dans la table client
	 * l'identifiant du client est produit automatiquement par la base de données en utilisant une séquence
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouvClient client à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Client nouvClient) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("INSERT INTO client (Nom, Prenom, Adresse, Telephone, Email) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1,nouvClient.getNom());
			ps.setString(2,nouvClient.getPrenom());
			ps.setString(3,nouvClient.getAdresse());
			ps.setString(4,nouvClient.getTelephone());
			ps.setString(5,nouvClient.getEmail());

			//Exécution de la requête
			retour=ps.executeUpdate();


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;

	}

	/**
	 * Permet de récupérer un client à partir de son identifiant
	 * @param identifiant l'identifiant du client à récupérer
	 * @return le client ou null si aucun client ne correspond à cet identifiant
	 */
	public Client getClient(int identifiant) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Client retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM client WHERE Identifiant = ?");
			ps.setInt(1,identifiant);

			//on exécute la requête
			//rs contient un pointeur situé juste avant la premiére ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée
			if(rs.next())
				retour=new Client(rs.getInt("Identifiant"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Adresse"),
						rs.getString("Telephone"),
						rs.getString("Email"));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;
	}

	/**
	 * Permet de récupérer des clients à partir de leur nom passé en paramètre
	 * @param nom nom du ou des clients à récupérer
	 * @return le ou les clients dont le nom correspond au paramètre
	 */
	public List<Client> rechercherClients(String nom) {
		System.out.println("Recherche d'article contenant '" + nom + "'");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Client> retour=new ArrayList<Client>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM client WHERE Nom LIKE ?");
			ps.setString(1,"%" + nom + "%");

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée
			while(rs.next())
				retour.add(new Client(rs.getInt("Identifiant"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Adresse"),
						rs.getString("Telephone"),
						rs.getString("Email")));

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;

	}

	/**
	 * Permet de modifier le client  passé en paramètre
	 * @param client client à modifier
	 * @return nombre de lignes modifées
	 */
	public int modifier(Client client) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("UPDATE client SET Nom = ?, Prenom = ?, Adresse = ?, Telephone = ?, Email = ? WHERE Identifiant = ?");
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getTelephone());
			ps.setString(5, client.getEmail());
			ps.setInt(6, client.getIdentifiant());

			//Exécution de la requ�te
			retour=ps.executeUpdate();


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;
	}

	/**
	 * Permet de supprimer le client passé en paramètre de la base de données
	 * @param client client à supprimer
	 * @return nombre de lignes supprimées
	 */
	public int supprimer(Client client) {
	    supprimerCommandesClient(client);
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM client WHERE Identifiant = ?");
			ps.setInt(1,client.getIdentifiant());

			//Exécution de la requête
			retour=ps.executeUpdate();


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;
	}

	/**
	 * Supprimer les commandes d'un client avant de supprimer le client lui même (contraintes)
	 * @param client dont les commandes sont à supprimer
	 * @return nombre de lignes supprimées
	 */
	private int supprimerCommandesClient(Client client) {
        CommandeDAO commandeDAO = new CommandeDAO();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        int retour=0;

        //connexion à la base de données
        try {
            //tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            //préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
            //les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
            ps = con.prepareStatement("SELECT Identifiant FROM commande WHERE Client = ?");
            ps.setInt(1,client.getIdentifiant());

            rs = ps.executeQuery();

            while(rs.next()) {
                retour++;
                commandeDAO.supprimer(new Commande(rs.getInt("Identifiant")));
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du preparedStatement et de la connexion
            try {if (ps != null)ps.close();} catch (Exception ignored) {}
            try {if (con != null)con.close();} catch (Exception ignored) {}
        }
        return retour;
    }

	/**
	 * Permet de récupérer tous les clients stockés dans la table client
	 * @return une ArrayList de clients
	 */
	public List<Client> getListeClients() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Client> retour=new ArrayList<Client>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM client");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				retour.add(new Client(rs.getInt("Identifiant"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Adresse"),
						rs.getString("Telephone"),
						rs.getString("Email")));

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du rs, du preparedStatement et de la connexion
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return retour;
	}
}
