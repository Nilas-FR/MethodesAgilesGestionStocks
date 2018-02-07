package accueil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static variables.Variables.*;

public class AccueilDAO {

	/**
	 * Constructeur
	 */
	public AccueilDAO() {
		// chargement du pilote de bases de donn�es
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}

	/**
	 * R�cup�re le nombre de client dans la base de donn�es
	 * @return le nombre de client, -1 en cas d'erreur
	 */
	public int getNombreClient() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(Identifiant) as nb FROM client");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nombre de fournisseur dans la base de donn�es
	 * @return le nombre de fournisseur, -1 en cas d'erreur
	 */
	public int getNombreFournisseur() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(siret) as nb FROM fournisseur");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nombre de article dans la base de donn�es
	 * @return le nombre de article, -1 en cas d'erreur
	 */
	public int getNombreArticle() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(Reference) as nb FROM article");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nombre de article en stock dans la base de donn�es
	 * @return le nombre de article, -1 en cas d'erreur
	 */
	public int getNombreArticleStock() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(Stock) as nb FROM article");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re la somme des achats dans la base de donn�es
	 * @return la somme des achats, -1 en cas d'erreur
	 */
	public int getValeurArticlesVendus() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(ia.Quantite * a.PrixUnitaireHT) as nb FROM inclu_article ia " +
					"INNER JOIN article a ON ia.Article = a.Reference");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nombre de article dans la base de donn�es
	 * @return le nombre de article, -1 en cas d'erreur
	 */
	public int getValeurStock() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(Stock * PrixUnitaireHT) as nb FROM article");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nombre de commande dans la base de donn�es
	 * @return le nombre de commande, -1 en cas d'erreur
	 */
	public int getNombreCommande() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(Identifiant) as nb FROM commande");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re la somme des achats dans la base de donn�es
	 * @return la somme des achats, -1 en cas d'erreur
	 */
	public int getNombreArticlesVendus() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(Quantite) as nb FROM inclu_article");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs = ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if (rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {
				if (rs != null) rs.close();
			} catch (Exception ignored) {
			}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignored) {
			}
			try {
				if (con != null) con.close();
			} catch (Exception ignored) {
			}
		}
		return nb;
	}

	/**
	 * R�cup�re le nom de l'article ayant �t� le plus vendu dans la base de donn�es
	 * @return le nom de l'article, null en cas d'erreur
	 */
	public String getMeilleurArticleNom() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String nom = null;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT a.Designation FROM article as a "
					+ " INNER JOIN inclu_article as ia on ia.Article = a.Reference "
					+ " GROUP BY ia.Article ORDER BY SUM(ia.Quantite) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				nom = rs.getString("Designation");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return nom;
	}

	/**
	 * R�cup�re le nom de l'article ayant rapport� le plus d'argent dans la base de donn�es
	 * @return le nom de l'article, null en cas d'erreur
	 */
	public String getMeilleurGainArticleNom() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String nom = null;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT a.Designation FROM article as a "
					+ " INNER JOIN inclu_article as ia on ia.Article = a.Reference "
					+ " GROUP BY ia.Article ORDER BY SUM(ia.Quantite*a.PrixUnitaireHT) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				nom = rs.getString("Designation");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return nom;
	}

	/**
	 * R�cup�re la quantit� de l'article ayant �t� le plus vendu dans la base de donn�es
	 * @return la quantit� de l'article, -1 en cas d'erreur
	 */
	public int getMeilleurArticleQuantite() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(Quantite) as nb FROM inclu_article GROUP BY ARTICLE ORDER BY SUM(Quantite) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return nb;
	}

	/**
	 * R�cup�re la somme d'argent de l'article ayant rapport� le plus d'argent dans la base de donn�es
	 * @return la somme d'argent de l'article, -1 en cas d'erreur
	 */
	public int getMeilleurArticleGainSomme() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT SUM(ia.Quantite*a.PrixUnitaireHT) as nb FROM article as a "
					+ " INNER JOIN inclu_article as ia on ia.Article = a.Reference "
					+ " GROUP BY ia.Article ORDER BY SUM(ia.Quantite*a.PrixUnitaireHT) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return nb;
	}


	/**
	 * R�cup�re le nom / pr�nom du meilleur client dans la base de donn�es
	 * @return le nom / pr�nom, -1 en cas d'erreur
	 */
	public String getMeilleurClientNomPrenom() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String client = null;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					" SELECT * FROM client as cl"
							+ " INNER JOIN commande as c on c.Client = cl.Identifiant"
							+ " INNER JOIN inclu_article as ia on c.Identifiant = ia.Commande"
							+ " INNER JOIN article as a on a.Reference = ia.Article"
							+ " GROUP BY c.Identifiant,cl.Identifiant ORDER BY SUM(a.PrixUnitaireHT*ia.Quantite) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				client = rs.getString("Nom") + " " + rs.getString("Prenom");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return client;
	}

	/**
	 * R�cup�re la somme des achats du meilleur client dans la base de donn�es
	 * @return la somme des achats, -1 en cas d'erreur
	 */
	public int getMeilleurClientSommeHT() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		int nb = -1;
		//connexion �  la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					" SELECT SUM(PrixUnitaireHT*ia.Quantite) as nb FROM article as a"
							+ " INNER JOIN inclu_article as ia on a.Reference = ia.Article"
							+ " INNER JOIN commande as c on c.identifiant = ia.Commande"
							+ " INNER JOIN client as cl on c.Client = cl.Identifiant"
							+ " GROUP BY c.Identifiant,cl.Identifiant ORDER BY SUM(a.PrixUnitaireHT*ia.Quantite) DESC");
			//on ex�cute la requ�te
			//rs contient un pointeur situ� juste avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe �  la premi�re (et unique) ligne retourn�e
			if(rs.next())
				nb = rs.getInt("nb");

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception ignored) {}
			try {if (ps != null)ps.close();} catch (Exception ignored) {}
			try {if (con != null)con.close();} catch (Exception ignored) {}
		}
		return nb;
	}
}