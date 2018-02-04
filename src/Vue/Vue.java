package Vue;

import Article.ArticleFenetre;
import Commande.CommandeFenetre;
import login.LoginFenetre;

import javax.swing.*;
import java.awt.event.*;


public class Vue extends JFrame {

    /**
     * Vue de gestion des articles
     */
    private ArticleFenetre vueArticle;

    /**
     * Vue de gestion des commandes
     */
    private CommandeFenetre vueCommande;

    /**
     * Vue de gestion des articles
     */
    private LoginFenetre vueLogin;

    /**
     * Menus affichant la fenetre de gestion des articles
     */
    public final JMenu menuArticle = new JMenu("Produit");

    /**
     * Menus affichant la fenetre de gestion des commandes
     */
    public final JMenu menuCommandes = new JMenu("Commandes");

    /**
     * Menus affichant la fenetre de gestion des fournisseurs
     */
    public final JMenu menuFournisseurs = new JMenu("Fournisseurs");

    /**
     * Menus affichant la fenetre de gestion des clients
     */
    public final JMenu menuClients = new JMenu("Clients");

    /**
     * Créé et initialise la JFrame principale de l'application
     */
    public Vue() {
        super("Stocks");

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720,480);
        this.setVisible(true);

        creerMenu();

        /**
         * Initialise les différentes vues
         */
        vueArticle = new ArticleFenetre(this);
        vueCommande = new CommandeFenetre(this);
        vueLogin = new LoginFenetre(this);

        setContentPane(vueCommande);
    }

    /**
     * Créé le menu de l'application
     */
    public void creerMenu() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(menuArticle);
        menuBar.add(menuCommandes);
        menuBar.add(menuFournisseurs);
        menuBar.add(menuClients);

        setJMenuBar(menuBar);
    }

    /**
     * Place les écouteurs sur le menu
     * @param listener écouteurs à placer sur le menu
     */
    public void setMenuController(MouseListener listener) {
        menuArticle.addMouseListener(listener);
        menuCommandes.addMouseListener(listener);
        menuFournisseurs.addMouseListener(listener);
        menuClients.addMouseListener(listener);
    }

    /**
     * Place les écouteurs sur la vue de gestion des articles
     * @param listener écouteur
     */
    public void setArticleController(ActionListener listener) {
        vueArticle.ajouterListener(listener);
    }

    /**
     * Place les écouteurs sur la vue de connexion
     * @param listener écouteur
     */
    public void setLoginController(ActionListener listener) {
        vueLogin.ajouterListener(listener);
    }

    /**
     * Renvoie la vue de gestion des articles
     * @return JPanel de gestion des articles
     */
    public ArticleFenetre getVueArticles() {
        return vueArticle;
    }

    /**
     * Renvoie la vue de gestion des connexions
     * @return JPanel de gestion des connexions
     */
    public LoginFenetre getVueLogin() {
        return vueLogin;
    }

    /**
     * Renvoie la vue de gestion des commandes
     * @return JPanel de gestion des commandes
     */
    public CommandeFenetre getVueCommandes() {
        return vueCommande;
    }

    /**
     * Affiche la page d'accueil une fois que l'utilisateur s'est connecté
     */
    public void connexionValidee() {
        setContentPane(vueArticle);
        pack();
    }

    /**
     * Affiche le Panel de gestion des articles
     */
    public void afficherVueArticles() {
        System.out.println("Afficher Vue article");
        vueArticle.setVisible(true);
        setContentPane(vueArticle);
        //setContentPane(vueArticle);
        pack();
    }

    /**
     * Affiche le Panel de gestion des clients
     */
    public void afficherVueClients() {
        System.out.println("Afficher Vue Client");
        //setContentPane(vueClient);
        pack();
    }

    /**
     * Affiche le Panel de gestion des commandes
     */
    public void afficherVueCommandes() {
        System.out.println("Afficher Vue Commande");
        setContentPane(vueCommande);
        pack();
    }

    /**
     * Affiche le Panel de gestion des fournisseurs
     */
    public void afficherVueFournisseurs() {
        System.out.println("Afficher Vue Fournisseur");
        //setContentPane(vueFournisseur);
        pack();
    }

    /**
     * Affiche le Panel de connexion
     */
    public void afficherVueConnexion() {
        System.out.println("Afficher Vue Connexion");
        setContentPane(vueLogin);
        pack();
    }
}
