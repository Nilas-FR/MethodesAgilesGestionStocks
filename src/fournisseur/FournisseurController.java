package fournisseur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import login.LoginVue;
import message.Message;
import principale.Controller;
import principale.PrincipaleController;
import variables.Variables;

public class FournisseurController extends Controller implements ActionListener {
	
	private FournisseurModel Model = new FournisseurModel();

	public FournisseurController(PrincipaleController PC) {
		super(PC);
		this.Vue = new FournisseurVue();
		Vue.ajouterListener(this);
		((FournisseurVue) Vue).MaJ(Model.getListeFournisseurs(), this); 
	}

    public void actionPerformed(ActionEvent e) {
    	JButton JB = (JButton) e.getSource();
		FournisseurVue vue = (FournisseurVue) Vue;
		if (Variables.Droit < 1) {
			Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification.");
			return;
		}
    	if (JB.getText() == "Supprimer") {
    		Model.supprimerFournisseur(JB.getToolTipText());
    		vue.MaJ(Model.getListeFournisseurs(), this); 
			PC.JF.refresh();
    	}
    	if (JB.getText() == "Ajouter") {
    		String siret = vue.TFSiret.getText(), nom = vue.TFNom.getText(), adresse = vue.TFAdresse.getText();
    		if (nom.length() == 0 || adresse.length() == 0 || siret.length() == 0) {
    			Message.MessageAlerte("Informations érronées", "Veuillez remplir tous les champs.");
    			return;
    		}
    		if (siret.length() < 14 || siret.toLowerCase() != siret.toUpperCase()) {
    			Message.MessageAlerte("Informations érronées", "Un numéro siret est composé de 14 chiffres.");
    			return;
    		}
    		Model.ajouterFournisseur(new Fournisseur(siret, nom, adresse));
    		vue.MaJ(Model.getListeFournisseurs(), this); 
    		vue.TFSiret.setText("");;
    		vue.TFNom.setText("");;
    		vue.TFAdresse.setText("");;
			PC.JF.refresh();
    	}
    }

}