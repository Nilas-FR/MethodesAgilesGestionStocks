package login;

import Controller.Control;
import Vue.Vue;
import message.Message;
import variables.Variables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController extends Control implements ActionListener {

	public LoginController(LoginModel modelUtilisateur, Vue vue) {
		super(modelUtilisateur, vue);
		vue.setLoginController(this);
	}

    public void actionPerformed(ActionEvent e) {
    	LoginModel modelUtilisateur = (LoginModel)model;
		Object source = e.getSource();

		if (source == vue.getVueLogin().BValider) {
			String login = vue.getVueLogin().getLogin(), pswd = vue.getVueLogin().getPassword();
			if (login.length() > 0 && pswd.length() > 0) {
				if (modelUtilisateur.VerificationConnection(login, pswd)) {
					Variables.Droit = 1;
					vue.connexionValidee();
				}
				else Message.MessageAlerte("Attention", "Les informations que vous avez entré sont érronées");
			}
			else Message.MessageAlerte("Attention", "Veuillez remplir les 2 champs");
			return;
		}
		if (source == vue.getVueLogin().BSpecta) {
			Variables.Droit = 0;
			return;
		}

    }
}