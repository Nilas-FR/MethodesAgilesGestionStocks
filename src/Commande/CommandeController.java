package Commande;

import message.Message;
import principale.Controller;
import principale.PrincipaleController;
import variables.Variables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CommandeController extends Controller {

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
		if (Variables.Droit < 1) {
			Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification, veuillez vous connecter.");
			return;
		}

    	Object source = e.getSource();

		// ouvre la fenêtre d'ajout de la nouvelle commande
		if (source == Vue.boutonAjouter) {
			fenetreCreationModification = new CommandeCreerOuModifier(null, ((CommandeModel)Model).recupererListeClients(), this);
			PC.afficherJPanel(fenetreCreationModification);
			return;
		}

		// ouvre la fenêtre de modification de commande (parcours boutons modification)
		List<JButton> boutonsModif = Vue.getListBoutonsModification();
		for (int i = 0; i < boutonsModif.size(); i++) {
			if(source == boutonsModif.get(i)) {
				fenetreCreationModification = new CommandeCreerOuModifier(((CommandeModel)Model).recupererListe().get(i), ((CommandeModel)Model).recupererListeClients(), this);
				PC.afficherJPanel(fenetreCreationModification);
				return;
			}
		}

		// vérifie si l'event est sur un bouton supprimer
		if(verifierEventBoutonsSupprimer(source)) return;

		CommandeCreerOuModifier fenetreCreerOuModifierCommande = (CommandeCreerOuModifier) fenetreCreationModification;
		// l'évènement a été délenché sur la page de modification/création de commande
		if (fenetreCreerOuModifierCommande != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAjouter) {
				Model.ajouter(fenetreCreerOuModifierCommande.validerCreation());
				fermerFenetreAjoutModificationCommande();
				return;
			}
			// valider la modification d'un client avec ou sans le changement de date
			if (source == fenetreCreerOuModifierCommande.boutonValiderModification) {
				((CommandeModel)Model).modifier(fenetreCreerOuModifierCommande.validerModification(), fenetreCreerOuModifierCommande.changementDateActive());
				fermerFenetreAjoutModificationCommande();
				return;
			}
			// Annuler la modification/création d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAnnuler) {
				fermerFenetreAjoutModificationCommande();
				return;
			}

			if (source == fenetreCreerOuModifierCommande.boutonAjouterArticle) {
				System.out.println("ajout article");
				fenetreAjoutArticle = new AjouterArticle(fenetreCreerOuModifierCommande.getCommande(),
						((CommandeModel)Model).recupererListeArticles(),this);
				PC.afficherJPanel(fenetreAjoutArticle);
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
		PC.afficherJPanel(Vue);
		fenetreCreationModification = null;
	}

	/**
	 * Ferme la fenêtre d'ajout d'article dans la commande et retourne sur la fenêtre de modification/ajout de commande
	 */
	private void fermerFenetreAjoutArticle() {
		((CommandeCreerOuModifier)fenetreCreationModification).afficherListeArticles(this);
		PC.afficherJPanel(fenetreCreationModification);
		fenetreAjoutArticle = null;
	}
}


