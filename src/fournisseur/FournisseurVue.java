package fournisseur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import principale.PrincipaleVue;
import principale.Vue;
import variables.Variables;

public class FournisseurVue extends Vue {
	
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFSiret		= new JTextField();
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFNom		= new JTextField();
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFAdresse	= new JTextField();
	
	/** Bouton pour ajouter un fournisseur */
	public final JButton BAjouter = new JButton("Ajouter");
	
	/** Constructeur */
	public FournisseurVue() {
		MaJ(new Vector<Fournisseur>(), null);
	}
	
	/** Mets à jour le tableau des fournisseurs et le réaffiche */
	public void MaJ(Vector<Fournisseur> Fournisseurs, ActionListener listener) {
		this.removeAll();
		JPanel Top = new JPanel();
		JPanel Bot = new JPanel();
		this.setLayout(new GridLayout(2, 1));
		Top.setLayout(new GridLayout(2, 4));
		Bot.setLayout(new GridLayout(Fournisseurs.size(), 4));
		//Partie 1 - Titre
		Top.add(new JLabel("Siret"));
		Top.add(new JLabel("Nom"));
		Top.add(new JLabel("Adresse"));
		Top.add(new JLabel(""));
		//Partie 2 - Ajouter
		TFSiret.setPreferredSize(new Dimension(150, 20));
		Top.add(TFSiret);
		Top.add(TFNom);
		Top.add(TFAdresse);
		Top.add(BAjouter);
		//Partie N - Fournisseur N
		for (int i = 0; i < Fournisseurs.size(); i++) {
			Bot.add(new JLabel(Fournisseurs.elementAt(i).Siret));
			Bot.add(new JLabel(Fournisseurs.elementAt(i).Nom));
			Bot.add(new JLabel(Fournisseurs.elementAt(i).Adresse));
			JButton JB = new JButton("Supprimer");
			JB.setToolTipText(Fournisseurs.elementAt(i).Siret);
			JB.addActionListener(listener);
			Bot.add(JB);
		}
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(Top);
		this.add(new JScrollPane(Bot));
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
