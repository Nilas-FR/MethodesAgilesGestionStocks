package client;

import principale.Model;

import java.util.List;

public class ClientModel extends Model {

    /**
     * Gestionnaire des clients de la base de données
     */
    private final ClientDAO clientDAO;

    /**
     * ClientDAO de gestion des clients
     */
    public ClientModel() {
        clientDAO = new ClientDAO();
    }

    /**
     * Ajoute un client dans la base de données
     * @param objet client à ajouter à la base
     * @return le succès de l'opération
     */
    @Override
    public int ajouter(Object objet) {
        Client client = (Client)objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.ajouter(client);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        return retour;
    }

    /**
     * Modifie le client passé en paramètre dans la base de données
     * @param objet client à modifier
     * @return le succès de l'opération
     */
    @Override
    public int modifier(Object objet) {
        Client client = (Client)objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.modifier(client);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        return retour;
    }

    /**
     * Supprime le client passé en paramètre dans la base de données
     * @param objet client à supprimer
     * @return le succès de l'opération
     */
    @Override
    public int supprimer(Object objet) {
        Client client = (Client)objet;
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.supprimer(client);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        return retour;
    }

    /**
     * Recherche les clients dont le nom contient le paramètre
     * @param nom texte contenu dans les articles à chercher
     * @return les clients correspondants
     */
    @Override
    public List<Client> chercher(String nom) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        List<Client> listeClients = clientDAO.rechercherClients(nom);
        // affichage du nombre de lignes récupérées dans la bdd pour vérification
        System.out.println(listeClients.size() + " ligne(s) récupérée(s)");

        return listeClients;
    }

    /**
     * Renvoie la liste des clients de la table associée
     * @return liste des clients
     */
    @Override
    public List<Client> recupererListe() {
        return clientDAO.getListeClients();
    }
}

