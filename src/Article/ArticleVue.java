package article;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import principale.PrincipaleVue;
import principale.Vue;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.util.ArrayList;
import java.util.List;


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


public class ArticleVue extends Vue {

	/**
	 * zone de texte pour la recherche d'article
	 */
	private JTextField textFieldRecherche;

	/**
	 * bouton d'envoi de l'article
	 */
	public final JButton boutonAjouter = new JButton("Ajouter");

	/**
	 * bouton de recherche d'article
	 */
	public final JButton boutonRecherche = new JButton("Rechercher un article selon sa désignation");

	/**
	 * Zone de texte pour afficher les articles
	 */
	private JPanel pan;

	/**
	 * Constructeur
	 * Définit la fenêtre et ses composants - affiche la fenêtre
	 */
	public ArticleVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

        
		//instantiation des  composants graphiques
		textFieldRecherche=new JTextField();

		pan = new JPanel();
		/*
	  Zone de défilement pour la zone de texte
	 */
		JScrollPane zoneDefilement = new JScrollPane(pan);
		zoneDefilement.setPreferredSize(new Dimension(500, 250));
		
		//ajout des composants sur le container
		JPanel panelRecherche = new JPanel();
		panelRecherche.setOpaque(false);
		add(panelRecherche);

		panelRecherche.setLayout(new GridBagLayout());
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.EAST;
		GridBagConstraints right = new GridBagConstraints();
		right.weightx = 2.0;
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridwidth = GridBagConstraints.REMAINDER;

		panelRecherche.add(boutonRecherche, left);
		panelRecherche.add(textFieldRecherche, right);

		add(Box.createRigidArea(new Dimension(0,20)));
		add(zoneDefilement);

		add(boutonAjouter);
		add(Box.createRigidArea(new Dimension(0,5)));

		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		listeBoutonsModifier = new ArrayList<>();
		listeBoutonsSupprimer = new ArrayList<>();

		// ajoute les écouteurs sur les boutons
		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);
	}

	/**
	 * Affiche la liste des articles avec leur désignation, prix et quantité ainsi qu'un bouton pour les modifier
	 * @param liste liste des articles à afficher
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) {
		List<Article> articles = liste;
		pan.removeAll();
		listeBoutonsModifier.clear();
		listeBoutonsSupprimer.clear();

		if (articles.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListe("Il n'y a aucun article dans la base de données"));
			return;
		}

		pan.setLayout(new GridLayout(articles.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
		pan.add(creerLabelListe("Désignation"));
		pan.add(creerLabelListe("Prix/u HT"));
		pan.add(creerLabelListe("Quantité"));
		pan.add(creerLabelListe("Actions"));

		for (Article article : articles) {
			pan.add(creerLabelListe(article.getDesignation()));
			pan.add(creerLabelListe(Double.toString(article.getPuHt())));
			pan.add(creerLabelListe(Integer.toString(article.getQteStock())));

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

	/**
	 * Ajoute des écouteurs sur les boutons de la liste des articles
	 * @return valeur du champ de recherche
	 */
	public String getDesignationRecherche() {
		return textFieldRecherche.getText();
	}
}
