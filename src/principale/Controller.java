package principale;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class Controller implements ActionListener {
	
	/** Un pointeur vers le controller principale */
	protected PrincipaleController PC;

	/** Un pointeur vers la vue du module */
	protected Vue Vue;

	/** Un pointeur vers le model du module */
	protected Model Model;

	protected FenetreCreationModification fenetreCreationModification = null;
	
	/**Constructeur
	 * @param PC controller principale
	 */
	public Controller(PrincipaleController PC) {
		this.PC = PC;
	}
	
	/** Ce module est maintenant actif */
	public void setActive(String titre) {
		Vue.setActive(PC.JF, titre);
	}

	/**
	 * Vérifie l'écouteur des boutons de suppression de la liste des commande
	 * @param source event récupéré
	 * @return le bouton a été trouvé
	 */
	protected boolean verifierEventBoutonsSupprimer(Object source) {
		List<JButton> boutonsSuppr = Vue.getListBoutonsSuppression();
		for (int i = 0; i < boutonsSuppr.size(); i++) {
			if(source == boutonsSuppr.get(i)) {
				Model.supprimer(Model.recupererListe().get(i));
				PC.updateListeSuppression();
				PC.JF.refresh();
				return true;
			}
		}
		return false;
	}

	/**
	 * Vérifie si l'event déclenché est sur la fenêtre de modification/ajout d'objet dans la base
	 * @param source event récupéré
	 */
	protected void verifierEventFenetreAjoutModification(Object source) {
		// l'évènement a été délenché sur la page de modification/création de client
		if (fenetreCreationModification != null) {
			// valider l'ajout d'un client
			if (source == fenetreCreationModification.boutonAjouter) {
				Model.ajouter(fenetreCreationModification.validerCreation());
			}
			// valider la modification d'un client
			if (source == fenetreCreationModification.boutonValiderModification) {
				Model.modifier(fenetreCreationModification.validerModification());
			}
			// Annuler la modification/création d'un client
			// Aucune action n'est requise pour l'annulation

			// ferme la fenêtre de modification/ajout
			Vue.afficherListe(Model.recupererListe(), this);
			PC.JF.setContentPane(Vue);
			PC.JF.refresh();
			fenetreCreationModification = null;
		}
	}
}
