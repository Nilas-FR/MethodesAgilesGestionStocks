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


public class ArticleVue extends Vue {

	/**
	 * numero de version pour classe serialisable
	 * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
	 */
	private static final long serialVersionUID = 1L;

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
	 * Liste des boutons associés aux articles pour leur modification
	 */
	private List<JButton> listeBoutonsModifierArticle;

	/**
	 * Liste des boutons associés aux articles pour leur suppression
	 */
	private List<JButton> listeBoutonsSupprimerArticle;

	/**
	 * Zone de défilement pour la zone de texte
	 */
	private JScrollPane zoneDefilement;

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
		zoneDefilement = new JScrollPane(pan);
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

		listeBoutonsModifierArticle = new ArrayList<>();
		listeBoutonsSupprimerArticle = new ArrayList<>();

		// ajoute les écouteurs sur les boutons
		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);
	}

	/**
	 * Ajoute des écouteurs sur les boutons de la liste des articles
	 * @return valeur du champ de recherche
	 */
	public String getDesignationRecherche() {
		return textFieldRecherche.getText();
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
		listeBoutonsModifierArticle.clear();
		listeBoutonsSupprimerArticle.clear();

		if (articles.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListeArticles("Il n'y a aucun article dans la base de données"));
			return;
		}

		pan.setLayout(new GridLayout(articles.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
		pan.add(creerLabelListeArticles("Désignation"));
		pan.add(creerLabelListeArticles("Prix/u HT"));
		pan.add(creerLabelListeArticles("Quantité"));
		pan.add(creerLabelListeArticles("Actions"));

		for (Article article : articles) {
			pan.add(creerLabelListeArticles(article.getDesignation()));
			pan.add(creerLabelListeArticles(Double.toString(article.getPuHt())));
			pan.add(creerLabelListeArticles(Integer.toString(article.getQteStock())));

			JPanel conteneurActions = new JPanel();
			conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JButton boutonModif = new JButton("Modifier");
			JButton boutonSuppr = new JButton("Supprimer");
			listeBoutonsModifierArticle.add(boutonModif);
			listeBoutonsSupprimerArticle.add(boutonSuppr);
			conteneurActions.add(boutonModif);
			conteneurActions.add(boutonSuppr);
			pan.add(conteneurActions);
		}

		ajouterListenerListeArticles(listener);
	}

	/**
	 * Créé un JLabel avec le texte passé en paramètr avec une bordure noire et le texte aligné au centre
	 * @param texte texte qui sera placé dans le JLabel
	 * @return JLabel créé
	 */
	private JLabel creerLabelListeArticles(String texte) {
		JLabel label = new JLabel(texte);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	/**
	 * Ajoute des écouteurs sur les boutons de modification et de suppression des articles
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	private void ajouterListenerListeArticles(ActionListener listener) {
		for (JButton bouton : listeBoutonsModifierArticle) {
			bouton.addActionListener(listener);
		}
		for (JButton bouton : listeBoutonsSupprimerArticle) {
			bouton.addActionListener(listener);
		}
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des articles
	 * @return liste des boutons de modification
	 */
	public List<JButton> getListBoutonsModificationArticles() {
		return listeBoutonsModifierArticle;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des articles
	 * @return liste des boutons de suppression
	 */
	public List<JButton> getListBoutonsSuppressionArticles() {
		return listeBoutonsSupprimerArticle;
	}

	/**
	 * Ajoute des écouteurs sur les boutons du panel
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	@Override
	public void ajouterListener(ActionListener listener) {
		// Inutile
	}
}
