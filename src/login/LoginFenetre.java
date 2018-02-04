package login;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginFenetre extends JPanel {
	
	private final JTextField TFLogin 	= new JTextField();
	private final JPasswordField TFMdp = new JPasswordField();
	public final JButton BValider = new JButton("Se connecter");
	public final JButton BSpecta = new JButton("Spectateur");
	
	public LoginFenetre(JFrame JF) {
		//On divise l'écran en 3 parties
		this.setLayout(new GridLayout(3, 2));
		//Une partie Login
		this.add(new JLabel("Login : "));
		this.add(TFLogin);
		//Une partie Mot de Passe
		add(new JLabel("Mot de passe : "));
		add(TFMdp);
		//On ajoute les 2 boutons dans la 3ème partie
		this.add(BValider);
		this.add(BSpecta);

		//On définit le titre de la fenêtre
		JF.setTitle("Login");
	}
	
	/**Retourne le login entré par l'utilisateur
	 * @return le login */
	public String getLogin() {
		return TFLogin.getText();
	}
	
	/**Retourne le mot de passe entré par l'utilisateur
	 * @return le mot de passe */
	public String getPassword() {
		return String.valueOf(TFMdp.getPassword());
	}

	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
		BValider.addActionListener(listener);
		BSpecta.addActionListener(listener);
	}
}
