package fournisseur;

import java.awt.event.ActionEvent;
import java.util.Objects;

import javax.swing.JButton;

import message.Message;
import principale.Controller;
import principale.PrincipaleController;
import variables.Variables;

public class FournisseurController extends Controller {

	public FournisseurController(PrincipaleController PC) {
		super(PC);
		this.Vue = new FournisseurVue(this);
		Model = new FournisseurModel();
		((FournisseurVue)Vue).MaJ(((FournisseurModel)Model).getListeFournisseurs(), this);
	}

    public void actionPerformed(ActionEvent e) {
    	JButton JB = (JButton) e.getSource();
		FournisseurVue vue = (FournisseurVue) Vue;
		FournisseurModel model = (FournisseurModel) Model;
		if (Variables.Droit < 1) {
			Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification.");
			return;
		}
    	if (JB.getText().equals("Supprimer")) {
			model.supprimerFournisseur(JB.getToolTipText());
    		vue.MaJ(model.getListeFournisseurs(), this);
			PC.JF.refresh();
    	}
    	if (Objects.equals(JB.getText(), "Ajouter")) {
    		String siret = vue.TFSiret.getText(), nom = vue.TFNom.getText(), adresse = vue.TFAdresse.getText();
    		if (nom.length() == 0 || adresse.length() == 0 || siret.length() == 0) {
    			Message.MessageAlerte("Informations érronées", "Veuillez remplir tous les champs.");
    			return;
    		}
    		if (siret.length() < 14 || !siret.toLowerCase().equals(siret.toUpperCase())) {
    			Message.MessageAlerte("Informations érronées", "Un numéro siret est composé de 14 chiffres.");
    			return;
    		}
			model.ajouterFournisseur(new Fournisseur(siret, nom, adresse));
    		vue.MaJ(model.getListeFournisseurs(), this);
    		vue.TFSiret.setText("");;
    		vue.TFNom.setText("");;
    		vue.TFAdresse.setText("");;
			PC.JF.refresh();
    	}
    }

}