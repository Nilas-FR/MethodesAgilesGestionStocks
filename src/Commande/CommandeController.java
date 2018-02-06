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

		// ouvre la fenêtre d'ajout de la nouvelle commande
		if (source == vue.boutonAjouter) {
			fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(null, model.recupererListeClients(), this);
			PC.JF.setContentPane(fenetreCreerOuModifierCommande);
			PC.JF.refresh();
			return;
		}

		// ouvre la fenêtre de modification de commande (parcours boutons modification)
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
		 * Vérifie l'écouteur des boutons de suppression de la liste des commande
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

		// l'évènement a été délenché sur la page de modification/création de commande
		if (fenetreCreerOuModifierCommande != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAjouter) {
				model.ajouter(fenetreCreerOuModifierCommande.validerCreation());
				fermerFenetreAjoutModificationCommande();
				return;
			}
			// valider la modification d'un client
			if (source == fenetreCreerOuModifierCommande.boutonValiderModification) {
				model.modifier(fenetreCreerOuModifierCommande.validerModification());
				fermerFenetreAjoutModificationCommande();
				return;
			}
			// Annuler la modification/création d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAnnulerModification) {
				fermerFenetreAjoutModificationCommande();
				return;
			}

			if (source == fenetreCreerOuModifierCommande.boutonAjouterArticle) {
				System.out.println("ajout article");
				fenetreAjoutArticle = new AjouterArticle(fenetreCreerOuModifierCommande.getCommande(), model.recupererListeArticles(),this);
				PC.JF.setContentPane(fenetreAjoutArticle);
				PC.JF.refresh();
				return;
			}

			// l'évènement a été délenché sur la page de d'ajout d'article dans la commande
			if (fenetreAjoutArticle != null) {
				if (source == fenetreAjoutArticle.boutonValider) {
					fenetreAjoutArticle.validerAjout();
					fermerFenetreAjoutArticle();
					return;
				}

				if (source == fenetreAjoutArticle.boutonAnnuler) {
					fermerFenetreAjoutArticle();
					return;
				}
			}

		}
    }

	/**
	 * Ferme la fenêtre d'ajout/modification de commande et retourne sur la fenêtre listant les commandes
	 */
	private void fermerFenetreAjoutModificationCommande() {
		Vue.afficherListe(Model.recupererListe(), this);
		PC.JF.setContentPane(Vue);
		PC.JF.refresh();
		fenetreCreerOuModifierCommande = null;
	}

	/**
	 * Ferme la fenêtre d'ajout d'article dans la commande et retourne sur la fenêtre de modification/ajout de commande
	 */
	private void fermerFenetreAjoutArticle() {
		PC.JF.setContentPane(fenetreCreerOuModifierCommande);
		fenetreCreerOuModifierCommande.afficherListeArticles(this);
		PC.JF.refresh();
		fenetreAjoutArticle = null;
	}
}


