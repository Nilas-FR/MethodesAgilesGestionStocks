package commande;

import javax.swing.*;

import principale.Vue;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import client.Client;

public class CommandeVue extends Vue {

	/**
	 * Zone de texte pour afficher les commandes
	 */
	private final JPanel pan = new JPanel();

	/**
	 * Constructeur
	 * D�finit la fen�tre et ses composants - affiche la fen�tre
	 */
	public CommandeVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de g�rer la position des �l�ments
		//il autorisera un retaillage de la fen�tre en conservant la pr�sentation
		//BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


		//instantiation des  composants graphiques
        JScrollPane zoneDefilement = new JScrollPane(pan);
		zoneDefilement.setPreferredSize(new Dimension(500, 250));

		add(zoneDefilement);

		add(boutonAjouter);
		add(Box.createRigidArea(new Dimension(0,5)));

		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		listeBoutonsModifier = new ArrayList<>();
		listeBoutonsSupprimer = new ArrayList<>();

		boutonAjouter.addActionListener(listener);
	}

	/**
	 * Affiche la liste des commandes avec l'identifiant, le client et la date de validation
     * ainsi qu'un bouton pour les modifier ou les supprimer
	 * @param liste liste des commandes � afficher
	 * @param listener �couteurs � placer sur les boutons de la liste
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) {
		List<Commande> commandes = liste;

		pan.removeAll();
		listeBoutonsModifier.clear();
		listeBoutonsSupprimer.clear();

		if (commandes.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListe("Il n'y a aucun article dans la base de donn�es"));
			return;
		}

		pan.setLayout(new GridLayout(commandes.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// cr�� tous les labels avec � chaque fois une lineBorder et un texte align� au centre
		pan.add(creerLabelListe("Identifiant"));
		pan.add(creerLabelListe("Client"));
		pan.add(creerLabelListe("Date"));
		pan.add(creerLabelListe("Actions"));

		for (Commande commande : commandes) {
			pan.add(creerLabelListe(Integer.toString(commande.getIdentifiant())));
			pan.add(creerLabelListe(commande.getClient().getNom() + " " + commande.getClient().getPrenom()));
			pan.add(creerLabelListe(commande.getDate().toString()));

			JPanel conteneurActions = new JPanel();
			conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JButton boutonModif = new JButton("Modifier");
			boutonModif.addActionListener(listener);
			JButton boutonSuppr = new JButton("Supprimer");
			boutonSuppr.addActionListener(listener);
			listeBoutonsModifier.add(boutonModif);
			listeBoutonsSupprimer.add(boutonSuppr);
			conteneurActions.add(boutonModif);
			conteneurActions.add(boutonSuppr);
			pan.add(conteneurActions);
		}
	}

}
