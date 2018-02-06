package fournisseur;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import principale.Vue;

public class FournisseurVue extends Vue {
	
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFSiret		= new JTextField();
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFNom		= new JTextField();
	/** Champ pour ajouter un fournisseur */
	public final JTextField TFAdresse	= new JTextField();
	
	/** Constructeur */
	public FournisseurVue(ActionListener listener) {
		MaJ(new Vector<Fournisseur>(), null);

		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);
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
		Top.add(boutonAjouter);
        boutonAjouter.addActionListener(listener);
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

	@Override
	public void afficherListe(List liste, ActionListener listener) {
		// TODO Auto-generated method stub
	}
}
