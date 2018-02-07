package commande;

import article.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AjouterArticle extends JPanel {
    /**
     * numero de version pour classe serialisable
     * Permet d'eviter le warning "The serializable class CommandeVue does not declare a static final serialVersionUID field of type long"
     */
    private static final long serialVersionUID = 1L;

    /**
     * choix de l'article à  ajouter
     */
    private JComboBox<Article> comboBoxArticle;

    /**
     * choix de la quantité d'article à  ajouter
     */
    private JComboBox<Integer> comboBoxQuantite;

    /**
     * bouton de validation d'ajout d'article
     */
    public final JButton boutonValider = new JButton("Ajouter");

    /**
     * bouton d'annulation d'ajout d'article
     */
    public final JButton boutonAnnuler = new JButton("Annuler");

    /**
     * Commande à  gérer
     */
    private Commande commande;

    /**
     * Constructeur
     * Définit la fenêtre et ses composants - affiche la fenêtre
     */
    public AjouterArticle(Commande commande, Article[] articles, ActionListener listener) {
        this.commande = commande;

        //choix du Layout pour ce conteneur
        //il permet de gérer la position des éléments
        //il autorisera un retaillage de la fenêtre en conservant la présentation
        //BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


        //instantiation des  composants graphiques
        comboBoxArticle = new JComboBox<>(articles);
        comboBoxQuantite = new JComboBox<>();
        updateBomboBoxQuantite();
        comboBoxArticle.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                updateBomboBoxQuantite();
            }
        });

        JLabel labelArticle = new JLabel("Article :");
        JLabel labelQuantite = new JLabel("Quantite :");

        //ajout des composants sur le container
        add(labelArticle);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(comboBoxArticle);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelQuantite);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(comboBoxQuantite);
        add(Box.createRigidArea(new Dimension(0,10)));

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.add(boutonValider);
        conteneurBoutons.add(boutonAnnuler);

        add(conteneurBoutons);

        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        ajouterListener(listener);
    }

    /**
     * Met à  jour le choix des quantités pour l'article sélectionné
     */
    private void updateBomboBoxQuantite() {
        Article article = (Article)comboBoxArticle.getSelectedItem();
        DefaultComboBoxModel modelComboBox = (DefaultComboBoxModel) comboBoxQuantite.getModel();
        modelComboBox.removeAllElements();
        for (int i = 1; i <= article.getQteStock(); i++)
            modelComboBox.addElement(i);
        comboBoxQuantite.setModel(modelComboBox);
    }

    /**
     * Ajoute des écouteurs sur les boutons
     */
    private void ajouterListener(ActionListener listener) {
        boutonValider.addActionListener(listener);
        boutonAnnuler.addActionListener(listener);
        comboBoxArticle.addActionListener(listener);
        comboBoxQuantite.addActionListener(listener);
    }

    /**
     * Valide l'ajout de l'article avec la quantité désirée
     */
    public void validerAjout() {
        Article article = (Article)comboBoxArticle.getSelectedItem();
        int quantite = (int)comboBoxQuantite.getSelectedItem();
        commande.ajouterArticle(article, quantite);
    }
}
