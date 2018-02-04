package fournisseur;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class FournisseurFenetre extends JPanel {
	
	private Vector<Fournisseur> fournisseurs = null;
	
	public FournisseurFenetre(JFrame JF) {

		//On définit le titre de la fenêtre
		JF.setTitle("Fournisseurs");
	}
	
	public void ajouterListener(ActionListener listener) {
		
	}
	
	public void chargerFournisseur() {
		
	}
}
