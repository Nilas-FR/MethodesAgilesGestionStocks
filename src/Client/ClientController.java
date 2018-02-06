package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import principale.Controller;
import principale.PrincipaleController;

import javax.swing.*;

public class ClientController extends Controller implements ActionListener {

	private ClientCreerOuModifier fenetreCreerOuModifierClient = null;

	public ClientController(PrincipaleController PC) {
		super(PC);
		Vue = new ClientVue(this);
		Model = new ClientModel();
		Vue.afficherListe(Model.recupererListe(), this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		ClientVue vue = (ClientVue) Vue;
		ClientModel model = (ClientModel) Model;

		// ouvre la fenêtre d'ajout de nouveau Client
		if (source == vue.boutonAjouter) {
			fenetreCreerOuModifierClient = new ClientCreerOuModifier(null, this);
			// TODO PC.JF.setContentPane(fenetreCreerOuModifierClient);
			PC.JF.setContentPane(fenetreCreerOuModifierClient);
			PC.JF.refresh();
			return;
		}

		// ouvre la fenêtre de modification de Client (parcours boutons modification)
        List<JButton> boutonsModifClient = vue.getListBoutonsModificationClients();
        for (int i = 0; i < boutonsModifClient.size(); i++) {
            if(source == boutonsModifClient.get(i)) {
				fenetreCreerOuModifierClient = new ClientCreerOuModifier(model.recupererListe().get(i), this);
				// TODO PC.JF.setContentPane(fenetreCreerOuModifierClient);
                return;
            }
        }

        /*
         * Vérifie l'écouteur des boutons de suppression de la liste des articles
         */
        List<JButton> boutonsSupprClient = vue.getListBoutonsSuppressionClients();
        for (int i = 0; i < boutonsSupprClient.size(); i++) {
            if(source == boutonsSupprClient.get(i)) {
                model.supprimer(model.recupererListe().get(i));
				Vue.afficherListe(Model.recupererListe(), this);
                return;
            }
        }


		// l'évènement a été délenché sur la page de modification/création de Client
		if (fenetreCreerOuModifierClient != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreerOuModifierClient.boutonAjouter) {
				model.ajouter(fenetreCreerOuModifierClient.validerCreation());
			}
			// valider la modification d'un client
			if (source == fenetreCreerOuModifierClient.boutonValiderModification) {
				model.modifier(fenetreCreerOuModifierClient.validerModification());
			}
			// Annuler la modification/création d'un client
			// Aucune action n'est requise pour l'annulation

			// ferme la fenêtre de modification/ajout
			// TODO réafficher la vue ClientVue
		}
	}

}
