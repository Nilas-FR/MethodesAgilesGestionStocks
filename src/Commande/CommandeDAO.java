package commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.Client;
import article.Article;

/**
 * Classe d'acc�s aux donn�es contenues dans la table article
 * @version 1.1
 * */
public class CommandeDAO {

	/**
	 * Param�tres de connexion � la base de donn�es MySQL
	 * URL, LOGIN et PASS sont des constantes
	 */
	private final static String URL = "jdbc:mysql://localhost/stocks";
	private final static String LOGIN="root";
	private final static String PASS="";

	/**
	 * Constructeur de la classe
	 */
	public CommandeDAO() {
		// chargement du pilote de bases de donn�es
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}

	/**
	 * Permet d'ajouter une commande dans la table commande
	 * l'identifiant de la commande est produit automatiquement par la base de donn�es en utilisant une s�quence
	 * Le mode est auto-commit par d�faut : chaque insertion est valid�e
	 * @param nouvCommande l'article � ajouter
	 * @param changerDate si true, enregistrer la date courante, sinon la date enregistr�e dans la commande
	 * @return le nombre de ligne ajout�es dans la table
	 */
	public int ajouter(Commande nouvCommande, boolean changerDate) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvCommande
			ps = con.prepareStatement("INSERT INTO commande (Client, DateCommande) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, nouvCommande.getClient().getIdentifiant());
			java.sql.Timestamp date;
			if (changerDate)
				date = new java.sql.Timestamp(new java.util.Date().getTime());
			else
				date = nouvCommande.getDate();

			ps.setTimestamp(2, date);

			//Ex�cution de la requ�te
			retour=ps.executeUpdate();

			// r�cup�re l'identifiant de la commande ins�r�e
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				nouvCommande.setIdentifiant(generatedKeys.getInt(1));
			}

			// ajoute les articles
			retour += ajouterArticles(nouvCommande);




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
	 * Permet de r�cup�rer une commande � partir de son identifiant pass� en param�tre
	 * @param identifiant d�signation du ou des articles � r�cup�rer
	 * @return la commande dont l'identifiant correspond
	 */
	public Commande rechercherCommande(int identifiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Commande retour = null;

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//ps = con.prepareStatement("SELECT * FROM commande WHERE Identifiant = ?");
			ps = con.prepareStatement("SELECT c.Identifiant idComm, c.DateCommande, cl.* FROM commande c INNER JOIN client cl ON c.Client = cl.Identifiant WHERE idComm = ?");

			ps.setInt(1,identifiant);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e

			while(rs.next()) {
				Client client = new Client(rs.getInt("Identifiant"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Adresse"),
						rs.getString("Telephone"),
						rs.getString("Email"));
				retour = getArticlesCommande(new Commande(rs.getInt("idComm"), client, rs.getTimestamp("DateCommande")));
			}

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
	 * Modifie une commande en la supprimant puis la r�ins�rant dans la base avec les bons param�tres
	 * @param commande commande � modifier
	 * @param changerDate indique si il faut mettre � jour la date ou pas
	 * @return nombre de lignes modifi�es
	 */
	public int modifier(Commande commande, boolean changerDate) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			/*
			 * Supprime les articles de la commande
			 */
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("DELETE FROM inclu_article WHERE Commande = ?");
			ps.setInt(1, commande.getIdentifiant());

			//Ex�cution de la requ�te
			retour = ps.executeUpdate();

			/*
			 * Update la commande
			 */
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			if (changerDate) {
				ps = con.prepareStatement("UPDATE commande SET Client = ?, DateCommande = ? WHERE Identifiant = ?");
				ps.setInt(1, commande.getClient().getIdentifiant());
				ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(3, commande.getIdentifiant());

			} else {
				ps = con.prepareStatement("UPDATE commande SET Client = ? WHERE Identifiant = ?");
				ps.setInt(1, commande.getClient().getIdentifiant());
				ps.setInt(2, commande.getIdentifiant());
			}

			//Ex�cution de la requ�te
			retour += ps.executeUpdate();

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du preparedStatement et de la connexion
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}

		// rajoute les articles
		return retour + ajouterArticles(commande);
	}

