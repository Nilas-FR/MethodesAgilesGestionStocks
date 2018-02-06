package client;

import java.awt.event.ActionEvent;
import java.util.List;

import message.Message;
import principale.Controller;
import principale.PrincipaleController;
import variables.Variables;

import javax.swing.*;

public class ClientController extends Controller {

	public ClientController(PrincipaleController PC) {
		super(PC);
		Vue = new ClientVue(this);
		Model = new ClientModel();
		Vue.afficherListe(Model.recupererListe(), this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (Variables.Droit < 1) {
			Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification, veuillez vous connecter.");
			return;
		}
		Object source = e.getSource();

		// ouvre la fenêtre d'ajout de nouveau client
		if (source == Vue.boutonAjouter) {
			fenetreCreationModification = new ClientCreerOuModifier(null, this);
			PC.JF.setContentPane(fenetreCreationModification);
			PC.JF.refresh();
			return;
		}

		// recherche de client selon son nom
		if (source == Vue.boutonRecherche) {
			Vue.afficherListe(Model.chercher(Vue.getStringRecherche()), this);
			PC.JF.refresh();
			return;
		}

		// ouvre la fenêtre de modification de client (parcours boutons modification)
        List<JButton> boutonsModif = Vue.getListBoutonsModification();
        for (int i = 0; i < boutonsModif.size(); i++) {
            if(source == boutonsModif.get(i)) {
				fenetreCreationModification = new ClientCreerOuModifier(((ClientModel)Model).recupererListe().get(i), this);
				PC.JF.setContentPane(fenetreCreationModification);
				PC.JF.refresh();
                return;
            }
        }

        // vérifie si l'event est sur un bouton supprimer
        if(verifierEventBoutonsSupprimer(source)) return;

		// vérifie si l'event est dans la fenêtre d'ajout/modification
        verifierEventFenetreAjoutModification(source);
	}

}
