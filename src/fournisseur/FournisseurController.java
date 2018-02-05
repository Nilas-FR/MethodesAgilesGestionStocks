package fournisseur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login2.LoginVue;
import principale2.Controller;
import principale2.PrincipaleController;

public class FournisseurController extends Controller implements ActionListener {
	
	private FournisseurModel Model = new FournisseurModel();

	public FournisseurController(PrincipaleController PC) {
		super(PC);
		this.Vue = new FournisseurVue();
		Vue.ajouterListener(this);
		((FournisseurVue) Vue).MaJ(Model.getListeFournisseurs()); 
	}

    public void actionPerformed(ActionEvent e) {
    	FournisseurVue vue = (FournisseurVue) Vue;
    }

}