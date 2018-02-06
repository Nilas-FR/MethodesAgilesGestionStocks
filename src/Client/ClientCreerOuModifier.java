package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


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


public class ClientCreerOuModifier extends JPanel {
    /**
     * numero de version pour classe serialisable
     * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
     */
    private static final long serialVersionUID = 1L;

    /**
     * zone de texte pour le  nom
     */
    private JTextField textFieldNom;

    /**
     * zone de texte pour le  prénom
     */
    private JTextField textFieldPrenom;

    /**
     * zone de texte pour l'adresse
     */
    private JTextField textFieldAdresse;

    /**
     * zone de texte pour le téléphone
     */
    private JTextField textFieldTelephone;

    /**
     * zone de texte pour l'email
     */
    private JTextField textFieldEmail;

    /**
     * label reference
     */
    private JLabel labelReference;

    /**
     * label nom
     */
    private JLabel labelNom;

    /**
     * label prénom
     */
    private JLabel labelPrenom;

    /**
     * label quantité en stock
     */
    private JLabel labelAdresse;

    /**
     * label téléphone
     */
    private JLabel labelTelephone;

    /**
     * label email
     */
    private JLabel labelEmail;

    /**
     * bouton d'envoi d'ajout du client
     */
    public final JButton boutonAjouter = new JButton("Ajouter");

    /**
     * bouton d'envoi de modification du client
     */
    public final JButton boutonValiderModification = new JButton("Modifier");

    /**
     * bouton d'annulation de création/modification du client
     */
    public final JButton boutonAnnuler = new JButton("Annuler");

    /**
     * Panel contenant les bouton pour valider ou annuler une modification du client
     */
    private JPanel conteneurBoutons;


    /**
     * Sauvegarde la référence du client sujet à une modification si il y en a une en cours
     */
    private Client client;

    /**
     * Constructeur
     * Définit la fenêtre et ses composants - affiche la fenêtre
     * Si client est null, on va créer un nouveau client, sinon on modifie celui passé en paramètre
     */
    public ClientCreerOuModifier(Client client, ActionListener listener) {
        this.client = client;

        //choix du Layout pour ce conteneur
        //il permet de gérer la position des éléments
        //il autorisera un retaillage de la fenêtre en conservant la présentation
        //BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);

        //instantiation des  composants graphiques
        textFieldNom =new JTextField();
        textFieldPrenom =new JTextField();
        textFieldTelephone =new JTextField();
        textFieldAdresse =new JTextField();
        textFieldEmail =new JTextField();
        labelReference=new JLabel("L'Identifiant sera généré par la base de données");
        labelNom =new JLabel("Nom :");
        labelPrenom =new JLabel("Prénom :");
        labelTelephone =new JLabel("Téléphone :");
        labelAdresse =new JLabel("Adresse :");
        labelEmail =new JLabel("Email :");

        //ajout des composants sur le container
        add(labelNom);
        //introduire une espace constant entre le label et le champ texte
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldNom);
        //introduire une espace constant entre le champ texte et le composant suivant
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelPrenom);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldPrenom);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelTelephone);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldTelephone);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelAdresse);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldAdresse);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelEmail);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(textFieldEmail);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(labelReference);
        add(Box.createRigidArea(new Dimension(0,5)));

        conteneurBoutons = new JPanel();
        conteneurBoutons.setOpaque(false);
        if (client == null)
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
     * Ajoute des écouteurs sur les boutons du panel
     */
    private void ajouterListener(ActionListener listener) {
        boutonAjouter.addActionListener(listener);
        boutonAnnuler.addActionListener(listener);
        boutonValiderModification.addActionListener(listener);
    }

    /**
     * Rempli automatiquement les champs text field si un client est à modifier
     */
    private void remplirChampsModification() {
        textFieldNom.setText(client.getNom());
        textFieldPrenom.setText(client.getPrenom());
        textFieldAdresse.setText(client.getAdresse());
        textFieldTelephone.setText(client.getTelephone());
        textFieldEmail.setText(client.getEmail());
    }

    /**
     * Renvoie le client à modifier avec les nouveaux paramètres
     * @return client sujet à la modification
     */
    public Client validerModification() {
        client.setNom(textFieldNom.getText());
        client.setPrenom(textFieldPrenom.getText());
        client.setAdresse(textFieldAdresse.getText());
        client.setTelephone(textFieldTelephone.getText());
        client.setEmail(textFieldEmail.getText());

        return client;
    }

    /**
     * Renvoie le nouveau client avec ses paramètres
     * @return client à ajouter
     */
    public Client validerCreation() {
        client = new Client(textFieldNom.getText(),
                textFieldPrenom.getText(),
                textFieldAdresse.getText(),
                textFieldTelephone.getText(),
                textFieldEmail.getText());

        return client;
    }
}
