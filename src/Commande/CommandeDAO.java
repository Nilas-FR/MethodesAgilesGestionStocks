package Commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Article.Article;

/**
 * Classe d'accès aux données contenues dans la table article
 * @version 1.1
 * */
public class CommandeDAO {

	/**
	 * Paramètres de connexion à la base de données MySQL
	 * URL, LOGIN et PASS sont des constantes
	 */
	private final static String URL = "jdbc:mysql://localhost/stocks";
	private final static String LOGIN="root";
	private final static String PASS="";

	/**
	 * Constructeur de la classe
	 */
	public CommandeDAO() {
		// chargement du pilote de bases de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}

	/**
	 * Permet d'ajouter un article dans la table article
	 * la référence de l'article est produite automatiquement par la base de données en utilisant une séquence
	 * Le mode est auto-commit par défaut : chaque insertion est validée
	 * @param nouvCommande l'article à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Commande nouvCommande) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvCommande
			ps = con.prepareStatement("INSERT INTO commande (Client, DateCommande) VALUES (?, ?)");
			ps.setInt(1, nouvCommande.getClient());
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			ps.setTimestamp(2, date);

			//Exécution de la requête
			retour=ps.executeUpdate();

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
	 * Permet de récupérer une commande à partir de son identifiant passé en paramètre
	 * @param identifiant désignation du ou des articles à récupérer
	 * @return la commande dont l'identifiant correspond
	 */
	public Commande rechercherCommande(int identifiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Commande retour = null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM commande WHERE Identifiant = ?");
			ps.setInt(1,identifiant);

			//on exécute la requête
			//rs contient un pointeur situé juste avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée
			while(rs.next())
				retour = getArticlesCommande(new Commande(rs.getInt("Identifiant"), rs.getInt("Client"), rs.getTimestamp("DateCommande")));

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

	public int modifier(Commande commande) {
		return supprimer(commande) + ajouter(commande);
	}

	public int supprimer(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			/*
			 * Supprime les articles de la commande
			 */
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM inclu_article WHERE Commande = ?");
			ps.setInt(1, commande.getIdentifiant());

			//Exécution de la requête
			retour = ps.executeUpdate();

			/*
			 * Supprime la commande
			 */
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM commande WHERE Identifiant = ?");
			ps.setInt(1, commande.getIdentifiant());

			//Exécution de la requête
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
	 * Permet de récupérer toutes les commandes stockées dans la table commande
	 * @return une ArrayList de commandes
	 */
	public List<Commande> getListeCommandes() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Commande> retour=new ArrayList<Commande>();

		//connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM commande");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next()) {
				retour.add(getArticlesCommande(new Commande(rs.getInt("Identifiant"), rs.getInt("Client"), rs.getTimestamp("DateCommande"))));
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

	/**
	 * Permet de récupérer les articles d'une commande
	 * @param commande la commande dont les articles sont à récupérer
	 * @return la commande avec les articles ajoutés
	 */
	public Commande getArticlesCommande(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT a.*, ia.Quantite FROM article a INNER JOIN inclu_article ia ON a.Reference = ia.Article WHERE ia.Commande = ?");
			ps.setInt(1, commande.getIdentifiant());

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next()) {
				Article article = new Article(rs.getInt("Reference"),
						rs.getString("Designation"),
						rs.getDouble("PrixUnitaireHT"),
						rs.getInt("StockReel"));

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
	 * Ajoute tous les articles de la commande passée en paramètre dans la table inclu_article
	 * @param commande commande dont les articles doivent être ajoutés à la base
	 * @return nombre de lignes insérées
	 */
	private int ajouterArticles(Commande commande) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			// ajoute tous les articles de la commande dans la base
			for(Map.Entry<Article, Integer> article : commande.getArticles().entrySet()) {
				//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
				//les getters permettent de récupérer les valeurs des attributs souhaités de nouvCommande
				ps = con.prepareStatement("INSERT INTO inclu_article (Commande, Article, Quantite) VALUES (?, ?, ?)");
				ps.setInt(1, commande.getIdentifiant());
				ps.setInt(2, article.getKey().getReference());
				ps.setInt(3, article.getValue());

				//Exécution de la requête
				retour += ps.executeUpdate();
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
