package Article;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;


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


public class ArticleCreerOuModifier extends JPanel {
    /**
     * numero de version pour classe serialisable
     * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
     */
    private static final long serialVersionUID = 1L;

    /**
     * zone de texte pour le champ designation
     */
    private JTextField textFieldDesignation;

    /**
     * zone de texte pour le prix unitaire hors taxe
     */
    private JTextField textFieldPuHt;

    /**
     * zone de texte pour la quantite en stock
     */
    private JTextField textFieldQteStock;

    /**
     * label reference
     */
    private JLabel labelReference;

    /**
     * label designation
     */
    private JLabel labelDesignation;

    /**
     * label prix unitaire hors taxe
     */
    private JLabel labelPu_ht;

    /**
     * label quantité en stock
     */
    private JLabel labelQtestock;

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonAjouter = new JButton("Ajouter");

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonValiderModification = new JButton("Modifier");

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonAnnulerModification = new JButton("Annuler");

    /**
     * Panel contenant les bouton pour valider ou annuler une modification d'article
     */
    private JPanel conteneurBoutons;

    /**
     * Vue.Vue de l'application
     */
    private JFrame JF;

    /**
     * Sauvegarde la référence de l'article sujet à une modification si il y en a une en cours
     */
    private Article article;

    /**
     * Constructeur
     * Définit la fenêtre et ses composants - affiche la fenêtre
     * Si article est null, on va créer un nouvel article, sinon on modifie celui passé en paramètre
     */
    public ArticleCreerOuModifier(JFrame JF, Article article) {
        this.JF = JF;
        this.article = article;

        //on fixe le titre de la fenêtre
        JF.setTitle("Article");

        //création du conteneur
        //containerPanel = new JPanel();

        //choix du Layout pour ce conteneur
        //il permet de gérer la position des éléments
        //il autorisera un retaillage de la fenêtre en conservant la présentation
        //BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


        //instantiation des  composants graphiques
        textFieldDesignation=new JTextField();
        textFieldPuHt=new JTextField();
        textFieldQteStock=new JTextField();
        labelReference=new JLabel("La Référence sera générée par la base de données");
        labelDesignation=new JLabel("Désignation :");
        labelPu_ht=new JLabel("Prix unitaire HT :");
        labelQtestock=new JLabel("Quantité :");

        //ajout des composants sur le container
        add(labelDesignation);
        //introduire une espace constant entre le label et le champ texte
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldDesignation);
        //introduire une espace constant entre le champ texte et le composant suivant
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelPu_ht);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldPuHt);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelQtestock);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldQteStock);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelReference);
        add(Box.createRigidArea(new Dimension(0,5)));

        conteneurBoutons = new JPanel();
        conteneurBoutons.setOpaque(false);
        if (article == null)
            conteneurBoutons.add(boutonAjouter);
        else {
            conteneurBoutons.add(boutonValiderModification);
            remplirChampsModification();
        }
        add(conteneurBoutons);
        conteneurBoutons.add(boutonAnnulerModification);
        add(Box.createRigidArea(new Dimension(0,5)));


        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JF.setContentPane(this);

        JF.pack();
    }

    /**
     * Ajoute des écouteurs sur les boutons du panel
     */
    public void ajouterListener(ActionListener listener) {
        boutonAjouter.addActionListener(listener);
        boutonAnnulerModification.addActionListener(listener);
        boutonValiderModification.addActionListener(listener);
    }

    /**
     * Ajoute des écouteurs sur les boutons de la liste des articles
     */
    public JTextField getTextFieldDesignation() {
        return textFieldDesignation;
    }

    /**
     * Ajoute des écouteurs sur les boutons de la liste des articles
     */
    public JTextField getTextFieldPuHt() {
        return textFieldPuHt;
    }

    public void remplirChampsModification() {
        textFieldPuHt.setText(Double.toString(article.getPuHt()));
        textFieldDesignation.setText(article.getDesignation());
        textFieldQteStock.setText(Integer.toString(article.getQteStock()));
    }

    public Article validerModification() {
        article.setDesignation(textFieldDesignation.getText());
        article.setPuHt(Double.parseDouble(textFieldPuHt.getText()));
        article.setQteStock(Integer.parseInt(textFieldQteStock.getText()));

        return article;
    }

    public Article validerCreation() {
        article = new Article(textFieldDesignation.getText(),
                Double.parseDouble(textFieldPuHt.getText()),
                Integer.parseInt(textFieldQteStock.getText()));

        return article;
    }
}
