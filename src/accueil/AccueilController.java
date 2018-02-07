package accueil;

import principale.Controller;
import principale.PrincipaleController;

import java.awt.event.ActionEvent;

public class AccueilController extends Controller {

    private AccueilVue vue;
    private AccueilModel model;
    /**
     * Créé le controleur des articles
     * @param PC controleur principal
     */
    public AccueilController(PrincipaleController PC) {
    	super(PC);
    	this.Vue = new AccueilVue(this);
    	vue = (AccueilVue) Vue;
    	Model = new AccueilModel();
        model = (AccueilModel) Model;
    }

    /**
     * Cherche les actions è  effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évè¨nement
     */
    public void actionPerformed(ActionEvent e) {
        // Aucun évè¨nement è  déclencher dans la fenêtre d'accueil
    }

    /**
     * Mets è  jour tous les champs des statistiques
     */
    public void updateTousLesChamps() {
        updateChampNombreArticlesStock();
        updateChampValeurStock();
        updateChampNombreArticles();
        updateChampNombreClients();
        updateChampNombreFournisseurs();
        updateChampNombreCommandes();
        updateChampMeilleurClient();
        updateChampArticleMieuxVendu();
        updateNombreArticlesVendus();
        updateValeurArticlesVendus();
        updateChampArticleMeileurGain();
    }

    /**
     * Met è  jour le nombre d'articles en stock
     */
    private void updateChampNombreArticlesStock() {
        vue.nombreArticlesStock(model.nombreArticlesStock());
    }

    /**
     * Met è  jour la valeur totale des articles en stock
     */
    private void updateChampValeurStock() {
        vue.valeurStock(model.valeurStock());
    }

    /**
     * Met è  jour le nombre d'articles différents proposés
     */
    private void updateChampNombreArticles() {
        vue.nombreArticles(model.nombreArticles());
    }

    /**
     * Met è  jour le nombre de clients
     */
    private void updateChampNombreClients() {
        vue.nombreClients(model.nombreClients());
    }

    /**
     * Met è  jour le nombre de fournisseurs
     */
    private void updateChampNombreFournisseurs() {
        vue.nombreFournisseurs(model.nombreFournisseurs());
    }

    /**
     * Met è  jour le nombre de commandes
     */
    private void updateChampNombreCommandes() {
        vue.nombreCommandes(model.nombreCommandes());
    }

    /**
     * Met è  jour le nombre de clients
     */
    private void updateChampMeilleurClient() {
        vue.meilleurClient(model.meilleurClient());
    }

    /**
     * Met è  jour l'article le mieux vendu
     */
    private void updateChampArticleMieuxVendu() {
        vue.articleMieuxVendu(model.articleMieuxVendu());
    }

    /**
     * Met è  jour l'article le mieux vendu
     */
    private void updateChampArticleMeileurGain() {
        vue.articleMeilleurGain(model.articleMeilleurGain());
    }

    /**
     * Met è  jour le nombre d'articles vendus
     */
    private void updateNombreArticlesVendus() {
        vue.nombreArticlesVendus(model.nombreArticlesVendus());
    }

    /**
     * Met è  jour le nombre d'articles vendus
     */
    private void updateValeurArticlesVendus() {
        vue.valeurArticlesVendus(model.valeurArticlesVendus());
    }
}


