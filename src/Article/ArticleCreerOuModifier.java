package article;

import principale.FenetreCreationModification;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;


public class ArticleCreerOuModifier extends FenetreCreationModification {

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
     * label quantit� en stock
     */
    private JLabel labelQtestock;

    /**
     * Sauvegarde la r�f�rence de l'article sujet � une modification si il y en a une en cours
     */
    private Article article;

    /**
     * Constructeur
     * D�finit la fen�tre et ses composants - affiche la fen�tre
     * Si article est null, on va cr�er un nouvel article, sinon on modifie celui pass� en param�tre
     */
    public ArticleCreerOuModifier(Article article, ActionListener listener) {
        this.article = article;

        //choix du Layout pour ce conteneur
        //il permet de g�rer la position des �l�ments
        //il autorisera un retaillage de la fen�tre en conservant la pr�sentation
        //BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);


        //instantiation des  composants graphiques
        textFieldDesignation=new JTextField();
        textFieldPuHt=new JTextField();
        textFieldQteStock=new JTextField();
        labelReference=new JLabel("La R�f�rence sera g�n�r�e par la base de donn�es");
        labelDesignation=new JLabel("D�signation :");
        labelPu_ht=new JLabel("Prix unitaire HT :");
        labelQtestock=new JLabel("Quantit� :");

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

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setOpaque(false);
        if (article == null)
            conteneurBoutons.add(boutonAjouter);
        else {
            conteneurBoutons.add(boutonValiderModification);
            remplirChampsModification();
        }
        add(conteneurBoutons);
        conteneurBoutons.add(boutonAnnuler);
        add(Box.createRigidArea(new Dimension(0,5)));


        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        ajouterListener(listener);
    }

    /**
     * Rempli automatiquement les champs text field si un article est � modifier
     */
    @Override
    protected void remplirChampsModification() {
        textFieldPuHt.setText(Double.toString(article.getPuHt()));
        textFieldDesignation.setText(article.getDesignation());
        textFieldQteStock.setText(Integer.toString(article.getQteStock()));
    }

    /**
     * Renvoie l'article � modifier avec les nouveaux param�tres
     * @return article sujet � la modification
     */
    @Override
    public Article validerModification() {
        article.setDesignation(textFieldDesignation.getText());
        article.setPuHt(Double.parseDouble(textFieldPuHt.getText()));
        article.setQteStock(Integer.parseInt(textFieldQteStock.getText()));

        return article;
    }

    /**
     * Renvoie le nouvel article avec ses param�tres
     * @return article � ajouter
     */
    @Override
    public Article validerCreation() {
        article = new Article(textFieldDesignation.getText(),
                Double.parseDouble(textFieldPuHt.getText()),
                Integer.parseInt(textFieldQteStock.getText()));

        return article;
    }
}
