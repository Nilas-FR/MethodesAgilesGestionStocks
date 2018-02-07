package commande;

import article.Article;
import client.Client;

import java.util.List;

import principale.Model;

public class CommandeModel extends Model {

    /**
     * Gestionnaire des commandes de la base de donn�es
     */
    private final CommandeDAO commandeDAO;

    /**
     * CommandeDAO de gestion des commandes
     */
    public CommandeModel() {
        commandeDAO = new CommandeDAO();
    }

    /**
     * Ajoute un commande dans la base de donn�es
     * @param objet commande � ajouter � la base
     * @return le succ�s de l'op�ration
     */
    @Override
    public int ajouter(Object objet) {
        Commande commande = (Commande) objet;
        //on demande � la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.ajouter(commande, true);
        // affichage du nombre de lignes ajout�es dans la bdd pour v�rification
        System.out.println(retour + " ligne ajout�e");

        return retour;
    }

    /**
     * Modifie la commande pass�e en param�tre dans la base de donn�es sans modifier la date
     * @param objet commande � modifier
     * @return le succ�s de l'op�ration
     */
    @Override
    public int modifier(Object objet) {
        return modifier(objet, false);
    }

    /**
     * Modifie la commande pass�e en param�tre dans la base de donn�es
     * @param objet commande � modifier
     * @param changerDate indique si il faut modifier la date de la commande ou non
     * @return le succ�s de l'op�ration
     */
    public int modifier(Object objet, boolean changerDate) {
        Commande commande = (Commande) objet;
        //on demande � la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.modifier(commande, changerDate);
        // affichage du nombre de lignes modifi�es dans la bdd pour v�rification
        System.out.println(retour + " ligne modifi�e");

        return retour;
    }

    /**
     * Supprime la commande pass�e en param�tre dans la base de donn�es
     * @param objet commande � supprimer
     * @return le succ�s de l'op�ration
     */
    @Override
    public int supprimer(Object objet) {
        Commande commande = (Commande) objet;
        //on demande � la classe de communication d'envoyer l'commande dans la table commande
        int retour = commandeDAO.supprimer(commande);
        // affichage du nombre de lignes supprim�es dans la bdd pour v�rification
        System.out.println(retour + " ligne supprim�e");

        return retour;
    }

    @Override
    public List chercher(String pattern) {
        return null;
        // non impl�ment�e pour les commandes
    }

    /**
     * Renvoie la liste des commandes de la table associ�e
     * @return liste des commandes
     */
    @Override
    public List<Commande> recupererListe() {
        return commandeDAO.getListeCommandes();
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
}

