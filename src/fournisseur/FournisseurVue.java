package fournisseur;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import principale2.PrincipaleVue;
import principale2.Vue;
import variables.Variables;

public class FournisseurVue extends Vue {
	
	private final JTextField TFSiret	= new JTextField();
	private final JTextField TFNom		= new JTextField();
	private final JTextField TFAdresse	= new JTextField();
	
	private Vector<Fournisseur> Fournisseurs = new Vector<Fournisseur>();
	
	public final JButton BAjouter = new JButton("Ajouter");
	
	public FournisseurVue() {
		
	}
	
	public void MaJ() {
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
		this.removeAll();
	}
	
	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
		BAjouter.addActionListener(listener);
	}
	
	/**On ajoute le panel à la fenêtre principale, on change son titre et sa taille
	 * @param JF est la fenêtre principale
	 */
	public void setActive(PrincipaleVue JF) {
		JF.setTitle("Fournisseur");
		JF.setSize(Variables.EcranLargeurDefaut,Variables.EcranHauteurDefaut);
		JF.add(this);
	}
}
