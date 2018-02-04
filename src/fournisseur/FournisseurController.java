package fournisseur;

import Controller.Control;
import Vue.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FournisseurController extends Control implements ActionListener {

	public FournisseurController(FournisseurModel fournisseurModel, Vue vue) {
		super(fournisseurModel, vue);
		vue.setFournisseurController(this);
	}

    public void actionPerformed(ActionEvent e) {
    	FournisseurModel modelUtilisateur = (FournisseurModel) model;
		Object source = e.getSource();
    }

}