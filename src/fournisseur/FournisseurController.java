package fournisseur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login2.LoginVue;
import principale2.Controller;
import principale2.PrincipaleController;

public class FournisseurController extends Controller implements ActionListener {

	public FournisseurController(PrincipaleController PC) {
		super(PC);
		this.Vue = new FournisseurVue();
		Vue.ajouterListener(this);
	}

    public void actionPerformed(ActionEvent e) {
    	FournisseurVue vue = (FournisseurVue) Vue;
    }

}