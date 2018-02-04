package Commande;

import Article.Article;
import Model.Model;
import Client.Client;

import java.util.List;

public class ModelCommande extends Model {

    /**
     * Gestionnaire des commandes de la base de données
     */
    private final CommandeDAO commandeDAO;

    /**
     * Liste des commandes
     */
    private List<Commande> listeCommandes;

    /**
     * CommandeDAO de gestion des commandes
     */
    public ModelCommande() {
        commandeDAO = new CommandeDAO();

        actualiserListeCommandes();
    }

    /**
     * Ajoute un commande dans la base de données
     * @param commande commande à ajouter à la base
     * @return le succès de l'opération
     */
    public int ajouterCommande(Commande commande) {
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.ajouter(commande, true);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        actualiserListeCommandes();
        return retour;
    }

    /**
     * Modifie la commande passée en paramètre dans la base de données
     * @param commande commande à modifier
     * @param changerDate indique si il faut modifier la date de la commande ou non
     * @return le succès de l'opération
     */
    public int modifierCommande(Commande commande, boolean changerDate) {
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.modifier(commande, changerDate);
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
     * Renvoie tous les clients de la base
     * @return tableau des clients
     */
    public Client[] recupererListeClients() {
        return commandeDAO.getListeClients();
    }

    /**
     * Renvoie tous les articles de la base
     * @return tableau des articles
     */
    public Article[] recupererListeArticles() {
        return commandeDAO.getListeArticles();
    }

    /**
     * Permet d'actualiser la liste des commandes, à appeler à chaque changement dans la table des commandes
     */
    public void actualiserListeCommandes() {
       listeCommandes = commandeDAO.getListeCommandes();
    }
}

