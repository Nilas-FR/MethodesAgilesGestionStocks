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
    	LoginModel model = new LoginModel();
		Object source = e.getSource();
		if (source == Vue.BValider) {
			String login = Vue.getLogin(), pswd = Vue.getPassword();
			if (login.length() > 0 && pswd.length() > 0) {
				if (model.VerificationConnection(login, pswd)) {
					Variables.Droit = 1;
					Variables.VueActive = 1;
					PC.refreshActive();
				} else Message.MessageAlerte("Attention", "Les informations que vous avez entré sont érronées");
			} else Message.MessageAlerte("Attention", "Veuillez remplir les 2 champs");
		} else if (source == Vue.BSpecta) {
			Variables.Droit = 0;
			Variables.VueActive = 1;
			PC.refreshActive();
		}
    }
}