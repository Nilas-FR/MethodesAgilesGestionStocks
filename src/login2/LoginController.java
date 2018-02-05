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
	
	/** Un pointeur vers le controller principale */
	private final PrincipaleController PC;

	/** Un pointeur vers la vue du module */
	public final LoginVue Vue = new LoginVue();

	/**Constructeur du controller de Login
	 * @param PC PC est le controller principale
	 */
	public LoginController(PrincipaleController PC) {
		this.PC = PC;
		Vue.ajouterListener(this);
	}
	
	/** Ce module est maintenant actif */
	public void setActive() {
		Vue.setActive(PC.JF);
	}
	
	/** Ce module est maintenant inactif */
	public void setUnactive() {
		Vue.setUnactive(PC.JF);
	}

	/** L'action des boutons est g�r� ici */
    public void actionPerformed(ActionEvent e) {
    	//On initialise le syst�me de gestion de donn�es
    	LoginModel model = new LoginModel();
    	//On r�cup�re l'objet cliqu� pour le comparer
		Object source = e.getSource();
		//Si le bouton est "Valider"
		if (source == Vue.BValider) {
			//On r�cup�re les informations
			String login = Vue.getLogin(), pswd = Vue.getPassword();
			//On v�rifie s'il ont �t� renseign� sinon on avertie l'utilisateur
			if (login.length() > 0 && pswd.length() > 0) {
				//On v�rifie les informations dans la base de donn�es
				if (model.VerificationConnection(login, pswd)) {
					//On attribue le niveau de droit ad�quat
					Variables.Droit = 1;
					//On le passe � la vue suivante
					Variables.VueActive = 1;
					//On actualise le controller principale
					PC.refreshActive();
				} else Message.MessageAlerte("Attention", "Les informations que vous avez entr� sont �rron�es");
			} else Message.MessageAlerte("Attention", "Veuillez remplir les 2 champs");
			//Si le bouton est "Spectateur"
		} else if (source == Vue.BSpecta) {
			//On attribue le niveau de droit ad�quat
			Variables.Droit = 0;
			//On le passe � la vue suivante
			Variables.VueActive = 1;
			//On actualise le controller principale
			PC.refreshActive();
		}
    }
}