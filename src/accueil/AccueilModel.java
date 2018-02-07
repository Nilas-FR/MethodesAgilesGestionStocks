package accueil;

import principale.Model;

import java.util.List;

public class AccueilModel extends Model {

    /**
     * Gestionnaire de l'accueil (DAO) de la base de données
     */
    private final AccueilDAO accueilDAO;

    /**
     * Constructeur, initialise le DAO
     */
    public AccueilModel() {
        accueilDAO = new AccueilDAO();
    }


    public String nombreArticlesStock() {
        return "Quantité d'articles en stock : " + accueilDAO.getNombreArticleStock();
    }

    public String valeurStock() {
        return "Valeur totale des articles en stock : " + accueilDAO.getValeurStock() + "€";
    }

    public String nombreArticles() {
        return "Nombre d'articles différents : " + accueilDAO.getNombreArticle();
    }

    public String nombreClients() {
        return "Nombre de clients différents : " + accueilDAO.getNombreClient();
    }

    public String nombreFournisseurs() {
        return "Nombre de fournisseurs différents : " + accueilDAO.getNombreFournisseur();
    }

    public String nombreCommandes() {
        return "Nombre de commandes différents : " + accueilDAO.getNombreCommande();
    }

    public String meilleurClient() {
        return "Meilleur client : " + accueilDAO.getMeilleurClientNomPrenom() + " (" + accueilDAO.getMeilleurClientSommeHT() + " € de commandes)";
    }

    public String articleMieuxVendu() {
        return "Article le mieux vendu : " + accueilDAO.getMeilleurArticleNom() + " (" + accueilDAO.getMeilleurArticleQuantite() + " fois)";
    }

    public String articleMeilleurGain() {
        return "Article ayant rapporté le plus : " + accueilDAO.getMeilleurGainArticleNom() + " (" + accueilDAO.getMeilleurArticleGainSomme() + " €)";
    }

    public String nombreArticlesVendus() {
        return "Nombre d'articles vendus au total : " + accueilDAO.getNombreArticlesVendus();
    }

    public String valeurArticlesVendus() {
        return "Valeur totale des articles vendus : " + accueilDAO.getValeurArticlesVendus() + "€";
    }

    /**
     * Fonctions inutile pour la page d'accueil
     * @param object objet à  ajouter à  la base
     * @return 0
     */
    @Override
    public int ajouter(Object object) {
        return 0;
    }

    @Override
    public int modifier(Object object) {
        return 0;
    }

    @Override
    public int supprimer(Object object) {
        return 0;
    }

    @Override
    public List chercher(String pattern) {
        return null;
    }

    @Override
    public List recupererListe() {
        return null;
    }


}

