package login2;

import Controller.Control;
import Vue.Vue;
import message.Message;
import variables.Variables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import principale2.Controller;
import principale2.PrincipaleController;
import principale2.PrincipaleVue;

public class LoginController extends Controller implements ActionListener {

	/**Constructeur du controller de Login
	 * @param PC PC est le controller principale
	 */
	public LoginController(PrincipaleController PC) {
		super(PC);
		this.Vue = new LoginVue();
		Vue.ajouterListener(this);
	}

	/** L'action des boutons est g�r� ici */
    public void actionPerformed(ActionEvent e) {
    	LoginVue vue = (LoginVue) Vue;
    	//On initialise le syst�me de gestion de donn�es
    	LoginModel model = new LoginModel();
    	//On r�cup�re l'objet cliqu� pour le comparer
		Object source = e.getSource();
		//Si le bouton est "Valider"
		if (source == vue.BValider) {
			//On r�cup�re les informations
			String login = vue.getLogin(), pswd = vue.getPassword();
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
		} else if (source == vue.BSpecta) {
			//On attribue le niveau de droit ad�quat
			Variables.Droit = 0;
			//On le passe � la vue suivante
			Variables.VueActive = 2;
			//On actualise le controller principale
			PC.refreshActive();
		}
    }
}