package fournisseur;

import java.sql.*;
import java.util.Vector;

import message.Message;
import variables.*;

public class FournisseurDAO {
	
	/**
	 * Constructeur
	 */
	public FournisseurDAO() {
		// chargement du pilote de bases de donn�es
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Message.MessageErreur("Erreur : Pilote Base de Donn�es", e.getMessage());
		}
	}
	
	/**
	 * R�cup�re la liste des fournisseurs dans la base de donn�es
	 * @return un vector des fournisseurs
	 */
	public Vector<Fournisseur> getListeFournisseurs() {
		Vector<Fournisseur> fournisseurs = new Vector<Fournisseur>(10,10);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		//connexion � la base de donn�es
		try {
			con = DriverManager.getConnection(Variables.URL, Variables.LOGIN, Variables.PASS);
			ps = con.prepareStatement("SELECT * FROM fournisseur");

			//on ex�cute la requ�te et on r�cup�re dans rs un pointeur situ� avant la premi�re ligne de r�sultat
			rs = ps.executeQuery();
			while (rs.next()) {
				fournisseurs.addElement(new Fournisseur(rs.getString("Siret"),rs.getString("Nom"),rs.getString("Adresse")));
			}
		} catch (Exception e) {
			Message.MessageErreur("Erreur : Connection Base de Donn�es", e.getMessage());
			return null;
		} finally {
			//fermeture du ResultSet, PreparedStatement et Connection
			try {if (rs  != null) rs.close();} catch (Exception t) {}
			try {if (ps  != null) ps.close();} catch (Exception t) {}
			try {if (con != null) con.close();}catch (Exception t) {}
		}
		return fournisseurs;
	}
	
	/**
	 * Supprime un fournisseur de la base de donn�es
	 * @param Siret le siret du fournisseur � supprimer
	 */
	public void supprimerFournisseur(String Siret) {
		Connection con = null;
		PreparedStatement ps = null;
		//connexion � la base de donn�es
		
		try {
			con = DriverManager.getConnection(Variables.URL, Variables.LOGIN, Variables.PASS);
			ps = con.prepareStatement("DELETE FROM fournisseur WHERE  Siret = ?");
			ps.setString(1,Siret);
			//on ex�cute la requ�te
			ps.executeUpdate();
		} catch (Exception e) {
			Message.MessageErreur("Erreur : Connection Base de Donn�es", e.getMessage());
		} finally {
			//fermeture du PreparedStatement et Connection
			try {if (ps  != null) ps.close();} catch (Exception t) {}
			try {if (con != null) con.close();}catch (Exception t) {}
		}
	}
	
	/**
	 * Ajoute un fournisseur � la base de donn�es
	 * @param F le fournisseur � ajouter
	 */
	public void ajouterFournisseur(Fournisseur F) {
		Connection con = null;
		PreparedStatement ps = null;
		//connexion � la base de donn�es
		
		try {
			con = DriverManager.getConnection(Variables.URL, Variables.LOGIN, Variables.PASS);
			ps = con.prepareStatement("INSERT INTO fournisseur (Siret, Nom, Adresse) VALUES (?, ?, ?)");
			ps.setString(1,F.Siret);
			ps.setString(2,F.Nom);
			ps.setString(3,F.Adresse);
			//on ex�cute la requ�te
			ps.executeUpdate();
		} catch (Exception e) {
			Message.MessageErreur("Erreur : Connection Base de Donn�es", e.getMessage());
		} finally {
			//fermeture du PreparedStatement et Connection
			try {if (ps  != null) ps.close();} catch (Exception t) {}
			try {if (con != null) con.close();}catch (Exception t) {}
		}
	}

}
