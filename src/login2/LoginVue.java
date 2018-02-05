package login2;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import principale2.PrincipaleVue;
import variables.Variables;

public class LoginVue extends JPanel {
	
	private final JTextField TFLogin 	= new JTextField();
	private final JPasswordField TFMdp = new JPasswordField();
	public final JButton BValider = new JButton("Se connecter");
	public final JButton BSpecta = new JButton("Spectateur");
	
	/** On construit le panel */
	public LoginVue() {
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
	
	/**On ajoute le panel à la fenêtre principale, on change son titre et sa taille
	 * @param JF est la fenêtre principale
	 */
	public void setActive(PrincipaleVue JF) {
		JF.setTitle("Login");
		JF.setSize(Variables.EcranLargeurLogin,Variables.EcranHauteurLogin);
		JF.add(this);
	}
	
	/**On supprime le panel de la fenêtre
	 * @param JF est la fenêtre principale
	 */
	public void setUnactive(PrincipaleVue JF) {
		JF.remove(this);
	}
}
