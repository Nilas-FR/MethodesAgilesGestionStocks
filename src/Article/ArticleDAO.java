package article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table article
 * @version 1.1
 * */
public class ArticleDAO {

	/**
	 * Paramètres de connexion à la base de données MySQL
	 * URL, LOGIN et PASS sont des constantes
	 */
	private final static String URL = "jdbc:mysql://localhost/stocks";
	private final static String LOGIN="root";
	private final static String PASS="";

	/**
	 * Constructeur de la classe
	 * 
	 */
	public ArticleDAO()
	{
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
	 * @param nouvArticle l'article à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int ajouter(Article nouvArticle) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("INSERT INTO article (Designation, PrixUnitaireHT, StockReel, StockVirt) VALUES (?, ?, ?, ?)");
			ps.setString(1,nouvArticle.getDesignation());
			ps.setDouble(2,nouvArticle.getPuHt());
			ps.setInt(3,nouvArticle.getQteStock());
			ps.setInt(4,nouvArticle.getQteStock());

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
	 * Permet de récupérer un article à partir de sa référence
	 * @param reference la référence de l'article à récupérer
	 * @return l'article ou null si aucun article ne correspond à cette référence
	 */
	public Article getArticle(int reference) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Article retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM article WHERE Reference = ?");
			ps.setInt(1,reference);

			//on exécute la requête
			//rs contient un pointeur situé juste avant la premiére ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée
			if(rs.next())
				retour=new Article(rs.getInt("Reference"),rs.getString("Designation"),rs.getDouble("PrixUnitaireHT"),rs.getInt("Stock"));


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
	 * Permet de récupérer des article à partir de la désignation passée en paramètre
	 * @param designation désignation du ou des articles à récupérer
	 * @return le ou les articles dont la désignation correspond au paramètre
	 */
	public List<Article> rechercherArticles(String designation) {
		System.out.println("Recherche d'article contenant '" + designation + "'");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Article> retour=new ArrayList<Article>();

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM article WHERE Designation LIKE ?");
			ps.setString(1,"%" + designation + "%");

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée
			while(rs.next())
				retour.add(new Article(rs.getInt("Reference"),rs.getString("Designation"),rs.getDouble("PrixUnitaireHT"),rs.getInt("Stock")));


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
	 * Permet de modifier l'article  passé en paramètre
	 * @param article article à modifier
	 * @return nombre de lignes modifées
	 */
	public int modifier(Article article) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("UPDATE article SET Designation = ?, PrixUnitaireHT = ?, StockReel = ?, StockVirt = ? WHERE Reference = ?");
			ps.setString(1,article.getDesignation());
			ps.setDouble(2,article.getPuHt());
			ps.setInt(3,article.getQteStock());
			ps.setInt(4,article.getQteStock());
			ps.setInt(5,article.getReference());

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
	 * Permet de supprimer l'article passé en paramètre de la base de données
	 * @param article article à supprimer
	 * @return nombre de lignes supprimées
	 */
	public int supprimer(Article article) {
		int retour = supprimerFourniArticle(article);
		retour += supprimerIncluArticle(article);
		Connection con = null;
		PreparedStatement ps = null;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM article WHERE Reference = ?");
			ps.setInt(1,article.getReference());

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
	 * Permet de supprimer les références des articles fournis par les fournisseurs (table fourni_article)
	 * @param article article dont les références sont à supprimer
	 * @return nombre de lignes supprimées
	 */
	private int supprimerFourniArticle(Article article) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM fourni_article WHERE Reference = ?");
			ps.setInt(1,article.getReference());

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
	 * Permet de supprimer les références des articles inclus dans des commandes (table inclu_article)
	 * @param article article dont les références sont à supprimer
	 * @return nombre de lignes supprimées
	 */
	private int supprimerIncluArticle(Article article) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
			ps = con.prepareStatement("DELETE FROM inclu_article WHERE Article = ?");
			ps.setInt(1,article.getReference());

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
	 * Permet de récupérer tous les articles stockés dans la table article
	 * @return une ArrayList d'Articles
	 */
	public List<Article> getListeArticles() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Article> retour=new ArrayList<Article>();

		//connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM article");

			//on exécute la requête
			rs=ps.executeQuery();
			//on parcourt les lignes du résultat
			while(rs.next())
				retour.add(new Article(rs.getInt("Reference"),rs.getString("Designation"),rs.getDouble("PrixUnitaireHT"),rs.getInt("Stock")));

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
