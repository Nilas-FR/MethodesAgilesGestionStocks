package Commande;

import javax.swing.*;

import principale.Vue;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Article.Article;
import Client.Client;


/**
 * Classe CommandeFenetre
 * Définit et ouvre une fenetre qui :
 *    - Permet l'insertion d'un nouvel commande dans la table commande via
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


public class CommandeCreerOuModifier extends Vue {
    /**
     * numero de version pour classe serialisable
     * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
     */
    private static final long serialVersionUID = 1L;

    /**
     * choix du client de la commande
     */
    private JComboBox<Client> comboBoxClient;

    /**
     * check box de la mise à jour de la date de la commande
     */
    private JCheckBox checkBoxUpdateDate;

    /**
     * label client
     */
    private JLabel labelClient;

    /**
     * label date
     */
    private JLabel labelDate;

    /**
     * bouton d'envoi de création de commande
     */
    public final JButton boutonValider = new JButton("Valider");

    /**
     * bouton d'ajout d'article
     */
    public final JButton boutonAjout = new JButton("Ajouter un article");

    /**
     * bouton d'envoi de modification de commande
     */
    public final JButton boutonValiderModification = new JButton("Modifier");

    /**
     * bouton d'annulation de modification/création de commande
     */
    public final JButton boutonAnnulerModification = new JButton("Annuler");

    /**
     * Sauvegarde la commande sujete à une modification si il y en a une en cours
     */
    private Commande commande;

    /**
     * Zone de texte pour afficher les articles
     */
    private JPanel pan;

    /**
     * Liste des boutons associés aux articles pour leur suppression
     */
    private List<JButton> listeBoutonsSupprimerArticles;

    /**
     * Fenetre d'ajout d'article à la commande
     */
    private AjouterArticle ajouterArticle;

    /**
     * Constructeur
     * Définit la fenêtre et ses composants - affiche la fenêtre
     * Si commande est null, on va créer un nouvel commande, sinon on modifie celui passé en paramètre
     */
    public CommandeCreerOuModifier(Commande commande, Client[] clients) {
        this.commande = commande;
        listeBoutonsSupprimerArticles = new ArrayList<>();

        //choix du Layout pour ce conteneur
        //il permet de gérer la position des éléments
        //il autorisera un retaillage de la fenêtre en conservant la présentation
        //BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

        //instantiation des  composants graphiques
        comboBoxClient = new JComboBox<>(clients);
        labelClient = new JLabel("Client :");
        labelDate = new JLabel("La date de la commande sera la date au moment de validation.");
        checkBoxUpdateDate = new JCheckBox("Mettre à jour la date de la commande");
        checkBoxUpdateDate.setOpaque(false);

        //ajout des composants sur le container
        add(labelClient);
        //introduire une espace constant entre le label et le champ texte
        add(Box.createRigidArea(new Dimension(0,5)));
        add(comboBoxClient);
        //introduire une espace constant entre le champ texte et le composant suivant
        add(Box.createRigidArea(new Dimension(0,10)));

        if (commande == null)
            add(labelDate);
        else {
            add(checkBoxUpdateDate);
        }
        add(Box.createRigidArea(new Dimension(0,10)));

        add(boutonAjout);

        pan = new JPanel();

        JScrollPane zoneDefilement = new JScrollPane(pan);
        zoneDefilement.setPreferredSize(new Dimension(500, 200));

        add(zoneDefilement);

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setOpaque(false);
        if (commande == null)
            conteneurBoutons.add(boutonValider);
        else
            conteneurBoutons.add(boutonValiderModification);


        add(conteneurBoutons);
        conteneurBoutons.add(boutonAnnulerModification);
        add(Box.createRigidArea(new Dimension(0,5)));

        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        if (commande == null) {
            this.commande = new Commande();
        } else {
        }
    }

    /**
     * Affiche la liste des articles de la commande et leur quantité ainsi qu'un bouton pour les supprimer
     */
    public void afficherListeArticles(ActionListener listener) {
        pan.removeAll();
        listeBoutonsSupprimerArticles.clear();

        if (commande == null || commande.getArticles().isEmpty()) {
            pan.setLayout(new GridLayout(1,1));
            pan.add(creerLabelListeArticles("Il n'y a aucun article dans la commande"));
            return;
        }

        pan.setLayout(new GridLayout(commande.getArticles().size()+1,1));
        pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        // créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
        pan.add(creerLabelListeArticles("Article"));
        pan.add(creerLabelListeArticles("Quantite"));
        pan.add(creerLabelListeArticles("Supprimer"));

        for(Map.Entry<Article, Integer> article : commande.getArticles().entrySet()) {
            pan.add(creerLabelListeArticles((article.getKey().getDesignation())));
            pan.add(creerLabelListeArticles(Integer.toString(article.getValue())));

            JPanel conteneurActions = new JPanel();
            conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JButton boutonSuppr = new JButton("Supprimer");
            listeBoutonsSupprimerArticles.add(boutonSuppr);
            conteneurActions.add(boutonSuppr);
            pan.add(conteneurActions);
        }

        for (JButton bouton : listeBoutonsSupprimerArticles) {
            bouton.addActionListener(listener);
        }
    }

    /**
     * Affiche la fenêtre d'ajout d'article à la commande
     * @param listener écouteurs pour les boutons
     * @param articles liste des articles disponibles
     */
    public void afficherFenetreAjouterArticle(ActionListener listener, Article[] articles) {
        //ajouterArticle = new AjouterArticle(JF, commande, articles);
       // ajouterArticle.ajouterListener(listener);
    }

    /**
     * Créé un JLabel avec le texte passé en paramètre avec une bordure noire et le texte aligné au centre
     * @param texte texte qui sera placé dans le JLabel
     * @return JLabel créé
     */
    public JLabel creerLabelListeArticles(String texte) {
        JLabel label = new JLabel(texte);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    /**
     * Ajoute des écouteurs sur les boutons du panel
     */
    public void ajouterListener(ActionListener listener) {
        boutonValider.addActionListener(listener);
        boutonAjout.addActionListener(listener);
        boutonAnnulerModification.addActionListener(listener);
        boutonValiderModification.addActionListener(listener);
    }

    /**
     * Valide l'ajout d'un article à la commande et ferme la fenêtre d'ajout d'article
     */
    public void validerAjoutArticle() {
        ajouterArticle.validerAjout();
        ajouterArticle = null;
    }

    /**
     * Valide la modification de la commande
     * @return la commande à modifier dans la base
     */
    public Commande validerModification() {
        Client client = (Client)comboBoxClient.getSelectedItem();
        commande.setClient(client);
        return commande;
    }

    /**
     * Valide la création de la commande
     * @return la commande à insérer dans la base
     */
    public Commande validerCreation() {
        Client client = (Client)comboBoxClient.getSelectedItem();
        commande.setClient(client);
        return commande;
    }

    /**
     * Récupère la fenêtre d'ajout d'article à la commande
     * @return fenêtre d'ajout d'article
     */
    public AjouterArticle getAjouterArticle() {
        return ajouterArticle;
    }

    /**
     * Annuler l'ajout d'un article et ferme la fenêtre d'ajout d'article
     */
    public void annulerAjoutArticle() {
        ajouterArticle = null;
    }

    /**
     * Récupère la liste des boutons de suppression des articles de la commande
     * @return liste des boutons de suppression
     */
    public List<JButton> getListeBoutonsSupprimerArticles() {
        return listeBoutonsSupprimerArticles;
    }

    /**
     * Supprime un article de la commande en fonction de son index et met à jour la liste des articles
     * @param index index de l'article à supprimer
     * @param listener écouteurs à placer sur la liste des articles de la commande une fois mise à jour
     */
    public void supprimerArticleCommande(int index, ActionListener listener) {
        Article article = null;
        for(Map.Entry<Article, Integer> art : commande.getArticles().entrySet()) {
            if (index == 0) {
                article = art.getKey();
                break;
            }
            index--;
        }
        commande.supprimerArticle(article);
        afficherListeArticles(listener);
    }

    /**
     * Vérifie que le checkbox de mise à jour de la date pour une modification d'article est activé ou non
     * @return modification de date activée ou non
     */
    public boolean changementDateActive() {
        return checkBoxUpdateDate.isSelected();
    }

	@Override
	public void afficherListe(List liste, ActionListener listener) {
		// TODO Auto-generated method stub
		
	}
}
