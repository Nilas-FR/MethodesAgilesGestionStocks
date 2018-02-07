package commande;

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
     * Cr�� le controleur des articles
	 * @param PC controleur principal
     */
    public CommandeController(PrincipaleController PC) {
		super(PC);
		this.Vue = new CommandeVue(this);
		Model = new CommandeModel();
		Vue.afficherListe(Model.recupererListe(), this);
    }

    /**
     * Cherche les actions � effectuer en fonction du bouton qui a �t� d�clench�
     * @param e bouton qui a d�clench� l'�v�nement
     */
    public void actionPerformed(ActionEvent e) {
		if (Variables.Droit < 1) {
			Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification, veuillez vous connecter.");
			return;
		}

    	Object source = e.getSource();

		// ouvre la fen�tre d'ajout de la nouvelle commande
		if (source == Vue.boutonAjouter) {
			fenetreCreationModification = new CommandeCreerOuModifier(null, ((CommandeModel)Model).recupererListeClients(), this);
			PC.afficherJPanel(fenetreCreationModification);
			return;
		}

		// ouvre la fen�tre de modification de commande (parcours boutons modification)
		List<JButton> boutonsModif = Vue.getListBoutonsModification();
		for (int i = 0; i < boutonsModif.size(); i++) {
			if(source == boutonsModif.get(i)) {
				fenetreCreationModification = new CommandeCreerOuModifier(((CommandeModel)Model).recupererListe().get(i), ((CommandeModel)Model).recupererListeClients(), this);
				PC.afficherJPanel(fenetreCreationModification);
				return;
			}
		}

		// v�rifie si l'event est sur un bouton supprimer
		if(verifierEventBoutonsSupprimer(source)) return;

		CommandeCreerOuModifier fenetreCreerOuModifierCommande = (CommandeCreerOuModifier) fenetreCreationModification;
		// l'�v�nement a �t� d�lench� sur la page de modification/cr�ation de commande
		if (fenetreCreerOuModifierCommande != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreerOuModifierCommande.boutonAjouter) {
				Model.ajouter(fenetreCreerOuModifierCommande.validerCreation());
				PC.updateListeDeductionStockCommande();
				PC.updateStats();
				fermerFenetreAjoutModificationCommande();
				return;
			}
			// valider la modification d'un client avec ou sans le changement de date
			if (source == fenetreCreerOuModifierCommande.boutonValiderModification) {
				((CommandeModel)Model).modifier(fenetreCreerOuModifierCommande.validerModification(), fenetreCreerOuModifierCommande.changementDateActive());
				fermerFenetreAjoutModificationCommande();
				PC.updateStats();
				return;
			}
			// Annuler la modification/cr�ation d'un client
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

			// l'�v�nement a �t� d�lench� sur la page de d'ajout d'article dans la commande
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
	 * Ferme la fen�tre d'ajout/modification de commande et retourne sur la fen�tre listant les commandes
	 */
	private void fermerFenetreAjoutModificationCommande() {
		Vue.afficherListe(Model.recupererListe(), this);
		PC.afficherJPanel(Vue);
		fenetreCreationModification = null;
	}

	/**
	 * Ferme la fen�tre d'ajout d'article dans la commande et retourne sur la fen�tre de modification/ajout de commande
	 */
	private void fermerFenetreAjoutArticle() {
		((CommandeCreerOuModifier)fenetreCreationModification).afficherListeArticles(this);
		PC.afficherJPanel(fenetreCreationModification);
		fenetreAjoutArticle = null;
	}
}


