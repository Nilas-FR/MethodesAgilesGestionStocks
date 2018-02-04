package Commande;

import Model.Model;

import java.util.List;

public class ModelCommande extends Model {

    /**
     * Gestionnaire des articles de la base de données
     */
    private final CommandeDAO commandeDAO;

    /**
     * Liste des articles
     */
    private List<Commande> listeCommandes;

    /**
     * UtilisateurDAO de gestion des articles
     */
    public ModelCommande() {
        commandeDAO = new CommandeDAO();

        actualiserListeCommandes();
    }

    /**
     * Ajoute un commande dans la base de données avec la désignation et le prix passés en paramètre
     * @param commande commande à ajouter à la base
     * @return le succès de l'opération
     */
    public int ajouterCommande(Commande commande) {
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.ajouter(commande);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        actualiserListeCommandes();
        return retour;
    }

    /**
     * Modifie la commande passée en paramètre dans la base de données
     * @param commande commande à modifier
     * @return le succès de l'opération
     */
    public int modifierCommande(Commande commande) {
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.modifier(commande);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        actualiserListeCommandes();
        return retour;
    }

    /**
     * Supprime la commande passée en paramètre dans la base de données
     * @param commande commande à supprimer
     * @return le succès de l'opération
     */
    public int supprimerCommande(Commande commande) {
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.supprimer(commande);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        actualiserListeCommandes();
        return retour;
    }

    /**
     * Renvoie la liste des commandes de la table associée
     * @return liste des commandes
     */
    public List<Commande> recupererListeCommandes() {
        return listeCommandes;
    }

    /**
     * Permet d'actualiser la liste des articles, à appeler à chaque changement dans la table des articles
     */
    private void actualiserListeCommandes() {
       listeCommandes = commandeDAO.getListeCommandes();
    }
}

