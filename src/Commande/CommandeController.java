package Commande;

import principale.Controller;
import principale.PrincipaleController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommandeController extends Controller implements ActionListener {

	private CommandeCreerOuModifier fenetreCreerOuModifierCommande = null;
	private AjouterArticle fenetreAjoutArticle = null;

    /**
     * Créé le controleur des articles
	 * @param PC controleur principal
     */
    public CommandeController(PrincipaleController PC) {
		super(PC);
		this.Vue = new CommandeVue(this);
		Model = new CommandeModel();
		Vue.afficherListe(Model.recupererListe(), this);
    }

    /**
     * Cherche les actions à effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évènement
     */
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
    	CommandeVue vue = (CommandeVue) Vue;
    	CommandeModel model = (CommandeModel) Model;

		// ouvre la fenêtre d'ajout de nouveau client
		if (source == vue.boutonAjouter) {
			fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(null, model.recupererListeClients(), this);
			PC.JF.setContentPane(fenetreCreerOuModifierCommande);
			PC.JF.refresh();
			return;
		}

		// ouvre la fenêtre de modification de client (parcours boutons modification)
		List<JButton> boutonsModif = vue.getListBoutonsModification();
		for (int i = 0; i < boutonsModif.size(); i++) {
			if(source == boutonsModif.get(i)) {
				fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(model.recupererListe().get(i), model.recupererListeClients(), this);
				PC.JF.setContentPane(fenetreCreerOuModifierCommande);
				PC.JF.refresh();
				return;
			}
		}

		/*
		 * Vérifie l'écouteur des boutons de suppression de la liste des articles
		 */
		List<JButton> boutonsSuppr = vue.getListBoutonsSuppression();
		for (int i = 0; i < boutonsSuppr.size(); i++) {
			if(source == boutonsSuppr.get(i)) {
				model.supprimer(model.recupererListe().get(i));
				Vue.afficherListe(Model.recupererListe(), this);
				PC.JF.refresh();
				return;
			}
		}

		// l'évènement a été délenché sur la page de modification/création de client
		if (fenetreCreerOuModifierCommande != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAjouter) {
				model.ajouter(fenetreCreerOuModifierCommande.validerCreation());
			}
			// valider la modification d'un client
			if (source == fenetreCreerOuModifierCommande.boutonValiderModification) {
				model.modifier(fenetreCreerOuModifierCommande.validerModification());
			}
			// Annuler la modification/création d'un client
			// Aucune action n'est requise pour l'annulation

			if (source == fenetreCreerOuModifierCommande.boutonAjouterArticle) {
				fenetreAjoutArticle = new AjouterArticle(fenetreCreerOuModifierCommande.getCommande(), model.recupererListeArticles(),this);
			} else {
				// ferme la fenêtre de modification/ajout
				Vue.afficherListe(Model.recupererListe(), this);
				PC.JF.setContentPane(Vue);
				PC.JF.refresh();
			}
		}
    }
}


