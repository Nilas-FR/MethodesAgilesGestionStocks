package Commande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Client.Client;


/**
 * Classe CommandeFenetre
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


public class CommandeFenetre extends JPanel {

	/**
	 * numero de version pour classe serialisable
	 * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * bouton de création de commande
	 */
	public final JButton boutonAjouter = new JButton("Créer une commande");

	/**
	 * Zone de texte pour afficher les commandes
	 */
	private JPanel pan;

	/**
	 * Liste des boutons associés aux commandes pour leur modification
	 */
	private List<JButton> listeBoutonsModifierCommande;

	/**
	 * Liste des boutons associés aux commandes pour leur suppression
	 */
	private List<JButton> listeBoutonsSupprimerCommande;

    /**
	 * Vue de l'application
	 */
	private JFrame JF;

	/**
	 * Fenêtre de création/modification de commande
	 */
	private CommandeCreerOuModifier fenetreCreerOuModifierCommande;

	/**
	 * Constructeur
	 * Définit la fenêtre et ses composants - affiche la fenêtre
	 * @param JF JFrame globale de l'application
	 */
	public CommandeFenetre(JFrame JF) {
		this.JF = JF;

		//on fixe le titre de la fenêtre
		JF.setTitle("Commande");

		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


		//instantiation des  composants graphiques
		pan = new JPanel();
        JScrollPane zoneDefilement = new JScrollPane(pan);
		zoneDefilement.setPreferredSize(new Dimension(500, 250));

		add(zoneDefilement);

		add(boutonAjouter);
		add(Box.createRigidArea(new Dimension(0,5)));

		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		listeBoutonsModifierCommande = new ArrayList<>();
		listeBoutonsSupprimerCommande = new ArrayList<>();

		JF.pack();
	}

	/**
	 * Affiche la vue de création de nouvelle commande
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 * @param clients liste des clients de la base
	 */
	public void afficherVueNouvelleCommande(ActionListener listener, Client[] clients) {
		fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(JF, null, clients);
		fenetreCreerOuModifierCommande.ajouterListener(listener);
		fenetreCreerOuModifierCommande.afficherListeArticles(listener);
	}

	/**
	 * Affiche la vue de modification de commande
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 * @param commande commande sujette à la modification
	 * @param clients liste des clients de la base
	 */
	public void afficherVueModifierCommande(ActionListener listener, Commande commande, Client[] clients) {
        System.out.println("modifier commande ");
        System.out.println(commande);
		fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(JF, commande, clients);
		fenetreCreerOuModifierCommande.ajouterListener(listener);
        fenetreCreerOuModifierCommande.afficherListeArticles(listener);
	}

	/**
	 * ferme la fenêtre de modification/création de commande et revient sur la page générale des commandes
	 */
	public void fermerFenetreCreationModification() {
		fenetreCreerOuModifierCommande = null;
		JF.setContentPane(this);
		JF.pack();
	}

	/**
	 * Affiche la liste des commandes avec l'identifiant, le client et la date de validation
     * ainsi qu'un bouton pour les modifier ou les supprimer
	 * @param commandes liste des commandes à afficher
	 * @param listener écouteurs à placer sur les boutons de la liste
	 */
	public void afficherListeCommandes(List<Commande> commandes, ActionListener listener) {
		boutonAjouter.addActionListener(listener);

		pan.removeAll();
		listeBoutonsModifierCommande.clear();
		listeBoutonsSupprimerCommande.clear();

		if (commandes.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListeCommandes("Il n'y a aucun article dans la base de données"));
			JF.pack();
			return;
		}

		pan.setLayout(new GridLayout(commandes.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
		pan.add(creerLabelListeCommandes("Identifiant"));
		pan.add(creerLabelListeCommandes("Client"));
		pan.add(creerLabelListeCommandes("Date"));
		pan.add(creerLabelListeCommandes("Actions"));

		for (Commande commande : commandes) {
			pan.add(creerLabelListeCommandes(Integer.toString(commande.getIdentifiant())));
			pan.add(creerLabelListeCommandes(commande.getClient().getNom() + " " + commande.getClient().getPrenom()));
			pan.add(creerLabelListeCommandes(commande.getDate().toString()));

			JPanel conteneurActions = new JPanel();
			conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JButton boutonModif = new JButton("Modifier");
			JButton boutonSuppr = new JButton("Supprimer");
			listeBoutonsModifierCommande.add(boutonModif);
			listeBoutonsSupprimerCommande.add(boutonSuppr);
			conteneurActions.add(boutonModif);
			conteneurActions.add(boutonSuppr);
			pan.add(conteneurActions);
		}

		ajouterListenerListeCommandes(listener);

		JF.pack();
	}

	/**
	 * Créé un JLabel avec le texte passé en paramètre avec une bordure noire et le texte aligné au centre
	 * @param texte texte qui sera placé dans le JLabel
	 * @return JLabel créé
	 */
	public JLabel creerLabelListeCommandes(String texte) {
		JLabel label = new JLabel(texte);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	/**
	 * Ajoute des écouteurs sur les boutons de modification et de suppression des commandes
	 * @param listener écouteurs à placer sur les boutons
	 */
	public void ajouterListenerListeCommandes(ActionListener listener) {
		for (JButton bouton : listeBoutonsModifierCommande) {
			bouton.addActionListener(listener);
		}
		for (JButton bouton : listeBoutonsSupprimerCommande) {
			bouton.addActionListener(listener);
		}
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des commandes
	 * @return liste des boutons de modification
	 */
	public List<JButton> getListBoutonsModificationCommandes() {
		return listeBoutonsModifierCommande;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux suppressions des commandes
	 * @return liste des boutons de suppression
	 */
	public List<JButton> getListBoutonsSuppressionCommandes() {
		return listeBoutonsSupprimerCommande;
	}

	/**
	 * renvoie la fenêtre de modification/ajout de commandes
	 * @return fenêtre de modification/ajout de commandes
	 */
	public CommandeCreerOuModifier getFenetreCreationOuModificationCommande() {
		return fenetreCreerOuModifierCommande;
	}
}
