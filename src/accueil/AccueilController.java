package accueil;

import principale.Controller;
import principale.PrincipaleController;

import java.awt.event.ActionEvent;

public class AccueilController extends Controller {

    private AccueilVue vue;
    private AccueilModel model;
    /**
     * Cr�� le controleur des articles
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
     * Cherche les actions � effectuer en fonction du bouton qui a �t� d�clench�
     * @param e bouton qui a d�clench� l'�v�nement
     */
    public void actionPerformed(ActionEvent e) {
        // Aucun �v�nement � d�clencher dans la fen�tre d'accueil
    }

    /**
     * Mets � jour tous les champs des statistiques
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
     * Met � jour le nombre d'articles en stock
     */
    private void updateChampNombreArticlesStock() {
        vue.nombreArticlesStock(model.nombreArticlesStock());
    }

    /**
     * Met � jour la valeur totale des articles en stock
     */
    private void updateChampValeurStock() {
        vue.valeurStock(model.valeurStock());
    }

    /**
     * Met � jour le nombre d'articles diff�rents propos�s
     */
    private void updateChampNombreArticles() {
        vue.nombreArticles(model.nombreArticles());
    }

    /**
     * Met � jour le nombre de clients
     */
    private void updateChampNombreClients() {
        vue.nombreClients(model.nombreClients());
    }

    /**
     * Met � jour le nombre de fournisseurs
     */
    private void updateChampNombreFournisseurs() {
        vue.nombreFournisseurs(model.nombreFournisseurs());
    }

    /**
     * Met � jour le nombre de commandes
     */
    private void updateChampNombreCommandes() {
        vue.nombreCommandes(model.nombreCommandes());
    }

    /**
     * Met � jour le nombre de clients
     */
    private void updateChampMeilleurClient() {
        vue.meilleurClient(model.meilleurClient());
    }

    /**
     * Met � jour l'article le mieux vendu
     */
    private void updateChampArticleMieuxVendu() {
        vue.articleMieuxVendu(model.articleMieuxVendu());
    }

    /**
     * Met � jour l'article le mieux vendu
     */
    private void updateChampArticleMeileurGain() {
        vue.articleMeilleurGain(model.articleMeilleurGain());
    }

    /**
     * Met � jour le nombre d'articles vendus
     */
    private void updateNombreArticlesVendus() {
        vue.nombreArticlesVendus(model.nombreArticlesVendus());
    }

    /**
     * Met � jour le nombre d'articles vendus
     */
    private void updateValeurArticlesVendus() {
        vue.valeurArticlesVendus(model.valeurArticlesVendus());
    }
}


