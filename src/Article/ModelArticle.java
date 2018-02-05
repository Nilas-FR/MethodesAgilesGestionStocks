package Article;

import java.util.List;

public class ModelArticle{

    /**
     * Gestionnaire des articles de la base de données
     */
    private final ArticleDAO articleDAO;

    /**
     * Liste des articles
     */
    private List<Article> listeArticles;

    /**
     * UtilisateurDAO de gestion des articles
     */
    public ModelArticle() {
        articleDAO = new ArticleDAO();

        actualiserListeArticles();
    }

    /**
     * Ajoute un article dans la base de données avec la désignation et le prix passés en paramètre
     * @param article article à ajouter à la base
     * @return le succès de l'opération
     */
    public int ajouterArticle(Article article) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.ajouter(article);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        actualiserListeArticles();
        return retour;
    }

    /**
     * Modifie l'article passé en paramètre dans la base de données
     * @param article article à modifier
     * @return le succès de l'opération
     */
    public int modifierArticle(Article article) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.modifier(article);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        actualiserListeArticles();
        return retour;
    }

    /**
     * Supprime l'article passé en paramètre dans la base de données
     * @param article article à supprimer
     * @return le succès de l'opération
     */
    public int supprimerArticle(Article article) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.supprimer(article);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        actualiserListeArticles();
        return retour;
    }

    /**
     * Recherche les articles dont le nom contient la désignation en paramètre
     * @param designation texte contenu dans les articles à chercher
     * @return les articles correspondants
     */
    public List<Article> chercher(String designation) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        listeArticles = articleDAO.rechercherArticles(designation);
        // affichage du nombre de lignes récupérées dans la bdd pour vérification
        System.out.println(listeArticles.size() + " lignes récupérées");

        return listeArticles;
    }

    /**
     * Renvoie la liste des articles de la table associée
     * @return liste des articles
     */
    public List<Article> recupererListeArticles() {
        return listeArticles;
    }

    /**
     * Permet d'actualiser la liste des articles, à appeler à chaque changement dans la table des articles
     */
    private void actualiserListeArticles() {
        listeArticles = articleDAO.getListeArticles();
    }
}

