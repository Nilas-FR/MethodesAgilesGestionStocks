package Commande;

import javax.swing.*;

import principale.Controller;
import principale.PrincipaleController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommandeController extends Controller implements ActionListener {

	CommandeCreerOuModifier fenetreCreerOuModifierCommande;
    /**
     * Créé le controleur des articles
     * @param modelCommande model gérant les articles
     * @param vue JFrame globale de l'application
     */
    public CommandeController(PrincipaleController PC) {
		super(PC);
		Vue = new CommandeFenetre();
		Vue.ajouterListener(this);
		Model = new CommandeModel();
    }

    /**
     * Cherche les actions à effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évènement
     */
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
    	CommandeFenetre vue = (CommandeFenetre) Vue;
    	CommandeModel model = (CommandeModel) Model;
    	
    	if (source == vue.boutonAjouter) {
    		fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(null, model.recupererListeClients());
    		fenetreCreerOuModifierCommande.ajouterListener(this);
    		fenetreCreerOuModifierCommande.afficherListeArticles(this);
    		PC.JF.setContentPane(fenetreCreerOuModifierCommande);
    	} else if (source == fenetreCreerOuModifierCommande.boutonValider) {
    		model.ajouterCommande(fenetreCreerOuModifierCommande.validerCreation());
    		vue.afficherListeCommandes(model.recupererListeCommandes(), this);
    	} else if (source == fenetreCreerOuModifierCommande.boutonAnnulerModification) {
            PC.JF.setContentPane(vue);
    	}
    	
    	PC.JF.refresh();
    }
}


