package article;

import principale.Model;

import java.util.List;

public class ArticleModel extends Model {

    /**
     * Gestionnaire des articles de la base de données
     */
    private final ArticleDAO articleDAO;
    /**
     * UtilisateurDAO de gestion des articles
     */
    public ArticleModel() {
        articleDAO = new ArticleDAO();
    }

    /**
     * Ajoute un article dans la base de données avec la désignation et le prix passés en paramètre
     * @param objet article à ajouter à la base
     * @return le succès de l'opération
     */
    @Override
    public int ajouter(Object objet) {
        Article article = (Article) objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.ajouter(article);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        return retour;
    }

    /**
     * Modifie l'article passé en paramètre dans la base de données
     * @param objet article à modifier
     * @return le succès de l'opération
     */
    @Override
    public int modifier(Object objet) {
        Article article = (Article) objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.modifier(article);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        return retour;
    }

    /**
     * Supprime l'article passé en paramètre dans la base de données
     * @param objet article à supprimer
     * @return le succès de l'opération
     */
    @Override
    public int supprimer(Object objet) {
        Article article = (Article) objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = articleDAO.supprimer(article);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        return retour;
    }

    /**
     * Recherche les articles dont le nom contient la désignation en paramètre
     * @param designation texte contenu dans les articles à chercher
     * @return les articles correspondants
     */
    @Override
    public List<Article> chercher(String designation) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        List<Article> listeArticles = articleDAO.rechercherArticles(designation);
        // affichage du nombre de lignes récupérées dans la bdd pour vérification
        System.out.println(listeArticles.size() + " lignes récupérées");

        return listeArticles;
    }

    /**
     * Renvoie la liste des articles de la table associée
     * @return liste des articles
     */
    @Override
    public List<Article> recupererListe() {
        return articleDAO.getListeArticles();
    }
}

