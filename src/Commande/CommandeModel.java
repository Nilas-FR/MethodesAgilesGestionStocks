package Commande;

import article.Article;
import client.Client;

import java.util.List;

import principale.Model;

public class CommandeModel extends Model {

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
    public CommandeModel() {
        commandeDAO = new CommandeDAO();

        actualiserListe();
    }

    /**
     * Ajoute un commande dans la base de données
     * @param objet commande à ajouter à la base
     * @return le succès de l'opération
     */
    @Override
    public int ajouter(Object objet) {
        Commande commande = (Commande) objet;
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.ajouter(commande, true);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        actualiserListe();
        return retour;
    }

    /**
     * Modifie la commande passée en paramètre dans la base de données sans modifier la date
     * @param objet commande à modifier
     * @return le succès de l'opération
     */
    @Override
    public int modifier(Object objet) {
        return modifier(objet, false);
    }

    /**
     * Modifie la commande passée en paramètre dans la base de données
     * @param objet commande à modifier
     * @param changerDate indique si il faut modifier la date de la commande ou non
     * @return le succès de l'opération
     */
    public int modifier(Object objet, boolean changerDate) {
        Commande commande = (Commande) objet;
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.modifier(commande, changerDate);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        actualiserListe();
        return retour;
    }

    /**
     * Supprime la commande passée en paramètre dans la base de données
     * @param objet commande à supprimer
     * @return le succès de l'opération
     */
    @Override
    public int supprimer(Object objet) {
        Commande commande = (Commande) objet;
        //on demande à la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.supprimer(commande);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        actualiserListe();
        return retour;
    }

    @Override
    public List chercher(String pattern) {
        return null;
        // non implémentée pour les commandes
    }

    /**
     * Renvoie la liste des commandes de la table associée
     * @return liste des commandes
     */
    @Override
    public List<Commande> recupererListe() {
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
    @Override
    public void actualiserListe() {
       listeCommandes = commandeDAO.getListeCommandes();
    }
}

