package login2;

import Controller.Control;
import Vue.Vue;
import message.Message;
import variables.Variables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import principale2.PrincipaleController;
import principale2.PrincipaleVue;

public class LoginController implements ActionListener {
	
	private final PrincipaleController PC;
	public final LoginVue Vue = new LoginVue();

	public LoginController(PrincipaleController PC) {
		this.PC = PC;
		Vue.ajouterListener(this);
	}
	
	public void setActive() {
		Vue.setActive(PC.JF);
	}
	
	public void setUnactive() {
		Vue.setUnactive(PC.JF);
	}

    public void actionPerformed(ActionEvent e) {
    	Variables.VueActive = 1;
    	Variables.Droit = 1; //0 Spectateur
    	PC.refreshActive();
    	/*LoginModel modelUtilisateur = (LoginModel)model;
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
		}*/

    }
}