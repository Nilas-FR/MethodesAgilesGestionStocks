package Commande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


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
	 * bouton d'envoi de l'article
	 */
	public final JButton boutonAjouter = new JButton("Ajouter");

	/**
	 * Zone de texte pour afficher les articles
	 */
	private JPanel pan;

	/**
	 * Liste des boutons associés aux articles pour leur modification
	 */
	private List<JButton> listeBoutonsModifierCommande;

	/**
	 * Liste des boutons associés aux articles pour leur suppression
	 */
	private List<JButton> listeBoutonsSupprimerCommande;

	/**
	 * Zone de défilement pour la zone de texte
	 */
	private JScrollPane zoneDefilement;

	/**
	 * Vue.Vue de l'application
	 */
	private JFrame JF;

	/**
	 * Panel de modification/ajout d'article
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
		JF.setTitle("Article");

		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


		//instantiation des  composants graphiques
		pan = new JPanel();
		zoneDefilement = new JScrollPane(pan);
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
	 * Ajoute des écouteurs sur les boutons du panel
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void ajouterListener(ActionListener listener) {
		boutonAjouter.addActionListener(listener);
	}

	/**
	 * Affiche la vue d'ajout d'article
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void afficherVueNouvelleCommande(ActionListener listener) {
		fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(JF, null);
		fenetreCreerOuModifierCommande.ajouterListener(listener);
	}

	/**
	 * Affiche la vue de modification d'commande
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 * @param commande commande sujet à la modification
	 */
	public void afficherVueModifierCommande(ActionListener listener, Commande commande) {

		fenetreCreerOuModifierCommande = new CommandeCreerOuModifier(JF, commande);
		fenetreCreerOuModifierCommande.ajouterListener(listener);
	}

	/**
	 * Valider la création d'un article
	 * @return article à entrer dans la base
	 */
	/*public Commande validerCreation() {
		Commande commande = fenetreCreerOuModifierCommande.validerCreation();
		fenetreCreerOuModifierCommande = null;
		fermerFenetreCreationModification();
		return commande;
	}*/

	/**
	 * ferme la fenêtre de modification/création d'article et revient sur la page générale des articles
	 */
	public void fermerFenetreCreationModification() {
		fenetreCreerOuModifierCommande = null;
		JF.setContentPane(this);
		JF.pack();
	}

	/**
	 * Affiche la liste des commandes avec leur désignation, prix et quantité ainsi qu'un bouton pour les modifier
	 * @param commandes liste des commandes à afficher
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void afficherListeCommandes(List<Commande> commandes, ActionListener listener) {
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
			pan.add(creerLabelListeCommandes(Integer.toString(commande.getClient())));
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

		ajouterListenerListeArticles(listener);

		JF.pack();
	}

	/**
	 * Créé un JLabel avec le texte passé en paramètr avec une bordure noire et le texte aligné au centre
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
	 * Ajoute des écouteurs sur les boutons de modification et de suppression des articles
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void ajouterListenerListeArticles(ActionListener listener) {
		for (JButton bouton : listeBoutonsModifierCommande) {
			bouton.addActionListener(listener);
		}
		for (JButton bouton : listeBoutonsSupprimerCommande) {
			bouton.addActionListener(listener);
		}
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des articles
	 * @return liste des boutons de modification
	 */
	public List<JButton> getListBoutonsModificationCommandes() {
		return listeBoutonsModifierCommande;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des articles
	 * @return liste des boutons de suppression
	 */
	public List<JButton> getListBoutonsSuppressionCommandes() {
		return listeBoutonsSupprimerCommande;
	}

	/**
	 * renvoie la fenêtre de modification/ajout d'article
	 * @return fenêtre de modification/ajout d'article
	 */
	public CommandeCreerOuModifier getFenetreCreationOuModificationCommande() {
		return fenetreCreerOuModifierCommande;
	}
}
