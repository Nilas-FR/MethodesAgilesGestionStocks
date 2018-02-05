package login2;

import Controller.Control;
import Vue.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUtilisateur extends Control implements ActionListener {

	public LoginUtilisateur(LoginModel modelUtilisateur, Vue vue) {
		super(modelUtilisateur, vue);
		vue.setLoginController(this);
	}

    public void actionPerformed(ActionEvent e) {
    	LoginModel modelUtilisateur = (LoginModel)model;
		Object source = e.getSource();

		if (source == vue.getVueLogin().BValider) {
			String login = vue.getVueLogin().getLogin(), pswd = vue.getVueLogin().getPassword();
			if (login.length() > 0 && pswd.length() > 0) {
				System.out.println("Connection : "+login+" - "+pswd);
				if (modelUtilisateur.VerificationConnection(login, pswd)) {
					System.out.println("Succès"); //TODO
					vue.connexionValidee();
				}
				else
					System.out.println("Erreur");
			}
			else System.out.println("Veuillez remplir les 2 champs");
			return;
		}
		if (source == vue.getVueLogin().BSpecta) {
			System.out.println("Spectateur");
			//TODO
			return;
		}

    }
}