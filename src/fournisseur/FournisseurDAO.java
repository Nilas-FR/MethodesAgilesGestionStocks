package fournisseur;

import java.sql.*;
import java.util.Vector;

import Article.Article;
import message.Message;
import variables.*;

public class FournisseurDAO {
	
	public FournisseurDAO() {
		// chargement du pilote de bases de données
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Message.MessageErreur("Erreur : Pilote Base de Données", e.getMessage());
		}
	}
	
	public Vector<Fournisseur> getListeFournisseurs() {
		Vector<Fournisseur> fournisseurs = new Vector<Fournisseur>(10,10);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		//connexion à la base de données
		try {
			con = DriverManager.getConnection(Variables.URL, Variables.LOGIN, Variables.PASS);
			ps = con.prepareStatement("SELECT * FROM fournisseur");

			//on exécute la requète et on récupère dans rs un pointeur situé avant la première ligne de résultat
			rs = ps.executeQuery();
			if(rs.next()) {
				fournisseurs.addElement(new Fournisseur(rs.getString("Siret"),rs.getString("Nom"),rs.getString("Adresse")));
			}
		} catch (Exception e) {
			Message.MessageErreur("Erreur : Connection Base de Données", e.getMessage());
			return null;
		} finally {
			//fermeture du ResultSet, PreparedStatement et Connection
			try {if (rs  != null) rs.close();} catch (Exception t) {}
			try {if (ps  != null) ps.close();} catch (Exception t) {}
			try {if (con != null) con.close();}catch (Exception t) {}
		}
		return fournisseurs;
	}

}
