package login;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import principale.Vue;

public class LoginVue extends Vue {
	
	public final JTextField TFLogin   = new JTextField();
	public final JPasswordField TFMdp = new JPasswordField();
	public final JButton BValider = new JButton("Se connecter");
	public final JButton BSpecta = new JButton("Spectateur");
	
	/** On construit le panel */
	public LoginVue() {
		//On divise l'écran en 3 parties
		this.setLayout(new GridLayout(3, 2));
		//Partie 1 - Login
		this.add(new JLabel("Login : "));
		this.add(TFLogin);
		//Partie 2 - Mot de Passe
		add(new JLabel("Mot de passe : "));
		add(TFMdp);
		//Partie 3 - Boutons
		this.add(BValider);
		this.add(BSpecta);
	}

	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
		BValider.addActionListener(listener);
		BSpecta.addActionListener(listener);
	}

	@Override
	public void afficherListe(List liste, ActionListener listener) {
		// TODO Auto-generated method stub
		
	}
}
