package accueil;

import principale.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AccueilVue extends Vue {

	/**
	 * Label nombre d'articles en stock
	 */
	private JLabel nombreArticlesStock;

	/**
	 * Label valeur stock
	 */
	private JLabel valeurStock;

	/**
	 * Label du nombre d'articles
	 */
	private JLabel nombreArticles;

	/**
	 * Label du nombre de clients
	 */
	private JLabel nombreClients;

	/**
	 * Label du nombre de fournisseurs
	 */
	private JLabel nombreFournisseurs;

	/**
	 * Label du nombre de commandes
	 */
	private JLabel nombreCommandes;

	/**
	 * Label du meilleur client
	 */
	private JLabel meilleurClient;

	/**
	 * Label de l'article le mieux vendu
	 */
	private JLabel articleMieuxVendu;

	/**
	 * Label de l'article ayant le plus rapport�
	 */
	private JLabel articleMeilleurGain;

	/**
	 * Label du nombre d'articles vendus
	 */
	private JLabel nombreArticlesVendus;

	/**
	 * Label de la valeur des articles vendus
	 */
	private JLabel valeurArticlesVendus;

	/**
	 * Constructeur
	 * D�finit la fen�tre et ses composants - affiche la fen�tre
	 */
	public AccueilVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de g�rer la position des �l�ments
		//il autorisera un retaillage de la fen�tre en conservant la pr�sentation
		//BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

		nombreArticles = new JLabel("nombreArticles");
		add(nombreArticles);
		add(Box.createRigidArea(new Dimension(0,5)));

		nombreClients = new JLabel("nombreClients");
		add(nombreClients);
		add(Box.createRigidArea(new Dimension(0,5)));

		nombreFournisseurs = new JLabel("nombreFournisseurs");
		add(nombreFournisseurs);
		add(Box.createRigidArea(new Dimension(0,5)));

		nombreCommandes = new JLabel("nombreCommandes");
		add(nombreCommandes);
		add(Box.createRigidArea(new Dimension(0,5)));

		add(new JSeparator());

		nombreArticlesStock = new JLabel("nombreArticlesStock");
		add(nombreArticlesStock);
		add(Box.createRigidArea(new Dimension(0,5)));

		valeurStock = new JLabel("valeurStock");
		add(valeurStock);
		add(Box.createRigidArea(new Dimension(0,5)));

		add(new JSeparator());

		meilleurClient = new JLabel("meilleurClient");
		add(meilleurClient);
		add(Box.createRigidArea(new Dimension(0,5)));

		add(new JSeparator());

		articleMieuxVendu = new JLabel("articleMieuxVendu");
		add(articleMieuxVendu);
		add(Box.createRigidArea(new Dimension(0,5)));

		articleMeilleurGain = new JLabel("articleMeilleurGain");
		add(articleMeilleurGain);
		add(Box.createRigidArea(new Dimension(0,5)));

		add(new JSeparator());

		nombreArticlesVendus = new JLabel("nombreArticlesVendus");
		add(nombreArticlesVendus);
		add(Box.createRigidArea(new Dimension(0,5)));

		valeurArticlesVendus = new JLabel("valeurArticlesVendus");
		add(valeurArticlesVendus);
		add(Box.createRigidArea(new Dimension(0,5)));

		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	/**
	 * Met � jour le champs du nombre d'articles en stock
	 * @param valeur texte � remplacer
	 */
	public void nombreArticlesStock(String valeur) {
		nombreArticlesStock.setText(valeur);
	}

	/**
	 * Met � jour le champs de la valeur du stock
	 * @param valeur texte � remplacer
	 */
	public void valeurStock(String valeur) {
		valeurStock.setText(valeur);
	}

	/**
	 * Met � jour le champs du nombre d'articles diff�rents
	 * @param valeur texte � remplacer
	 */
	public void nombreArticles(String valeur) {
		nombreArticles.setText(valeur);
	}

	/**
	 * Met � jour le champs du nombre de clients
	 * @param valeur texte � remplacer
	 */
	public void nombreClients(String valeur) {
		nombreClients.setText(valeur);
	}

	/**
	 * Met � jour le champs du nombre de fournisseurs
	 * @param valeur texte � remplacer
	 */
	public void nombreFournisseurs(String valeur) {
		nombreFournisseurs.setText(valeur);
	}

	/**
	 * Met � jour le champs du nombre de commandes
	 * @param valeur texte � remplacer
	 */
	public void nombreCommandes(String valeur) {
		nombreCommandes.setText(valeur);
	}

	/**
	 * Met � jour le champs du meilleur client
	 * @param valeur texte � remplacer
	 */
	public void meilleurClient(String valeur) {
		meilleurClient.setText(valeur);
	}

	/**
	 * Met � jour le champs de l'article le mieux vendu
	 * @param valeur texte � remplacer
	 */
	public void articleMieuxVendu(String valeur) {
		articleMieuxVendu.setText(valeur);
	}

	/**
	 * Met � jour le champs de l'article ayant le meilleur gain
	 * @param valeur texte � remplacer
	 */
	public void articleMeilleurGain(String valeur) {
		articleMeilleurGain.setText(valeur);
	}

	/**
	 * Met � jour le champs du nombre d'articles vendus
	 * @param valeur texte � remplacer
	 */
	public void nombreArticlesVendus(String valeur) {
		nombreArticlesVendus.setText(valeur);
	}

	/**
	 * Met � jour le champs de la valeur des articles vendus
	 * @param valeur texte � remplacer
	 */
	public void valeurArticlesVendus(String valeur) {
		valeurArticlesVendus.setText(valeur);
	}

	/**
	 * Inutile, aucune liste dans l'accueil
	 * @param liste liste � afficher
	 * @param listener �couteurs � placer sur les boutons de la fen�tre
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) { }
}
