package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import principale.Controller;
import principale.PrincipaleController;

public class ClientController extends Controller implements ActionListener {
	
	private ClientCreerOuModifier fenetreCreerOuModifierClient;
	ClientDAO modele = new ClientDAO();

	public ClientController(PrincipaleController PC) {
		super(PC);
		this.Vue = new ClientVue();
		Vue.ajouterListener(this);
		((ClientVue) Vue).afficherListeClients(modele.getListeClients(), this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		ClientVue vue = (ClientVue) Vue;
		
		if (source == vue.boutonAjouter) {
			fenetreCreerOuModifierClient = new ClientCreerOuModifier(PC.JF, null);
			fenetreCreerOuModifierClient.ajouterListener(this);
		} else ;
	}

}
