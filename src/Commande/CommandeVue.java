package Commande;

import javax.swing.*;

import principale.Vue;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import client.Client;


/**
 * Classe CommandeVue
 * Définit et ouvre une fenetre qui :
 *    - Permet l'insertion d'un nouvel article dans la table article via
 * la saisie des valeurs de désignation, prix et quantité en stock
 *    - Permet l'affichage de tous les articles une zone de texte
 *    
 *    Pour aller plus loin : 
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
 *    Différents types de composants graphiques sont disponibles
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html
 *    Sans oublier la référence d'ouvrage utilisée dans le cours "programmer avec Java"
 *    
 * @version 1.2
 * */


public class CommandeVue extends Vue {

	/**
	 * Zone de texte pour afficher les commandes
	 */
	private final JPanel pan = new JPanel();

	/**
	 * Constructeur
	 * Définit la fenêtre et ses composants - affiche la fenêtre
	 */
	public CommandeVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
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
	 * @param liste liste des commandes à afficher
	 * @param listener écouteurs à placer sur les boutons de la liste
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) {
		List<Commande> commandes = liste;

		pan.removeAll();
		listeBoutonsModifier.clear();
		listeBoutonsSupprimer.clear();

		if (commandes.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListe("Il n'y a aucun article dans la base de données"));
			return;
		}

		pan.setLayout(new GridLayout(commandes.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
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
