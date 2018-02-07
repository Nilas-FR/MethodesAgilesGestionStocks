package commande;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import article.Article;
import client.Client;
import principale.FenetreCreationModification;

public class CommandeCreerOuModifier extends FenetreCreationModification {

    /**
     * choix du client de la commande
     */
    private JComboBox<Client> comboBoxClient;

    /**
     * check box de la mise � jour de la date de la commande
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
     * bouton d'ajout d'article
     */
    public final JButton boutonAjouterArticle = new JButton("Ajouter un article");

    /**
     * Sauvegarde la commande sujete � une modification si il y en a une en cours
     */
    private Commande commande;

    /**
     * Zone de texte pour afficher les articles
     */
    private JPanel pan;

    /**
     * Liste des boutons associ�s aux articles pour leur suppression
     */
    private List<JButton> listeBoutonsSupprimerArticles;

    /**
     * Fenetre d'ajout d'article � la commande
     */
    private AjouterArticle ajouterArticle;

    /**
     * Constructeur
     * D�finit la fen�tre et ses composants - affiche la fen�tre
     * Si commande est null, on va cr�er un nouvel commande, sinon on modifie celui pass� en param�tre
     */
    public CommandeCreerOuModifier(Commande commande, Client[] clients, ActionListener listener) {
        this.commande = commande;
        listeBoutonsSupprimerArticles = new ArrayList<>();

        //choix du Layout pour ce conteneur
        //il permet de g�rer la position des �l�ments
        //il autorisera un retaillage de la fen�tre en conservant la pr�sentation
        //BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

        //instantiation des  composants graphiques
        comboBoxClient = new JComboBox<>(clients);
        labelClient = new JLabel("Client :");
        labelDate = new JLabel("La date de la commande sera la date au moment de validation.");
        checkBoxUpdateDate = new JCheckBox("Mettre � jour la date de la commande");
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

        add(boutonAjouterArticle);

        pan = new JPanel();

        JScrollPane zoneDefilement = new JScrollPane(pan);
        zoneDefilement.setPreferredSize(new Dimension(500, 200));

        add(zoneDefilement);

        JPanel conteneurBoutons = new JPanel();
        conteneurBoutons.setOpaque(false);
        if (commande == null)
            conteneurBoutons.add(boutonAjouter);
        else
            conteneurBoutons.add(boutonValiderModification);


        add(conteneurBoutons);
        conteneurBoutons.add(boutonAnnuler);
        add(Box.createRigidArea(new Dimension(0,5)));

        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        if (commande == null) {
            this.commande = new Commande();
        }

        ajouterListener(listener);
        boutonAjouterArticle.addActionListener(listener);

        afficherListeArticles(listener);
    }

    /**
     * Affiche la liste des articles de la commande et leur quantit� ainsi qu'un bouton pour les supprimer
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

        // cr�� tous les labels avec � chaque fois une lineBorder et un texte align� au centre
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
     * Cr�� un JLabel avec le texte pass� en param�tre avec une bordure noire et le texte align� au centre
     * @param texte texte qui sera plac� dans le JLabel
     * @return JLabel cr��
     */
    private JLabel creerLabelListeArticles(String texte) {
        JLabel label = new JLabel(texte);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    /**
     *
     */
    @Override
    protected void remplirChampsModification() { }

    /**
     * Valide la modification de la commande
     * @return la commande � modifier dans la base
     */
    @Override
    public Commande validerModification() {
        Client client = (Client)comboBoxClient.getSelectedItem();
        commande.setClient(client);
        return commande;
    }

    /**
     * Valide la cr�ation de la commande
     * @return la commande � ins�rer dans la base
     */
    @Override
    public Commande validerCreation() {
        Client client = (Client)comboBoxClient.getSelectedItem();
        commande.setClient(client);
        return commande;
    }

    /**
     * V�rifie que le checkbox de mise � jour de la date pour une modification d'article est activ� ou non
     * @return modification de date activ�e ou non
     */
    public boolean changementDateActive() {
        return checkBoxUpdateDate.isSelected();
    }

    public Commande getCommande() {
        return commande;
    }
}
