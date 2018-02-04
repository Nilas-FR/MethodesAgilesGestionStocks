package login;

import java.sql.*;

import message.Message;
import variables.*;

public class LoginDAO {
	
	public LoginDAO() {
		// chargement du pilote de bases de donn�es
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Message.MessageErreur("Erreur : Pilote Base de Donn�es", e.getMessage());
		}
	}
	
	public boolean VerificationConnection(String login, String pswd)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		boolean retour = false;

		//connexion � la base de donn�es
		try {
			con = DriverManager.getConnection(Variables.URL, Variables.LOGIN, Variables.PASS);
			ps = con.prepareStatement(
					"SELECT * FROM utilisateur" +
					"WHERE Login = ? AND Mdp = ?");
			ps.setString(1,login);
			ps.setString(2,pswd);

			//on ex�cute la requ�te et on r�cup�re dans rs un pointeur situ� avant la premi�re ligne de r�sultat
			rs = ps.executeQuery();
			//Si le nombre d'enregistrement est sup�rieur � 0, alors il y a bien un compte avec ces identifiants
			if(rs.last()) retour = 0 < rs.getRow();
		} catch (Exception e) {
			Message.MessageErreur("Erreur : Connection Base de Donn�es", e.getMessage());
			return false;
		} finally {
			//fermeture du ResultSet, PreparedStatement et Connection
			try {if (rs  != null) rs.close();} catch (Exception t) {}
			try {if (ps  != null) ps.close();} catch (Exception t) {}
			try {if (con != null) con.close();}catch (Exception t) {}
		}
		return retour;

	}

}
