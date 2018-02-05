package fournisseur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import login.LoginVue;
import principale.Controller;
import principale.PrincipaleController;

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
    	if (JB.getText() == "Supprimer") {
    		Model.supprimerFournisseur(JB.getToolTipText());
    		vue.MaJ(Model.getListeFournisseurs(), this); 
			PC.JF.refresh();
    	}
    	if (JB.getText() == "Ajouter") {
    		Model.ajouterFournisseur(new Fournisseur(vue.TFSiret.getText(), vue.TFNom.getText(), vue.TFAdresse.getText()));
    		vue.MaJ(Model.getListeFournisseurs(), this); 
    		vue.TFSiret.setText("");;
    		vue.TFNom.setText("");;
    		vue.TFAdresse.setText("");;
			PC.JF.refresh();
    	}
    }

}