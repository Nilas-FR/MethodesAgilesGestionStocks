package fournisseur;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import principale.PrincipaleVue;
import principale.Vue;
import variables.Variables;

public class FournisseurVue extends Vue {
	
	public final JTextField TFSiret		= new JTextField();
	public final JTextField TFNom		= new JTextField();
	public final JTextField TFAdresse	= new JTextField();
	
	public final JButton BAjouter = new JButton("Ajouter");
	
	public FournisseurVue() {
		MaJ(new Vector<Fournisseur>(), null);
	}
	
	public void MaJ(Vector<Fournisseur> Fournisseurs, ActionListener listener) {
		this.removeAll();
		this.setLayout(new GridLayout(2 + Fournisseurs.size(), 4));
		//Partie 1 - Titre
		this.add(new JLabel("Siret"));
		this.add(new JLabel("Nom"));
		this.add(new JLabel("Adresse"));
		this.add(new JLabel(""));
		//Partie 2 - Ajouter
		this.add(TFSiret);
		this.add(TFNom);
		this.add(TFAdresse);
		this.add(BAjouter);
		//Partie N - Fournisseur N
		for (int i = 0; i < Fournisseurs.size(); i++) {
			this.add(new JLabel(Fournisseurs.elementAt(i).Siret));
			this.add(new JLabel(Fournisseurs.elementAt(i).Nom));
			this.add(new JLabel(Fournisseurs.elementAt(i).Adresse));
			JButton JB = new JButton("Supprimer");
			JB.setToolTipText(Fournisseurs.elementAt(i).Siret);
			JB.addActionListener(listener);
			this.add(JB);
		}
	}
	
	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
		BAjouter.addActionListener(listener);
	}
	
	/**On ajoute le panel à la fenêtre principale, on change son titre et sa taille
	 * @param JF est la fenêtre principale
	 */
	public void setActive(JFrame JF) {
		JF.setTitle("Fournisseur");
		JF.add(this);
	}
}
