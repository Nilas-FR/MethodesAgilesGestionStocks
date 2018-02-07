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
 * D�finit et ouvre une fenetre qui :
 *    - Permet l'insertion d'un nouvel article dans la table article via
 * la saisie des valeurs de d�signation, prix et quantit� en stock
 *    - Permet l'affichage de tous les articles une zone de texte
 *    
 *    Pour aller plus loin : 
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
 *    Diff�rents types de composants graphiques sont disponibles
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html
 *    Sans oublier la r�f�rence d'ouvrage utilis�e dans le cours "programmer avec Java"
 *    
 * @version 1.2
 * */


public class ArticleVue extends Vue {

	/**
	 * Zone de texte pour afficher les articles
	 */
	private JPanel pan;

	/**
	 * Constructeur
	 * D�finit la fen�tre et ses composants - affiche la fen�tre
	 */
	public ArticleVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de g�rer la position des �l�ments
		//il autorisera un retaillage de la fen�tre en conservant la pr�sentation
		//BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

        
		//instantiation des  composants graphiques
		textFieldRecherche=new JTextField();

		pan = new JPanel();
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

		// ajoute les �couteurs sur les boutons
		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);

		boutonRecherche.setText("Recherche un article selon sa d�signation");
	}

	/**
	 * Affiche la liste des articles avec leur d�signation, prix et quantit� ainsi qu'un bouton pour les modifier
	 * @param liste liste des articles � afficher
	 * @param listener �couteurs � placer sur les boutons de la fen�tre
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) {
		List<Article> articles = liste;
		pan.removeAll();
		listeBoutonsModifier.clear();
		listeBoutonsSupprimer.clear();

		if (articles.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListe("Il n'y a aucun article dans la base de donn�es"));
			return;
		}

		pan.setLayout(new GridLayout(articles.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// cr�� tous les labels avec � chaque fois une lineBorder et un texte align� au centre
		pan.add(creerLabelListe("D�signation"));
		pan.add(creerLabelListe("Prix/u HT"));
		pan.add(creerLabelListe("Quantit�"));
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
	 * Ajoute des �couteurs sur les boutons de la liste des articles
	 * @return valeur du champ de recherche
	 */
	public String getStringRecherche() {
		return textFieldRecherche.getText();
	}
}