	/**
	 * Supprime la commande pass� en param�tre de la base de donn�es avec les articles associ�s
	 * @param commande commande � supprimer
	 * @return nombre de lignes modifi�es
	 */
	public int supprimer(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			/*
			 * Supprime les articles de la commande
			 */
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("DELETE FROM inclu_article WHERE Commande = ?");
			ps.setInt(1, commande.getIdentifiant());

			//Ex�cution de la requ�te
			retour = ps.executeUpdate();

			/*
			 * Supprime la commande
			 */
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("DELETE FROM commande WHERE Identifiant = ?");
			ps.setInt(1, commande.getIdentifiant());

			//Ex�cution de la requ�te
			retour += ps.executeUpdate();

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
	 * Permet de r�cup�rer toutes les commandes stock�es dans la table commande
	 * @return un ArrayList de commandes
	 */
	public List<Commande> getListeCommandes() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Commande> retour=new ArrayList<Commande>();

		//connexion � la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT c.Identifiant idComm, c.DateCommande, cl.* FROM commande c INNER JOIN client cl ON c.Client = cl.Identifiant");

			//on ex�cute la requ�te
			rs=ps.executeQuery();
			//on parcourt les lignes du r�sultat
			while(rs.next()) {
				Client client = new Client(rs.getInt("Identifiant"),
						rs.getString("Nom"),
						rs.getString("Prenom"),
						rs.getString("Adresse"),
						rs.getString("Telephone"),
						rs.getString("Email"));
				retour.add(getArticlesCommande(new Commande(rs.getInt("idComm"), client, rs.getTimestamp("DateCommande"))));
			}

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

	private int reduireStockArticles(Article article, int quantite) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("UPDATE article SET Stock = Stock - ? WHERE Reference = ?");
			ps.setInt(1,quantite);
			ps.setInt(2,article.getReference());

			//Ex�cution de la requ�te
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
     * Permet de r�cup�rer tous les clients afin de les afficher pour cr�er/modifier une commande
     * @return tableau de tous les clients
     */
    public Client[] getListeClients() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        List<Client> clients = new ArrayList<Client>();

        //connexion � la base de donn�es
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM client");

            //on ex�cute la requ�te
            rs=ps.executeQuery();
            //on parcourt les lignes du r�sultat
            while(rs.next()) {
                clients.add(new Client(rs.getInt("Identifiant"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Adresse"),
                        rs.getString("Telephone"),
                        rs.getString("Email")));
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du rs, du preparedStatement et de la connexion
            try {if (rs != null)rs.close();} catch (Exception ignored) {}
            try {if (ps != null)ps.close();} catch (Exception ignored) {}
            try {if (con != null)con.close();} catch (Exception ignored) {}
        }
        return clients.toArray(new Client[clients.size()]);
    }

    /**
     * Permet de r�cup�rer tous les articles afin de les afficher pour ajouter un article dans une commande
     * @return tableau de tous les articles
     */
    public Article[] getListeArticles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        List<Article> articles = new ArrayList<Article>();

        //connexion � la base de donn�es
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM article");

            //on ex�cute la requ�te
            rs=ps.executeQuery();
            //on parcourt les lignes du r�sultat
            while(rs.next()) {
                articles.add(new Article(rs.getInt("Reference"),
                        rs.getString("Designation"),
                        rs.getDouble("PrixUnitaireHT"),
                        rs.getInt("Stock")));
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du rs, du preparedStatement et de la connexion
            try {if (rs != null)rs.close();} catch (Exception ignored) {}
            try {if (ps != null)ps.close();} catch (Exception ignored) {}
            try {if (con != null)con.close();} catch (Exception ignored) {}
        }
        return articles.toArray(new Article[articles.size()]);
    }

	/**
	 * Permet de r�cup�rer les articles d'une commande
	 * @param commande la commande dont les articles sont � r�cup�rer
	 * @return la commande avec les articles ajout�s
	 */
	public Commande getArticlesCommande(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//connexion � la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT a.*, ia.Quantite FROM article a INNER JOIN inclu_article ia ON a.Reference = ia.Article WHERE ia.Commande = ?");
			ps.setInt(1, commande.getIdentifiant());

			//on ex�cute la requ�te
			rs=ps.executeQuery();
			//on parcourt les lignes du r�sultat
			while(rs.next()) {
				Article article = new Article(rs.getInt("Reference"),
						rs.getString("Designation"),
						rs.getDouble("PrixUnitaireHT"),
						rs.getInt("Stock"));

				commande.ajouterArticle(article, rs.getInt("Quantite"));
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return commande;
	}

	/**
	 * Ajoute tous les articles de la commande pass�e en param�tre dans la table inclu_article
	 * @param commande commande dont les articles doivent �tre ajout�s � la base
	 * @return nombre de lignes ins�r�es
	 */
	private int ajouterArticles(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			// ajoute tous les articles de la commande dans la base
			for(Map.Entry<Article, Integer> article : commande.getArticles().entrySet()) {
				//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
				//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvCommande
				ps = con.prepareStatement("INSERT INTO inclu_article (Commande, Article, Quantite) VALUES (?, ?, ?)");
				ps.setInt(1, commande.getIdentifiant());
				ps.setInt(2, article.getKey().getReference());
				ps.setInt(3, article.getValue());

				//Ex�cution de la requ�te
				retour += ps.executeUpdate() + reduireStockArticles(article.getKey(), article.getValue());
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
}
